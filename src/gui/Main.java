package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The main class of the snake application.
 *
 * @author mikolajdeja
 * @version 2021.05.15
 */
public class Main extends Application {
    /**
     * Start the gui of the application.
     *
     * @param primaryStage The primary stage.
     * @throws Exception The exceptions that may be thrown by the FXMLLoader.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("snake.fxml")));
        primaryStage.setTitle("Snake");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }

    /**
     * Launch the application.
     *
     * @param args The arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
