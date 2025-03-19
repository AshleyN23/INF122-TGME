package TGME.Game;

import TGME.Board.Board;
import TGME.Board.Piece;

public abstract class SwipingGame extends Game{
    int score, targetScore;
    Board board;
    int movesLeft;

    public SwipingGame(Board board, int targetScore , String name) {
        super(name, board);
        this.score = 0;
        this.board = board;
    }

    abstract void updateBoard(int col, int row); //for subclasses to implement

    // call updateBoard(moveType)
    public void swipeLeft(int row, int col) {
        if (col > 0) 
        {  
            swapPieces(row, col, row, col - 1);
            updateBoard(col - 1, row);
        }
    }

    public void swipeRight(int row, int col) {
        if (col < board.getNumOfCol() - 1) 
        { 
            swapPieces(row, col, row, col + 1);
            updateBoard(col + 1, row);
        }
    }

    public void swipeUp(int row, int col) {
        if (row > 0) 
        {
            swapPieces(row, col, row - 1, col);
            updateBoard(col, row - 1);
        }
    }

    public void swipeDown(int row, int col) {
        if (row < board.getNumOfRow() - 1) 
        {  
            swapPieces(row, col, row + 1, col);
            updateBoard(col, row + 1);
        }
    }

    private void swapPieces(int row1, int col1, int row2, int col2) {
        Piece[][] layout = board.getLayout();
        Piece temp = layout[col1][row1];
        layout[col1][row1] = layout[col2][row2];
        layout[col2][row2] = temp;
    }

    public void addPoints(int points) {
        score += points;
        System.out.println("Your score is now " + score + "!");
        reachedTargetScore();
    }

    public boolean reachedTargetScore() {
        if (score >= targetScore) {
            System.out.println("You have reached the target score of " + targetScore + "!");
        }
        else {
            System.out.println("Your score is now " + score + " and the target score is " + targetScore + ". Keep going!");
        }
        return score >= targetScore;
    }

        //Handles logic in Board class alr
    // public void doesMatch(){
    //     return;
    // }

    // public void fillSpace() {
    //     return;
    // }

}
