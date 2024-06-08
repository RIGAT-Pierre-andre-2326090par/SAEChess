package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Reine extends ChessPiece {

    public Reine(boolean blanc, int x, int y) {
        super(blanc, "", x, y, 9);
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
        return (Math.abs(deltaX) == Math.abs(deltaY) || y == getY() || x == getX());
    }
}
