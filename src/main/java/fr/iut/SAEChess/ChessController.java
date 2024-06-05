package fr.iut.SAEChess;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChessController implements Initializable {

    @FXML
    private Label J1;

    @FXML
    private Label J2;

    @FXML
    private Button btnJouer;

    @FXML
    private Button btnTournoi;

    @FXML
    private GridPane Gboard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChessBoard board = new ChessBoard();
        updateBoard(board);
    }

    private void updateBoard(ChessBoard board) {
        for (int i = 0; i < board.board.size(); i++) {
            for (int j = 0; j < board.board.get(i).size(); j++) {
                ImageView tmp;
                if (board.get(i, j) != null)
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/" + board.get(i, j).getImg()))));
                else tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/vide.png"))));
                Gboard.add(tmp, j, i);
            }
        }
    }
}
