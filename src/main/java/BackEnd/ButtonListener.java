/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author A titkos mikkentyű
 */
public class ButtonListener implements ActionListener{
    
    private JButton[][] tileMatrix;
    private int dirBtnPos;
    private Directions dir;

    public ButtonListener(JButton[][] tileMatrix, int dirBtnPos, Directions dir) {
        this.tileMatrix = tileMatrix;
        this.dirBtnPos = dirBtnPos;
        this.dir = dir;
    }
    
    public ButtonListener(JButton[][] tileMatrix, int dirBtnPos) {
        this.tileMatrix = tileMatrix;
        this.dirBtnPos = dirBtnPos;
        this.dir = null;
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            switch(dir) {
                case UP : 
            }
//            Color btnClr = tileMatrix[0][dirBtnPos].getBackground();
//            for (int i = 1; i < 4; ++i) {
//                tileMatrix[i-1][dirBtnPos].setBackground(tileMatrix[i][dirBtnPos].getBackground());
//            }
//            tileMatrix[3][dirBtnPos].setBackground(btnClr);
        }
}
