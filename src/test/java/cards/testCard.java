package cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The test class for the Card class. This class tests all the methods within
 * the Card class.
 * This class confirms if a card can be correctly generated or not.
 */
public class testCard {

    int testCardNumber;
    Card card;

    /**
     * Custom setup for the JUnit Jupiter test.
     * This method generates the number for the card as well as
     * creating the card itself
     */
    @BeforeEach
    @DisplayName("Card Generation")
    void generateCard(){
        this.testCardNumber = (int) Math.round(Math.random() * 100);
        this.card = new Card(this.testCardNumber);
    }

    @Test
    @DisplayName("Card Number")
    void testCardNumber(){
        Assertions.assertEquals(this.card.getValue(), testCardNumber);
    }
}
