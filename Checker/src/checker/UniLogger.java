package checker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public abstract class UniLogger {
    
    public abstract void log(String string);
    
    private static class FileLogger extends UniLogger{
        final String filename;
        public FileLogger(String filename, boolean rewrite) {
            this.filename = filename;
            if (rewrite) new File(filename).delete();
        }

        @Override
        public void log(String string) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(filename,true))) {
                pw.println(string);
            } catch (IOException ex) {
                throw new RuntimeException("Can't open log file",ex);
            }
        }
    }
    
    private static class StreamLogger extends UniLogger{
        final PrintStream stream;
        public StreamLogger(PrintStream stream) {
            this.stream = stream;
        }
        @Override
        public void log(String string) {
            stream.println(string);
    }
    }
    
    public static UniLogger getFileLogger(String filename, boolean rewrite){
            return new FileLogger(filename, rewrite);
    }
    
    public static UniLogger getLogger(PrintStream stream){
        return new StreamLogger(stream);
    }

}