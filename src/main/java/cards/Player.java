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

    public synchronized void takeTurn() {
        Random random = new Random();
        boolean isPreferredDenomination = true;
        Card possibleDiscard = null;
        int randomNumber = 0;
        while (isPreferredDenomination) {
            randomNumber = random.nextInt(0,3);
            possibleDiscard = this.cards[randomNumber];
            if (possibleDiscard.getValue() != this.playerNumber) {
                isPreferredDenomination = false;
            }
        }

        while(!(leftDeck.getAllCards().isEmpty())){
            this.cards[randomNumber] = leftDeck.removeCard();
        }
        rightDeck.addCard(possibleDiscard);

    }

    @Override
    public void run() {
        while (!CardGame.isGameWon()) {
            takeTurn();
            if(hasWon()){
                CardGame.setGameWon(true);
                System.out.printf("Player %d has won the game!%n", this.playerNumber);
            }
        }
    }
}
