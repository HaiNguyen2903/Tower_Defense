package gameEntities.towers;

import game.GameField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class NormalTower extends Tower {
    public NormalTower(int x, int y) {
        super(x, y);
        towerImage = GameField.split(baseImage, 10, 19);
        towerView.setImage(towerImage);
        buyingCost = newBuyingCost;
        sellingCost = newSellingCost;
        range = newRange;
        power = newPower;
        rotationSpeed = newRotationSpeed;
        rangeCircle = new Circle(x + towerImage.getWidth() / 2, y + towerImage.getHeight() / 2, range, Color.rgb(0, 0, 0, 0));
        rangeCircle.setStroke(Color.GREEN);
        rangeCircle.setStrokeWidth(3);
        bullet = new NormalBullet(this);
    }

    private static int newBuyingCost = 100;
    private static int newSellingCost = 60;
    private static double newRange = 200;
    private static double newPower = 0.6;
    private static double newRotationSpeed = 3;
}
