package ui;

import card.Card;

public interface IDataSource {
    public Card getDeckCard(int index);
    public int countDeckBacks();
    public int countDeckFaces();

    public Card getListCard(int indexCard, int indexList);
    public int countListBacks(int index);
    public int countListFaces(int index);

    public Card getStackCard(int indexCard, int indexStack);
    public int countStackFaces(int index);
}
