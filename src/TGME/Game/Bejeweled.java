package TGME.Game;

import java.sql.Time;
import java.util.ArrayList;

import TGME.Board.Piece;

public class Bejeweled extends Game{
    Time timeLeft;
    private ArrayList<Piece> gemsTypes;

    public Bejeweled(String name, int maxMove, Time time) {
        super(name, maxMove, time);
        timeLeft = time;
        gemsTypes = new ArrayList<Piece>();
        // Add Gem types here
    }
    
}
