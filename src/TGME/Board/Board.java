package TGME.Board;

import java.util.ArrayList;

public class Board {
    private int numOfRow;
    private int numOfCol;
    private Piece[][] layout;

    public Board(int row, int col) {
        numOfRow = row;
        numOfCol = col;
        layout = new Piece[col][row];
    }

    public void resetBoard() {
        layout = new Piece[this.numOfCol][this.numOfRow];  // reset the board to its original dimensions without pieces
    }

    public void updateBoard(int col, int row, String move) {
        // assuming that move has been validated
        Piece currentPiece = layout[col][row];
        ArrayList<Piece> matches = new ArrayList<>();
        int previousRow = -1;
        int previousCol = -1;

        switch (move) {
            case "left" -> {
                for (int i = col; i >= 0; i--) {
                    if (layout[row][i].getName().equals(currentPiece.getName())) {
                        matches.add(layout[row][i]);
                        previousRow = row;
                        previousCol = i;
                    } else {
                        break;
                    }
                }
            }
            case "right" -> {
                for (int i = col; i < numOfCol; i++) {
                    if (layout[row][i].getName().equals(currentPiece.getName())) {
                        matches.add(layout[row][i]);
                        previousRow = row;
                        previousCol = i;
                    } else {
                        break;
                    }
                }
            }
            case "up" -> {
                for (int i = row; i >= 0; i--) {
                    if (layout[i][col].getName().equals(currentPiece.getName())) {
                        matches.add(layout[i][col]);
                        previousRow = i;
                        previousCol = col;
                    } else {
                        break;
                    }
                }
            }
            case "down" -> {
                for (int i = row; i < numOfRow; i++)
                    if (layout[i][col].getName().equals(currentPiece.getName())) {
                        matches.add(layout[i][col]);
                        previousRow = i;
                        previousCol = col;
                    } else {
                        break;
                    }
                }
            }

        // remove the matching pieces
        removeMatches(matches);
        // move current piece to correct position
        currentPiece.updatePosition(previousCol, previousRow);
        layout[previousRow][previousCol] = currentPiece;
        // refill board
        refillBoard();
    }

    public void removeMatches(ArrayList<Piece> matches){
        // check if its 3 or 4 matches and the type
        for (Piece p : matches){
            int col = p.getPosition()[0];
            int row = p.getPosition()[1];
            layout[col][row] = null;
        }

    }

    public void refillBoard() {
        // iterate over board and add in random new pieces
        return;
    }

}
