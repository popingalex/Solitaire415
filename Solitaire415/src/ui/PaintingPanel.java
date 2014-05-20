package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * @author Alex
 */
public class PaintingPanel extends AbstractUI {
	private int frameWidth;
	private int frameHeight;
	private int cardWidth;
	private int cardHeight;
	private int cardGap;
	private int deckGap;//review
	private int deckPad;
	private int listPad;

	private LinkedList<CardUI> cardUIstore;
	private LinkedList<CardUI> cardUIusing;
	private ArrayList<LinkedList<CardUI>> cardListFaces;

	private JFrame frame;
	private JLayeredPane cardPanel;
	private JTextField commandField;
	private JButton commandButton;
	private String lastCommand = "";

	private Rectangle[] listTopArea;
	private Rectangle[] stackArea;

	private MouseListener drawListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			handleCommand("draw");
		}
	};
	private MouseAdapter deckListener = new MouseAdapter() {
		private Point last;
		private Point base;
		private int LAYER_HOVER = 100;
		@Override
		public void mousePressed(MouseEvent e) {
			cardPanel.setLayer(e.getComponent(), cardPanel.getLayer(e.getComponent())+LAYER_HOVER);
			last = e.getLocationOnScreen();
			base = e.getComponent().getLocation();
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			Point current = e.getLocationOnScreen();
			Point location = e.getComponent().getLocation();
			e.getComponent().setLocation(
					location.x+current.x-last.x, 
					location.y+current.y-last.y);
			last = current;
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			Component component = e.getComponent();
			Point location = component.getLocation();
			Point point = e.getPoint();
			for(int i=0, sum=stackArea.length; i<sum; i++) {
				if(stackArea[i].contains(location.x+point.x, location.y+point.y)) {
					handleCommand("send "+component.getName());
					return;
				}
			}
			for(int i=0, sum=listTopArea.length; i<sum; i++) {
				//strict judge by cursor location.
				if(
						listTopArea[i].getBounds().contains(location.x+e.getX(), location.y+e.getY())) {
					handleCommand("deck "+(i+1));
					return;
				}
			}
			e.getComponent().setLocation(base);
			cardPanel.setLayer(e.getComponent(), cardPanel.getLayer(e.getComponent())-LAYER_HOVER);
		}
	};
	private MouseAdapter listListener = new MouseAdapter() {
		private Point last;
		private Point base;
		private int LAYER_HOVER = 100;
		private List<CardUI> listList = new ArrayList<CardUI>();
		@Override
		public void mousePressed(MouseEvent e) {
			last = e.getLocationOnScreen();
			base = e.getComponent().getLocation();
			for(int i=0, sum = cardListFaces.size();i<sum;i++) {
				int index = cardListFaces.get(i).indexOf(e.getComponent());
				if(index<0)
					continue;
				listList.clear();
				for(int j=index, max=cardListFaces.get(i).size();j<max;j++) {
					listList.add(cardListFaces.get(i).get(j));
				}
				for(CardUI cardUI : listList) {
					cardPanel.setLayer(cardUI, cardPanel.getLayer(e.getComponent())+LAYER_HOVER);
				}
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			Point current = e.getLocationOnScreen();
			//			Point location = e.getComponent().getLocation();
			for(CardUI cardUI : listList) {
				cardUI.setLocation(
						cardUI.getX()+current.x-last.x, 
						cardUI.getY()+current.y-last.y);
			}
			last = current;
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			Component component = e.getComponent();
			Point location = component.getLocation();
			Point point = e.getPoint();
			if(listList.size()==1) {
				for(int i=0, sum=stackArea.length; i<sum; i++) {
					if(stackArea[i].contains(location.x+point.x, location.y+point.y)) {
						handleCommand("send "+component.getName());
						return;
					}
				}
			}
			for(int i=0, sum = listTopArea.length;i<sum;i++) {
				//strict judge by cursor location.
				if(listTopArea[i].contains(location.x+point.x, location.y+point.y)) {
					handleCommand("link "+component.getName()+" "+(i+1));
					return;
				}
			}
			for(CardUI cardUI : listList) {
				location = cardUI.getLocation();
				cardPanel.setLayer(cardUI, cardPanel.getLayer(component)+LAYER_HOVER);
				cardUI.setLocation(
						location.x+base.x-last.x, 
						location.y+base.y-last.y);
			}
			component.setLocation(base);
			cardPanel.setLayer(component, cardPanel.getLayer(component)-LAYER_HOVER);
		}
	};
	private MouseListener sendListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2) {
				handleCommand("send "+e.getComponent().getName());
			}
		}
	};

	/**
	 * Draw the game stored in game.
	 * @param g
	 */
	private void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
	}
	//===================
	public PaintingPanel() {
		frameWidth = 750;
		frameHeight = 600;
		cardWidth = 62*3/2;
		cardHeight = 88*3/2;
		cardGap = 8;
		deckGap = 30;
		deckPad = 20;
		listPad = 32;
		cardUIstore = new LinkedList<CardUI>();
		cardUIusing = new LinkedList<CardUI>();
		cardListFaces = new ArrayList<LinkedList<CardUI>>();
		for(int i=0;i<52+1+4;i++) {
			cardUIstore.add(new CardUI());
		}
		for(int i=0;i<7;i++) {
			cardListFaces.add(new LinkedList<CardUI>());
		}
		stackArea = new Rectangle[4];
		for(int i=0;i<4;i++) {
			stackArea[i] = new Rectangle(cardWidth, cardHeight); 
		}
		listTopArea = new Rectangle[7];
		for(int i=0;i<7;i++) {
			listTopArea[i] = new Rectangle(cardWidth, cardHeight); 
		}

		for (CardUI ui : cardUIstore) {
			ui.setPreferredSize(new Dimension(cardWidth, cardHeight));
			ui.setSize(new Dimension(cardWidth, cardHeight));
			Insets i = ui.getInsets();
			i.set(0, 0, 0, 0);
			//			ui.setMargin(i);
			ui.addMouseListener(sendListener);
		}
		cardPanel = new JLayeredPane();
		cardPanel.setBorder(BorderFactory.createEtchedBorder());
		cardPanel.setPreferredSize(new Dimension(frameWidth, frameHeight));

		commandField = new JTextField();
		commandField.setPreferredSize(new Dimension(200, 30));
		commandField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					commandButton.doClick();
				}
				if(e.getKeyCode()==KeyEvent.VK_UP) {
					commandField.setText(lastCommand);
				}
			}
		});
		commandButton = new JButton("execute");
		commandButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleCommand(commandField.getText());
				lastCommand = commandField.getText();
				commandField.setText("");
			}
		});
		JPanel commandPanel = new JPanel();
		commandPanel.add(commandField);
		commandPanel.add(commandButton);

		frame = new JFrame();
		Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		frame.setBounds((maxBounds.width - frameWidth)/2, (maxBounds.height - frameHeight)/2, frameWidth, frameHeight);
		//        frame.setLayout(new FlowLayout());
		frame.add(cardPanel, BorderLayout.CENTER);
		frame.add(commandPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	@Override
	public void refresh() {
		paintComponent(null);
		cardPanel.removeAll();
		if(cardUIusing.size()>0) {
			cardUIstore.addAll(cardUIusing);
			cardUIusing.clear();
		}
		for(CardUI cardUI : cardUIstore) {
			cardUI.removeMouseListener(deckListener);
			cardUI.removeMouseMotionListener(deckListener);
			cardUI.removeMouseListener(listListener);
			cardUI.removeMouseMotionListener(listListener);
			cardUI.removeMouseListener(drawListener);
		}
		{//paint deck
			if(countDeckBacks()==0) {
				CardUI cardUI = cardUIstore.poll();
				cardUI.setCard(null, true);
				cardUI.setLocation(deckPad, deckPad);
				cardUI.addMouseListener(drawListener);
				cardUIusing.add(cardUI);
				cardPanel.setLayer(cardUI, -1);
				cardPanel.add(cardUI);
			} else {
				for(int i=0, sum=countDeckBacks();i<sum;i++) {
					CardUI cardUI = cardUIstore.poll();
					cardUI.setCard(getDeckCard(i), false);
					cardUI.setLocation(deckPad+i, deckPad);
					cardUI.addMouseListener(drawListener);
					cardUIusing.add(cardUI);
					cardPanel.setLayer(cardUI, i);
					cardPanel.add(cardUI);
				}
			}
			for(int i=0, sum=countDeckFaces();i<sum;i++) {
				CardUI cardUI = cardUIstore.poll();
				cardUI.setCard(getDeckCard(countDeckBacks()+countDeckFaces()-i-1), true);
				cardUI.setLocation(deckPad+cardWidth+deckGap+i, deckPad);
				cardUIusing.add(cardUI);
				cardPanel.setLayer(cardUI, i);
				cardPanel.add(cardUI);
				if(i==sum-1) {
					cardUI.addMouseListener(deckListener);
					cardUI.addMouseMotionListener(deckListener);
				}
			}
		}
		{//paint stack
			for(int j=0;j<4;j++) {
				//for(int i=0, sum=countStackFaces(j);i<sum;i++) {
				stackArea[j].setLocation(frameWidth-deckPad-(4-j)*(cardWidth+cardGap), deckPad);//尺寸不改变不需要重新设置
				if(countStackFaces(j)>0) {
					CardUI cardUI = cardUIstore.poll();
					cardUI.setCard(getStackCard(countStackFaces(j)-1, j), true);
					cardUI.setLocation(frameWidth-deckPad-(4-j)*(cardWidth+cardGap), deckPad);
					cardUIusing.add(cardUI);
					cardPanel.setLayer(cardUI, j);
					cardPanel.add(cardUI);
				} else {
					CardUI cardUI = cardUIstore.poll();
					cardUI.setCard(null, false);
					cardUI.setLocation(frameWidth-deckPad-(4-j)*(cardWidth+cardGap), deckPad);
					cardUIusing.add(cardUI);
					cardPanel.setLayer(cardUI, j);
					cardPanel.add(cardUI);
				}
				//            }
			}
		}
		{//paint list
			for(int j=0;j<7;j++) {
				listTopArea[j].setLocation(deckPad+j*(cardWidth+cardGap), deckPad+cardHeight+listPad+(countListBacks(j)+countListFaces(j))*20);
				for(int i=0, sum=countListBacks(j);i<sum;i++) {
					CardUI cardUI = cardUIstore.poll();
					cardUI.setCard(getListCard(i, j), false);
					cardUI.setLocation(deckPad+j*(cardWidth+cardGap), deckPad+cardHeight+listPad+i*20);
					cardUIusing.add(cardUI);
					cardPanel.setLayer(cardUI, i);
					cardPanel.add(cardUI);
				}
				cardListFaces.get(j).clear();
				for(int i=countListBacks(j), sum=i+countListFaces(j);i<sum;i++) {
					CardUI cardUI = cardUIstore.poll();
					cardUI.setCard(getListCard(i, j), true);
					cardUI.setLocation(deckPad+j*(cardWidth+cardGap), deckPad+cardHeight+listPad+i*20);
					cardUI.addMouseListener(listListener);
					cardUI.addMouseMotionListener(listListener);
					cardUIusing.add(cardUI);
					cardPanel.setLayer(cardUI, i);
					cardPanel.add(cardUI);
					cardListFaces.get(j).offer(cardUI);
				}
			}
		}
		cardPanel.repaint();
	}

	@Override
	public void startTracking() {
		//temp
		Thread thread = new Thread() {
			@Override
			public void run() {
				Scanner scanner = new Scanner(System.in);
				String command = scanner.nextLine();
				handleCommand(command);
			}
		};
		thread.start();
	}
}
