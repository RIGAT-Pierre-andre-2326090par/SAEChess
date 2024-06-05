package fr.iut.SAEChess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
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
        btnJouer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setGame();
        });
    }

    private void setGame() {
        ChessBoard board = new ChessBoard();
        updateBoard(board);
    }

    private void updateBoard(ChessBoard board) {
        for (int i = 0; i < board.board.size(); i++) {
            for (int j = 0; j < board.board.get(i).size(); j++) {
                ImageView tmp;
                if (board.get(i, j) != null)
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/" + board.get(i, j).getImg()))));
                else
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/vide.png"))));
                int finalI = i;
                int finalJ = j;
                tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        setPremove(finalI, finalJ, board.get(finalI, finalJ).imagineAllMoves(board), board);
                        System.out.println("premove set");
                });
                Gboard.add(tmp, j, i);
            }
        }
    }

    private void setPremove(int x, int y,int[][] poss, ChessBoard board) {
        int ind = 0;
        while (ind < poss.length) {
            for (int i = 0; i < board.board.size(); i++) {
                for (int j = 0; j < board.board.get(i).size(); j++) {
                    ImageView tmp;
                    if (board.get(i, j) != null)
                        tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/" + board.get(i, j).getImg()))));
                    else tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/vide.png"))));

                    if (ind < poss.length && poss[ind].equals(new int[]{i, j})) {
                        int finalI = i;
                        int finalJ = j;
                        tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                            board.swap(x, y, finalI, finalJ);
                            System.out.println("piece moved");
                        });
                        ++ind;
                    }
                }
            }
        }
    }
}