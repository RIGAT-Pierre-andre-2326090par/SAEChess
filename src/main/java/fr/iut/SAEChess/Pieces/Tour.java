package fr.iut.SAEChess.Pieces;

import fr.iut.SAEChess.ChessBoard;
import fr.iut.SAEChess.ChessPiece;

import static java.lang.Math.abs;

public class Tour extends ChessPiece {

    public Tour(boolean blanc, int x, int y) {
        super(blanc, "", x, y);
        if (blanc) setImg("tourB.png");
        else setImg("tourN.png");
    }


    @Override
    public boolean isValidMove(int x, int y, ChessBoard board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) return false;

        else if (y==getY()||x==getX()) {
            return true;
        } return false;
    }

    @Override
    public int[][] imagineAllMoves(ChessBoard board) {
        int[][] moves = new int[4][];
        int ind = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidMove(i, j, board)) moves[ind++] = new int[]{i, j};
            }
        }
        return moves;
    }
    }
