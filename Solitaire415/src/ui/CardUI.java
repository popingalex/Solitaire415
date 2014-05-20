package ui;

import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import card.Card;

public class CardUI extends JLabel {
	private static final long serialVersionUID = 1L;
	private Card card;
	private boolean isFace;

	public CardUI() {
		super();
		this.setBorder(BorderFactory.createEtchedBorder());
	}
	public void setCard(Card card, boolean isFace) {
		this.card = card;
		this.isFace = isFace;
		this.setText(card==null?isFace?"Tap":"Deck":card.toString());
	}
	@Override
	public void paint(Graphics g) {
//		super.paint(g);
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(getForeground());
		g.drawString(getText(), 8, 12);
		paintBorder(g);
	}
	@Override
	public String getName() {
		return card.toString();
	}
}
