package checker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class UserData {
    private final List<Passwd> samples = new LinkedList<>();
    
    private UserData(Scanner scanner){
        while (scanner.hasNextLine()) {
            samples.add(Passwd.readPasswd(scanner));
            scanner.nextLine();
        }
    }
    
    public static UserData loadUserData(String filename) throws FileNotFoundException{
        return new UserData(new Scanner(new File(filename)));
    }
    
    public List<Double> getDistances(Passwd passwd, boolean normalized){
        List<Double> distances = new LinkedList<>();
        for (Passwd sample : samples) {
            distances.add(passwd.distance(sample,normalized));
        }
        return distances;
    }
    
    public int getCountInRange(Passwd passwd, double maxDistance, boolean normalized){
        int count = 0;
        for (double distance : getDistances(passwd,normalized)){
            if (distance<maxDistance) count++;
        }
        return count;
    }

    @Override
    public String toString() {
        return samples.toString();
    }
    
}
