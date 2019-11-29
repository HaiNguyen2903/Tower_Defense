package gameEntities.towers;

import gameEntities.enemies.Enemy;
import gameEntities.enemies.Troop;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public abstract class Tower {
    protected Image baseImage = new Image("file:resources/images/base.png");
    protected Image towerImage;
    protected ImageView towerView = new ImageView();
    protected int buyingCost;
    protected int sellingCost;
    protected int x;
    protected int y;
    protected double range;
    protected double power;
    protected double angle = 0;
    protected double rotationSpeed;
    protected Enemy target;
    protected double targetAngle;
    protected Circle rangeCircle;
    protected Bullet bullet;

    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setCoordinate() {
        towerView.setX(x);
        towerView.setY(y);
    }

    public boolean inRange(Enemy enemy) {
        return Math.sqrt((x - enemy.getX()) * (x - enemy.getX()) + (y - enemy.getY()) * (y - enemy.getY())) <= this.range;
    }

    public boolean foundTarget(Troop troop) {
        if (troop.getTroop().size() != 0) {
            target = troop.getTroop().get(0);
            if (inRange(target)) {
                double dx = target.getX() - x;
                double dy = target.getY() - y;
                targetAngle = (Math.atan2(dy, dx) + Math.PI / 2) / Math.PI * 180;
                if (targetAngle > 180) targetAngle = targetAngle - 360;
                if (targetAngle < -180) targetAngle = targetAngle + 360;
                return true;
            } else {
                for (int i = 1; i < troop.getTroop().size(); i++) {
                    if (inRange(troop.getTroop().get(i))) {
                        target = troop.getTroop().get(i);
                        double dx = target.getX() - x;
                        double dy = target.getY() - y;
                        targetAngle = (Math.atan2(dy, dx) + Math.PI / 2) / Math.PI * 180;
                        if (targetAngle > 180) targetAngle = targetAngle - 360;
                        if (targetAngle < -180) targetAngle = targetAngle + 360;
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public void followEnemy(Troop troop, AnchorPane root) {
        bullet.move(this, target, troop, root);
        if (attackable()) {
            if (bullet.isMoving)
                bullet.move(this, target, troop, root);
        } else {
            if (angle == 0) {
                if (targetAngle < 0)
                    angle -= rotationSpeed;
                else angle += rotationSpeed;
            }
            // targetAngle and current angle have the same sign
            else if (targetAngle * angle > 0) {
                if (targetAngle > angle) angle += rotationSpeed;
                else angle -= rotationSpeed;
            } else if (targetAngle * angle < 0) {
                if (angle > 0) {
                    if (angle + Math.abs(targetAngle) > 180)
                        angle += rotationSpeed;
                    else angle -= rotationSpeed;
                } else {
                    if (Math.abs(angle) + targetAngle > 180)
                        angle -= rotationSpeed;
                    else angle += rotationSpeed;
                }
            }
            if (angle > 180) angle = angle - 360;
            if (angle < -180) angle = angle + 360;
            towerView.setRotate(angle);
        }
    }

    public void returnNormal () {
        bullet.setCoordinate(-64, -64);
        bullet.setX(x);
        bullet.setY(y);

        if (angle > 0) angle -= rotationSpeed;
        else if (angle < 0) angle += rotationSpeed;

        if (angle > 180) angle = angle - 360;
        if (angle < -180) angle = angle + 360;
        towerView.setRotate(angle);
    }


    public boolean attackable() {
        return Math.abs(targetAngle - angle) < 1;
    }

    public ImageView getTowerView() {
        return towerView;
    }

    public Circle getRangeCircle() {
        return rangeCircle;
    }

    public Bullet getBullet() {
        return bullet;
    }
}
