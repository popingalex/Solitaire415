package ui;

import solitaire.ICommandReceiver;
import card.Card;

public abstract class AbstractUI implements IDataAccessor {

    private ICommandReceiver commandReceiver;
    private IDataSource dataSource;
    public abstract void refresh();
    public final void addCommandReceiver(ICommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    public final void addDataSouce(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected void handleCommand(String command) {
        commandReceiver.handleCommand(command);
    }
    @Override
    public int countDeckBacks() {
        return dataSource.countDeckBacks();
    }
    @Override
    public int countDeckFaces() {
        return dataSource.countDeckFaces();
    }
    @Override
    public Card getDeckCard(int index) {
        return dataSource.getDeckCard(index);
    }
    @Override
    public Card[] getDeckCards() {
        Card[] cards = new Card[countDeckFaces()];
        for(int i=countDeckBacks(), sum=i+countDeckFaces();i<sum;i++) {
            cards[i] = getDeckCard(i);
        }
        return cards;
    }

    @Override
    public int countListBacks(int index) {
        return dataSource.countListBacks(index);
    }
    @Override
    public int countListFaces(int index) {
        return dataSource.countListFaces(index);
    }
    @Override
    public Card getListCard(int indexCard, int indexList) {
        return dataSource.getListCard(indexCard, indexList);
    }
    @Override
    public Card[] getListCards(int index) {
        Card[] cards = new Card[countListFaces(index)];
        for(int i=0, sum=countListFaces(index);i<sum;i++) {
            cards[i] = getListCard(countListBacks(index)+i, index);
        }
        return cards;
    }

    @Override
    public int countStackFaces(int index) {
        return dataSource.countStackFaces(index);
    }
    @Override
    public Card getStackCard(int indexCard, int indexStack) {
        return dataSource.getStackCard(indexCard, indexStack);
    }
    @Override
    public Card[] getStackCards(int index) {
        Card[] cards = new Card[countStackFaces(index)];
        for(int i=0, sum=countStackFaces(index);i<sum;i++) {
            cards[i] = getStackCard(i, index);
        }
        return cards;
    }
    public abstract void startTracking();
}
