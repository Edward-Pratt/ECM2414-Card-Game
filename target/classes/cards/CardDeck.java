package cards;
import java.util.LinkedList;
import java.util.Queue;

/**
 * CardDeck class. This class handles the card decks in the CardGame,
 * where it handles adding and removing cards from the decks and retrieves
 * the cards from those decks.
 * <p>
 * The number of decks in the game equates to the amount of players that
 * are playing the card game.
 * </p>
 * 
 * @author Edward Pratt & Alexander Hay
 * @version 1.0
 */
public class CardDeck {
    private final int deckNumber;
    private final Queue<Card> deckOfCards = new LinkedList<>();

    /**
     * The constructor of the CardDeck class that instantiates and
     * initialises the non-static variables
     * <p>
     * Assigns each card deck in the game with a unique identifier.
     * </p>
     * 
     * @param deckNumber the card deck number
     */
    public CardDeck(int deckNumber){
        this.deckNumber = deckNumber;
    }

    /**
     * Removes the top card from the deck.
     * <p>
     * This method is synchronized to ensure thread-safe access to the buffer.
     * The lock used for synchronization is the instance lock of this object
     * (i.e., {@code synchronized(this)}).
     * </p>
     * 
     * @return The deck of cards after removing the top card from the deck.
     */
    public synchronized Card removeCard(){
        return this.deckOfCards.remove();
    }

    /**
     * Adds a card to the bottom of the deck.
     * <p>
     * This method is synchronized to ensure thread-safe access to the buffer.
     * The lock used for synchronization is the instance lock of this object
     * (i.e., {@code synchronized(this)}).
     * </p>
     * 
     * @param card the card that is being added to the bottom of the deck
     */
    public synchronized void addCard(Card card){
        this.deckOfCards.add(card);
    }

    /**
     * Retrieves all the cards from the deck
     * <p>
     * This method is synchronized to ensure thread-safe access to the buffer.
     * The lock used for synchronization is the instance lock of this object
     * (i.e., {@code synchronized(this)}).
     * </p>
     * 
     * @return all the cards within the deck of cards
     */
    public synchronized Queue<Card> getAllCards(){
        return this.deckOfCards;
    }

    /**
     * Retrieves the number of the assigned deck.
     * 
     * @return the deck number
     */
    public int getDeckNumber(){
        return this.deckNumber;
    }

    /**
     * Helper to write deck contents to file at end of game
     *
     * @param s  the file name
     * @param s1 the content to write to the file
     */
    public void writeFile(String s, String s1) {
        FileEditor fileEditor = new FileEditor();
        fileEditor.createFile(s);
        fileEditor.writeFile(s, s1);
    }
}
