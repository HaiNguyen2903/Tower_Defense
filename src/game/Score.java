package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Score extends GameStage {
    final static Image backGround2 = new Image("file:resources/images/background2.png");

    public static void draw (Stage theStage){
        Group root2 = new Group();
        score = new Scene(root2);
        score.setOnKeyTyped(event ->
        {
            theStage.setScene(menu);
        });
        Canvas canvas2 = new Canvas(1377, 772.5);
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();

        GameField.drawImage(gc2, backGround2, 1.5, 1.5, 0, 0);

        Text tScore2 = GameField.drawText("SCORE", 200, 250);
        Text tName = GameField.drawText("NAME", 880, 250);

        root2.getChildren().addAll(canvas2, tName, tScore2);
    }
}
