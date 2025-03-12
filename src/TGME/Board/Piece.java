package TGME.Board;

public abstract class Piece {
    private String name;
    private String color;
    private int[] position;
    private int value;
    private Board board;
    public Piece leftNeighbor;
    public Piece rightNeighbor;
    public Piece upNeighbor;
    public Piece downNeighbor;

    public Piece(String n, String c, int[] p, Board b) {
        name = n;
        color = c;
        position = p; //ex. [1, 2] 
        board = b;
    }

    public void showPiece() {
        return;
    }

    public void move(int x, int y) {
        return;
    }
    
}
