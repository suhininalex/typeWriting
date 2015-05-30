package checker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class UserData {
    private final List<Passwd> samples = new LinkedList<>();
    private final UniLogger logger;
    
    private UserData(Scanner scanner, UniLogger logger){
        this.logger = logger;
        while (scanner.hasNextLine()) {
            samples.add(Passwd.readPasswd(scanner));
            scanner.nextLine();
        }
    }
    
    public static UserData loadUserData(String filename, UniLogger logger) throws FileNotFoundException{
        return new UserData(new Scanner(new File(filename)), logger);
    }
    
    public List<Double> getDistances(Passwd passwd){
        List<Double> distances = new LinkedList<>();
        for (Passwd sample : samples) {
            distances.add(passwd.distance(sample));
        }
        return distances;
    }
    
    public int getCountInRange(Passwd passwd, double maxDistance){
        int count = 0;
        for (double distance : getDistances(passwd)){
            if (Checker.config.viewDistance()) logger.log("Distance: " + distance);
            if (distance<maxDistance) count++;
        }
        return count;
    }
    
    public String getAsTable(){
        StringBuilder table = new StringBuilder();
        for (Passwd passwd : samples) {
            table.append(passwd).append("\r\n");
        }
        return table.toString();
    } 
   
    /**
     * Tris to classificate each passwd entity.
     * If this is impossible removes the entity from collection.
     */
    public void filterBadExamples(double maxDistance, int minNeighbours){
        logger.log("Filtering samples...");
        Iterator<Passwd> it = samples.iterator();
        while(it.hasNext()) {
            Passwd passwd = it.next();
            if (getCountInRange(passwd, maxDistance)<minNeighbours) {
                it.remove();
                logger.log("Filtered!");
            } else {
                logger.log("OK!");
            }
        }
        logger.log("----------------");
    }

    @Override
    public String toString() {
        return samples.toString();
    }
    
}
