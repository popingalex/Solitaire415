package card;

import java.util.LinkedList;

/**
 * 
 * @author [sign your own name]
 *
 */
public class CardDeck {
    /**
     * A circularly-linked list storing all the cards in the deck.
     */
    private LinkedList<Card> cards;
    /**
     * The current card.
     */
    private Card currentCard;

    public CardDeck() {
        cards = new LinkedList<Card>();
        currentCard = null;
    }
    /**
     * when renew game, clear deck, fill new deck Cards.
     * @param cards
     */
    public void init(LinkedList<Card> cards) {
        this.cards.clear();
        this.cards.addAll(cards);
    }
    
    /**
     * Open the next card, if this is the tail card, return null.
     * @return
     */
    public Card drawCard() {
        if(cards.size()>1) {
            int index = cards.indexOf(currentCard);
            index = (index+1)%cards.size();
            currentCard = cards.get(index);
        }
        return currentCard;
    }
    
    /**
     * Delete and return the current card 
     * (so we can place it in a list or a stack).
     * @return
     */
    public Card takeCard() {
        if(currentCard == null)
            return null;
        Card temp = currentCard;
        int index = cards.indexOf(currentCard);
        if(index==0)
            currentCard = null;
        else {
            index = (index-1);
            currentCard = cards.get(index);
        }
        cards.remove(temp);
        return temp;
    }
}
