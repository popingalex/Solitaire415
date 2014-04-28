package card;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

/**
 * 
 * @author Alex
 *
 */
public class Card {
    /**
     * a <String HashCode, Card Index> map;
     */
    private static HashMap<Integer, Integer> cardMap = new HashMap<Integer, Integer>();

    /**
     * The card's index, from 1 to 52.
     * Such card's index only define the face of such card.
     */
    private int cardIndex;
    public Card(int cardIndex) {
        this.cardIndex = cardIndex;
        cardMap.put(getShortName().toLowerCase().hashCode(), cardIndex);
        cardMap.put(getLongName().toLowerCase().hashCode(), cardIndex);
    }

    /**
     * @return short name like SpadeQ
     */
    public String getShortName() {
        return getSuit().toString()+getValue().toString();
    }

    /**
     * @return long name like Queen of Spade
     */
    public String getLongName() {
        return getValue().toName()+" of "+getSuit().toString();
    }

    /**
     * Returns the card's suit.
     * @return
     */
    public ESuit getSuit() {
        return ESuit.getSuit(cardIndex);
    }

    /**
     * Returns the card's value (such as ten(10), king(13), etc).
     * @return
     */
    public EValue getValue() {
        return EValue.getValue(cardIndex);
    }

    /**
     * The colour of the card is 'red' if this card is a heart or diamond,
     * and 'black' otherwise.
     * @return
     */
    public EColour getColour() {
        return EColour.getColour(getSuit());
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
        return getSuit().toString()+getValue().toString();
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString(this.toString(), 20, 20);
    }

    /**
     * get the index card in ONE deck, from 1 to 52
     * @param cardString
     * @return [1, 52] for a card or 0 for wrong string.
     */
    public static int indexOf(String cardString) {
        Integer index = cardMap.get(cardString.toLowerCase().hashCode());
        return index==null?0:index.intValue()+1;
    }
}
