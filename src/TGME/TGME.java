package TGME;

import java.util.ArrayList;

import TGME.Board.Board;
import TGME.Game.Bejeweled;
import TGME.Game.CandyCrush;
import java.util.Scanner;

public class TGME {
    ArrayList<String> games;
    ArrayList<Player> players;
    ArrayList<Player> currentPlayers;
    int numCurPlayers;

    public TGME() {
        // By default have no games and players
        games = new ArrayList<String>();
        players = new ArrayList<Player>();
        currentPlayers = new ArrayList<Player>();
        numCurPlayers = 0;
        TMGEStart();
    }

    public TGME(ArrayList<String> g, ArrayList<Player> players, ArrayList<Player> current, int currentNum) {
        // Or can initialize with Games if already made
        this.games = g;
        this.players = players;
        currentPlayers = current;
        numCurPlayers = currentNum;
        TMGEStart();
    }

    public void addGame(String game) {
        this.games.add(game);
        return;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        return;
    }

    public int startSelectedGame(String gameName, Player p) {
        int score = 0;
        if (gameName.equals("Bejeweled")) {
            Board b = new Board(8, 8, "Bejeweled");
            Bejeweled g = new Bejeweled(1000, b, 30, true);
            score = g.startGame();
        } else if (gameName.equals("Candy Crush")) {
            Board b = new Board(9, 9, "Candy Crush");
            CandyCrush g = new CandyCrush(10, 2000, b);
            score = g.startGame();
        }
        return score;
    }

    public void endProgram() {
        System.exit(0);
        return;
    }

    public void displayGames() {
        System.out.println("PLEASE SELECT A GAME");
        for (int i = 0; i < this.games.size(); i++) {
            System.out.println(i + ": " + this.games.get(i));
        }
        return;
    }

    public Player login(String userID) {
        Player player = null;
        for (int i = 0; i < this.numCurPlayers; i++) {
            player = this.currentPlayers.get(i);
            if (userID.equals(player.getUserName())) {
                this.currentPlayers.add(player);
                this.numCurPlayers += 1;
                return player;
            }
        }

        player = new Player(userID, null);
        this.currentPlayers.add(player);
        this.numCurPlayers += 1;
        return player;
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

    public ArrayList<Player> getCurrentPlayers() {
        return this.currentPlayers;
    }

    private void TMGEStart() {
        Scanner scanner = new Scanner(System.in);
        players.add(new Player("Mario", "Red"));
        players.add(new Player("Luigi", "Blue"));
        System.out.println("PLEASE SELECT PLAYER 1: ");
        for (Player p : players) {
            System.out.println(p.getUserName());
        }
        String p1name = scanner.nextLine();

        System.out.println("PLEASE SELECT PLAYER 2 (if singleplayer type NONE): ");
        for (Player p : players) {
            if (!p.getUserName().equals(p1name))  {
                System.out.println(p.getUserName());
            }
        }
        String p2name = scanner.nextLine();

        Player p1 = login(p1name);
        Player p2 = null;
        if (!p2name.equals("NONE")) {
            p2 = login(p2name);
        }

        games.add("Bejeweled");
        games.add("Candy Crush");
        displayGames();

        String gameSelected = scanner.nextLine();
        System.out.println(p1name + " plays first!");
        int p1Score = startSelectedGame(gameSelected, p1);
        p1.updateHighScore(p1Score);

        System.out.println(p2name + "'s Turn");
        int p2Score = startSelectedGame(gameSelected, p2);
        p2.updateHighScore(p2Score);

        System.out.println(p1name + "'s Score: " + p1Score);
        System.out.println(p2name + "'s Score: " + p2Score);

        if (p1Score > p2Score){
            System.out.println(p1name + " Wins!");
        } else if (p1Score < p2Score) {
            System.out.println(p2name + " Wins!");
        } else {
            System.out.println("Games Tied! Try Again.");
        }
        scanner.close();
        endProgram();
    }
}
