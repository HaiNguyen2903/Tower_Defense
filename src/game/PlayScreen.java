package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PlayScreen
{
//    Group root = new Group();
//    Scene scene = new Scene(root);
//    Canvas canvas = new Canvas(64 * 21, 64 * 14);
//    GraphicsContext gc = canvas.getGraphicsContext2D();
//
//    public void draw(Stage stage)
//    {
//        stage.setTitle("Tower Defense");
//        stage.setScene(scene);
//        root.getChildren().add(canvas);
//        gc.scale(GameField.scaleY, GameField.scaleX);
//    }

    public void draw()
    {
        AnchorPane root3 = new AnchorPane();
        Main.playScreen = new Scene(root3);

        Canvas canvas3 = new Canvas(64 * 21, 64 * 14);
        AnchorPane.setTopAnchor(canvas3, 0.0);
        AnchorPane.setLeftAnchor(canvas3, 0.0);
        AnchorPane.setRightAnchor(canvas3, 100.0);
        GraphicsContext gc3 = canvas3.getGraphicsContext2D();

        Button towerButton = new Button("Create a Tower");
        AnchorPane.setTopAnchor(towerButton, 0.0);
        AnchorPane.setLeftAnchor(towerButton, (double) 64 * 21 + 10);
        AnchorPane.setRightAnchor(towerButton, 10.0);
    }
}
