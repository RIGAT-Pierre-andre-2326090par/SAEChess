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

    @FXML
    private void handleSwitchButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Jouer.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root,1096,675);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}