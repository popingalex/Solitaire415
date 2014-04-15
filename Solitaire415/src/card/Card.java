package card;

import java.awt.Graphics;

/**
 * 
 * @author [sign your own name]
 *
 */
public class Card {
    /**
     * The card's index, from 1 to 52.
     */
    private int cardIndex;
    /**
     * Returns the card's suit.
     * @return
     */
    public Suit getSuit() {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * Returns the card's value (such as 10, king, etc).
     * @return
     */
    public Value getValue() {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * The colour of the card is 'red' if this card is a heart or diamond,
     * and 'black' otherwise.
     * @return
     */
    public Colour getColour() {
        return null;
    }
    /**
     * Returns a string representation of this card,
     * including its suit and rank.
     * Example: Ace of clubs would be ClubA,
     * ten of diamonds would be Diamond10,
     * and queen of spades
     * would be SpadeQ.
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    /**
     * Draws the card.
     * In the simple GUI,
     * this should draw a Rectangle,
     * with the string representation of the card
     * written in the colour corre-sponding to
     * the colour of the card (either black or red).
     * (You don't have to make it pretty)
     * @param g
     */
    public void paintThis(Graphics g) {
        // TODO Auto-generated method stub
    }
}
