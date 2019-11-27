package gameEntities.enemies;

import game.GameField;
import javafx.scene.image.Image;

import java.util.*;

public class BossEnemy extends Enemy
{
    public BossEnemy()
    {
        enemy = GameField.split(GameField.image_base, 10, 16);
        speed = 2;
        number = 5;
        distance = 0;
        direction = "GO_RIGHT";
    }

    public void add(Queue<Enemy> startTroop)
    {
        for (int i = 0; i < number; i++)
        {
            BossEnemy bossEnemy = new BossEnemy();
            startTroop.add(bossEnemy);
            bossEnemy.currentX = ((-0-1)*64 + (-0-2)*64)/2;
            bossEnemy.currentY = (3*64 + 4*64)/2;
        }
    }

}
