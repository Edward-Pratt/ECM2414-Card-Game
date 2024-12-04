package cards;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The test class for the CardGame class. Tests if the methods
 * that run the main game work as intended.
 * This is to test if the main functionality of the game works and to
 * simulate how the game should play out.
 * 
 * @author Edward Pratt & Sandy Hay
 * @version 1.0
 */
class testCardGame {

    /**
     * Custom setup for the JUnit Jupiter test.
     * This method sets up a game, where it resets the game
     * so that no player has won and clears all the players
     * currently in the game.
     */
    @BeforeEach
    @DisplayName("Set up Game")
    void resetGame() {
        CardGame.setGameWon(false);
        CardGame.players.clear();
    }

    @Test
    @DisplayName("Checks that when the game starts it is not won instantly.")
    void testIsGameWonInitial() {
        assertFalse(CardGame.isGameWon(), "Game should not be won initially.");
    }

    @Test
    @DisplayName("Checks that the setGameWon method works.")
    void testSetGameWon() {
        CardGame.setGameWon(true);
        assertTrue(CardGame.isGameWon(), "Game should be marked as won after setting it.");
    }

    @Test
    @DisplayName("Checks that players are given 4 cards each when game is setup.")
    void testSetupGame() {
        Queue<Card> mockPack = new ArrayDeque<>();
        for (int i = 1; i <= 32; i++) {
            mockPack.add(new Card(i));
        }
        CardGame.PackofCards = mockPack;

        int numPlayers = 4;
        CardGame.setupGame(numPlayers);

        assertEquals(numPlayers, CardGame.players.size(), "Number of players should match setup.");
        for (Player player : CardGame.players) {
            assertEquals(4, player.getAllCards().length, "Each player should have 4 cards initially.");
        }
    }

    @Test
    @DisplayName("Check if game is won when a player has won.")
    void testPlayGameWinningCondition() {
        CardDeck lDeck = new CardDeck(1);
        CardDeck rDeck = new CardDeck(2);
        Player winningPlayer = new Player(1, lDeck, rDeck);
        for(int i = 0; i<4; i++){
            lDeck.addCard(new Card(i));
            rDeck.addCard(new Card(i));
            winningPlayer.addCard(i, new Card(i));
        }

        CardGame.players.add(winningPlayer);

        CardGame.setGameWon(true);

        CardGame.playGame();
        assertTrue(CardGame.isGameWon(), "Game should detect a winner immediately.");
    }

    @Test
    @DisplayName("Tests that an invalid pack file throws an exception")
    void testInvalidPackFile() {
        assertThrows(RuntimeException.class, () -> {
            Pack.readPack("invalid_pack.txt", 4);
        });
    }
}
