package cards;

/**
 * Card class. This class manages all the cards that are
 * played in CardGame and handles the values for each card
 * 
 * @author Edward Pratt, Alexander Hay
 * @version 1.0
 */

public class Card {

    private final int value;

    /**
     * The constructor method for the Card class, instantiates
     * and initialises the non-static variables
     * <p>
     * Assigns a value for each card in CardGame.
     * 
     * @param value The value of the card.
     */
    public Card(int value){
        this.value = value;
    }
    
    /**
     * GETTER method for retreving the value of the card
     * 
     * @return The value of the card.
     */
    public int getValue(){
        return this.value;
    }



}