package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Roi extends ChessPiece {

    public Roi(boolean blanc, int x, int y) {
        super(blanc, "", x, y);
        if (blanc) setImg("roiB.png");
        else setImg("roiN.png");
    }

    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) return false;
        else if (board.get(x, y) == null || board.get(x, y).isBlanc() != isBlanc())
            if (Math.abs(x - getX()) <= 1 && Math.abs(y - getY()) <= 1 && (Math.abs(x - getX()) + Math.abs(y - getY()) != 0)) return true;
        return false;
    }

    @Override
    public int[][] imagineAllMoves(ChessBoard board) {
        int[][] moves = new int[8][];
        int ind = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidMove(i, j, board)) moves[ind++] = new int[]{i, j};
            }
        }
        return moves;
    }
}
