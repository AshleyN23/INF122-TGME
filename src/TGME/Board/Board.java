package TGME.Board;

import java.lang.reflect.Array;
import java.util.*;

public class Board {
    private int numOfRow;
    private int numOfCol;
    private Piece[][] layout;
    private String game;

    public Board(int row, int col, String game) {
        numOfRow = row;
        numOfCol = col;
        layout = new Piece[col][row];
        this.game = game;
    }

    public void resetBoard() {
        layout = new Piece[this.numOfCol][this.numOfRow];  // reset the board to its original dimensions without pieces
    }

    public void updateBoard(int col, int row) {
        // assuming that move has been validated
        Piece currentPiece = layout[col][row];
        ArrayList<ArrayList<Piece>> matches = findMatches(); // finds matches horizontally and vertically

        while (matches.size() != 0){
            // remove the matching pieces
            removeMatches(matches);
            // move pieces to fill new spots
            movePieces();
            // find new matches
            matches = findMatches();
        }

        // refill board
        refillBoard();
    }

    public ArrayList<ArrayList<Piece>>  findMatches(){
        ArrayList<ArrayList<Piece>> matches = new ArrayList<>();

        for (int r = 0; r < numOfRow; r++){ // check horizontally
            ArrayList<Piece> temp = new ArrayList<>();
            temp.add(layout[r][0]);
            for (int c = 0; c < numOfCol - 1; c++){
                String currentPiece = layout[r][c].getName();
                String nextPiece = layout[r][c + 1].getName();
                if (currentPiece.equals(nextPiece)){
                    temp.add(layout[r][c]);
                }else{
                    if (temp.size() >= 3){
                        matches.add(temp);
                    }
                    temp = new ArrayList<>();
                    temp.add(layout[r][c]);
                }
            }

            if (temp.size() >= 3){
                matches.add(temp);
            }
        }

        for (int c = 0; c < numOfCol; c++) { // check vertically
            ArrayList<Piece> temp = new ArrayList<>();
            temp.add(layout[0][c]);
            for (int r = 0; r < numOfRow - 1; r++){
                String currentPiece = layout[r][c].getName();
                String nextPiece = layout[r + 1][c].getName();
                if (currentPiece.equals(nextPiece)){
                    temp.add(layout[r][c]);
                }else{
                    if (temp.size() >= 3){
                        matches.add(temp);
                    }
                    temp = new ArrayList<>();
                    temp.add(layout[r][c]);
                }
            }
            if (temp.size() >= 3){
                matches.add(temp);
            }
        }

        return matches;
    }

    public void removeMatches(ArrayList<ArrayList<Piece>> matches){
        for (ArrayList<Piece> list : matches){
            for (Piece p : list){
                int col = p.getPosition()[0];
                int row = p.getPosition()[1];
                layout[col][row] = null;
            }
        }
    }

    public void movePieces(){
        // use bubble sort to move null spaces to topmost position
        for (int c = 0; c < numOfCol; c++) {
            boolean swapped = false;
            for (int r = 0; r < numOfRow; r++){
                for (int j = 0; j < numOfRow - r - 1; j ++){
                    if (layout[j][c] == null && layout[j + 1][c] != null) {
                        layout[j][c] = layout[j + 1][c];
                        layout[j + 1][c] = null;
                        swapped = true;
                    }
                }
                if (!swapped)
                    break;
            }
        }

    }

    public void refillBoard() {
        Object[][] candyCrushPieceTypes = {
                {"jelly bean", 1, "red"},
                {"lozenge", 2, "orange"},
                {"lemon drop", 3, "yellow"},
                {"gum square", 4, "green"},
                {"lollipop head", 5, "blue"},
                {"jujube cluster", 6, "purple"},
                {"striped candy", 7, "pink"},
                {"wrapped candy", 8, "grey"},
                {"color bomb", 9, "black"}
        };

        Object[][] bejeweledPieceTypes = {
                {"red gem", 1, "red"},
                {"orange gem", 2, "orange"},
                {"yellow gem", 3, "yellow"},
                {"green gem", 4, "green"},
                {"blue gem", 5, "blue"},
                {"purple gem", 6, "purple"},
                {"white gem", 7, "white"}
        };


        if (game.equals("Candy Crush")){
            for (int r = 0; r < numOfRow; r ++) {
                for (int c = 0; c < numOfCol; c ++){
                    if (layout[r][c] == null){
                        Random random = new Random();
                        int index = random.nextInt(candyCrushPieceTypes.length);

                        String name = (String) candyCrushPieceTypes[index][0];
                        int value = (int) candyCrushPieceTypes[index][1];
                        int[] pos = new int[]{r, c};
                        String color = (String) candyCrushPieceTypes[index][2];

                        layout[r][c] = index < 7
                                ? new CCNormalPiece(name, color, pos, value)
                                : new CCPowerPiece(name, color, pos, value, "test desc");
                    }
                }
            }
        }

        if (game.equals("Bejeweled")){
            for (int r = 0; r < numOfRow; r ++) {
                for (int c = 0; c < numOfCol; c ++){
                    if (layout[r][c] == null){
                        Random random = new Random();
                        int index = random.nextInt(bejeweledPieceTypes.length);

                        String name = (String) bejeweledPieceTypes[index][0];
                        int value = (int) bejeweledPieceTypes[index][1];
                        int[] pos = new int[]{r, c};
                        String color = (String) bejeweledPieceTypes[index][2];
                        layout[r][c] = new BNormalPiece(name, color, pos, value);
                    }
                }
            }
        }


        return;
    }

}
