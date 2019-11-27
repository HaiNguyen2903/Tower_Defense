package gameEntities.gameTiles;

import game.GameField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Scanner;


public abstract class GameTile
{
    protected Image image;
    protected ArrayList<Image> imageArrayList;
    protected Scanner sc;
    protected int[][] map;

    public abstract void draw(GraphicsContext gc);
}
