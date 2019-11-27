package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Menu extends Main
{
    public static Image backGround;
    public static Image logo;

    public Menu()
    {
        backGround = new Image("file:Sources/Sample.png");
        logo = new Image("file:SourcesTD_logo_final.png");
    }
    public void draw(GraphicsContext gc, Stage stage)
    {
        System.out.println("Draw");
//        Group root2 = new Group();
//        GameField.menuScene = new Scene(root2);
//        Canvas canvas1 = new Canvas(1377, 772.5);
//        GraphicsContext gc1 = canvas1.getGraphicsContext2D();

        gc.drawImage(backGround, 0, 0);
//        gc.drawImage(logo, 0.5, 0.5, 700, 150);
        gc.drawImage(logo, 200, 200);
    }
}
