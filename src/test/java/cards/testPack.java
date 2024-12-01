package cards;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class testPack {
    
    File packFileName;
    Queue<Card> cardsPack;

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
        Queue<Card> testPack = new LinkedList<>();
        Queue<Card> fileCards = Pack.readPack(packFileName.getName(), 3);
        for (Card card : fileCards)
            testPack.add(card);
        Assertions.assertArrayEquals(testPack.toArray(), cardsPack.toArray());
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

        Assertions.assertEquals(true, Pack.validatePack(1, testPack1));
        Assertions.assertEquals(false, Pack.validatePack(2, testPack2));
    }
}
