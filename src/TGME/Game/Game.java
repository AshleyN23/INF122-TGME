package TGME.Game;

import TGME.Board.Board;
import java.sql.Time;

public abstract class Game {
    private String gameName;
    private int maxMoves;
    private Time maxTime;
    private Board board;

    public Game(String name, int maxMove, Time time) {
        gameName = name;
        maxMoves = maxMove;
        maxTime = time;
        board = null;
    }

    // public void addGame(Game game) {
    //     return;
    // }

    // public void addPlayer(Player player) {
    //     return;
    // }
    

    // public void startGame(Game game) {
    //     return;
    // }
    
    public abstract void startGame();

    public String getGameName() {
        return this.gameName;
    }
}
