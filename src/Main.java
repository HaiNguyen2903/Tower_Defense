import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main extends Application {

    public static Image split(Image img, int i, int j) {
        ImageView imageView = new ImageView(img);
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setViewport(new Rectangle2D(j*64, i*64, 64, 64));
        return imageView.snapshot(snapshotParameters, null);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(64*21,64*14);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Image image = new Image("a.png");

        ArrayList<Image> imageArrayList = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 5; j++) {
                Image img = split(image, i+6, j);
                imageArrayList.add(img);
            }
        }
        Image img = split(image, 4,22);
        imageArrayList.add(img);
        int map[][] = new int[14][21];

        Scanner sc = new Scanner(new File("map2.txt"));
        for(int i = 0; i < 14; i++) {
            for(int j = 0; j < 21; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        sc.close();

        for(int i = 0; i < 14; i++) {
            for(int j = 0; j < 21; j++) {
                System.out.print(map[i][j] + (j == 20 ? "\n" : " "));
                graphicsContext.drawImage(imageArrayList.get(map[i][j]), j*64, i*64);
            }
        }
        primaryStage.show();
    }
}

