package gameEntities.gameTiles;

import game.GameField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Road extends GameTile
{
    public Road()
    {
        imageArrayList = new ArrayList<>();
        map = new int[14][21];
    }

    public void readFile()
    {
        try
        {
            sc = new Scanner(new File("map2.txt"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        for(int i = 0; i < 14; i++)
        {
            for(int j = 0; j < 21; j++)
            {
                map[i][j] = sc.nextInt();
            }
        }
        sc.close();
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                Image img = GameField.split(GameField.image_base, i+6, j);
                imageArrayList.add(img);
            }
        }
        Image img = GameField.split(GameField.image_base, 4,22);
        imageArrayList.add(img);
    }

    public int[][] getMap()
    {
        return this.map;
    }

    public void setMap(int[][] map)
    {
        this.map = map;
    }

    public void draw(GraphicsContext gc) /*throws FileNotFoundException*/
    {
        for(int i = 0; i < 14; i++)
        {
            for(int j = 0; j < 21; j++)
            {
                gc.drawImage(imageArrayList.get(map[i][j]), j*64, i*64);
            }
        }

        for(int i=0;i<14;i++)
        {
            for(int j=0;j<21;j++)
            {
                gc.strokeLine(j*64,i*64,(j+1)*64,i*64);
            }
        }

        for(int i = 0; i < 14; i++)
        {
            for(int j = 0; j < 21; j++)
            {
                gc.strokeLine(j*64,i*64,j*64,(i+1)*64);
            }
        }
    }
}
