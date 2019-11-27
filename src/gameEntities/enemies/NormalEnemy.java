package gameEntities.enemies;

import game.GameField;
import javafx.scene.image.Image;

import java.util.*;

public class NormalEnemy extends Enemy {
    public NormalEnemy()
    {
        enemy = GameField.split(GameField.image_base, 10, 15);
        speed = 4;
        number = 0;
        direction = "GO_RIGHT";
    }

    public void add(Queue<Enemy> startTroop)
    {
        for (int i = 0; i < number; i++)
        {
            NormalEnemy normalEnemy = new NormalEnemy();
            startTroop.add(normalEnemy);
            normalEnemy.currentX = ((-0-1)*64 + (-0-2)*64)/2;
            normalEnemy.currentY = (3*64 + 4*64)/2;
        }
    }

}
