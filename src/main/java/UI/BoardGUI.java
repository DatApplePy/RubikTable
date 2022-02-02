/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BackEnd.Board;
import BackEnd.Directions;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Collections;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author A titkos mikkentyÅ±
 */
public class BoardGUI {
    
    private JButton[][] buttons;
    private JPanel innerPanel;
    private JPanel boardPanel;
    private JPanel topButtons;
    private JPanel leftButtons;
    private JPanel bottomButtons;
    private JPanel rightButtons;
    private Directions moveDirection;
    private Board board;
    private ArrayList<Point> coordinates;
    private int stepCount = 0;
    
    public BoardGUI(int boardSize) {
        
        board = new Board(boardSize);
        coordinates = new ArrayList<Point>();
        innerPanel = new JPanel(new GridLayout(boardSize, boardSize));
        buttons = new JButton[boardSize][boardSize];
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                JButton button = new JButton();
                button.setEnabled(false);
                button.setPreferredSize(new Dimension(50,50));
                buttons[i][j] = button;
                innerPanel.add(button);
                coordinates.add(new Point(i, j));
            }
        }
        
        shuffleBoard();
        while(board.isOver()) {
            System.out.println("Reshuffled");
            shuffleBoard();
        }
        
        boardPanel = new JPanel(new GridBagLayout());
        topButtons = new JPanel(new GridLayout(0, boardSize));
        for (int i = 0; i < boardSize; ++i) {
            JButton button = new JButton("\u2191");
            button.setPreferredSize(new Dimension(50,50));
            button.addActionListener(new ButtonListener(i, moveDirection.UP));
            topButtons.add(button);
        }
        leftButtons = new JPanel(new GridLayout(boardSize, 0));
        for (int i = 0; i < boardSize; ++i) {
            JButton button = new JButton("\u2190");
            button.setPreferredSize(new Dimension(50,50));
            button.addActionListener(new ButtonListener(i, moveDirection.LEFT));
            leftButtons.add(button);
        }
        bottomButtons = new JPanel(new GridLayout(0, boardSize));
        for (int i = 0; i < boardSize; ++i) {
            JButton button = new JButton("\u2193");
            button.setPreferredSize(new Dimension(50,50));
            button.addActionListener(new ButtonListener(i, moveDirection.DOWN));
            bottomButtons.add(button);
        }
        rightButtons = new JPanel(new GridLayout(boardSize, 0));
        for (int i = 0; i < boardSize; ++i) {
            JButton button = new JButton("\u2192");
            button.setPreferredSize(new Dimension(50,50));
            button.addActionListener(new ButtonListener(i, moveDirection.RIGHT));
            rightButtons.add(button);
        }
        
        //-----------------CONTROL-TEST----------------
