package game;

import com.sun.javafx.scene.paint.GradientUtils;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import player.Player;
import player.PlayerHP;

public class Main extends Application
{
    public static Scene playScreen;
    public PlayScreen playScreen_ = new PlayScreen();
    public static Group root = new Group();
    public static Scene scene = new Scene(root);
    public static Canvas canvas = new Canvas(64 * 21, 64 * 14);
    public static GraphicsContext gc = canvas.getGraphicsContext2D();

  //  PlayScreen playScreen = new PlayScreen();

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(scene);
        root.getChildren().add(canvas);
        gc.scale(GameField.scaleY, GameField.scaleX);

        GameField gameField = new GameField();
        gameField.initialize();

 //       GameField.menu.draw(gc, primaryStage);

 //       playScreen_.draw();
        AnimationTimer timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                gameField.gameLoop(gc, root);
            }
        };
        timer.start();
        primaryStage.show();
    }
}
