package typeinput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

    public class FileController implements AutoCloseable{

        private final PrintWriter out;
        
        public FileController(String filePath, boolean appendMode) throws IOException {
            out = new PrintWriter(new FileWriter(filePath,appendMode));
        }
        
        public void writeRecord(Passwd passwd){
            out.print(passwd);
        }

        @Override
        public void close() {
            out.close();
        }
    }
