package fr.iut.SAEChess;

import fr.iut.SAEChess.Pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ChessBoard {

    private final ChessPiece[][] board;

    private int scoreJ1;
    private int scoreJ2;

    private List<String[]> partie = new ArrayList<>();
    private String[] coup = new String[2];

    private Function<Integer, Integer> lambda = (x) -> (x * -1) + 8;
    private Function<Integer, Integer> lambda2 = (x) -> (x - 8) / -1;

    public ChessBoard() {
        scoreJ1 = 0;
        scoreJ2 = 0;
        board = new ChessPiece[][]{{new Tour(false, 0, 0), new Cavalier(false, 0, 1), new Fou(false, 0, 2), new Reine(false, 0, 3), new Roi(false, 0, 4), new Fou(false, 0, 5), new Cavalier(false, 0, 6), new Tour(false, 0, 7)},
                {new Pion(false, 1, 0), new Pion(false, 1, 1), new Pion(false, 1, 2), new Pion(false, 1, 3), new Pion(false, 1, 4), new Pion(false, 1, 5), new Pion(false, 1, 6), new Pion(false, 1, 7)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {new Pion(true, 6, 0), new Pion(true, 6, 1), new Pion(true, 6, 2), new Pion(true, 6, 3), new Pion(true, 6, 4), new Pion(true, 6, 5), new Pion(true, 6, 6), new Pion(true, 6, 7)},
                {new Tour(true, 7, 0), new Cavalier(true, 7, 1), new Fou(true, 7, 2), new Reine(true, 7, 3), new Roi(true, 7, 4), new Fou(true, 7, 5), new Cavalier(true, 7, 6), new Tour(true, 7, 7)}};
    }

    public void set(int x, int y, ChessPiece piece) {
        board[x][y] = piece;
    }

    public ChessPiece get(int x, int y) {
        return board[x][y];
    }

    public void swap(int x, int y, int x2, int y2){
        if (get(x2, y2) != null) {
            take(x, y, x2, y2);
        } else {
            if (get(x, y).isBlanc()) {
                coup[0] = coupToString(x, y, x2, y2, false);
            } else {
                coup[1] = coupToString(x, y, x2, y2, false);
                partie.add(coup.clone());
            }
            set(x2, y2, get(x, y));
            set(x, y, null);
            get(x2, y2).setX(x2);
            get(x2, y2).setY(y2);
        }
        if (get(x2, y2) instanceof Pion) {
            if (get(x2, y2).isBlanc() && x2 == 0) {
                set(x2, y2, new Reine(true, x2, y2));
            } else if (!get(x2, y2).isBlanc() && x2 == 7) {
                set(x2, y2, new Reine(false, x2, y2));
            }
        }
    }

    public void take(int x, int y, int x2, int y2){
        if (get(x, y) != null && get(x, y).isBlanc() != get(x2, y2).isBlanc()) {
            if (get(x, y).isBlanc()) {
                scoreJ1 += get(x2, y2).getPoints();
                coup[0] = coupToString(x, y, x2, y2, true);
            } else {
                scoreJ2 += get(x2, y2).getPoints();
                coup[1] = coupToString(x, y, x2, y2, true);
                partie.add(coup.clone());
            }
            set(x2, y2, get(x, y));
            get(x2, y2).setX(x2);
            get(x2, y2).setY(y2);
            set(x, y, null);
        }
    }

    public int getScoreJ1() {
        return scoreJ1;
    }

    public int getScoreJ2() {
        return scoreJ2;
    }

    public int getBoardSize() {
        return board.length;
    }

    public List<String[]> getPartie() {
        return partie;
    }

    public void setPartie(List<String[]> partie) {
        this.partie = partie;
    }

    private String piecetoString(int x, int y) {
        String tmp = get(x, y).getPiece();
        switch (y) {
            case 0: tmp = tmp + "a"; break;
            case 1: tmp = tmp + "b"; break;
            case 2: tmp = tmp + "c"; break;
            case 3: tmp = tmp + "d"; break;
            case 4: tmp = tmp + "e"; break;
            case 5: tmp = tmp + "f"; break;
            case 6: tmp = tmp + "g"; break;
            case 7: tmp = tmp + "h"; break;
        }
        return tmp + lambda.apply(x);
    }

    private ChessPiece stringtoPiece(char piece) {
        ChessPiece tmp;
        switch (piece) {
            case 'F': tmp = new Fou(true, -1, -1); break;
            case 'D': tmp = new Reine(true, -1, -1); break;
            case 'R': tmp = new Roi(true, -1, -1); break;
            case 'T': tmp = new Tour(true, -1, -1); break;
            case 'C': tmp = new Cavalier(true, -1, -1); break;
            default: tmp = new Pion(true, -1, -1); break;
        }
        return tmp;
    }

    private String posToString(int x, int y) {
        String tmp = "";
        switch (y) {
            case 0: tmp = "a"; break;
            case 1: tmp = "b"; break;
            case 2: tmp = "c"; break;
            case 3: tmp = "d"; break;
            case 4: tmp = "e"; break;
            case 5: tmp = "f"; break;
            case 6: tmp = "g"; break;
            case 7: tmp = "h"; break;
        }
        return tmp + lambda.apply(x);
    }

    private int[] stringToPos(char pos1, char pos2){
        int[] tmp = new int[2];
        switch (pos1) {
            case 'a': tmp[1] = 7; break;
            case 'b': tmp[1] = 6; break;
            case 'c': tmp[1] = 5; break;
            case 'd': tmp[1] = 4; break;
            case 'e': tmp[1] = 3; break;
            case 'f': tmp[1] = 2; break;
            case 'g': tmp[1] = 1; break;
            case 'h': tmp[1] = 0; break;
        }
        tmp[0] = lambda2.apply(Character.getNumericValue(pos2));
        return tmp;
    }

    public String coupToString(int x, int y, int x2, int y2, boolean take){
        String tmp = "";
        tmp = tmp + piecetoString(x, y);
        if (take)
            tmp = tmp + "x" + piecetoString(x2, y2);
        else
            tmp = tmp + posToString(x2, y2);
        System.out.println(tmp);
        return tmp;
    }

    public void stringToCoup(String str){
        int[] tmp = new int[4];
        ChessPiece piece = stringtoPiece(str.charAt(0));
        int[] tmp2;
        if (piece instanceof Pion) {
            tmp2 = stringToPos(str.charAt(0), str.charAt(1));
        } else {
            tmp2 = stringToPos(str.charAt(1), str.charAt(2));
        }
        tmp[0] = tmp2[0];
        tmp[1] = tmp2[1];
        tmp2 = stringToPos(str.charAt(str.length() - 2), str.charAt(str.length() - 1));
        tmp[2] = tmp2[0];
        tmp[3] = tmp2[1];
        swap(tmp[0], tmp[1], tmp[2], tmp[3]);
    }
}
