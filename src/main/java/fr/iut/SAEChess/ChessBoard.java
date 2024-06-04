package fr.iut.SAEChess;

import com.almasb.fxgl.app.services.FXGLAssetLoaderService;
import fr.iut.SAEChess.Pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {

    public List<List<ChessPiece>> board;
    public ChessBoard() {
        board = new ArrayList<>(Arrays.asList(Arrays.asList(new Tour('N'), new Cavalier('N'), new Fou('N'), new Reine('N'), new Roi('N'), new Fou('N'), new Cavalier('N'), new Tour('N')),
            Arrays.asList(new Pion(false, 1, 0), new Pion(false, 1, 1), new Pion(false, 1, 2), new Pion(false, 1, 3), new Pion(false, 1, 4), new Pion(false, 1, 5), new Pion(false, 1, 6), new Pion(false, 1, 7)),
            Arrays.asList(null, null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null, null),
            Arrays.asList(new Pion(false, 6, 0), new Pion(false, 6, 1), new Pion(false, 6, 2), new Pion(false, 6, 3), new Pion(false, 6, 4), new Pion(false, 6, 5), new Pion(false, 6, 6), new Pion(false, 6, 7)),
            Arrays.asList(new Tour('B'), new Cavalier('B'), new Fou('B'), new Reine('B'), new Roi('B'), new Fou('B'), new Cavalier('B'), new Tour('B'))));
    }

    public void set(int x, int y, ChessPiece piece) {
        board.get(x).set(y, piece);
    }

    public ChessPiece get(int x, int y) {
        return board.get(x).get(y);
    }

    public void rmPremove(){
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j) instanceof PreMoveTile) board.get(i).set(j, null);
            }
        }
    }

    public void swap(int x, int y, int x2, int y2){
        ChessPiece tmp = board.get(x).get(y);
        board.get(x).set(y, board.get(x2).get(y2));
        board.get(x2).set(y2, tmp);
    }
}
