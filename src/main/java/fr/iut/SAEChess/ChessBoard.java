package fr.iut.SAEChess;

import fr.iut.SAEChess.Pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {

    public List<List<ChessPiece>> board;
    public ChessBoard() {
        board = new ArrayList<>(Arrays.asList(Arrays.asList(new Tour(false,0,0), new Cavalier(false,0,1), new Fou(false,0,2), new Reine(false,0,3), new Roi(false,0,4), new Fou(false,0,5), new Cavalier(false,0,6), new Tour(false,0,7)),
            Arrays.asList(new Pion(false, 1, 0), new Pion(false, 1, 1), new Pion(false, 1, 2), new Pion(false, 1, 3), new Pion(false, 1, 4), new Pion(false, 1, 5), new Pion(false, 1, 6), new Pion(false, 1, 7)),
            Arrays.asList(null, null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null, null),
            Arrays.asList(new Pion(true, 6, 0), new Pion(true, 6, 1), new Pion(true, 6, 2), new Pion(true, 6, 3), new Pion(true, 6, 4), new Pion(true, 6, 5), new Pion(true, 6, 6), new Pion(true, 6, 7)),
            Arrays.asList(new Tour(true,7,0), new Cavalier(true,7,1), new Fou(true,7,2), new Reine(true,7,3), new Roi(true,7,4), new Fou(true,7,5), new Cavalier(true,7,6), new Tour(true,7,7))));
    }

    public void set(int x, int y, ChessPiece piece) {
        board.get(x).set(y, piece);
    }

    public ChessPiece get(int x, int y) {
        return board.get(x).get(y);
    }

    public void swap(int x, int y, int x2, int y2){
        ChessPiece tmp = board.get(x).get(y);
        board.get(x).set(y, board.get(x2).get(y2));
        board.get(x2).set(y2, tmp);
        if (get(x, y) != null) {
            get(x, y).setX(x2);
            get(x, y).setY(y2);
        } else {
            get(x2, y2).setX(x);
            get(x2, y2).setY(y);
        }
    }

    public void take(int x, int y, int x2, int y2){
        board.get(x2).set(y2, board.get(x).get(y));
        board.get(x).set(y, null);
        if (get(x, y) != null) {
            get(x, y).setX(x2);
            get(x, y).setY(y2);
        } else {
            get(x2, y2).setX(x);
            get(x2, y2).setY(y);
        }
    }

    public void joue(){

    }
}
