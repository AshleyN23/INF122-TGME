package TGME.Board;

public class CCNormalPiece extends Piece{

    public CCNormalPiece(String n, String c, int[][] p, Board b) {
        super(n, c, p, b);
    }

    public void is4InRow() {
        return;
    }

    public void is3InRow() {
        return;
    }

    private void destroyPiecesVertical() {
        return;
    }

    private void destroyPiecesHorizont() {
        return;
    }
}
