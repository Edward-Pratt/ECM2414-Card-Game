package cards;

public class Player extends Thread {
    private final int playerNumber;

    private Card[] cards = new Card[4];

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void addCard (int location, Card newCard){
        this.cards[location] = newCard;
    }
}
