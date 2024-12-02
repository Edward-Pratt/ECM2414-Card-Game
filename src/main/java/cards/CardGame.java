package cards;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;


/**
 * The CardGame class. This class is the main class for the card game. It handles a setup
 * of the game for a number of players, and then initialises the threads of players.
 *
 * @author Edward Pratt & Sandy Hay
 * @version 1.0
 */
public class CardGame{
    private static final ArrayList<Player> players = new ArrayList<>();
    private static Queue<Card> PackofCards;
    private static volatile boolean gameWon = false;


    /**
     * The isGameWon method. This method checks if the game has been won.
     *
     * @return boolean statement, TRUE if the game has been won, otherwise, returns FALSE
     */
    public static boolean isGameWon() {
        return gameWon;
    }

    /**
     * The setGameWon method. This method sets the game as won.
     *
     * @param won the boolean statement to set the game as won
     */
    public static void setGameWon(boolean won) {
        gameWon = won;
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        int numPlayers;
        String packFile;
        while (true) {
            try {
                Scanner commandReader = new Scanner(System.in);
                System.out.println("Welcome to the card game! Please enter the number of players: ");
                numPlayers = commandReader.nextInt();
                System.out.println("Please enter the directory of your pack file: ");
                packFile = commandReader.next();

                PackofCards = Pack.readPack(packFile, numPlayers);

            } catch (RuntimeException e) {
                {
                    System.out.println("Invalid Number of Players or Deck. Please try again.");
                    continue;
                }
            }
            break;

        }
        System.out.println(("You are playing with " + numPlayers + " players and the pack file" + packFile));
        setupGame(numPlayers);
        playGame();
    }

    /**
     * 
     * @param numPlayers
     */
    private static void setupGame(int numPlayers){
        CardDeck[] decks = new CardDeck[numPlayers];
        for (int i = 0; i < numPlayers; i++){
            decks[i] = new CardDeck(i);
        }
        for (int i = 0; i < numPlayers; i++){
            CardDeck leftDeck = decks[((i-1) + numPlayers) % numPlayers];
            CardDeck rightDeck = decks[(i+1) % numPlayers];
            players.add(new Player(i+1, leftDeck, rightDeck));
        }

        for(int i=0; i<4; i++) {
            for (int j = 0; j < numPlayers; j++) {
                players.get(j).addCard(i, PackofCards.remove());

            }
        }
        while(!PackofCards.isEmpty()){
            for(int k=0; k<numPlayers; k++) {
                decks[k].addCard(PackofCards.remove());
            }
        }
    }

    /**
     * 
     */
    private static void playGame() {
        for (Player player: players){
            if(player.hasWon()){
                System.out.println("Player " + player.getPlayerNumber() + " has won the game!");
                gameWon = true;
            }
        }
        if(!gameWon){
            for (Player player: players){
                player.start();
            }
        }
    }
}
