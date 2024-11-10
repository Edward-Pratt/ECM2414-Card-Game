package cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class testCard {

    int testCardNumber;
    Card card;

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
