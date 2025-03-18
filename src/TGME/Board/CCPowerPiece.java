package TGME.Board;

import java.util.ArrayList;

public class CCPowerPiece extends Piece {

    private ArrayList<String> skills;
    public String description;

    public CCPowerPiece(String n, String c, int[] p, int v, String desc) {
        super(n, c, p, v);
        description = desc;
        skills = new ArrayList<String>();
    }

    
}
