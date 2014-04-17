package card;

import java.util.LinkedList;
/**
 * 
 * @author [sign your own name]
 *
 */
public class CardList {
    /**
     * A linked list to store the cards in this list.
     */
    LinkedList<Card> cards;
    /**
     * The index of the first opened card.
     */
    private int openedIndex;
    /**
     * The tail card.
     */
    private Card tailCard;

    public CardList() {
        cards = new LinkedList<Card>();
    }
    public void init(LinkedList<Card> cards, int openedIndex) {
        this.cards.clear();
        this.cards.addAll(cards);
        this.openedIndex = openedIndex;
        if(!this.cards.isEmpty()) {
            tailCard = cards.getLast();
            openedIndex = cards.size()-1;
        }
    }
    /**
     * Separate the list into two: [0..(i-1)] and [i..count].
     * Open the card at (i-1) if necessary. Return the second list.
     * @param index
     * @return
     */
    public LinkedList<Card> cut(int index) {
        if(index <0 || index >= cards.size() || index < openedIndex)
            return new LinkedList<Card>();
        LinkedList<Card> child = new LinkedList<Card>();
        child.addAll(index, cards);
        cards.removeAll(child);
        if(openedIndex == cards.size())
            openedIndex--;
        tailCard = cards.get(cards.size()-1);
        return child;
    }
    /**
     * Join this list to the tail of the other list,
     * if the rules allow this.
     * @param other
     */
    public void link(CardList other) {
        if(
                cards.getLast().getValue().compareTo(other.cards.getFirst().getValue())==1 && 
                cards.getLast().getColour().compareTo(other.cards.getFirst().getColour())==0) {
            cards.addAll(other.cards);
            this.tailCard = cards.getLast();
        }
    }
    /**
     * Add c as the new tail card,
     * if the rules allow this.
     * @param c
     */
    public void add(Card c) {
        if(
                cards.getLast().getValue().compareTo(c.getValue())==1 && 
                cards.getLast().getColour().compareTo(c.getColour())==0) {
            this.add(c);
            tailCard = c;
        }
    }
    /**
     * Delete and return the tail card.
     * Set the card beneath it as the new tail card.
     * Open the new tail card if necessary.
     */
    public Card moveTail() {
        if(tailCard==null)
            return null;
        cards.remove(tailCard);
        if(openedIndex==cards.size())
            openedIndex--;
        Card temp = tailCard;
        if(cards.size()>0)
            tailCard = cards.getLast();
        return temp;
    }
    
    public static void main(String[] args) {
        
    }
}
