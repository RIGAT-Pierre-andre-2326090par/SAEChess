package fr.iut.SAEChess;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
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
    private MenuButton temps;

    @FXML
    private MenuItem min1;

    @FXML
    private MenuItem min2;

    @FXML
    private MenuItem min3;

    @FXML
    private MenuItem min5;

    @FXML
    private MenuItem min10;

    @FXML
    private MenuItem min15;

    @FXML
    private MenuItem min30;

    @FXML
    private GridPane Gboard;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnExport;

    private ChessBoard board;

    private final CSVgest exportPartie = new CSVgest();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnJouer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setGame(false));
        btnBot.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setGame(true));
        min1.setOnAction(actionEvent -> temps.setText(min1.getText()));
        min2.setOnAction(actionEvent -> temps.setText(min2.getText()));
        min3.setOnAction(actionEvent -> temps.setText(min3.getText()));
        min5.setOnAction(actionEvent -> temps.setText(min5.getText()));
        min10.setOnAction(actionEvent -> temps.setText(min10.getText()));
        min15.setOnAction(actionEvent -> temps.setText(min15.getText()));
        min30.setOnAction(actionEvent -> temps.setText(min30.getText()));
        bot = new ChessBot();
        btnPause.setVisible(false);
        btnExport.setVisible(false);
    }

    private void setTime() {
        switch (temps.getText()) {
            case "1min": {
                timeInSeconds = 60;
                timeInSeconds2 = 60;
                break;
            }
            case "2min": {
                timeInSeconds = 120;
                timeInSeconds2 = 120;
                break;
            }
            case "3min": {
                timeInSeconds = 180;
                timeInSeconds2 = 180;
                break;
            }
            case "5min": {
                timeInSeconds = 300;
                timeInSeconds2 = 300;
                break;
            }
            case "10min": {
                timeInSeconds = 600;
                timeInSeconds2 = 600;
                break;
            }
            case "15min": {
                timeInSeconds = 900;
                timeInSeconds2 = 900;
                break;
            }
            case "30min": {
                timeInSeconds = 1800;
                timeInSeconds2 = 1800;
                break;
            }
        }
        updateTimerLabel();
        updateTimerLabel2();
    }

    private void setGame(boolean bot) {
        btnPause.setVisible(true);
        setTime();
        resetTime();
        startTime();
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
                    if (isWhiteTurn == board.get(finalI, finalJ).isBlanc() &&
                            board.get(finalI, finalJ).imagineAllMoves(board)[0] != null)
                        setPremove(finalI, finalJ, board.get(finalI, finalJ).imagineAllMoves(board), board);
                });
                Gboard.add(tmp, j, i);
            }
        }
    }

    public void movePiece(int x, int y, int finalI, int finalJ, ChessBoard board) throws InterruptedException {
        board.swap(x, y, finalI, finalJ);
        if (board.getScoreJ1() >= 40) {
            System.out.println(J1.getText() + " gagnez");
            btnExport.setVisible(true);
        } else if (board.getScoreJ2() >= 40) {
            System.out.println(J2.getText() + " gagne");
            btnExport.setVisible(true);
        } else {
            isWhiteTurn = !isWhiteTurn;
            updateBoard(board);
            Reprise();
            if (isBotPlaying && !isWhiteTurn) playBotMove();
        }
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
                    tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        try {
                            movePiece(x, y, finalI, finalJ, board);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    Gboard.add(tmp, j, i);
                }
                if (board.get(i, j) != null)
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/" + board.get(i, j).getImg()))));
                else
                    tmp = new ImageView(Objects.requireNonNull(String.valueOf(ChessController.class.getResource("img/vide.png"))));
                int finalI = i;
                int finalJ = j;
                if (poss[k] != null && Arrays.equals(new int[]{i, j}, poss[k])) {
                    tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        try {
                            movePiece(x, y, finalI, finalJ, board);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
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

    private void resetTime() {
        timeline = null;
        timeline2 = null;
    }

    private void startTime() {
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
        int[] move = bot.getMove(board, false);
        if (move != null) {
            board.swap(move[0], move[1], move[2], move[3]);
            isWhiteTurn = !isWhiteTurn;
            updateBoard(board);
            Reprise();
        }
    }

    @FXML
    public void importCSV() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if (file != null && file.getName().contains(".csv"))
            exportPartie.loadCsv(file);
    }

    @FXML
    public void importTmpCSV() {
        File file = new File(Objects.requireNonNull(ChessController.class.getResource("tmp.csv")).getFile());
        exportPartie.exportCsv(file);
    }

    @FXML
    public void exportTmpCSV() {
        File file = new File(Objects.requireNonNull(ChessController.class.getResource("tmp.csv")).getFile());
        exportPartie.exportCsv(file);
    }

    @FXML
    public void exportCSV() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if (file != null && file.getName().contains(".csv"))
            exportPartie.exportCsv(file);
    }
}