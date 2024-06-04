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
        return false;
    }

    @Override
    public int[][] imagineAllMoves(ChessBoard board) {
        return new int[0][];
    }
}
