package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Fou extends ChessPiece {
    public Fou(boolean blanc, int x, int y) {
        super(blanc, "", x, y, 3);
        if (blanc) setImg("fouB.png");
        else setImg("fouN.png");
    }


    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8 && (x == getX() && y == getY())) return false;
        int deltaX = x - this.getX();
        int deltaY = y - this.getY();
        if (Math.abs(deltaX) == Math.abs(deltaY) && deltaX != 0 && deltaY != 0) {
            return true;
        }
        return false;
    }
}
