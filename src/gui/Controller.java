package gui;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Board;

/**
 * A controller class for the snake application.
 *
 * @author mikolajdeja
 * @version 2021.05.14
 */
public class Controller {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label endGame;

    private AnimationTimer animationTimer;
    private Board board;
    private boolean alive;
    private boolean stop;
    private String direction;
    private int speed;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    /**
     * Initialise the gui components.
     */
    @FXML
    private void initialize() {
        board = new Board(WIDTH, HEIGHT);
        alive = true;
        stop = false;
        Platform.runLater(() -> {
            Stage stage = ((Stage) borderPane.getScene().getWindow());
            ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> drawBoard();
            stage.widthProperty().addListener(stageSizeListener);
            stage.heightProperty().addListener(stageSizeListener);
            animationTimer = new AnimationTimer() {
                long lastTick = 0;
                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick();
                        return;
                    }
                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick();
                    }
                }
            };
            Scene scene = borderPane.getScene();
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP)
                    direction = "up";
                if (key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT)
                    direction = "left";
                if (key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN)
                    direction = "down";
                if (key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT)
                    direction = "right";
                if (key.getCode() == KeyCode.ESCAPE) {
                    stop = !stop;
                    if (stop && alive)
                        animationTimer.stop();
                    else
                        animationTimer.start();
                }
            });
            drawBoard();
        });

    }

    /**
     * Draw the board.
     */
    private void drawBoard() {
        Canvas canvas = new Canvas(borderPane.getWidth() * 0.9, borderPane.getHeight() * 0.9);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                graphicsContext.setFill(board.getState(i, j).getColor());
                double x = i * borderPane.getWidth() * 0.9 / WIDTH;
                double y = j * borderPane.getHeight() * 0.9 / HEIGHT;
                double width = borderPane.getWidth() * 0.85 / WIDTH;
                double height = borderPane.getHeight() * 0.85 / HEIGHT;
                graphicsContext.fillPolygon(new double[]{x, x + width, x + width, x},
                        new double[]{y, y, y + height, y + height}, 4);
            }
        }
        borderPane.setCenter(canvas);
    }

    /**
     * Run the simulation for an indefinite number of ticks.
     */
    @FXML
    private void run() {
        animationTimer.stop();
        if (!alive) {
            endGame.setText("");
            alive = true;
            stop = false;
            direction = null;
            board = new Board(WIDTH, HEIGHT);
            drawBoard();
        }
        animationTimer.start();
    }

    /**
     * A single tick in the game.
     */
    private void tick() {
        if (!alive)
            return;
        if (direction != null) {
            switch (direction) {
                case "up" -> board.upArrow();
                case "down" -> board.downArrow();
                case "left" -> board.leftArrow();
                case "right" -> board.rightArrow();
            }
        }
        if (!board.moveForward()) {
            endGame.setText("You lost!");
            alive = false;
        }
        speed = board.getScore() + 3;
        drawBoard();
    }
}
