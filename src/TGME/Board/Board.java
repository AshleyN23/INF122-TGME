package TGME.Board;


public class Board {
    private int numOfRow;
    private int numOfCol;
    private Piece[][] layout;

    public Board(int row, int col) {
        numOfRow = row;
        numOfCol = col;
        // layout = Array[][];
        layout = new Piece[col][row];
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
