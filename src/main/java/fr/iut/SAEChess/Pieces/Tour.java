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
        if (x < 0 || x >= 8 || y < 0 || y >= 8 && (x == getX() && y == getY())) return false;
        else return y == getY() || x == getX() && x - getX() != 0 && y - getY() != 0;
    }
}
