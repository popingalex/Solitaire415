package card;

import java.util.Stack;

import solitaire.EResult;

/**
 * @author Alex
 */
public class CardStack {
	public int countFaces() {
	    return cards.size();
	}
	
	public Card getCard(int index) {
	    return cards.get(index);
	}
	//==============================
	/**
	 * A stack to store the cards.
	 */
	private Stack<Card> cards;
	
	public CardStack() {
		cards = new Stack<Card>();
	}
	public void init() {
		cards.clear();
	}
	/**
	 * Adds c to the top of the stack, if the rules allow this.
	 * @return
	 */
	public EResult add(Card card) {
		if(
		        (this.cards.size()==0 && card.getValue()==EValue.Ace) ||
		        this.cards.size()>0 &&
				Card.isNext(this.cards.lastElement(), card)&&
				Card.isSameSuit(this.cards.lastElement(), card)) {
			this.cards.add(card);
			return EResult.Welldone;
		}
		return EResult.Impossible;
	}
}
