/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

/**
 *
 * @author A titkos mikkentyÅ±
 */
public class RubikTableGUI {
    
    private JFrame table;
    private BoardGUI boardGUI;
    private final int INIT_BOARD_SIZE = 4;

    public RubikTableGUI() {
        table = new JFrame("Rubik's Table");
        table.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        table.setResizable(false);
        
        
        boardGUI = new BoardGUI(INIT_BOARD_SIZE);
        table.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        table.setJMenuBar(menuBar);
        JMenu game = new JMenu("Játék");
        menuBar.add(game);
        JMenu newGame = new JMenu("Új játék");
        game.add(newGame);
        int boardSizes[] = {2, 4, 6};
        for (int boardSize : boardSizes) {
            JMenuItem sizeMenuItem = new JMenuItem(boardSize + "x" + boardSize);
            newGame.add(sizeMenuItem);
            sizeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    table.getContentPane().remove(boardGUI.getBoardPanel());
                    boardGUI = new BoardGUI(boardSize);
                    table.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
                    table.pack();
                }
            });
        }
        JMenuItem exitGame = new JMenuItem("Kilépés");
        game.add(exitGame);
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        table.pack();
        table.setLocationRelativeTo(null);
        table.setVisible(true);
    }
}
