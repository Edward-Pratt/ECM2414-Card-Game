package cards;
import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {
    private final int deckNumber;
    private final Queue<Card> deckOfCards = new LinkedList<>();

    public CardDeck(int deckNumber){
        this.deckNumber = deckNumber;
    }
    public synchronized Card getCard(){
        return this.deckOfCards.remove();
    }

    public synchronized void setCard(Card c){
        this.deckOfCards.add(c);
    }

    public synchronized Queue<Card> getAllCards(){
        return this.deckOfCards;
    }
    public int getDeckNumber(){
        return this.deckNumber;
    }
}
