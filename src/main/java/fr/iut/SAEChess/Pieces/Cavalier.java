package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Cavalier extends ChessPiece {
    public Cavalier(boolean blanc, int x, int y) {
        super(blanc, "", x, y, 3);
        if (blanc) setImg("cavalierB.png");
        else setImg("cavalierN.png");
    }

    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8 || (x == getX() && y == getY())) return false;
        else if (board.get(x, y) == null || board.get(x, y).isBlanc() != isBlanc())
            return (Math.abs(x - getX()) == 2 && Math.abs(y - getY()) == 1) || (Math.abs(x - getX()) == 1 && Math.abs(y - getY()) == 2);
        else return false;
    }
}
