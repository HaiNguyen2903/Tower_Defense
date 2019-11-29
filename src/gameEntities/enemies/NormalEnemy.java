package gameEntities.enemies;

import game.GameField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NormalEnemy extends Enemy {
    public NormalEnemy() {
        enemyImage = GameField.split(baseImage, 10, 15);
        enemyView.setImage(enemyImage);
        x = -32;
        y = 7  * 64 / 2;
        direction = Direction.RIGHT;
        blood = newBlood;
        leftBlood = blood;
        speed = newSpeed;
        leftBloodBar = new Rectangle(x, y - 16, 64, 16);
        leftBloodBar.setFill(Color.RED);
        leftBloodBar.setStroke(Color.WHITE);
        setCoordinate();
    }

    private static double newBlood = 500;
    private static double newSpeed = 4;
}