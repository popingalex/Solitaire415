package card;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import solitaire.EResult;
import solitaire.Solitaire;
/**
 * 
 * @author [sign your own name]
 *
 */
public class CardList {
	public int countFaces() {
		return cards.size()-openedIndex;
	}

	public int countBacks() {
		return openedIndex;
	}

	/**
	 * @param index
	 * @return a faced card, or null if the index is back.
	 */
	public Card getCard(int index) {
		return (Solitaire.DEBUG_STRICT && index < openedIndex) ? null : cards.get(index);
	}

	public int getOpenedIndex() {
		return countBacks();
	}

	public Card getTailCard() {
		return tailCard;
	}

	public int indexOf(Card card) {
	    return this.cards.indexOf(card);
	}
	//==============================
	/**
	 * A linked list to store the cards in this list.
	 */
	LinkedList<Card> cards;
	
	private Card tailCard;
	
	private int openedIndex;

	public CardList() {
		cards = new LinkedList<Card>();
		openedIndex = 0;
	}

	public void init(List<Card> cards, int openedIndex) {
		this.cards.clear();
		this.cards.addAll(cards);
		this.openedIndex = openedIndex;
		if(this.cards.size()>0) {
			this.tailCard = this.cards.getLast();
		}
	}

	/**
	 * Separate the list into two: [0..(i-1)] and [i..count].
	 * Open the card at (i-1) if necessary. Return the second list.
	 * @param index
	 * @return
	 */
	public CardList cut(int index) {
		if(index < cards.size() && index >= openedIndex) {
			if(openedIndex>0 && openedIndex==index) {
				this.openedIndex--;
			}
			List<Card> subList = this.cards.subList(index, this.cards.size());
			LinkedList<Card> childCards = new LinkedList<Card>();
			childCards.addAll(subList);
			subList.clear();

			this.tailCard = cards.size()>0?cards.getLast():null;

			CardList childList = new CardList();
			childList.init(childCards, 0);
			return childList;
		}
		return null;
	}

	/**
	 * Join this list to the tail of the other list,
	 * if the rules allow this.
	 * @param other
	 */
	public void link(CardList other) {
	    System.out.println("{}"+Arrays.toString(other.cards.toArray()));
		if(
				(this.cards.size()==0) ||
				this.openedIndex==0 &&
				Card.isNext(this.cards.get(0), other.tailCard) &&
				!Card.isSameColour(this.cards.get(0), other.tailCard)){
			other.cards.addAll(this.cards);
			other.tailCard = tailCard;
		}
		System.out.println("{}"+Arrays.toString(other.cards.toArray()));
	}

	/**
	 * Add c as the new tail card,
	 * if the rules allow this.
	 * @param c
	 */
	public EResult add(Card c) {
		if(
				(cards.size()==0) ||
				Card.isNext(c, this.tailCard) &&
				!Card.isSameColour(this.tailCard, c)) {
			this.cards.add(c);
			this.tailCard = c;
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
//	    System.out.println("no");
		if(tailCard==null)
			return null;
		cards.remove(tailCard);
		if(openedIndex>0 && openedIndex==cards.size())
			openedIndex--;
		Card temp = tailCard;
		if(cards.size()>0)
			tailCard = cards.getLast();
		return temp;
	}

}
