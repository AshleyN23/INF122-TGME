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
        this.games.add(game);
        return;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        return;
    }

    public void startGame(Game game) {
        return;
    }

    public void endGame(Game game) {
        return;
    }

    public void displayGames() {
        System.out.println("PLEASE SELECT A GAME");
        for (int i = 0; i < this.games.size(); i++) {
            System.out.println(i + ": " + this.games.get(i).toString());
        }
        return;
    }

    public Player login(String userID) {
        Player newUser = new Player(userID, "");
        this.currentPlayers.add(newUser);
        this.numCurPlayers += 1;

        return newUser;
    }

    public Player logout(String userID) {
        Player player = null;
        for (int i = 0; i < this.numCurPlayers; i++) {
            player = this.currentPlayers.get(i);
            if (userID.equals(player.getUserName())) {
                this.currentPlayers.remove(i);
                this.numCurPlayers -= 1;
                break;
            }
        }
        return player;
    }
}
