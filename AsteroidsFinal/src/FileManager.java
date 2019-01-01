
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileManager {
    public FileManager(){}
    
    public void removeLine(String lineToRemove, String fileName){
        try {
            String file = fileName;
            File inFile = new File(file);
      
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }
       
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader buffReader = new BufferedReader(new FileReader(file));
            PrintWriter printWriter = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            while ((line = buffReader.readLine()) != null) {

                if (!line.trim().equals(lineToRemove)) {
                  printWriter.println(line);
                  printWriter.flush();
                }
            }
            printWriter.close();
            buffReader.close();

            if (!inFile.delete()) {
              System.out.println("Could not delete file");
              return;
            } 

            if (!tempFile.renameTo(inFile))
              System.out.println("Could not rename file");
        }
        catch (FileNotFoundException ex) {
          ex.printStackTrace();
        }
        catch (IOException ex) {
          ex.printStackTrace();
        }
    }
    
        public void clearFile(String fileName){
        try {
            String file = fileName;
            File inFile = new File(file);
      
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }
       
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader buffReader = new BufferedReader(new FileReader(file));
            PrintWriter printWriter = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            while ((line = buffReader.readLine()) != null) {
                  printWriter.println(line);
                  printWriter.flush();
            }
            printWriter.close();
            buffReader.close();

            if (!inFile.delete()) {
              System.out.println("Could not delete file");
              return;
            } 

            if (!tempFile.renameTo(inFile))
              System.out.println("Could not rename file");
        }
        catch (FileNotFoundException ex) {
          ex.printStackTrace();
        }
        catch (IOException ex) {
          ex.printStackTrace();
        }
    }
        
    public int countLines(String fileName){
        String aLine;
        int numOfLines = 0;
        try{
            FileReader fr2 = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr2);
            aLine = bf.readLine();
            while(aLine != null){ 
                numOfLines++;
                aLine = bf.readLine();
            }
            bf.close();
        }
        catch(IOException e){
            System.out.printf(e.getMessage());
        }
        return numOfLines;
    }    
}
