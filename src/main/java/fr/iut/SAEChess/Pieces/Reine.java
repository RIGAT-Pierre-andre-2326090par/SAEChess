package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Reine extends ChessPiece {

    public Reine(boolean blanc, int x, int y) {
        super(blanc, "", x, y, 3, "D");
        if (blanc) setImg("reineB.png");
        else setImg("reineN.png");
    }


    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8 ||
                (x == getX() && y == getY()) ||
                (board.get(x, y) != null && board.get(x, y).isBlanc() == isBlanc())) return false;
        int deltaX = x - this.getX();
        int deltaY = y - this.getY();
        if (x == getX() && y == getY()) return false;
        else if ((y == getY())) {
            if (getX() < x) {
                for (int nb = getX() + 1; nb < x; nb++)
                    if (board.get(nb, y) != null) return false;
            } else {
                for (int nb = getX() - 1; nb > x; nb--)
                    if (board.get(nb, y) != null) return false;
            }
            return true;
        } else if (((x) == getX())) {
            if (getY() < y) {
                for (int nb = getY() + 1; nb < y; nb++)
                    if (board.get(x, nb) != null) return false;
            } else {
                for (int nb = getY() - 1; nb > y; nb--)
                    if (board.get(x, nb) != null) return false;
            }
            return true;
        }
        else if (Math.abs(deltaX) == Math.abs(deltaY)) {
            if (x > getX() && y > getY()) {
                int j = getY() + 1;
                for (int i = getX() + 1; i < x && j < y; i++) {
                    if (board.get(i, j) != null) return false;
                    j++;
                }
                return true;
            } else if (x > getX() && y < getY()) {
                int j = getY() - 1;
                for (int i = getX() + 1; i < x && j > y; i++) {
                    if (board.get(i, j) != null) return false;
                    j--;
                }
                return true;
            } else if (x < getX() && y > getY()) {
                int j = getY() + 1;
                for (int i = getX() - 1; i > x && j < y; i--) {
                    if (board.get(i, j) != null) return false;
                    j++;
                }
                return true;
            } else if (x < getX() && y < getY()) {
                int j = getY() - 1;
                for (int i = getX() - 1; i > x && j > y; i--) {
                    if (board.get(i, j) != null) return false;
                    j--;
                }
                return true;
            }
            return (Math.abs(deltaX) == Math.abs(deltaY) || y == getY() || x == getX());
        }
        return false;
    }
}