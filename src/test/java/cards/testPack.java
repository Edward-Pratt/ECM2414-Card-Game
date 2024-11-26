package cards;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class testPack {
    
    // @BeforeEach
    // @DisplayName("Generate Packs")
    // void generatePack(){

    // }
    
    @Test
    @DisplayName("Reading Pack")
    void readTestPack() {
        
    }

    @Test
    @DisplayName("Validate Pack Size")
    void validateTestPack() {
        Queue<Card> testPack1 = new LinkedList<>();
        Queue<Card> testPack2 = new LinkedList<>();
        Card testCard;
        for (int i = 0; i < 3; i++) {
            testCard = new Card((int) Math.round(Math.random()*100));
            testPack1.add(testCard);
        }
        for (int j = 0; j < 13; j++) {
            testCard = new Card((int) Math.round(Math.random()*100));
            testPack1.add(testCard);
            testPack2.add(testCard);
        }
        assert Pack.validatePack(2, testPack1);
        assert Pack.validatePack(2, testPack2);
    }
}
