package TGME.Game;

import java.sql.Time;
import java.util.ArrayList;

import TGME.Board.Piece;
import TGME.Board.Board;

public class CandyCrush extends SwipingGame { // extends SwipingGame??? 

    int movesLeft;
    ArrayList<Piece> candyTypes;
    int score, targetScore;
    
    public CandyCrush(int maxMove, int targetScore, Board board) {
        super(board, targetScore);  // Call SwipingGame constructor
        this.movesLeft = maxMove;
        this.candyTypes = new ArrayList<Piece>();
        // Add Gem types here
        // update 3/17 added some gem types in the board class
    }

    void updateBoard(int col, int row) {
        if (movesLeft <= 0) {
            System.out.println("No more moves left!");
            return;
        }

        movesLeft--;

        System.out.println("Updating board after swipe...");
        board.updateBoard(col, row); // update the board after a swipe

        if (reachedTargetScore()) {
            System.out.println("You won!");
        }
    }

    //not sure where we are gon use these

    // private void doesMatchMore() {
    //     return;
    // }

    // public void clearSection() {
    //     return;
    // }

    // public void reachedScore() {
    //     return;
    // }

}
