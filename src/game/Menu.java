package game;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class Menu extends GameStage {
    final static Image backGround = new Image("file:resources/images/Sample.png");
    final static Image logo = new Image("file:resources/images/TD_logo_final.png");

    public static void draw(Stage theStage) {

        // =============== CREATE SCENE =====================================//
        Group root1 = new Group();
        menu = new Scene(root1);
        Canvas canvas1 = new Canvas(1377, 772.5);
        GraphicsContext gc1 = canvas1.getGraphicsContext2D();

        GameField.drawImage(gc1, backGround, 1.5, 1.5, 0, 0);
        GameField.drawImage(gc1, logo, 0.5, 0.5, 700, 150);

        // =================== CREATE BUTTONS ====================//

        Text tPlay = GameField.drawText("PLAY", 600, 600);
        tPlay.setOnMouseEntered(event ->
        {
            tPlay.setFill(Color.YELLOW);
        });
        tPlay.setOnMouseExited(event ->
        {
            tPlay.setFill(Color.WHITE);
        });
        tPlay.setOnMouseClicked(event ->
        {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("");
            dialog.setHeaderText("Please enter your name:");

            ButtonType buttonTypeStart = new ButtonType("Start", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(buttonTypeStart, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 100, 10, 10));

            TextField userName = new TextField();
            userName.setPromptText("Name");

            grid.add(new Label("Your name:"), 0, 0);
            grid.add(userName, 1, 0);

            Node startButton = dialog.getDialogPane().lookupButton(buttonTypeStart);
            startButton.setDisable(true);

            userName.textProperty().addListener((observable, oldValue, newValue) -> {
                startButton.setDisable(newValue.trim().isEmpty());
            });
            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == buttonTypeStart) {
                    return userName.getText();
                }
                return null;
            });
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(playerName -> {
                name = playerName;
                PlayScreen.draw(theStage);
                theStage.setScene(playscreen);

            });
        });

        Text tScore = GameField.drawText("SCORE", 140, 600);
        tScore.setOnMouseEntered(event ->
        {
            tScore.setFill(Color.YELLOW);
        });
        tScore.setOnMouseExited(event ->
        {
            tScore.setFill(Color.WHITE);
        });
        tScore.setOnMouseClicked(event ->
        {
            Score.draw(theStage);
            theStage.setScene(score);
        });

        Text tQuit = GameField.drawText("QUIT", 1080, 600);
        tQuit.setOnMouseEntered(event ->
        {
            tQuit.setFill(Color.YELLOW);
        });
        tQuit.setOnMouseExited(event ->

        {
            tQuit.setFill(Color.WHITE);
        });
        tQuit.setOnMouseClicked(event ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Exit");
            alert.setContentText("Are you sure you want to exit?");

            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) theStage.close();
        });

        root1.getChildren().addAll(canvas1, tPlay, tScore, tQuit);
    }
}
