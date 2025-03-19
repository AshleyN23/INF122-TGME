package TGME.Game;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import TGME.Board.Board;
import TGME.Board.Piece;

public class Bejeweled extends SwipingGame{ //initally extends Game
    private Timer timer;
    private boolean timeMode;  // true = timed, false = untimed
    private boolean multiplayerMode;  // true = allows bombs
    private ArrayList<Piece> gemsTypes;
    private boolean gameStart;
   
    public Bejeweled( int targetScore, Board board, int timeInSeconds, boolean timeMode, boolean multiplayerMode) {
        super(board, targetScore, "Bejeweled");
        this.timeMode = timeMode;
        this.multiplayerMode = multiplayerMode;
        gemsTypes = new ArrayList<Piece>();
        // Add Gem types here

        if (timeMode) {
            gameStart = true;
            startTimer(timeInSeconds);
        }
        startGame();
    }

    private void startTimer(int timeInSeconds) {
        timer = new Timer();
        System.out.println("⏳ Game will last " + timeInSeconds + " seconds.");
    
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time’s up! Game Over.");
                timer.cancel();
                gameStart = false;
            }
        }, timeInSeconds * 1000L); // Convert seconds to milliseconds
    }

    public int updateBoard(int col, int row) {
        int score = 0;
        System.out.println("Updating board after swipe...");
        score += super.board.updateBoard(col, row); // Calls Board’s update logic

        // Check if a bomb should be activated in multiplayer mode
        if (multiplayerMode && shouldTriggerBomb()) {
            activateBomb(col, row);
            score += super.board.updateBoard(col, row);
        }

        if (reachedTargetScore()) {
            System.out.println("You won!");
            if (timeMode) {
                timer.cancel();  // Stop the timer if the player wins early
                gameStart = false;
            }
        }
        return score;
    }
    
    private boolean shouldTriggerBomb() {
        return Math.random() < 0.1; // 10% chance after a move
    }

    private void activateBomb(int col, int row) {
        System.out.println("Bomb activated at (" + col + ", " + row + ")!");
        // Bomb logic here
    }

    public int startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("GAME START!!\n Swipe two pieces by typing what piece you want to switch and in what direction.\n Ex. 1 2 Down, Swaps the piece at row 1 column 2 with the piece below.");
        while (gameStart) {
            System.out.println("Your Score: " + super.score);
            super.board.printBoard();
            System.out.println("Next Move.");
            String[] playerMove = scanner.nextLine().split(" ");
            if (playerMove[2].equals("Down")) {
                super.score += super.swipeDown(Integer.parseInt(playerMove[0]) - 1, Integer.parseInt(playerMove[1]) - 1);
            } else if (playerMove[2].equals("Up")) {
                super.score += super.swipeUp(Integer.parseInt(playerMove[0]) - 1, Integer.parseInt(playerMove[1]) - 1);
            } else if (playerMove[2].equals("Left")) {
                super.score += super.swipeLeft(Integer.parseInt(playerMove[0]) - 1, Integer.parseInt(playerMove[1]) - 1);
            } else if (playerMove[2].equals("Right")) {
                super.score += super.swipeRight(Integer.parseInt(playerMove[0]) - 1, Integer.parseInt(playerMove[1]) - 1);        
            } else {
                System.out.println("Invalid Input. Try Again.");
            }
        }
        System.out.println("Your Final Score: " + super.score);
        return super.score;
    }
}
