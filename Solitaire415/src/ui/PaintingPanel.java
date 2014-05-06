package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
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

    private JFrame frame;
    private JLayeredPane contentPanel;
    private ActionListener drawListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleCommand("draw");
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
        frameWidth = 800;
        frameHeight = 600;
        cardWidth = 62*2;
        cardHeight = 88*2;
        cardGap = 8;
        deckGap = 30;
        deckPad = 20;
        listPad = 32;

        cardUIstore = new LinkedList<CardUI>();
        cardUIusing = new LinkedList<CardUI>();
        for(int i=0;i<52+1;i++) {
            cardUIstore.add(new CardUI());
        }

        for (CardUI ui : cardUIstore) {
            ui.setPreferredSize(new Dimension(cardWidth, cardHeight));
            ui.setSize(new Dimension(cardWidth, cardHeight));
            Insets i = ui.getInsets();
            i.set(0, 0, 0, 0);
            ui.setMargin(i);
        }

        contentPanel = new JLayeredPane();
        contentPanel.setBorder(BorderFactory.createEtchedBorder());
        contentPanel.setPreferredSize(new Dimension(frameWidth, frameHeight));

        frame = new JFrame();
        Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        frame.setBounds((maxBounds.width - frameWidth)/2, (maxBounds.height - frameHeight)/2, frameWidth, frameHeight);
        //        frame.setLayout(new FlowLayout());
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    @Override
    public void refresh() {
        contentPanel.removeAll();
        if(cardUIusing.size()>0) {
            cardUIstore.addAll(cardUIusing);
            cardUIusing.clear();
        }
        //paint deck
        {
            CardUI cardUI = cardUIstore.poll();
            cardUI.setCard(null, true);
            cardUI.setLocation(deckPad, deckPad);
            cardUI.removeActionListener(drawListener);
            cardUI.addActionListener(drawListener);
            cardUIusing.add(cardUI);
            contentPanel.setLayer(cardUI, -1);
            contentPanel.add(cardUI);
        }
        for(int i=0, sum=countDeckBacks();i<sum;i++) {
            CardUI cardUI = cardUIstore.poll();
            cardUI.setCard(getDeckCard(i), false);
            cardUI.setLocation(deckPad+i, deckPad);
            cardUI.removeActionListener(drawListener);
            cardUI.addActionListener(drawListener);
            cardUIusing.add(cardUI);
            contentPanel.setLayer(cardUI, i);
            contentPanel.add(cardUI);
        }
        for(int i=0, sum=countDeckFaces();i<sum;i++) {
            CardUI cardUI = cardUIstore.poll();
            cardUI.setCard(getDeckCard(countDeckBacks()+countDeckFaces()-i-1), true);
            cardUI.setLocation(deckPad+cardWidth+deckGap+i, deckPad);
            cardUI.removeActionListener(drawListener);
            cardUIusing.add(cardUI);
            contentPanel.setLayer(cardUI, i);
            contentPanel.add(cardUI);
        }
        //paint stack
        for(int j=0;j<4;j++) {
            //            for(int i=0, sum=countStackFaces(j);i<sum;i++) {
            if(countStackFaces(j)>0) {
                CardUI cardUI = cardUIstore.poll();
                cardUI.setCard(getStackCard(countStackFaces(j)-1, j), true);
                cardUI.setLocation(frameWidth-deckPad-(4-j)*(cardWidth+cardGap), deckPad);
                cardUI.removeActionListener(drawListener);
                cardUIusing.add(cardUI);
                contentPanel.setLayer(cardUI, j);
                contentPanel.add(cardUI);
            }
            //            }
        }

        for(int j=0;j<7;j++) {
            for(int i=0, sum=countListBacks(j);i<sum;i++) {
                CardUI cardUI = cardUIstore.poll();
                cardUI.setCard(getListCard(i, j), false);
                cardUI.setLocation(deckPad+j*(cardWidth+cardGap), deckPad+cardHeight+listPad);
                cardUI.removeActionListener(drawListener);
                cardUIusing.add(cardUI);
                contentPanel.setLayer(cardUI, i);
                contentPanel.add(cardUI);
            }
            for(int i=countListBacks(j), sum=i+countListFaces(j);i<sum;i++) {
                CardUI cardUI = cardUIstore.poll();
                cardUI.setCard(getListCard(i, j), true);
                cardUI.setLocation(deckPad+j*(cardWidth+cardGap), deckPad+cardHeight+listPad);
                cardUI.removeActionListener(drawListener);
                cardUIusing.add(cardUI);
                contentPanel.setLayer(cardUI, i);
                contentPanel.add(cardUI);
            }
        }
        contentPanel.repaint();
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
