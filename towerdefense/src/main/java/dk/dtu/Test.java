package dk.dtu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Test extends Application {
    private static final int GRID_SIZE = 10;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        GridPane player1Maze = createMazePane();
        GridPane player2Maze = createMazePane();

        root.setLeft(player1Maze);
        root.setRight(player2Maze);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Split-Screen Tower Defense");
        primaryStage.show();

        handleTowerPlacement(player1Maze, 1);
        handleTowerPlacement(player2Maze, 2);
    }

    private GridPane createMazePane() {
        GridPane mazePane = new GridPane();
        // Add maze elements or background here
        return mazePane;
    }

    private void handleTowerPlacement(GridPane mazePane, int player) {
        mazePane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                int col = (int) (event.getX() / (mazePane.getWidth() / GRID_SIZE));
                int row = (int) (event.getY() / (mazePane.getHeight() / GRID_SIZE));

                // You can now place a tower at (col, row) for the respective player
                // Example: placeTower(player, col, row);
                System.out.println("Player " + player + " placed a tower at (" + col + ", " + row + ")");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
