package TGME;

import TGME.Game.Game;

public class Player {
    private String userName;
    private String userColor;
    private Game currentGame;
    private int highScore;

    public Player(String name, String color) {
        userName = name;
        userColor = color;
        currentGame = null;
        highScore = 0;
    }

    public void displayHighScore() {
        return;
    }

    public void changeUsername() {
        return;
    }

    public void changeUserColor() {
        return;
    }
}
