package cards;

import java.util.Random;

public class Player extends Thread {
    private final int playerNumber;
    private final CardDeck leftDeck;
    private final CardDeck rightDeck;
    FileEditor fileEditor = new FileEditor();
    private Card[] cards = new Card[4];


    public Player(int playerNumber, CardDeck leftDeck, CardDeck rightDeck) {
        this.playerNumber = playerNumber;
        this.leftDeck = leftDeck;
        this.rightDeck = rightDeck;
        fileEditor.createFile("Player" + this.playerNumber + "_output.txt");

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

    public synchronized boolean hasWon(){
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
            randomNumber = random.nextInt(4);
            removedCard = this.cards[randomNumber];
        } while (removedCard.getValue() == this.playerNumber);
        rightDeck.addCard(this.cards[randomNumber]);
        fileEditor.writeFile("Player" + this.playerNumber + "_output.txt", "Player " + this.playerNumber + " discards " + this.cards[randomNumber].getValue());
        this.cards[randomNumber] = leftDeck.removeCard();
        fileEditor.writeFile("Player" + this.playerNumber + "_output.txt", "Player " + this.playerNumber + " draws " + this.cards[randomNumber].getValue());


    }

    public void takeTurn() {
        if(this.rightDeck.getAllCards().size()<5 && this.leftDeck.getAllCards().size()>3){
            drawAndDiscard();
            fileEditor.writeFile("Player" + this.playerNumber + "_output.txt", "Player " + this.playerNumber + " hand: " + this.cards[0].getValue() + ", " + this.cards[1].getValue() + ", " + this.cards[2].getValue() + ", " + this.cards[3].getValue());
        }



    }

    public void run() {

        fileEditor.writeFile("Player" + this.playerNumber + "_output.txt", "Player " + this.playerNumber + " initial hand: " + this.cards[0].getValue() + ", " + this.cards[1].getValue() + ", " + this.cards[2].getValue() + ", " + this.cards[3].getValue());
        while (!CardGame.isGameWon() && !Thread.interrupted()) {
            synchronized (this) {
                if (hasWon()) {
                    System.out.printf("Player %d has won the game!%n", this.playerNumber);
                    CardGame.setGameWon(true);
                } else {
                    takeTurn();
                }
            }

            try {
                Thread.sleep(10); // Allow other threads time to execute
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve the interrupt status
            }
        }
    }}
