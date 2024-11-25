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
 * 
 * @author Edward Pratt and Alexander Hay
 * @version 1.0
 */

public class CardDeck {
    private final int deckNumber;
    private final Queue<Card> deckOfCards = new LinkedList<>();

    /**
     * The constructor of the CardDeck class that instantiates and
     * initialises the non-static variables
     * <p>
     * Assigns each deck in the game with a unique number.
     * @param deckNumber
     */
    public CardDeck(int deckNumber){
        this.deckNumber = deckNumber;
    }

    /**
     * Synchronously removes the cards
     * @return
     */
    public synchronized Card removeCard(){
        return this.deckOfCards.remove();
    }

    public synchronized void addCard(Card card){
        this.deckOfCards.add(card);
    }

    public synchronized Queue<Card> getAllCards(){
        return this.deckOfCards;
    }
    public int getDeckNumber(){
        return this.deckNumber;
    }
}
