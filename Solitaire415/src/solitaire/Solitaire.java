package solitaire;

import card.CardDeck;
import card.CardList;
import card.CardStack;

/**
 * This is the main class of the game.
 * @author [sign your own name]
 */
public class Solitaire {
    /**
     * A CardDeck for the current game's deck.
     */
    private CardDeck deck;
    /**
     * An array of CardStacks (of length 4).
     */
    private CardStack[] stacks;
    /**
     * An array of CardLists (of length 7).
     */
    private CardList[] lists;
    /**
     * Should create a new Solitaire, then call showGUI,
     * passing the newly-created Solitaire object to it.
     * Lastly, should call the Solitaire object's
     * startGame method.
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
    /**
     * Should create a GUI and display the game.
     * @param game
     */
    public static void showGUI(Solitaire game) {
        // TODO Auto-generated method stub
    }
    /**
     * Perform whatever command indicates if
     * the rules allow it and return a success message.
     * If the command is invalid,
     * return a warning instead.
     * @param command
     */
    public void executeCommand(String command) {
        // TODO Auto-generated method stub
    }
    /**
     * Runs a loop that accepts commands
     * until either a quit command is given or the player wins.
     * Should attempt to perform any commands given,
     * and prints all messages back to the user.
     */
    public void startGame() {
        // TODO Auto-generated method stub
    }
}
