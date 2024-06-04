package fr.iut.SAEChess;

public abstract class ChessPiece {

    private int x, y;

    private final boolean blanc;

    private String img;

    public ChessPiece(boolean blanc, String img, int x, int y) {
        this.blanc = blanc;
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public boolean isBlanc() {
        return blanc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract boolean isValidMove(int x, int y, ChessBoard board);

    public abstract int[][] imagineAllMoves(ChessBoard board);
}
