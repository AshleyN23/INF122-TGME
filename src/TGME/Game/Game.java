package TGME.Game;

import java.sql.Time;

import TGME.Player;
import TGME.Board.Board;

public class Game {
    private String gameName;
    private int maxMoves;
    private Time maxTime;
    private Board board;

    public Game(String name, int maxMove, Time time) {
        gameName = name;
        maxMoves = maxMove;
        maxTime = time;
        board = null; // pass in the type of game into the board
    }

    public void addGame(Game game) {
        return;
    }

    public void addPlayer(Player player) {
        return;
    }

    public void startGame(Game game) {
        return;
    }

    public String getGameName() {
        return "";
    }
}
