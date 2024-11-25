package cards;

import java.util.Random;

public class Player extends Thread {
    private final int playerNumber;
    private final CardDeck leftDeck;
    private final CardDeck rightDeck;

    private Card[] cards = new Card[4];

    public Player(int playerNumber, CardDeck leftDeck, CardDeck rightDeck) {
        this.playerNumber = playerNumber;
        this.leftDeck = leftDeck;
        this.rightDeck = rightDeck;

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

    public boolean hasWon(){
        Card firstCard = this.cards[0];
        for (Card card: cards){
            if (firstCard.getValue() != card.getValue()){
                return false;
            }
        }
        System.out.printf("Player %d has won the game!%n", this.playerNumber);
        return true;
    }

    public synchronized void drawAndDiscard() {
        Random random = new Random();
        Card removedCard;
        int randomNumber;
        do {
            randomNumber = random.nextInt(0, 3);
            removedCard = this.cards[randomNumber];
        } while (removedCard.getValue() == this.playerNumber);
        rightDeck.addCard(this.cards[randomNumber]);
        this.cards[randomNumber] = leftDeck.removeCard();


    }

    public synchronized void takeTurn() {
        Random random = new Random();
        boolean isPreferredDenomination = true;
        Card possibleDiscard = null;
        int randomNumber = 0;
        if(this.rightDeck.getAllCards().size()<5 && this.leftDeck.getAllCards().size()>3){
            drawAndDiscard();
        }



    }

    @Override
    public void run() {
        while (!CardGame.isGameWon()) {
            takeTurn();
            if(hasWon()){
                CardGame.setGameWon(true);
                //System.out.printf("Player %d has won the game!%n", this.playerNumber);
            }
        }
    }
}
