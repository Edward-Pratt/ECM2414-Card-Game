package cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The test class for the Player class. This class tests all the methods
 * within the Player class.
 * This class checks if player can be assigned an identifier and add/remove
 * cards from their deck. It also checks if the player class can do make
 * a valid turn by taking a card from their left and placing a card on their
 * right.
 * 
 * @author Edward Pratt & Sandy Hay
 * @version 1.0
 */
public class testPlayer {

    Player player;
    int playerNumber;
    CardDeck testLeftDeck;
    CardDeck testRightDeck;
    Card[] cards;

    /**
     * Custom setup for the JUnit Jupiter tests.
     * This method sets up the test player's number as well as creating
     * their supposed "left deck" and "right deck"
     */
    @BeforeEach
    @DisplayName("Generating Player")
    void generatePlayer(){
        this.playerNumber = (int) (Math.round(Math.random() * 100));
        this.testLeftDeck = new CardDeck(1);
        this.testRightDeck = new CardDeck(2);
    }

    @Test
    @DisplayName("Player Number")
    void testPlayerNumber() {
        int testPlayerNumber = (int) (Math.round(Math.random() * 100));
        this.player = new Player(testPlayerNumber, this.testLeftDeck, this.testRightDeck);
        Assertions.assertEquals(player.getPlayerNumber(), testPlayerNumber);
    }

    @Test
    @DisplayName("Adding a card")
    void testPlayerCard() {
        Card testCard = new Card((int) Math.round(Math.random()*100));
        this.player = new Player(this.playerNumber, this.testLeftDeck, this.testRightDeck);
        this.player.addCard(this.player.getAllCards().length - 1, testCard);
        Assertions.assertEquals(this.player.getCard(player.getAllCards().length - 1), testCard);
    }

    @Test
    @DisplayName("Retrieve all player cards")
    void multiplePlayerCards() {
        this.player = new Player(this.playerNumber, this.testLeftDeck, this.testRightDeck);
        Card[] playerDeck = new Card[4];
        for (int i = 0; i < 4; i++) {
            Card testCard = new Card((int) Math.round(Math.random()*100));
            this.player.addCard(i, testCard);
            playerDeck[i] = testCard;
        }
        Assertions.assertArrayEquals(this.player.getAllCards(), playerDeck);
    }
    
    @Test
    @DisplayName("Player wins")
    void playerWinsTest() {
        Player testPlayer1 = new Player(1, this.testLeftDeck, this.testRightDeck);
        Player testPlayer2 = new Player(2, this.testLeftDeck, this.testRightDeck);
        Player testPlayer3 = new Player(3, this.testLeftDeck, this.testRightDeck);
        Card testCard;
        for (int i = 0; i < 4; i++) {
            testCard = new Card(1);
            testPlayer1.addCard(i, testCard);

            testCard = new Card(5);
            testPlayer2.addCard(i, testCard);

            testCard = new Card((int) Math.round(Math.random()*100) * i);
            testPlayer3.addCard(i, testCard);
        }
        Assertions.assertEquals(true, testPlayer1.hasWon());
        Assertions.assertEquals(true, testPlayer2.hasWon());
        Assertions.assertEquals(false, testPlayer3.hasWon());
    }

    @Test
    @DisplayName("Players Turn")
    void playerTakesTurn() {
        CardDeck tempLeftDeck = new CardDeck(playerNumber);
        CardDeck tempRightDeck = new CardDeck(2);
        for (int i = 0; i < 10; i++) {
            Card card1 = new Card((int) Math.round(Math.random()*100));
            Card card2 = new Card((int) Math.round(Math.random()*100));
            tempLeftDeck.addCard(card1);
            tempRightDeck.addCard(card2);
        }
        this.player = new Player(this.playerNumber, tempLeftDeck, tempRightDeck);
        this.player.takeTurn();
        Assertions.assertEquals(4, this.player.getAllCards().length);
    }
}
