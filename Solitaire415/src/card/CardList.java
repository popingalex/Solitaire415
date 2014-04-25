package card;

import java.util.LinkedList;
import java.util.List;

import solitaire.EResult;
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

    public LinkedList<Card> getCards() {
        return cards;
    }
    public int getOpenedIndex() {
        return openedIndex;
    }
    public Card getTailCard() {
        return tailCard;
    }

    public CardList() {
        cards = new LinkedList<Card>();
        openedIndex = -1;
    }
    public void init(List<Card> cards, int openedIndex) {
        this.cards.clear();
        this.cards.addAll(cards);
        this.openedIndex = openedIndex;
        if(!this.cards.isEmpty()) {
            tailCard = this.cards.getLast();
            openedIndex = cards.size()-1;
        }
    }
    /**
     * Separate the list into two: [0..(i-1)] and [i..count].
     * Open the card at (i-1) if necessary. Return the second list.
     * @param index
     * @return
     */
    public CardList cut(int index) {
        CardList childList = new CardList();
        if(index <0 || index >= cards.size() || index < openedIndex)
            return childList;
        LinkedList<Card> childCards = new LinkedList<Card>();
        childCards.addAll(cards.subList(index, cards.size()));
        cards.removeAll(childCards);
        tailCard = cards.size()>0?cards.get(cards.size()-1):null;

        if(index>openedIndex) {
            childList.init(childCards, 0);
        } else {
            childList.init(childCards, openedIndex-index);
            if(openedIndex == index)
                openedIndex--;
        }
        return childList;
    }
    /**
     * Join this list to the tail of the other list,
     * if the rules allow this.
     * @param other
     */
    public void link(CardList other) {
        if(
                this.openedIndex==0 &&
                other.cards.getLast().getValue().compareTo(cards.getFirst().getValue())==1 &&
                other.cards.getLast().getColour().compareTo(cards.getFirst().getColour())!=0) {
            other.cards.addAll(cards);
            other.tailCard = cards.getLast();
        }
    }
    /**
     * Add c as the new tail card,
     * if the rules allow this.
     * @param c
     */
    public EResult add(Card c) {
        if(cards.size()==0 ||
                cards.getLast().getValue().compareTo(c.getValue())==1 && 
                cards.getLast().getColour().compareTo(c.getColour())!=0) {
            this.cards.add(c);
            tailCard = c;
            return EResult.Welldone;
        }
        return EResult.Impossible;
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
    public int indexOf(Card card) {
        // TODO Auto-generated method stub
        return 0;
    }
}
