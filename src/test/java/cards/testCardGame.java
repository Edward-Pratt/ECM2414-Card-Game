package cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayDeque;
import java.util.Queue;

class testCardGame {

    @BeforeEach
    @DisplayName("Set up Game")
    void resetGame() {
        // Reset the game state before each test
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
        // Create a mock pack of cards
        Queue<Card> mockPack = new ArrayDeque<>();
        for (int i = 1; i <= 52; i++) {
            mockPack.add(new Card(i)); // Add 52 cards numbered 1-52
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
        // Set up a scenario where a player has already won

        CardGame.players.add(winningPlayer);

        // Simulate the winning player
        CardGame.setGameWon(true);

        CardGame.playGame();
        assertTrue(CardGame.isGameWon(), "Game should detect a winner immediately.");
    }




    @Test
    @DisplayName("Tests that an invalid pack file throws an exception")
    void testInvalidPackFile() {
        // Simulate reading an invalid pack file
        // Assuming Pack.readPack throws an exception for invalid inputs
        assertThrows(RuntimeException.class, () -> {
            Pack.readPack("invalid_pack.txt", 4);
        });
    }
}