//        4x4-es tÃ¡blÃ¡hoz
//        buttons[0][0].setBackground(Color.RED);
//        buttons[1][0].setBackground(Color.GREEN);
//        buttons[2][0].setBackground(Color.BLUE);
//        buttons[3][0].setBackground(Color.WHITE);
//        buttons[0][1].setBackground(Color.RED);
//        buttons[1][1].setBackground(Color.GREEN);
//        buttons[2][1].setBackground(Color.BLUE);
//        buttons[3][1].setBackground(Color.WHITE);
//        buttons[0][2].setBackground(Color.RED);
//        buttons[1][2].setBackground(Color.GREEN);
//        buttons[2][2].setBackground(Color.BLUE);
//        buttons[3][2].setBackground(Color.WHITE);
//        buttons[0][3].setBackground(Color.RED);
//        buttons[1][3].setBackground(Color.GREEN);
//        buttons[2][3].setBackground(Color.BLUE);
//        buttons[3][3].setBackground(Color.WHITE);
        //----------------------------------------------
        
        // A tÃ¡bla Ã©s az irÃ¡nyÃ­tÃ³ gombok elhelyezÃ©se
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        boardPanel.add(innerPanel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        boardPanel.add(topButtons, gbc);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        boardPanel.add(leftButtons, gbc);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        boardPanel.add(rightButtons, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        boardPanel.add(bottomButtons, gbc);
    }
    
    public JPanel getBoardPanel() {
        return boardPanel;
    }
    
    private void shuffleBoard() {
        Collections.shuffle(coordinates);
        
        Color[] tileColorPalette = {Color.WHITE, Color.RED, Color.GREEN, Color.ORANGE, Color.BLUE, Color.YELLOW};
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                Point actPoint = coordinates.get(i*board.getBoardSize()+j);
                buttons[(int)actPoint.getX()][(int)actPoint.getY()].setBackground(tileColorPalette[i]);
                board.getField((int)actPoint.getX(), (int)actPoint.getY()).setColor(tileColorPalette[i]);
            }
        }
    }
    
    public int getStepCount() {
        return stepCount;
    }
    
    class ButtonListener implements ActionListener {
        
        private int dirBtnPos;
        private Directions dir;
        
        public ButtonListener(int dirBtnPos, Directions dir) {
            this.dirBtnPos = dirBtnPos;
            this.dir = dir;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Color tileColor;
            switch(dir) {
                case UP: 
                    tileColor = buttons[0][dirBtnPos].getBackground();
                    for (int i = 1; i < board.getBoardSize(); ++i) {
                        buttons[i-1][dirBtnPos].setBackground(buttons[i][dirBtnPos].getBackground());
                        board.getField(i-1, dirBtnPos).setColor(board.getField(i, dirBtnPos).getColor());
                    }
                    buttons[board.getBoardSize()-1][dirBtnPos].setBackground(tileColor);
                    board.getField(board.getBoardSize()-1, dirBtnPos).setColor(tileColor);
                    break;
                    
                case DOWN: 
                    tileColor = buttons[board.getBoardSize()-1][dirBtnPos].getBackground();
                    for (int i = board.getBoardSize()-2; i > -1; --i) {
                        buttons[i+1][dirBtnPos].setBackground(buttons[i][dirBtnPos].getBackground());
                        board.getField(i+1, dirBtnPos).setColor(board.getField(i, dirBtnPos).getColor());
                    }
                    buttons[0][dirBtnPos].setBackground(tileColor);
                    board.getField(0, dirBtnPos).setColor(tileColor);
                    break;
                    
                case LEFT: 
                    tileColor = buttons[dirBtnPos][0].getBackground();
                    for (int i = 1; i < board.getBoardSize(); ++i) {
                        buttons[dirBtnPos][i-1].setBackground(buttons[dirBtnPos][i].getBackground());
                        board.getField(dirBtnPos, i-1).setColor(board.getField(dirBtnPos, i).getColor());
                    }
                    buttons[dirBtnPos][board.getBoardSize()-1].setBackground(tileColor);
                    board.getField(dirBtnPos, board.getBoardSize()-1).setColor(tileColor);
                    break;
                    
                case RIGHT: 
                    tileColor = buttons[dirBtnPos][board.getBoardSize()-1].getBackground();
                    for (int i = board.getBoardSize()-2; i > -1; --i) {
                        buttons[dirBtnPos][i+1].setBackground(buttons[dirBtnPos][i].getBackground());
                        board.getField(dirBtnPos, i+1).setColor(board.getField(dirBtnPos, i).getColor());
                    }
                    buttons[dirBtnPos][0].setBackground(tileColor);
                    board.getField(dirBtnPos, 0).setColor(tileColor);
                    break;
            }
            stepCount++;
            if(board.isOver()) {
                Object[] options = {"Új játék", "Kilépés"};
                int option = JOptionPane.showOptionDialog(boardPanel,
                        stepCount + " lépéssel teljesí­tetted a játékot!",
                        "Gratulálok!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, options, null);
                
                if(option == JOptionPane.YES_OPTION) {
                    while(board.isOver()) {
                        System.out.println("Reshuffled");
                        shuffleBoard();
                    }
                    stepCount = 0;
                }
                if (option == JOptionPane.NO_OPTION){
                    System.exit(0);
                }
            }
        }
    }
}
