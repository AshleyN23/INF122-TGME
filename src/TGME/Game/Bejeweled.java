package TGME.Game;

import TGME.Board.Board;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Bejeweled extends SwipingGame{ //initally extends Game
    private Timer timer;
    private boolean timeMode;  // true = timed, false = untimed
    private boolean gameStart;
   
    public Bejeweled( int targetScore, Board board, int timeInSeconds, boolean timeMode) {
        super(board, "Bejeweled");
        this.timeMode = timeMode;

        if (timeMode) {
            gameStart = true;
            startTimer(timeInSeconds);
        }
    }

    private void startTimer(int timeInSeconds) {
        timer = new Timer();
        System.out.println("Game will last " + timeInSeconds + " seconds.");
    
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time is up! Game Over. Type 'End' to complete your turn.");
                timer.cancel();
                gameStart = false;
            }
        }, timeInSeconds * 1000L); // Convert seconds to milliseconds
    }

    public int updateBoard(int col, int row) {
        int score = 0;
        System.out.println("Updating board after swipe...");
        score += super.board.updateBoard(col, row); // Calls Board’s update logic
        return score;
    }


    public int startGame() {
        Scanner scanner = new Scanner(System.in);
        if (gameStart) {
            System.out.println("GAME START!!\n Swipe two pieces by typing what piece you want to switch and in what direction.\n Ex. 1 2 Down, Swaps the piece at row 1 column 2 with the piece below.");
        }
        while (gameStart) {
            System.out.println("Your Score: " + super.score);
            super.board.printBoard();
            System.out.println("Next Move.");
            String[] playerMove = scanner.nextLine().toLowerCase().split(" ");
            if (playerMove[0].equals("End")) {
                break;
            } else if (playerMove[2].equals("down")) {
                super.score += super.swipeDown(Integer.parseInt(playerMove[1]) - 1, Integer.parseInt(playerMove[0]) - 1);
            } else if (playerMove[2].equals("up")) {
                super.score += super.swipeUp(Integer.parseInt(playerMove[1]) - 1, Integer.parseInt(playerMove[0]) - 1);
            } else if (playerMove[2].equals("left")) {
                super.score += super.swipeLeft(Integer.parseInt(playerMove[1]) - 1, Integer.parseInt(playerMove[0]) - 1);
            } else if (playerMove[2].equals("right")) {
                super.score += super.swipeRight(Integer.parseInt(playerMove[1]) - 1, Integer.parseInt(playerMove[0]) - 1);        
            } else {
                System.out.println("Invalid Input. Try Again.");
            }
        }
        System.out.println("Your Final Score: " + super.score);
        return super.score;
    }
}
