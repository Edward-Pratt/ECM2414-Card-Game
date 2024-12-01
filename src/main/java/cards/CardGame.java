package cards;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class CardGame{
    private static final ArrayList<Player> players = new ArrayList<>();
    private static Queue<Card> PackofCards;
    private static volatile boolean gameWon = false;

    public static boolean isGameWon() {
        return gameWon;
    }
    public static void setGameWon(boolean won) {
        gameWon = won;
    }

    public static void main(String[] args) {
        try{
            Scanner commandReader = new Scanner(System.in);
            System.out.println("Welcome to the card game! Please enter the number of players: ");
            int numPlayers = commandReader.nextInt();
            System.out.println("Please enter the directory of your pack file: ");
            String packFile = commandReader.next();
            System.out.println(("You are playing with "+ numPlayers+ " players and the pack file"+ packFile));
            PackofCards = Pack.readPack(packFile, numPlayers);
            setupGame(numPlayers);
            playGame();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void setupGame(int numPlayers){
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
