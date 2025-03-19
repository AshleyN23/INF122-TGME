package TGME.Board;

import java.util.ArrayList;

public class CCPowerPiece extends Piece {

    public String description;

    public CCPowerPiece(String n, String c, int[] p, int v, String desc) {
        super(n, c, p, v);
        description = desc;
    }

    private void destroyPiecesVertical() {
        return;
    }

    private void destroyPiecesHorizont() {
        return;
    }

    
}
