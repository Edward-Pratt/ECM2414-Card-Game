package cards;
import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {
    private final int deckNumber;
    private final Queue<Card> deckOfCards = new LinkedList<>();

    public CardDeck(int deckNumber){
        this.deckNumber = deckNumber;
    }
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
