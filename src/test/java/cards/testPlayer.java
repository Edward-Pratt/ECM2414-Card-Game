package cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class testPlayer {

    Player player;
    int playerNumber;
    CardDeck testLeftDeck;
    CardDeck testRightDeck;
    Card[] cards;

    @BeforeEach
    @DisplayName("Generating Player")
    void generatePlayer(){
        this.playerNumber = (int) (Math.round(Math.random() * 100));
        this.testLeftDeck = 
    }

    @Test
    @DisplayName("Player Number ")
    void testPlayerNumber() {
        
    }
    
}
