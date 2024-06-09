package fr.iut.SAEChess;

import fr.iut.SAEChess.Pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    private final ChessPiece[][] board;

    private int scoreJ1;
    private int scoreJ2;

    private List<String[]> partie = new ArrayList<>();
    private String[] coup = new String[2];

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
                coup[0] = "";
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
            } else {
                scoreJ2 += get(x2, y2).getPoints();
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

    public void setScoreJ1(int scoreJ1) {
        this.scoreJ1 = scoreJ1;
    }

    public int getScoreJ2() {
        return scoreJ2;
    }

    public void setScoreJ2(int scoreJ2) {
        this.scoreJ2 = scoreJ2;
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
        return tmp + Integer.toString(x);
    }
}
