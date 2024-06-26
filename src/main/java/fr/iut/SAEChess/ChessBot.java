package fr.iut.SAEChess;

import java.util.*;

public class ChessBot {

    private final Random random = new Random();

    /**
     * trie les mouvements possible en fonction du nombre de point que peut gagner le bot en un coup
     * @param moves les mouvements possible
     */
    private void sortMoves(List<int[]> moves) {
        moves.sort(Comparator.comparingInt(s -> s[4]));
    }

    /**
     * @param board l'échiquier
     * @param isWhite si la pièce est blanche
     * @return le mouvement que le bot choisi
     */
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

    /**
     * @param board l'échiquier
     * @param isWhite si la pièce est blanche
     * @return tout les mouvements possibles ainsi que le score de chacun(le score est celui d'une pièce qu'on peut prendre)
     */
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