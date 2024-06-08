package fr.iut.SAEChess;

public abstract class ChessPiece {

    private int x, y;

    private final boolean blanc;

    private String img;

    private int points;

    public ChessPiece(boolean blanc, String img, int x, int y, int point) {
        this.blanc = blanc;
        this.img = img;
        this.x = x;
        this.y = y;
        this.points = point;
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

    public int[][] imagineAllMoves(ChessBoard board) {
        int[][] moves = new int[64][];
        int ind = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidMove(i, j, board)) moves[ind++] = new int[]{i, j};
            }
        }
        return moves;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}