package card;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Alex
 *
 */
public class CardDeck {
    public Card getCard(int index) {
        return (index<currentIndex || index==cards.size()) ? null : cards.get(index);
    }
    
    public int countFaces() {
        return cards.size()-currentIndex;
    }
    
    public int countBacks() {
        return currentIndex;
    }
    //==============================
    /**
	 * A circularly-linked list storing all the cards in the deck.
	 */
	private LinkedList<Card> cards;

	private Card currentCard;

	private int currentIndex;

	public Card getCurrentCard() {
		return currentCard;
	}

	public CardDeck() {
		cards = new LinkedList<Card>();
		currentCard = null;
		currentIndex = 0;
	}

	/**
	 * when renew game, clear deck, fill new deck Cards.
	 * @param cards
	 */
	public void init(List<Card> cards) {
		this.cards.clear();
		this.cards.addAll(cards);
		this.currentIndex = cards.size();
	}

	/**
	 * Open the next card, if this is the tail card, return null.
	 * @return
	 */
	public Card drawCard() {
		Card current = currentCard;
		currentIndex = (currentIndex + cards.size())%(cards.size()+1);
		currentCard = currentIndex==cards.size()?null:cards.get(currentIndex);
		return current;
	}

	/**
	 * Delete and return the current card 
	 * (so we can place it in a list or a stack).
	 * @return
	 */
	public Card takeCard() {
		if(currentCard == null)
			return null;
		Card current = cards.removeLast();
		currentCard = currentIndex==cards.size()?null:cards.get(currentIndex);
		return current;
	}
}
