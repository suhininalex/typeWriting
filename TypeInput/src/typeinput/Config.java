package typeinput;

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
    
    public String getFilePath(){
        return config.getProperty("passwordFile", "passwd.bio");
    }
    
    public String getProgramToRun(){
        return config.getProperty("runProgram", "java -jar checker.jar");
    }
    
    public boolean isSaveOnly(){
        return "true".equals(config.getProperty("saveOnly", "true"));
    }
    
    public boolean isAppendMode(){
        return "true".equals(config.getProperty("appendMode", "false"));
    }
}
