package cards;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class testFileEditor {

    @Test
    @DisplayName("Test File Creation")
    void testFileCreation() {
        FileEditor fe = new FileEditor();
        fe.createFile("testFile.txt");
        File f = new File("testFile.txt");
        Assertions.assertTrue(f.exists(), "File was not created");
    }
    @Test
    @DisplayName("Test Write and Read File")
    void testFileWriting() {
        FileEditor fe = new FileEditor();
        fe.createFile("testFile.txt");
        fe.writeFile("testFile.txt", "Test Data");
        Assertions.assertEquals(fe.readFile("testFile.txt")[0], "Test Data", "File was not written to or read from correctly");
    }
}
