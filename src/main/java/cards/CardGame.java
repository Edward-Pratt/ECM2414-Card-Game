package cards;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class CardGame extends Pack{
    private static final ArrayList<Player> players = new ArrayList<>();
    private static Queue<Card> PackofCards;

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
            players.set(i, new Player(i));
        }
        for(int i=0; i<4; i++){
            for(int j=0; j<numPlayers; j++) {
                players.get(j).addCard(i, PackofCards.remove());

            };
        while(!PackofCards.isEmpty()){
            for(int k=0; k<numPlayers; k++) {
                decks[k].addCard(PackofCards.remove());
            }
        }

    }

}
    private static void playGame() {
    }
}
