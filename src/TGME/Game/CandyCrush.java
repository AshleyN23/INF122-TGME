package TGME.Game;

import java.util.ArrayList;
import java.util.Scanner;

import TGME.Board.Piece;
import TGME.Board.Board;

public class CandyCrush extends SwipingGame { // extends SwipingGame??? 

    int movesLeft;
    ArrayList<Piece> candyTypes;
    int score, targetScore;
    
    public CandyCrush(int maxMove, int targetScore, Board board) {
        super(board, targetScore, "Candy Crush");
        this.movesLeft = maxMove;
        this.candyTypes = new ArrayList<Piece>();
        // Add Gem types here
        // update 3/17 added some gem types in the board class
        startGame();
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

    public int startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("GAME START!!\n Swipe two pieces by typing what piece you want to switch and in what direction.\n Ex. 1 2 Down, Swaps the piece at row 1 column 2 with the piece below.");
        while (movesLeft != 0) {
            super.board.printBoard();
            String[] playerMove = scanner.nextLine().split(" ");
            if (playerMove[2].equals("Down")) {
                super.swipeDown(Integer.parseInt(playerMove[0]), Integer.parseInt(playerMove[1]));
            } else if (playerMove[2].equals("Up")) {
                super.swipeUp(Integer.parseInt(playerMove[0]), Integer.parseInt(playerMove[1]));
            } else if (playerMove[2].equals("Left")) {
                super.swipeLeft(Integer.parseInt(playerMove[0]), Integer.parseInt(playerMove[1]));
            } else if (playerMove[2].equals("Right")) {
                super.swipeRight(Integer.parseInt(playerMove[0]), Integer.parseInt(playerMove[1]));
            
            } else {
                System.out.println("Invalid Input. Try Again.");
            }
            // 
        }
        System.out.println("Out of Moves!");
        System.out.println("Your Score: " + super.score);
        return super.score;
    }
}
