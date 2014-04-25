package ui;

import solitaire.ICommandReceiver;

public abstract class AbstractUI {

    protected ICommandReceiver commandReceiver;
    protected IDataSource dataSource;
    public abstract void refresh();
    public final void addCommandReceiver(ICommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    public final void addDataSouce(IDataSource dataSource) {
        this.dataSource = dataSource;
    }
}
