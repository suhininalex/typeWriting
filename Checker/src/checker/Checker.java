package checker;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class Checker {

    static String batchname="batch_my_2.bio";
    static String checkfile="batch_my_2.bio";
    
    static UniLogger logger;
    public static Config config;
    
    public static void main(String[] args) {
        config = new Config("checker.properties");
        logger = UniLogger.getFileLogger("checker.log", config.rewriteLog());
        
        if (args.length>=1) batchname = args[0];
        if (args.length>=2) checkfile = args[1];
        
        logger.log(new Date() + "\tRunning. Batch: "+batchname+". Passwd: "+checkfile);
        
        try {
            UserData userData = UserData.loadUserData(batchname, logger);
//            userData.filterBadExamples(10, 5);
            System.out.println(userData.getAsTable());
            logger.log(userData.getAsTable());
            logger.log("--------------------");
            Scanner main = new Scanner(new File(checkfile));
            int exitCode;
            do {
                Passwd passwd = Passwd.readPasswd(main);
                int count = userData.getCountInRange(passwd, config.getMaxDistance());
                if (count>config.getMinNeighbors()){ 
                    logger.log("OK. CountInRange("+count+")");
                    exitCode = 0;
                }
                else {
                    logger.log("FAIL. CountInRange("+count+")");
                    exitCode = 2;
                }
                if (!config.isBatchMode()) System.exit(exitCode);
                main.nextLine();
            } while (config.isBatchMode() && main.hasNextLine());
            System.exit(exitCode);
        } catch (Exception ex) {
            logger.log("Error due checking." + ex.toString());
            ex.printStackTrace();
            System.exit(3);
        }
    }
    
}
