package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Fou extends ChessPiece {
    public Fou(boolean blanc, int x, int y) {
        super(blanc, "", x, y, 3, "F");
        if (blanc) setImg("fouB.png");
        else setImg("fouN.png");
    }


    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8 ||
                (x == getX() && y == getY()) ||
                (board.get(x, y) != null && board.get(x, y).isBlanc() == isBlanc())) return false;
        int deltaX = x - this.getX();
        int deltaY = y - this.getY();
        if (Math.abs(deltaX) == Math.abs(deltaY)) {
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
        }
        return false;
    }
}