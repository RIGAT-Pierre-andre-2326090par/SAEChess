package fr.iut.SAEChess;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChessController implements Initializable {

    @FXML
    private Label timerLabel;

    @FXML
    private Label timerLabel2;

    private int timeInSeconds = 600; // 10 minutes = 600 seconds
    private Timeline timeline;

    @FXML
    private void initialize() {
        // Initialize the timer label if needed
        updateTimerLabel();
    }

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
        Gboard.getChildren().clear();
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

    private void setPremove(int x, int y, int[][] poss, ChessBoard board) {
        for (int[] pos : poss) {
            if (pos != null) {
                int i = pos[0];
                int j = pos[1];
                ImageView tmp;
                if (board.get(i, j) != null)
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/" + board.get(i, j).getImg()))));
                else
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/vide.png"))));
                tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    board.swap(x, y, i, j);
                    System.out.println("piece moved");
                    updateBoard(board);
                });
                Gboard.add(tmp, i, j);
            }
        }
    }
    @FXML
    private void startTime(ActionEvent actionEvent) {
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeInSeconds--;
            updateTimerLabel();
            updateTimerLabel2();

            if (timeInSeconds <= 0) {
                timeline.stop();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTimerLabel() {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }
    private void updateTimerLabel2() {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        timerLabel2.setText(String.format("%02d:%02d", minutes, seconds));
    }

}