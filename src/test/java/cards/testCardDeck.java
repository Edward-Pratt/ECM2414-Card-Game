package cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The test class for the CardDeck class. This class tests all the methods within
 * the CardDeck class.
 * This class checks if the deck can be set an identifier, add cards to the deck
 * and retrieve all the cards within the deck.
 * 
 * @author Edward Pratt & Sandy hay
 * @version 1.0
 */
public class testCardDeck {

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
