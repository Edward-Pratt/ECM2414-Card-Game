package cards;

public class Player extends Thread {
    private final int playerNumber;
    private Card[] cards = new Card[4];

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public synchronized void addCard (int location, Card newCard){
        this.cards[location] = newCard;
    }

    public synchronized Card getCard(int location){
        return this.cards[location];
    }

    public synchronized Card[] getAllCards(){
        return this.cards;
    }

    public int getPlayerNumber(){
        return this.playerNumber;
    }
}
