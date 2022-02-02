/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author A titkos mikkentyű
 */
public class Board {
    
    private Field[][] board;
    private int boardSize;
    
    public Board(int boardSize){
        this.boardSize = boardSize;
        board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                board[i][j] = new Field();
            }
        }
    }
    
    public boolean isOver() {
        boolean maybeFinished = true;
        for (int i = 0; maybeFinished && i < this.boardSize; ++i) {
            for (int j = 1; maybeFinished && j < this.boardSize; ++j) {
                if (board[i][j].getColor() != board[i][j-1].getColor()) {maybeFinished = false;}
            }
        }
        if (!maybeFinished) {
            maybeFinished = true;
            for (int j = 0; maybeFinished && j < this.boardSize; ++j) {
                for (int i = 1; maybeFinished && i < this.boardSize; ++i) {
                    if (board[i][j].getColor().getRGB() != board[i-1][j].getColor().getRGB()) {maybeFinished = false;}
                }
            }
        }
        return maybeFinished;
    }
    
    public Field getField(int x, int y) {
        return board[x][y];
    }
    
    public int getBoardSize() {
        return boardSize;
    }
    
 }
