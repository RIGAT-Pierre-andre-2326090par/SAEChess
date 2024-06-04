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
        if (x < 0 || x >= 8 || y < 0 || y >= 8) return false;
        else if (isBlanc()) {
            if (getX() == 6 && x - getX() == -2 && x - getX() == -1 && y - getY() == 0) return true;
            else if (x - getX() == -1 && y - getY() == 0) return true;
            else if (board.get(x, y).isBlanc()) return x - getX() == -1 && abs(y - getY()) <= 1;
        } else {
            if (getX() == 1 && x - getX() == 2 && x - getX() == 1 && y - getY() == 0) return true;
            else if (x - getX() == 1 && y - getY() == 0) return true;
            else if (!board.get(x, y).isBlanc()) return x - getX() == 1 && abs(y - getY()) <= 1;
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
