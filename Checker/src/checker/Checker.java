package checker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Checker {

    static String batchname="batch_my.bio";
    static String checkfile="passwd.bio";
    
    public static void main(String[] args) {
        if (args.length>1) batchname = args[1];
        if (args.length>2) checkfile = args[2];
        try {
            UserData userData = UserData.loadUserData("batch_my.bio");
            Passwd passwd = Passwd.readPasswd(new Scanner(new File(checkfile)));
            if (userData.getCountInRange(passwd, 50, true)>5) System.exit(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        try (Scanner main = new Scanner(new File("batch_my.bio"))) {
//            while (main.hasNextLine()){
//                Passwd passwd = Passwd.readPasswd(main);
//                main.nextLine();
//                System.out.println(userData.getCountInRange(passwd, 50, true));
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
//        }
        System.exit(1);
    }
    
}
