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

    
    
    public LinkedList<Card> getCards() {
        return cards;
    }
    public Card getCurrentCard() {
        return currentCard;
    }
    
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
        Card temp = currentCard;
        
        if(cards.size()>0) {
            int index = cards.indexOf(currentCard);
            index = (index + (cards.size()+1) -1 +1) %(cards.size()+1)-1;
            currentCard = index<0 ? null:cards.get(index);
        }
        return temp;
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
