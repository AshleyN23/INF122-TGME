package TGME.Game;

import TGME.Board.Board;

public abstract class Game {
    private String gameName;
    private Board board;

    public Game(String name, Board board) {
        gameName = name;
        this.board = board;
    }
    
    public abstract int startGame();

    public String getGameName() {
        return this.gameName;
    }
}
