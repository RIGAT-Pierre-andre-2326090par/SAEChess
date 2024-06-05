package fr.iut.SAEChess;

public class Premove extends ChessPiece {
    private final int xStart;
    private final int yStart;

    public Premove(boolean blancPremove, int x, int y, int xStart, int yStart) {
        super(blancPremove, "", x, y);
        this.xStart = xStart;
        this.yStart = yStart;
    }

    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        return false;
    }

    @Override
    public int[][] imagineAllMoves(ChessBoard board) {
        return new int[0][];
    }

    public int getxStart() {
        return xStart;
    }

    public int getyStart() {
        return yStart;
    }
}
