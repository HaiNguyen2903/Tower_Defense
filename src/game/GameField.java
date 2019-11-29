package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GameField {
    final static Font theFont = Font.loadFont("file:resources/fonts/Squirk-RMvV.ttf", 84);

    public static void drawMap(GraphicsContext gc, ArrayList<Image> imageArrayList, int[][] map) {
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 21; j++) {
                gc.drawImage(imageArrayList.get(map[i][j]), j * 64, i * 64);
            }
        }
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 21; j++) {
                gc.strokeLine(j * 64, i * 64, (j + 1) * 64, i * 64);
            }
        }

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 21; j++) {
                gc.strokeLine(j * 64, i * 64, j * 64, (i + 1) * 64);
            }
        }
    }

    public static void drawImage(GraphicsContext gc, Image img, double scale_x, double scale_y, int x, int y) {
        gc.scale(scale_x, scale_y);
        gc.drawImage(img, x, y);
    }

    public static Text drawText(String string, int x, int y) {
        Text text = new Text(x, y, string);
        text.setFont(theFont);
        text.setFill(Color.WHITE);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(5);
        return text;
    }

    public static Image split(Image image, int i, int j) {
        ImageView imageView = new ImageView(image);
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);
        snapshotParameters.setViewport(new Rectangle2D(j * 64, i * 64, 64, 64));
        return imageView.snapshot(snapshotParameters, null);
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
