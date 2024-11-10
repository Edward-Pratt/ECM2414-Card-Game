package cards;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class testCardDeck {
    
    // CardDeck cardDeck;
    // int testdeckNumber;
    // Queue<Card> testCardDeck;

    // @BeforeEach
    // @Display("Generate Deck")
    // void generateDeck{
    //     testdeckNumber = (int) Math.round(Math.random() * 100);
    //     cardDeck = new CardDeck(testdeckNumber)
    //     testCardDeck = new LinkedList<>();

    // }

    @Test
    @DisplayName("Deck Number")
    void getDeckNumber(){
        int testdeckNumber = (int) Math.round(Math.random() * 100);
        CardDeck cardDeck = new CardDeck(testdeckNumber);
        Assertions.assertEquals(cardDeck.getDeckNumber(), testdeckNumber);
    }

    @Test
    @DisplayName("GET Card")
    void getTestCard(){

    }

    @Test
    @Display("SET Card")
    void setTestCard(){
        CardDeck cardDeck = new CardDeck(1);
        Card[] testDeck = new Cards[4];
        Card testCard;
        for(int i = 0; i < 5; i++){
            testCard = new Card((int) Math.round(Math.random()*100));
            testDeck[i] = testCard;
            cardDeck.addCard(testCard);
        }
    }
}
