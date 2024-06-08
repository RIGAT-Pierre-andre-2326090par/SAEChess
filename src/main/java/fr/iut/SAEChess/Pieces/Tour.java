package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Tour extends ChessPiece {

    public Tour(boolean blanc, int x, int y) {
        super(blanc, "", x, y, 5);
        if (blanc) setImg("tourB.png");
        else setImg("tourN.png");
    }


    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        System.out.println(x);
        System.out.println(y);
        if (x < 0 || x >= 8 || y < 0 || y >= 8 ||
                (x == getX() && y == getY()) ||
                (board.get(x, y) != null && board.get(x, y).isBlanc() == isBlanc())) return false;
        else if (y == getY()) {
            if (getX() < x) {
                for (int nb = getX() + 1; nb < x; nb++)
                    if (board.get(nb, y) != null) return false;
            } else {
                for (int nb = getX() - 1; nb > x; nb--)
                    if (board.get(nb, y) != null) return false;
            }
            return true;
        } else if (x == getX()) {
            if (getY() < y) {
                for (int nb = getY() + 1; nb < y; nb++)
                    if (board.get(x, nb) != null) return false;
            } else {
                for (int nb = getY() - 1; nb > y; nb--)
                    if (board.get(x, nb) != null) return false;
            }
            return true;
        }
        return false;
    }
}