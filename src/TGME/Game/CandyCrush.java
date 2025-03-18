package TGME.Game;

import java.sql.Time;
import java.util.ArrayList;

import TGME.Board.Piece;

public class CandyCrush extends Game {

    int movesLeft;
    ArrayList<Piece> candyTypes;
    
    public CandyCrush(String name, int maxMove, Time time) {
        super(name, maxMove, time);
        movesLeft = maxMove;
        candyTypes = new ArrayList<Piece>();
        // Add Gem types here
        // update 3/17 added some gem types in the board class
    }

    private void doesMatchMore() {
        return;
    }

    public void clearSection() {
        return;
    }

    public void reachedScore() {
        return;
    }


    
}
