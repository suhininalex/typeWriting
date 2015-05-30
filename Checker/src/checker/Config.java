package checker;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
    
    private final Properties config = new Properties();
    
    public Config(String filename){
        try (FileInputStream fileStream  = new FileInputStream(filename)){
            config.load(fileStream);
        } catch (Exception ex){
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getMinNeighbors(){
        return Integer.parseInt(config.getProperty("minNeighbors", "5"));
    }
    
    public double getMaxDistance(){
        return Double.parseDouble(config.getProperty("maxDistance", "25"));
    }
    
    public boolean isBatchMode(){
        return "true".equals(config.getProperty("batchMode", "false"));
    }
    
    public boolean rewriteLog(){
        return "true".equals(config.getProperty("rewriteLog", "true"));
    }
    
    public boolean viewDistance(){
        return "true".equals(config.getProperty("viewDistance", "false"));
    }
}
