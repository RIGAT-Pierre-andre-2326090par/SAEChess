package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

import static java.lang.Math.abs;

public class Pion extends ChessPiece {

    public Pion(boolean blanc, int x, int y) {
        super(blanc, "", x, y);
        if (blanc) setImg("pionB.png");
        else setImg("pionN.png");
    }

    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        System.out.println(x - getX());
        System.out.println(y - getY());
        if (y < 0 || y >= 8 || x < 0 || x >= 8) return false;
        else if (isBlanc()) {
            if (getY() == 6 && (y - getY() == 2 || y - getY() == 1) && x - getX() == 0) return true;
            else if (y - getY() == 1 && x - getX() == 0) return true;
            else if (board.get(x, y) != null && board.get(x, y).isBlanc() && board.get(x, y) != this) return abs(y - getY()) <= 1 && abs(x - getX()) == 1;
        } else {
            if (getY() == 1 && (abs(y - getY()) == 2 || abs(y - getY()) == 1) && x - getX() == 0) return true;
            else if (y - getY() == 1 && x - getX() == 0) return true;
            else if (board.get(x, y) != null && !board.get(x, y).isBlanc() && board.get(x, y) != this) return abs(y - getY()) <= 1 && abs(x - getX()) == 1;
        } return false;
    }

    @Override
    public int[][] imagineAllMoves(ChessBoard board) {
        int[][] moves = new int[4][];
        int ind = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidMove(i, j, board)) moves[ind++] = new int[]{i, j};
            }
        }
        return moves;
    }
}
