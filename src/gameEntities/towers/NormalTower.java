package gameEntities.towers;

import game.GameField;
import gameEntities.enemies.Enemy;
import javafx.scene.paint.Color;
import sun.plugin.com.event.COMEventListener;

import java.util.ArrayList;

public class NormalTower extends Tower
{
    public NormalTower()
    {
        tower = GameField.split(GameField.image_base, 10, 19);
        posX = 7*64;
        posY = 8*64;
        range = 300;
        angle = 0;
        bullet = new Bullet(this);
        circleRange.setFill(Color.BLACK);
        circleRange.setCenterX(posX);
        circleRange.setCenterY(posY);
        circleRange.setRadius(range);
        circleRange.setStroke(Color.BLACK);
    }

    @Override
    public void add(ArrayList<Tower> towerList)
    {
        NormalTower normalTower = new NormalTower();
        towerList.add(normalTower);
    }

}
