package TGME;

import java.util.ArrayList;

import TGME.Game.Game;

public class TGME {
    ArrayList<Game> games;
    ArrayList<Player> players;
    ArrayList<Player> currentPlayers;
    int numCurPlayers;

    public TGME() {
        // By default have no games and players
        games = new ArrayList<Game>();
        players = new ArrayList<Player>();
        currentPlayers = new ArrayList<Player>();
        numCurPlayers = 0;
    }

    public TGME(ArrayList<Game> g, ArrayList<Player> players, ArrayList<Player> current, int currentNum) {
        // Or can initialize with Games if already made
        this.games = g;
        this.players = players;
        currentPlayers = current;
        numCurPlayers = currentNum;
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

    public void endGame(Game game) {
        return;
    }

    public void displayGames() {
        return;
    }

    public Player login (String userID) {
        return new Player();
    }

    public Player logout (String userID) {
        return new Player();
    }
}

