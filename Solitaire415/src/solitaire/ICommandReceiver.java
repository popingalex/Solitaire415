package solitaire;


public interface ICommandReceiver {

    void handleCommand(String command);
    boolean isTracking();

}
