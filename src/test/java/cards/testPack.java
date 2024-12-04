package cards;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The test class for the Pack class. This class tests all the methods within
 * the Pack class.
 * The class aims to confirm that the abstract Pack class can read from the Pack
 * file and be able to validate whether a class has the suitable amount of cards
 * of 8n.
 * 
 * @author Edward Pratt & Sandy Hay
 * @version 1.0
 */
public class testPack {
    
    File packFileName;
    Queue<Card> cardsPack;

    /**
     * Custom setup for JUnit Jupiter test
     * This method creates a file for the test pack and adds random cards 
     * to the test pack, allow Pack class to read off the contents 
     * from the file
     */
    @BeforeEach
    @DisplayName("Generate Pack File")
    void generatePack(){
        FileEditor fe = new FileEditor();
        fe.createFile("testPack.txt");
        this.packFileName = new File("testPack.txt");
        int cardValue;
        this.cardsPack = new LinkedList<>();
        for (int i = 0; i < 24; i++) {
            cardValue = (int) Math.round(Math.random()*100);
            fe.writeFile(packFileName.getName(), String.valueOf(cardValue));
            cardsPack.add(new Card(cardValue));
        }
    }
    
    @Test
    @DisplayName("Reading Pack File")
    void readTestPack() {
        Queue<Card> fileCards = Pack.readPack(packFileName.getName(), 3);
        ArrayList<Integer> testPackContents = new ArrayList<>();
        ArrayList<Integer> filePackContents = new ArrayList<>();
        Queue<Card> testPack = new LinkedList<>(cardsPack);
        for (int i=0; i<24; i++) {
            testPackContents.add(testPack.remove().getValue());
            filePackContents.add(fileCards.remove().getValue());
        }
        Assertions.assertArrayEquals(testPackContents.toArray(), filePackContents.toArray(), "The array from the pack file should equal to the original array of cards.");
    }

    @Test
    @DisplayName("Validate Pack Size")
    void validateTestPack() {
        Queue<Card> testPack1 = new LinkedList<>();
        Queue<Card> testPack2 = new LinkedList<>();
        Card testCard;
         for (int i = 0; i < 8; i++) {
             testCard = new Card((int) Math.round(Math.random()*100));
             testPack1.add(testCard);
         }
         for (int j = 0; j < 4; j++) {
             testCard = new Card((int) Math.round(Math.random()*100));
             testPack2.add(testCard);
         }

        Assertions.assertEquals(true, Pack.validatePack(1, testPack1), "The size of this pack is a multiple of 8.");
        Assertions.assertEquals(false, Pack.validatePack(2, testPack2), "The size of this pack is not a multiple of 8.");
    }
}
