package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Reine extends ChessPiece {

    public Reine(boolean blanc, int x, int y) {
        super(blanc, "", x, y);
        if (blanc) setImg("reineB.png");
        else setImg("reineN.png");
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
