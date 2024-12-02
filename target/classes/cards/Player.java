package cards;

import java.util.ArrayList;
import java.util.Random;

/**
 * Player class that represents a player in the card game.
 * This class has a run method for threading, and methods for adding and getting cards, 
 * checking if the player has won, and allowing them to interact with decks.
 *
 * @author Edward Pratt & Alexander Hay
 * @version 1.0
 */
public class Player extends Thread {
    private final int playerNumber;
    private final CardDeck leftDeck;
    private final CardDeck rightDeck;
    FileEditor fileEditor = new FileEditor();
    private Card[] cards = new Card[4];

    /**
     * Constructor for the Player class.
     * 
     * @param playerNumber The number of the player being created.
     * @param leftDeck The deck that will be drawn from.
     * @param rightDeck The deck that will be discarded to.
     */
    public Player(int playerNumber, CardDeck leftDeck, CardDeck rightDeck) {
        this.playerNumber = playerNumber;
        this.leftDeck = leftDeck;
        this.rightDeck = rightDeck;
        fileEditor.createFile("Player" + this.playerNumber + "_output.txt");

    }

    /**
     * Method to add a card to the player's hand.
     * 
     * @param location The location in the hand that the card will be added to 1-4.
     * @param newCard The card that will be added to the hand.
     */
    public synchronized void addCard (int location, Card newCard){
        this.cards[location] = newCard;
    }

    /**
     * Method to get a card from the player's hand.
     * 
     * @param location The location in the hand that the card will be retrieved from 1-4.
     * @return The card at the specified location.
     */
    public synchronized Card getCard(int location){
        return this.cards[location];
    }

    /**
     * Method to get all cards from the player's hand.
     * 
     * @return An array of all the cards in the player's hand.
     */
    public synchronized Card[] getAllCards(){
        return this.cards;
    }

    /**
     * Method to get the player's number.
     * 
     * @return The player's number.
     */
    public int getPlayerNumber(){
        return this.playerNumber;
    }

    /**
     * Method to check if the player has won the game.
     * 
     * @return True if the player has won, false if they have not.
     */
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

    /**
     * Method to draw a card from the left deck and discard a card 
     * to the right deck atomically.
     */
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

    /**
     * Method to take a turn in the game.
     */
    public void takeTurn() {
        if(this.rightDeck.getAllCards().size()<5 && this.leftDeck.getAllCards().size()>3){
            drawAndDiscard();
            fileEditor.writeFile("Player" + this.playerNumber + "_output.txt", "Player " + this.playerNumber + " hand: " + this.cards[0].getValue() + ", " + this.cards[1].getValue() + ", " + this.cards[2].getValue() + ", " + this.cards[3].getValue());
        }
    }

    /**
     * Method that operates the main functionality for the player,
     * checking if the game has been won while synchronously maintaining
     * the threads of the players'
     * <p>
     * It also maintains each players turn of taking and discard a card
     * and logging the data to the player's respective file.
     * </p>
     */
    public void run() {

        fileEditor.writeFile("Player" + this.playerNumber + "_output.txt", "Player " + this.playerNumber + " initial hand: " + this.cards[0].getValue() + ", " + this.cards[1].getValue() + ", " + this.cards[2].getValue() + ", " + this.cards[3].getValue());
        while (!CardGame.isGameWon() && !Thread.interrupted()) {
            synchronized (this) {
                if (hasWon()) {
                    CardGame.setGameWon(true);

                } else {
                    takeTurn();
                }
            }
        }
        ArrayList<Integer> deckContents = new ArrayList<>(
                rightDeck.getAllCards().stream()
                        .map(Card::getValue) // Converts each card to its string representation
                        .toList()
        );

        fileEditor.writeFile("Player" + this.playerNumber + "_output.txt", "Player " + this.playerNumber + "exits");
        fileEditor.writeFile("Player" + this.playerNumber + "_output.txt", "Player " + this.playerNumber + " final hand" + this.cards[0].getValue() + ", " + this.cards[1].getValue() + ", " + this.cards[2].getValue() + ", " + this.cards[3].getValue());
        rightDeck.writeFile("Deck" + rightDeck.getDeckNumber() + "_output.txt", "Deck:" + rightDeck.getDeckNumber() + " has these cards:" + deckContents);
    }
}