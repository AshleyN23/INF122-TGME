package TGME.Board;

import java.lang.reflect.Array;

public class Board {
    private int numOfRow;
    private int numOfCol;
    private Array[][] layout;

    public Board(int row, int col) {
        numOfRow = row;
        numOfCol = col;
        // layout = Array[][];
    }

    // public Piece initPieces(){
    //     return new Piece("", "", [1,2], this);
    // }

    public void resetBoard() {
        return;
    }

    public void updateBoard() {
        return;
    }

    public void refillBoard() {
        return;
    }
    
}
