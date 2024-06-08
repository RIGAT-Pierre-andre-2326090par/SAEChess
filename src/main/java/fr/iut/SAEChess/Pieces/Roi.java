package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Roi extends ChessPiece {

    public Roi(boolean blanc, int x, int y) {
        super(blanc, "", x, y, 50);
        if (blanc) setImg("roiB.png");
        else setImg("roiN.png");
    }

    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8 ||
                (x == getX() && y == getY()) ||
                (board.get(x, y) != null && board.get(x, y).isBlanc() == isBlanc())) return false;
        else if (board.get(x, y) == null || board.get(x, y).isBlanc() != isBlanc())
            return Math.abs(x - getX()) <= 1 && Math.abs(y - getY()) <= 1 && (Math.abs(x - getX()) + Math.abs(y - getY()) != 0);
        return false;
    }
}
