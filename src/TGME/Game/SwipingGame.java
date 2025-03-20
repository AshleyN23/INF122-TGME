package TGME.Game;

import TGME.Board.Board;
import TGME.Board.Piece;

public abstract class SwipingGame extends Game{
    int score;
    Board board;
    int movesLeft;

    public SwipingGame(Board board, String name) {
        super(name, board);
        this.score = 0;
        this.board = board;
    }

    abstract int updateBoard(int col, int row); //for subclasses to implement

    // call updateBoard(moveType)
    public int swipeLeft(int row, int col) {
        if (col >= 0) 
        {  
            swapPieces(row, col, row - 1, col);
            return updateBoard(col, row - 1);
        }
        return 0;
    }

    public int swipeRight(int row, int col) {
        if (col <= board.getNumOfCol() - 1) 
        { 
            swapPieces(row, col, row + 1, col);
            return updateBoard(col, row + 1);
        }
        return 0;
    }

    public int swipeUp(int row, int col) {
        if (row >= 0) 
        {
            swapPieces(row, col, row, col - 1);
            return updateBoard(col - 1, row);
        }
        return 0;
    }

    public int swipeDown(int row, int col) {
        if (row <= board.getNumOfRow() - 1) 
        {  
            swapPieces(row, col, row, col + 1);
            return updateBoard(col + 1, row);
        }
        return 0;
    }

    private void swapPieces(int row1, int col1, int row2, int col2) {
        Piece[][] layout = board.getLayout();
        Piece temp = layout[col1][row1];
        layout[col1][row1] = layout[col2][row2];
        layout[col2][row2] = temp;
    }

    // public void addPoints(int points) {
    //     score += points;
    //     System.out.println("Your score is now " + score + "!");
    //     reachedTargetScore();
    // }

    // public boolean reachedTargetScore() {
    //     if (score >= targetScore) {
    //         System.out.println("You have reached the target score of " + targetScore + "!");
    //     }
    //     else {
    //         System.out.println("Your score is now " + score + " and the target score is " + targetScore + ". Keep going!");
    //     }
    //     return score >= targetScore;
    // }

        //Handles logic in Board class alr
    // public void doesMatch(){
    //     return;
    // }

    // public void fillSpace() {
    //     return;
    // }

}
