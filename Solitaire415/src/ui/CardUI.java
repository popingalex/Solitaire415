package ui;

import java.awt.Graphics;

import javax.swing.JButton;

import card.Card;

public class CardUI extends JButton {
    private static final long serialVersionUID = 1L;
    private Card card;
    private boolean isFace;
    
    public void setCard(Card card, boolean isFace) {
        this.card = card;
        this.isFace = isFace;
        this.setText(card==null?isFace?"Tap":"Deck":card.toString());
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString(getText(), 8, 12);
    }
}
