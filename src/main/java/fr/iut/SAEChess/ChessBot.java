package fr.iut.SAEChess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChessBot {
    private final Random random = new Random();

    public int[] getMove(ChessBoard board, boolean isWhite) {
        List<int[]> legalMoves = getAllLegalMoves(board, isWhite);
        if (legalMoves.isEmpty()) {
            return null; // No legal moves available
        }

        return legalMoves.get(random.nextInt(legalMoves.size()));
    }

    private List<int[]> getAllLegalMoves(ChessBoard board, boolean isWhite) {
        List<int[]> legalMoves = new ArrayList<>();
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                ChessPiece piece = board.get(i, j);
                if (piece != null && piece.isBlanc() == isWhite) {
                    int[][] possibleMoves = piece.imagineAllMoves(board);
                    for (int[] move : possibleMoves) {
                        if (move != null && piece.isValidMove(move[0], move[1], board)) {
                            legalMoves.add(new int[]{i, j, move[0], move[1]});
                        }
                    }
                }
            }
        }
        return legalMoves;
    }
}