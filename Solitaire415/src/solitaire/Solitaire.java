package solitaire;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import ui.AbstractUI;
import ui.CommandConsole;
import ui.IDataSource;
import ui.PaintingPanel;
import card.Card;
import card.CardDeck;
import card.CardList;
import card.CardStack;

/**
 * This is the main class of the game.
 * @author [sign your own name]
 */
public class Solitaire implements ICommandReceiver, IDataSource {
	public static boolean DEBUG_STRICT = true;
	private GameState gameState;

	private AbstractUI ui;

	private LinkedBlockingQueue<String> commandQueue = new LinkedBlockingQueue<String>();
	//====================
	private Card[] cards;
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
		//        Solitaire.DEBUG_STRICT = false;
		Solitaire solitaire = new Solitaire();
		switch (1) {
		case 1:
			Solitaire.showGUI(solitaire);
			break;
		case 2:
			Solitaire.showCUI(solitaire);
			break;
		}
		solitaire.startGame();
	}


	public Solitaire() {
		deck = new CardDeck();
		stacks = new CardStack[4];
		for(int i=0;i<4;i++) {
			stacks[i] = new CardStack();
		}
		lists = new CardList[7];
		for(int i=0;i<7;i++) {
			lists[i] = new CardList();
		}
		cards = new Card[52];
		for(int i=0;i<52;i++) {
			cards[i] = new Card(i+1);
		}
	}

	private static void showCUI(Solitaire game) {
		CommandConsole gameConsole = new CommandConsole();
		gameConsole.addCommandReceiver(game);
		gameConsole.addDataSouce(game);
		game.linkUI(gameConsole);
	}
	/**
	 * Should create a GUI and display the game.
	 * @param game
	 */
	public static void showGUI(Solitaire game) {
		PaintingPanel gamePanel = new PaintingPanel();
		gamePanel.addCommandReceiver(game);
		gamePanel.addDataSouce(game);
		game.linkUI(gamePanel);
	}
	private void linkUI(AbstractUI ui) {
		this.ui = ui;
	}

	/**
	 * Perform whatever command indicates if
	 * the rules allow it and return a success message.
	 * If the command is invalid,
	 * return a warning instead.
	 * @param command
	 */
	public EResult executeCommand(String commandString) {
		String paramString = null;
		if(commandString.indexOf(' ')>0) {
			paramString = commandString.substring(commandString.indexOf(' ')+1);
			commandString = commandString.substring(0, commandString.indexOf(' '));
		}
		ECommand command = ECommand.valueOfIgnoreCase(commandString);
		if(command==null) {
			return EResult.Unresolvable;
		}
		switch (command) {
		case Draw:
			System.out.println("draw");
			deck.drawCard();
			break;
		case Restart:
			System.out.println("rest");
			gameState = GameState.Prepare;
			break;
		case Deck:
		{
			if(paramString==null)
				return EResult.IllegalCommand;
			int listIndex;
			try {
				listIndex = Integer.valueOf(paramString);
			} catch (NumberFormatException e) {
				listIndex = 0;
			}
			if(listIndex<1||listIndex>7)
				return EResult.IllegalCommand;
			System.out.println("deck "+listIndex);
			return deckTo(listIndex-1);
		}
		case Send:
		{
			if(paramString==null)
				return EResult.IllegalCommand;
			int cardIndex = Card.indexOf(paramString)-1;
			if(cardIndex<0)
				return EResult.IllegalCommand;
			Card card = cards[cardIndex];
			if(card==null)
				return EResult.IllegalCommand;
			return send(card);
		}
		case Link:
		{
			if(paramString==null)
				return EResult.IllegalCommand;
			int index = paramString.indexOf(' ');

			if(index<0)
				return EResult.IllegalCommand;
			int cardIndex = Card.indexOf(paramString.substring(0, index))-1;
			if(cardIndex<0)
				return EResult.IllegalCommand;
			Card card = cards[cardIndex];
			int listIndex;
			try {
				listIndex = Integer.valueOf(paramString.substring(index+1));
			} catch (NumberFormatException e) {
				listIndex = 0;
			}
			if(listIndex<1||listIndex>7)
				return EResult.IllegalCommand;
			if(card==null || listIndex <1 || listIndex > 7)
				return EResult.IllegalCommand;

			return link(card, listIndex-1);
		}
		case Quit:
			gameState = GameState.Closing;
			break;
		}
		return EResult.Welldone;
	}
	private EResult link(Card card, int listIndex) {
		CardList cut = null;
		for(int i=0;i<7;i++) {
			int cardIndex = lists[i].indexOf(card);
			if(cardIndex<0)
				continue;
			System.out.println("link "+card+" to "+lists[listIndex].getTailCard());
			if(
					cardIndex >= lists[i].getOpenedIndex() && 
					lists[listIndex].getTailCard()==null||Card.isNext(card, lists[listIndex].getTailCard()) &&
					!Card.isSameColour(card, lists[listIndex].getTailCard())) {
				cut = lists[i].cut(cardIndex);
				break;
			}
			return EResult.Impossible;
		}
		if(cut==null)
			return EResult.Impossible;
		cut.link(lists[listIndex]);
		return EResult.Welldone;
	}

	private EResult send(Card card) {
		if(card.equals(deck.getCurrentCard())) {
			for(int j=0;j<4;j++) {
				if(stacks[j].add(deck.getCurrentCard())==EResult.Welldone) {
					deck.takeCard();
					return EResult.Welldone;
				}
			}
			return EResult.Impossible;
		}
		for(int i=0;i<7;i++) {
			if(lists[i].countFaces()>0 && lists[i].getTailCard().equals(card)) {//card find
				for(int j=0;j<4;j++) {
					if(stacks[j].add(card)==EResult.Welldone) {
						lists[i].moveTail();
						return EResult.Welldone;
					}
				}
			}
		}
		return EResult.Impossible;
	}

	private EResult deckTo(int listIndex) {
		Card deckCard = deck.getCurrentCard();
		System.out.println("deck "+deckCard+" to "+lists[listIndex].getTailCard());
		if(deckCard==null)
			return EResult.Impossible;
		if(lists[listIndex].add(deckCard) == EResult.Impossible)
			return EResult.Impossible;
		System.out.println(deck.takeCard());
		return EResult.Welldone;
	}

	/**
	 * Runs a loop that accepts commands
	 * until either a quit command is given or the player wins.
	 * Should attempt to perform any commands given,
	 * and prints all messages back to the user.
	 */
	public void startGame() {
		gameState = GameState.Prepare;
		while(gameState != GameState.Closed) {
			switch (gameState) {
			case Prepare://prepare for a new game
				prepare();
				break;
			case Looping://loop a game
				loop();
				break;
			case Closing://closing the game
				close();
				break;
			case Closed://here's a No Man Land
				break;
			}
		}
	}
	private void close() {
		gameState = GameState.Closed;
	}

	private void loop() {
		//run the clock ?
		String command = commandQueue.poll();
		if(command != null) {
			EResult result = executeCommand(command);
			System.out.println("->"+result);
			ui.refresh();
		}
	}

	private void prepare() {
		commandQueue.clear();
		LinkedList<Card> cardLink = new LinkedList<Card>();
		cardLink.addAll(Arrays.asList(cards));
		Shuffler.shuffle(cardLink);

		int index = 0;

		for(int i=0;i<4;i++) {
			stacks[i].init();
		}
		for(int i=0;i<7;i++) {
			List<Card> subList = cardLink.subList(index, index+i+1);
			lists[i].init(subList, i);
			subList.clear();
			index+=i+1;
		}
		deck.init(cardLink);

		gameState = GameState.Looping;
		ui.refresh();
		ui.startTracking();
	}

	@Override
	public synchronized void handleCommand(String command) {
		commandQueue.offer(command);
	}

	//====================
	// Interface DataSource Deck Methods
	//====================
	@Override
	public int countDeckBacks() {
		return deck.countBacks();
	}
	@Override
	public int countDeckFaces() {
		return deck.countFaces();
	}
	@Override
	public Card getDeckCard(int index) {
		return deck.getCard(index);
	}
	//====================
			// Interface DataSource List Methods
	//====================
	@Override
	public int countListBacks(int index) {
		return lists[index].countBacks();
	}
	@Override
	public int countListFaces(int index) {
		return lists[index].countFaces();
	}
	@Override
	public Card getListCard(int indexCard, int indexList) {
		return lists[indexList].getCard(indexCard);
	}
	//====================
			// Interface DataSource Stack Methods
	//====================
	@Override
	public int countStackFaces(int index) {
		return stacks[index].countFaces();
	}
	@Override
	public Card getStackCard(int indexCard, int indexStack) {
		return stacks[indexStack].getCard(indexCard);
	}


	@Override
	public boolean isTracking() {
		return gameState == GameState.Looping;
	}
}
