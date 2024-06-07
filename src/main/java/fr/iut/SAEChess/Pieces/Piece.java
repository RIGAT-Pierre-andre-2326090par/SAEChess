package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;

public abstract class Piece {
    private String img;
    private boolean isWhite;

    public Piece(String img, boolean isWhite) {
        this.img = img;
        this.isWhite = isWhite;
    }

    public String getImg() {
        return img;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract int[][] possibleMoves(ChessBoard board, int x, int y);
}
