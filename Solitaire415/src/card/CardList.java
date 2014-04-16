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
    private LinkedList<Card> cards;
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
        if(!this.cards.isEmpty())
            tailCard = cards.getLast();
    }
    /**
     * Separate the list into two: [0..(i-1)] and [i..count].
     * Open the card at (i-1) if necessary. Return the second list.
     * @param index
     * @return
     */
    public LinkedList<Object> cut(int index) {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * Join this list to the tail of the other list,
     * if the rules allow this.
     * @param other
     */
    public void link(CardList other) {
        // TODO Auto-generated method stub
    }
    /**
     * Add c as the new tail card,
     * if the rules allow this.
     * @param c
     */
    public void add(Card c) {
        // TODO Auto-generated method stub
    }
    /**
     * Delete and return the tail card.
     * Set the card beneath it as the new tail card.
     * Open the new tail card if necessary.
     */
    public void moveTail() {
        // TODO Auto-generated method stub
    }
}
