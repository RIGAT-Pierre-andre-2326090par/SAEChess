package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Reine extends ChessPiece {

    public Reine(boolean blanc, int x, int y) {
        super(blanc, "", x, y, 3);
        if (blanc) setImg("reineB.png");
        else setImg("reineN.png");
    }


    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8){ return false;}
        int deltaX = x - this.getX();
        int deltaY = y - this.getY();
        if (Math.abs(deltaX) == Math.abs(deltaY) || y==getY()||x==getX()) {
            return true;
        }return false;
    }
    @Override
    public int[][] imagineAllMoves(ChessBoard board) {
        int[][] moves = new int[50][];
        int ind = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidMove(i, j, board)) moves[ind++] = new int[]{i, j};
            }
        }
        return moves;
    }
}
