package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Cavalier extends ChessPiece {
    public Cavalier(boolean blanc, int x, int y) {
        super(blanc, "", x, y);
        if (blanc) setImg("cavalierB.png");
        else setImg("cavalierN.png");
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
