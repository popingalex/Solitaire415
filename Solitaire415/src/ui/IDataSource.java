package ui;

import card.CardDeck;
import card.CardList;
import card.CardStack;

public interface IDataSource {
    public CardDeck getDeckData();
    public CardList[] getListData();
    public CardStack[] getStackData();
}
