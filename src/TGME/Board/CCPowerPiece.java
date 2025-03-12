package TGME.Board;

import java.util.ArrayList;

public class CCPowerPiece extends Piece {

    private ArrayList<String> skills;
    public String description;

    public CCPowerPiece(String n, String c, int[] p, Board b, String desc) {
        super(n, c, p, b);
        description = desc;
        skills = new ArrayList<String>();
    }

    public void destroyPiecesInRow() {
        return;
    }

    public void destroyPiecesInColumn() {
        return;
    }

    public void destroyPiecesInSection() {
        return;
    }
    
}
