package typeinput;

import java.util.LinkedList;
import java.util.List;

public class Passwd {
    public final List<KeyStroke> keyStrokes = new LinkedList<KeyStroke>();
    private final StringBuilder passwd = new StringBuilder();
    private final Chronometer chronometer = new Chronometer();
    
    private int upIterator = 0;
        
    public void keyDown(char key){
        KeyStroke keyStroke = new KeyStroke();
        keyStroke.down = chronometer.getDeltaTime();
        keyStroke.key  = key;
        keyStrokes.add(keyStroke);
        passwd.append(key);
    }
    
    public void keyUp(){
        if (upIterator>=keyStrokes.size()) throw new IllegalStateException("You must press key at first");
        KeyStroke keyStroke = keyStrokes.get(upIterator++);
        keyStroke.up = chronometer.getDeltaTime();
    }
    
    public String getPassword(){
        return passwd.toString();
    }
    
    @Override
    public String toString(){
       StringBuilder string = new StringBuilder();
       string.append(passwd).append("\n");
       for (KeyStroke keyStroke : keyStrokes){
           string.append(keyStroke).append("\n");
       }
       return string.toString();
    }

}
/**
 * Do not use constructor!
 */
class KeyStroke{
    long down = 0;
    long up = 0;
    char key = '\0';

    @Override
    public String toString() {
        return key + " " + down + " " + up;
    }
}