package fr.iut.SAEChess;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChessController implements Initializable {

    private boolean isWhiteTurn = true;
    @FXML
    private Label timerLabel;

    @FXML
    private Label timerLabel2;

    private int timeInSeconds = 600; // 10 minutes = 600 seconds
    private int timeInSeconds2 = 600; // 10 minutes = 600 seconds
    private Timeline timeline;
    private Timeline timeline2;
    @FXML
    private Label J1;

    private ChessBot bot;

    private boolean isBotPlaying = false;

    @FXML
    private Label J2;

    @FXML
    private Button btnJouer;

    @FXML
    private Button btnBot;

    @FXML
    private Button btnTournoi;

    @FXML
    private GridPane Gboard;

    private ChessBoard board;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnJouer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setGame(false));
        btnBot.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setGame(true));
        bot = new ChessBot();
    }

    private void setGame(boolean bot) {
        board = new ChessBoard();
        updateBoard(board);
        isBotPlaying = bot;
        if (isBotPlaying) {
            J2.setText("Bot");
        } else {
            J2.setText("Adversaire");
        }
    }

    private void updateBoard(ChessBoard board) {
        Gboard.getChildren().clear();
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                ImageView tmp;
                if (board.get(i, j) != null)
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/" + board.get(i, j).getImg()))));
                else
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/vide.png"))));
                int finalI = i;
                int finalJ = j;
                tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (isWhiteTurn == board.get(finalI, finalJ).isBlanc())
                        setPremove(finalI, finalJ, board.get(finalI, finalJ).imagineAllMoves(board), board);
                });
                Gboard.add(tmp, j, i);
            }
        }
    }

    public void movePiece(int x, int y, int finalI, int finalJ, ChessBoard board) {
        board.swap(x, y, finalI, finalJ);
        if (board.getScoreJ1() >= 40) {
            System.out.println(J1.getText() + " gagnez");
            System.exit(0);
        } else if (board.getScoreJ2() >= 40) {
            System.out.println(J2.getText() + " gagne");
            System.exit(0);
        }
        isWhiteTurn = !isWhiteTurn;
        updateBoard(board);
        if (isBotPlaying && !isWhiteTurn) playBotMove();
        Reprise();
    }

    private void setPremove(int x, int y, int[][] poss, ChessBoard board) {
        Gboard.getChildren().clear();
        int k = 0;
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                ImageView tmp;
                if (poss[k] != null && Arrays.equals(new int[]{i, j}, poss[k])) {
                    int finalI = i;
                    int finalJ = j;
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/led.png"))));
                    tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> movePiece(x, y, finalI, finalJ, board));
                    Gboard.add(tmp, j, i);
                }
                if (board.get(i, j) != null)
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/" + board.get(i, j).getImg()))));
                else
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/vide.png"))));
                int finalI = i;
                int finalJ = j;
                if (poss[k] != null && Arrays.equals(new int[]{i, j}, poss[k])) {
                    tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> movePiece(x, y, finalI, finalJ, board));
                    k++;
                } else tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> updateBoard(board));
                Gboard.add(tmp, j, i);
            }
        }
    }

    private void Reprise() {
        if (isWhiteTurn) {
            timeline.stop();
            timeline2.play();
        } else {
            timeline2.stop();
            timeline.play();
        }
    }

    @FXML
    private void startTime() {
        timeInSeconds = 600;
        timeInSeconds2 = 600;
        timeline = null;
        timeline2 = null;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeInSeconds--;
            updateTimerLabel();
            if (timeInSeconds <= 0) {
                timeline.stop();
            }
        }));
        timeline2 = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeInSeconds2--;
            updateTimerLabel2();
            if (timeInSeconds2 <= 0) {
                timeline.stop();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();
    }

    private void updateTimerLabel2() {
        int minutes = timeInSeconds2 / 60;
        int seconds = timeInSeconds2 % 60;
        timerLabel2.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void updateTimerLabel() {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void playBotMove() {
        int[] move = bot.getMove(board, false); // Bot plays as black
        if (move != null) {
            board.swap(move[0], move[1], move[2], move[3]);
            updateBoard(board);
            isWhiteTurn = true;
        }
    }
}