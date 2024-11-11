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
    @DisplayName("SET Card and GET ALL cards")
    void setTestCard(){
        CardDeck cardDeck = new CardDeck(2);
        Card[] expectedDeck = new Card[4];
        Card testCard;
        for(int i = 0; i < 4; i++){
            testCard = new Card((int) Math.round(Math.random()*100));
            expectedDeck[i] = testCard;
            cardDeck.addCard(testCard);
        }
        Card[] actualDeck = cardDeck.getAllCards().toArray(new Card[0]);
        Assertions.assertArrayEquals(expectedDeck, actualDeck);
    }
}
