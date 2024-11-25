package cards;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * The FileEditor class. This class handles all the main components and
 * function for creating files, writing to files and reading from files.
 * 
 * @author Edward Pratt & Sandy Hay
 * @version 1.0
 */
public class FileEditor {
    /**
     * 
     * @param fileName
     */
    public void createFile(String fileName){
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param fileName
     * @param fileContent
     */
    public void writeFile(String fileName, String fileContent){
        try{
            File file = new File(fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContent);
            writer.newLine();
            writer.close();
        } catch (FileNotFoundException e){
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("An error occured.");
        }
    }

    /**
     * 
     * @param fileName
     * @return
     */
    public String[] readFile(String fileName){
        try{
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            ArrayList<String> lines = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            return lines.toArray(String[]::new);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("An error occured.");
        }
        return null;
    }
}
