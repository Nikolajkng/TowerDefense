package dk.dtu.app.view.GameBoardsGUI;

import java.io.File;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LostAlertGUI extends Application{

    Button BackToMenu = new Button();

    @Override
    public void start(Stage loserStage) {

        loserStage.initModality(Modality.APPLICATION_MODAL);
        loserStage.setTitle("Game Over");
        loserStage.setMinHeight(200);
        loserStage.setMinWidth(400);

        VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		Label label = new Label("GAME OVER");
		Font font = new Font ("Commic Sans MS", 25);
		layout.getChildren().add(label);
		label.setFont(font);

        Text text = new Text(" YOU LOST ");
		text.setFill(Paint.valueOf("linear-gradient(to bottom, gold, goldenrod)"));
		text.setFont(new Font("Impact",50));
		layout.getChildren().add(text);

		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(3));
		transition.setNode(text);
		transition.setFromY(-100);
		transition.setToY(0);
		transition.play();

        Button close = new Button();
		close.setText("  NEW GAME  ");
		close.setOnAction(e -> loserStage.close());
		close.setStyle("-fx-background-radius: 18");
		layout.getChildren().add(close);
		Font font11 = new Font("Arial", 12);
		close.setFont(font11);
		close.setStyle("-fx-background-color: linear-gradient(to bottom, green, #33CC66);"
				+ "-fx-border-color: black;"
				+ "-fx-border-radius: 15;"
				+ " -fx-background-radius: 15;"
				+ " -fx-text-fill : black;"
				+ " -fx-border-width: 1;"
				+ " -fx-font-size : 15px");

		close.setOnMouseEntered(e -> close.setScaleX(1.2));
		close.setOnMouseExited(e -> close.setScaleX(1));
		close.setOnMouseEntered(e -> close.setScaleY(1.2));
		close.setOnMouseExited(e -> close.setScaleY(1));

        close.setOnAction(event -> {
			loserStage.close();
		});

        Scene scene = new Scene(layout,300, 200);
		loserStage.setScene(scene);
		loserStage.show();

        Image popupImage = new Image(new File(
				"/dk/dtu/app/view/Images/46.png").toURI().toString());
		layout.setBackground(new Background(new BackgroundImage(popupImage,
				BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false))));
    }

}
