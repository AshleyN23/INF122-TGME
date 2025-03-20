package TGME.Board;

import java.util.*;

public class Board {
    private int numOfRow;
    private int numOfCol;
    private Piece[][] layout;
    private String game;

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

    public Board(int row, int col, String game) {
        numOfRow = row;
        numOfCol = col;
        layout = new Piece[col][row];
        this.game = game;
        refillBoard();
        updateBoard(0, 0);
    }

    public Piece[][] getLayout() {
        return layout;
    }

    public void printBoard() {
        System.out.print("   ");
        for (int col = 1; col <= layout[0].length; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        System.out.print("  -");
        for (int col = 0; col < layout[0].length; col++) {
            System.out.print("--");
        }
        System.out.println("-");

        for (int row = 0; row < layout.length; row++) {
            System.out.print((row + 1) + " |");
            for (int col = 0; col < layout[row].length; col++) {
                System.out.print(layout[row][col].showPiece() + " ");
            }
            System.out.println("|");
        }

        System.out.print("  -");
        for (int col = 0; col < layout[0].length; col++) {
            System.out.print("--");
        }
        System.out.println("-");
    }


    public void resetBoard() {
        layout = new Piece[this.numOfCol][this.numOfRow];  // reset the board to its original dimensions without pieces
    }

    public int updateBoard(int col, int row) {
        // assuming that move has been validated
        Piece currentPiece = layout[col][row];
        ArrayList<ArrayList<Piece>> matches = findMatches(); // finds matches horizontally and vertically
        int score = 0;

        while (matches.size() != 0){
            // remove the matching pieces
            score += removeMatches(matches);
            // move pieces to fill new spots
            movePieces();
            // refill blank spots
            refillBoard();
            // find new matches
            matches = findMatches();
        }

        // refill board
        refillBoard();
        return score;
    }

    public ArrayList<ArrayList<Piece>>  findMatches(){
        ArrayList<ArrayList<Piece>> matches = new ArrayList<>();

        for (int r = 0; r < numOfRow - 1; r++){ // check horizontally
            ArrayList<Piece> temp = new ArrayList<>();
            temp.add(layout[r][0]);

            for (int c = 1; c < numOfCol - 1; c++){ // should this be numOfCol-1
                String currentPiece = layout[r][c].getName();
                String nextPiece = layout[r][c + 1].getName();
                if ((nextPiece != null && currentPiece != null) && currentPiece.equals(nextPiece)){
                    temp.add(layout[r][c+1]);
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

        for (int c = 0; c < numOfCol - 1; c++) { // check vertically
            ArrayList<Piece> temp = new ArrayList<>();
            temp.add(layout[0][c]);
            for (int r = 0; r < numOfRow - 1; r++){
                String currentPiece = layout[r][c].getName();
                String nextPiece = layout[r + 1][c].getName();
                if ((nextPiece != null && currentPiece != null) && currentPiece.equals(nextPiece)){
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

    public int removeMatches(ArrayList<ArrayList<Piece>> matches){
        int score = 0;
        for (ArrayList<Piece> list : matches){
            for (Piece p : list){
                int col = p.getPosition()[0];
                int row = p.getPosition()[1];
                layout[col][row] = null;
                score += 200;
            }
        }
        int val = matches.get(0).get(0).getValue(); // gets the value of the first matched piece
        int r = matches.get(0).get(0).getPosition()[0]; // gets the row of the first matched piece
        int c = matches.get(0).get(0).getPosition()[1]; // gets the column of the first matched piece
        if (game.equals("Candy Crush")){
            String name;
            String color;
            int[] pos;
            int value;
            if(val <= 8){ // this will get the info of the next stronger value piece
                name = (String) candyCrushPieceTypes[val][0]; 
                color = (String) candyCrushPieceTypes[val][2];
                pos = new int[]{r, c};
                value = (int) candyCrushPieceTypes[val][1];
            }
            else{ // it is the strongest piece, gets the information for itself
                name = (String) candyCrushPieceTypes[val - 1][0]; 
                color = (String) candyCrushPieceTypes[val - 1][2];
                pos = new int[]{r, c};
                value = (int) candyCrushPieceTypes[val - 1][1];
            }
            layout[r][c] = val < 7
                ? new CCNormalPiece(name, color, pos, value)
                : new CCPowerPiece(name, color, pos, value, "*");
        }
        if (game.equals("Bejeweled")){
            String name;
            String color;
            int[] pos;
            int value;
            if(val <= 6){ // this will get the info of the next stronger value piece
                name = (String) bejeweledPieceTypes[val][0]; 
                color = (String) bejeweledPieceTypes[val][2];
                pos = new int[]{r, c};
                value = (int) bejeweledPieceTypes[val][1];
            }
            else{ // it is the strongest piece, gets the information for itself
                name = (String) bejeweledPieceTypes[val - 1][0]; 
                color = (String) bejeweledPieceTypes[val - 1][2];
                pos = new int[]{r, c};
                value = (int) bejeweledPieceTypes[val - 1][1];
            }
            layout[r][c] = new BNormalPiece(name, color, pos, value);
        }
        return score;
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
        // Object[][] candyCrushPieceTypes = {
        //         {"jelly bean", 1, "red"},
        //         {"lozenge", 2, "orange"},
        //         {"lemon drop", 3, "yellow"},
        //         {"gum square", 4, "green"},
        //         {"lollipop head", 5, "blue"},
        //         {"jujube cluster", 6, "purple"},
        //         {"striped candy", 7, "pink"},
        //         {"wrapped candy", 8, "grey"},
        //         {"color bomb", 9, "black"}
        // };

        // Object[][] bejeweledPieceTypes = {
        //         {"red gem", 1, "red"},
        //         {"orange gem", 2, "orange"},
        //         {"yellow gem", 3, "yellow"},
        //         {"green gem", 4, "green"},
        //         {"blue gem", 5, "blue"},
        //         {"purple gem", 6, "purple"},
        //         {"white gem", 7, "white"}
        // };


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

    public int getNumOfRow() {
        return numOfRow;
    }

    public int getNumOfCol() {
        return numOfCol;
    }

}
