package ui;

import card.Card;

public interface IDataAccessor extends IDataSource {
    public Card[] getDeckCards();
    public Card[] getListCards(int index);
    public Card[] getStackCards(int index);
}
