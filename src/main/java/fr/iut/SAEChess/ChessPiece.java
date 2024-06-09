package fr.iut.SAEChess;

public abstract class ChessPiece {

    private int x, y;

    private final boolean blanc;

    private String img;

    private final int points;

    private final String piece;

    public ChessPiece(boolean blanc, String img, int x, int y, int point, String piece) {
        this.blanc = blanc;
        this.img = img;
        this.x = x;
        this.y = y;
        this.points = point;
        this.piece = piece;
    }

    /**
     * @return true si la pièce est blanche
     */
    public boolean isBlanc() {
        return blanc;
    }

    /**
     * @return le path de l'image de la pièce
     */
    public String getImg() {
        return img;
    }

    /**
     * définit l'image de la piece
     * @param img l'image de la pièce
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return l'absysse x de la pièce
     */
    public int getX() {
        return x;
    }

    /**
     * définit l'absysse x de la pièce
     * @param x l'absysse x de la pièce
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return l'ordonné y de la pièce
     */
    public int getY() {
        return y;
    }

    /**
     * définit l'ordonné y de la pièce
     * @param y l'ordonné y de la pièce
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @param x position x de la case du déplacement
     * @param y position y de la case du déplacement
     * @param board l'échiquier ou se trouve la pièce
     * @return true si la pièce peut se déplacer sur la case donné, false sinon
     */
    public abstract boolean isValidMove(int x, int y, ChessBoard board);

    /**
     * @param board échiquier ou la pièce doit se déplacer
     * @return tout les mouvements possible pour la pièce
     */
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

    /**
     * @return le nombre de point que vaut la piece
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return le charactère à qui correspond la pièce
     */
    public String getPiece() {
        return piece;
    }
}
