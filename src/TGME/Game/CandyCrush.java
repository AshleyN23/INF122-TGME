package TGME.Game;

import java.util.Scanner;
import TGME.Board.Board;

public class CandyCrush extends SwipingGame { // extends SwipingGame??? 

    int movesLeft;
    
    public CandyCrush(int maxMove, int targetScore, Board board) {
        super(board, "Candy Crush");
        this.movesLeft = maxMove;
    }

    public int updateBoard(int col, int row) {
        int score = 0;
        if (movesLeft <= 0) {
            System.out.println("No more moves left!");
            return score;
        }

        movesLeft--;

        System.out.println("Updating board after swipe...");
        score += super.board.updateBoard(col, row); // update the board after a swipe
        return score;
    }

    public int startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("GAME START!!\n Swipe two pieces by typing what piece you want to switch and in what direction.\n Ex. 1 2 Down, Swaps the piece at row 1 column 2 with the piece below.");
        while (movesLeft > 0) {
            System.out.println("Your Score: " + super.score);
            System.out.println("Moves Left: " + movesLeft);
            super.board.printBoard();
            System.out.println("Next Move.");
            String[] playerMove = scanner.nextLine().toLowerCase().split(" ");
            if (playerMove[0].equals("end")) {
                break;
            } else if (playerMove[2].toLowerCase().equals("down")) {
                super.score += super.swipeDown(Integer.parseInt(playerMove[1]) - 1, Integer.parseInt(playerMove[0]) - 1);
            } else if (playerMove[2].toLowerCase().equals("up")) {
                super.score += super.swipeUp(Integer.parseInt(playerMove[1]) - 1, Integer.parseInt(playerMove[0]) - 1);
            } else if (playerMove[2].toLowerCase().equals("left")) {
                super.score += super.swipeLeft(Integer.parseInt(playerMove[1]) - 1, Integer.parseInt(playerMove[0]) - 1);
            } else if (playerMove[2].toLowerCase().equals("right")) {
                super.score += super.swipeRight(Integer.parseInt(playerMove[1]) - 1, Integer.parseInt(playerMove[0]) - 1);        
            } else {
                System.out.println("Invalid Input. Try Again.");
            }
        }
        System.out.println("Out of Moves!");
        System.out.println("Your Final Score: " + super.score);
        return super.score;
    }
}
