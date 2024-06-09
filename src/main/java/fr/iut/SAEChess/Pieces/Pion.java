package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

public class Pion extends ChessPiece {

    public Pion(boolean blanc, int x, int y) {
        super(blanc, "", x, y, 1, "");
        if (blanc) setImg("pionB.png");
        else setImg("pionN.png"); // si la c'est blanc mettre l'image blanche sinon mettre l'image noir
    }

    /**
     * @param x position x de la case du déplacement
     * @param y position y de la case du déplacement
     * @param board l'échiquier ou se trouve la pièce
     * @return true si la pièce peut se déplacer sur la case donné, false sinon
     */
    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8 ||
                (x == getX() && y == getY()) ||
                (board.get(x, y) != null && board.get(x, y).isBlanc() == isBlanc())) return false; // si la pièce dépasse l'échiquier ou sinon il y a deja une pièce de sa couleur il ne peut pas y aller
        else if (isBlanc()) {
            if (board.get(x, y) != null && !board.get(x, y).isBlanc()) {
                return x - getX() == -1 && Math.abs(y - getY()) == 1;
            } else if (getX() == 6 && (x - getX() == -2 || x - getX() == -1) && y - getY() == 0) {
                for (int nb = x; nb < getX(); nb++) {
                    if (board.get(nb, y) != null && board.get(nb, y) != this) return false;
                }
                return true;
            } else return x - getX() == -1 && y - getY() == 0;
        } else {
            if (board.get(x, y) != null && board.get(x, y).isBlanc()) {
                return x - getX() == 1 && Math.abs(y - getY()) == 1;
            } else if (getX() == 1 && (x - getX() == 2 || x - getX() == 1) && y - getY() == 0) {
                for (int nb = x; nb > getX(); nb--) {
                    if (board.get(nb, y) != null && board.get(nb, y) != this) return false;
                }
                return true;
            } else return x - getX() == 1 && y - getY() == 0;
        }
    }
}
