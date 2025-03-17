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

        System.out.println(this.userName + "'S HIGHSCORE: " + this.highScore);
        return;
    }

    public void changeUsername(String newName) {
        this.userName = newName;
        System.out.println("NEW USERNAME SET TO: " + this.userName);
        return;
    }

    public void changeUserColor(String newColor) {
        this.userColor = newColor;
        System.out.println("NEW USERCOLOR SET TO: " + this.userColor);
        return;
    }

    public void changeCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
        return;
    }

    public void updateHighScore(int score) {
        if (score > this.highScore) {
            this.highScore = score;
        }
        return;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserColor() {
        return this.userColor;
    }

    public static void main(String[] args) {

    }
}
