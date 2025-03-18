package TGME.Game;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import TGME.Board.Board;
import TGME.Board.Piece;

public class Bejeweled extends SwipingGame{ //initally extends Game
    private Timer timer;
    private boolean timeMode;  // true = timed, false = untimed
    private boolean multiplayerMode;  // true = allows bombs
    private ArrayList<Piece> gemsTypes;
   
    public Bejeweled( int targetScore, Board board, int timeInSeconds, boolean timeMode, boolean multiplayerMode) {
        super(board, targetScore);
        this.timeMode = timeMode;
        this.multiplayerMode = multiplayerMode;
        gemsTypes = new ArrayList<Piece>();
        // Add Gem types here

        if (timeMode) {
            startTimer(timeInSeconds);
        }
    }

    private void startTimer(int timeInSeconds) {
        timer = new Timer();
        System.out.println("⏳ Game will last " + timeInSeconds + " seconds.");
    
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time’s up! Game Over.");
                timer.cancel();
            }
        }, timeInSeconds * 1000L); // Convert seconds to milliseconds
    }

    public void updateBoard(int col, int row) {
        System.out.println("Updating board after swipe...");
        board.updateBoard(col, row); // Calls Board’s update logic

        // Check if a bomb should be activated in multiplayer mode
        if (multiplayerMode && shouldTriggerBomb()) {
            activateBomb(col, row);
        }

        if (reachedTargetScore()) {
            System.out.println("You won!");
            if (timeMode) {
                timer.cancel();  // Stop the timer if the player wins early
            }
        }
    }
    
    private boolean shouldTriggerBomb() {
        return Math.random() < 0.1; // 10% chance after a move
    }

    private void activateBomb(int col, int row) {
        System.out.println("Bomb activated at (" + col + ", " + row + ")!");
        // Bomb logic here
    }
}
