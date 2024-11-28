package cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        this.testLeftDeck = new CardDeck(1);
        this.testRightDeck = new CardDeck(2);
    }

    @Test
    @DisplayName("Player Number ")
    void testPlayerNumber() {
        int testPlayerNumber = (int) (Math.round(Math.random() * 100));
        this.player = new Player(testPlayerNumber, this.testLeftDeck, this.testRightDeck);
        Assertions.assertEquals(player.getPlayerNumber(), testPlayerNumber);
    }

    @Test
    @DisplayName("Adding a card")
    void testPlayerCard() {
        Card testCard = new Card((int) Math.round(Math.random()*100));
        this.player.addCard(this.playerNumber, testCard);
        Assertions.assertEquals(this.player.getCard(player.getAllCards().length - 1), testCard);
    }

    @Test
    @DisplayName("Retrieve all player cards")
    void multiplePlayerCards() {
        this.player = new Player(this.playerNumber, this.testLeftDeck, this.testRightDeck);
        Card[] playerDeck = new Card[4];
        for (int i = 0; i < 5; i++) {
            Card testCard = new Card((int) Math.round(Math.random()*100));
            this.player.addCard(i, testCard);
        }
        Assertions.assertArrayEquals(this.player.getAllCards(), playerDeck);
    }

    @Test
    @DisplayName()
    void
}
