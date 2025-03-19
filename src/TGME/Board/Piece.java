package TGME.Board;

public abstract class Piece {
    private String name;
    private String color;
    private int[] position; // row, col
    private int value;
    private Board board;

    public Piece(String n, String c, int[] p, int v) {
        name = n;
        color = c;
        position = p; //ex. [1, 2]
        value = v;
    }

    public char showPiece() {
        return color.charAt(0);
    }

    public void updatePosition(int row, int col) {
        this.position = new int[]{row, col};
    }

    public String getName(){
        return this.name;
    }

    public int[] getPosition(){
        return this.position;
    }
}
