package fr.iut.SAEChess;

import java.util.*;

public class ChessBot {

    private final Random random = new Random();

    private List<int[]> sortMoves(List<int[]> moves) {
        Collections.sort(moves, new Comparator<int[]>() {
            @Override
            public int compare(int[] s1, int[] s2) {
                return Integer.compare(s1[4], s2[4]);
            }
        });
        for (int[] move : moves) {
            System.out.println(Arrays.toString(move));
        }
        System.out.println("");
        return moves;
    }

    public int[] getMove(ChessBoard board, boolean isWhite) {
        List<int[]> legalMoves = getAllLegalMoves(board, isWhite);
        if (legalMoves.isEmpty()) {
            return null;
        }
        sortMoves(legalMoves);
        if (legalMoves.get(legalMoves.size() - 1)[4] != 0) {
            return legalMoves.get(legalMoves.size() - 1);
        } else {
            return legalMoves.get(random.nextInt(legalMoves.size()));
        }
    }

    private List<int[]> getAllLegalMoves(ChessBoard board, boolean isWhite) {
        List<int[]> legalMoves = new ArrayList<>();
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                ChessPiece piece = board.get(i, j);
                if (piece != null && piece.isBlanc() == isWhite) {
                    int[][] possibleMoves = piece.imagineAllMoves(board);
                    for (int[] move : possibleMoves) {
                        if (move != null) {
                            int score = 0;
                            if (board.get(move[0], move[1]) != null) {
                                score = board.get(move[0], move[1]).getPoints();
                            }
                            legalMoves.add(new int[]{i, j, move[0], move[1], score});
                        }
                    }
                }
            }
        }
        return legalMoves;
    }
}