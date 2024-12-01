package cards;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The FileEditor class. This class handles all the main components and
 * function for creating files, writing to files and reading from files.
 * The functions in this class are all general classes where multiple classes,
 * for example the Player or the CardDeck will use this class to read or write
 * to their designated files.
 * 
 * @author Edward Pratt & Sandy Hay
 * @version 1.0
 */
public class FileEditor {
    /**
     * The createFile class. Used for creating the desired file for the
     * card game. This class is a general class used for both, Player and Card
     * <p>
     * This method pops up an error message if the file already exists or an error 
     * occurs when creating the file.
     * </p>
     * 
     * @param fileName the name of the file
     * @throws IOException if an I/O occurs during the creation of a file
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
     * The writeFile class. This allows the card game to write to any file
     * of its choosing, with the desired content that the game wants to
     * write with.
     * <p>
     * This method attempts to overwrite a file with the specified
     * content. If the file cannot be found or an I/O error occurs, an error
     * message will be printed to the console.
     * </p>
     * 
     * @param fileName    the name of the file to write to
     * @param fileContent the content to be written to the file
     * @throws FileNotFoundException if the specified file cannot be found
     * @throws IOException           if an I/O error occurs during writing
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
     * The readFile class. This allows the card game to read from the desired 
     * file and retrieves all the contents of the file, line-by-line. T
     * <p>
     * This method attempts to read from the file and retrieve all its contents
     * for the desired class. If the desired file does not exists or an I/O error
     * occurs, an error message will be printed to the console.
     * </p>
     * 
     * @param fileName the name of the file
     * @throws FileNotFoundException if the specified file cannot be found
     * @throws IOException if an I/O error occurs during reading
     * @return all the data retrieved from the file
     */
    public String[] readFile(String fileName){
        try{
            File file = new File(fileName); //are we using file??
            BufferedReader reader = new BufferedReader(new FileReader(file));
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
