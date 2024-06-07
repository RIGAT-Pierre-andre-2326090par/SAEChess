package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Pion extends ChessPiece {

    public Pion(boolean blanc, int x, int y) {
        super(blanc, "", x, y, 1);
        if (blanc) setImg("pionB.png");
        else setImg("pionN.png");
    }

    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8 || (x == getX() && y == getY())) return false;
        else if (isBlanc()) {
            if (board.get(x, y) != null && !board.get(x, y).isBlanc()) {
                if (x - getX() == 1 && Math.abs(y - getY()) == 1) return true;
                else return x - getX() == -1 && Math.abs(y - getY()) == 1;
            } else if (getX() == 6 && (x - getX() == -2 || x - getX() == -1) && y - getY() == 0) {
                for (int nb = x; nb < getX(); nb++) {
                    if (board.get(nb, y) != null && board.get(nb, y).isBlanc()) return false;
                }
                return true;
            } else return x - getX() == -1 && y - getY() == 0;
        } else {
            if (board.get(x, y) != null && board.get(x, y).isBlanc()) {
                if (x - getX() == -1 && Math.abs(y - getY()) == 1) return true;
                else return x - getX() == 1 && Math.abs(y - getY()) == 1;
            } else if (getX() == 1 && (x - getX() == 2 || x - getX() == 1) && y - getY() == 0) {
                for (int nb = x; nb > getX(); nb--) {
                    if (board.get(nb, y) != null && board.get(nb, y).isBlanc()) return false;
                }
                return true;
            } else return x - getX() == 1 && y - getY() == 0;
        }
    }
}
