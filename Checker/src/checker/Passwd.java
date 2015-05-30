package checker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Passwd {
    private final List<Integer> retentions;
    private final List<Integer> shifts;
    private final String text;
    
    private Passwd(String passwd){
        this.text = passwd;
        shifts = new ArrayList<>(text.length());
        retentions = new ArrayList<>(text.length());
    }
    
    public static Passwd readPasswd(Scanner scanner){
        try {
        Passwd passwd = new Passwd(scanner.nextLine());
        for (int i=0;i<passwd.text.length();i++) {
            scanner.next();
            passwd.shifts.add(scanner.nextInt());
            passwd.retentions.add(scanner.nextInt());
        }
        return passwd;
        } catch (Exception e){
            throw new RuntimeException("Can't read passwd from stream!", e);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i=1;i<shifts.size();i++)
            builder.append(shifts.get(i)).append("\t");
        for (int i=0;i<retentions.size();i++)
            builder.append(retentions.get(i)).append("\t");
        return builder.toString();
    }
    
    public double distance(Passwd passwd){
        if (!text.equals(passwd.text)) return Long.MAX_VALUE;
        double distance = 0d;
        for (int i=1;i<shifts.size();i++){
            double delta = (shifts.get(i)-passwd.shifts.get(i))*100/shifts.get(i);
            distance+=delta*delta;
        }
        for (int i=0;i<retentions.size();i++){
            double delta = (retentions.get(i)-passwd.retentions.get(i))/retentions.get(i);
            distance+=delta*delta;
        }
        distance = Math.sqrt(distance);
        distance/=Math.sqrt(shifts.size()+retentions.size());
        return distance;
    }
}
