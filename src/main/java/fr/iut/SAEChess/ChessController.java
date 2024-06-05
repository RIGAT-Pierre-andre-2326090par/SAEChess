package fr.iut.SAEChess;

import javafx.event.Event;
import javafx.event.EventHandler;
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

    @FXML
    private GridPane Pboard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnJouer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {setGame();});
    }

    private void setGame(){
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
                int finalI = i;
                int finalJ = j;
                tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                        setPremove(board.get(finalI, finalJ).imagineAllMoves(board), board)
                );
                Gboard.add(tmp, j, i);
            }
        }
    }

    private void setPremove(int[][] poss, ChessBoard board) {
        int ind = 0;
        while (ind < poss.length) {
            for (int i = 0; i < board.board.size(); i++) {
                for (int j = 0; j < board.board.get(i).size(); j++) {
                    ImageView tmp;
                    if (ind < poss.length && poss[ind].equals(new int[]{i, j})) {
                        tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/led.png"))));
                        ++ind;
                    } else tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/vide.png"))));
                    int finalI = i;
                    int finalJ = j;
                    tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        setPremove(board.get(finalI, finalJ).imagineAllMoves(board), board);
                        System.out.println("premove set");
                    });
                    Pboard.add(tmp, j, i);
                }
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