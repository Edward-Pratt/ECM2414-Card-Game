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
    @DisplayName("")
    void testIsGameWonInitial() {
        assertFalse(CardGame.isGameWon(), "Game should not be won initially.");
    }

    @Test
    @DisplayName("")
    void testSetGameWon() {
        CardGame.setGameWon(true);
        assertTrue(CardGame.isGameWon(), "Game should be marked as won after setting it.");
    }

    @Test
    @DisplayName("")
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
            assertEquals(4, player.getHandSize(), "Each player should have 4 cards initially.");
        }
    }

    @Test
    @DisplayName("")
    void testPlayGameWinningCondition() {
        // Setup a scenario where a player has already won
        Player winningPlayer = new Player(1, null, null);
        CardGame.players.add(winningPlayer);

        // Simulate the winning player
        winningPlayer.setWon(true);

        CardGame.playGame();
        assertTrue(CardGame.isGameWon(), "Game should detect a winner immediately.");
    }

    @Test
    @DisplayName("")
    void testPlayGameNoWinners() {
        // Setup a game with no winners
        int numPlayers = 3;
        for (int i = 1; i <= numPlayers; i++) {
            CardGame.players.add(new Player(i, null, null));
        }

        CardGame.playGame();
        assertFalse(CardGame.isGameWon(), "Game should not be marked as won if no players have won.");
    }

    @Test
    @DisplayName("")
    void testInvalidPlayerCount() {
        // Simulate invalid player count in the main loop
        // Mock scanner input or adjust for invalid inputs
        // Note: This test would ideally require decoupling `main` for better testability
        assertThrows(RuntimeException.class, () -> {
            CardGame.main(new String[]{"-1"}); // Simulate invalid input
        });
    }

    @Test
    @DisplayName("")
    void testInvalidPackFile() {
        // Simulate reading an invalid pack file
        // Assuming Pack.readPack throws an exception for invalid inputs
        assertThrows(RuntimeException.class, () -> {
            Pack.readPack("invalid_pack.txt", 4);
        });
    }
}
