package gameEntities.towers;

import com.sun.javafx.geom.Vec2d;
import gameEntities.enemies.Enemy;
import gameEntities.enemies.Troop;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class Bullet {
    protected Image baseImage = new Image("file:resources/images/base.png");
    protected Image bulletImage;
    protected ImageView bulletView = new ImageView();
    protected double moveSpeed;
    protected double x;
    protected double y;
    protected double vecX;
    protected double vecY;
    protected int damage;
    protected boolean isMoving;

    public void setCoordinate(double x, double y) {
        bulletView.setX(x);
        bulletView.setY(y);
    }

    public void move(Tower tower, Enemy target, Troop troop, AnchorPane root) {

        double d = Vec2d.distance(target.getX(), target.getY(), x, y);
        double dx = target.getX() - x;
        double dy = target.getY() - y;

        if (d < moveSpeed) {
            vecX = dx;
            vecY = dy;
            x += vecX;
            y += vecY;
            target.setLeftBlood(target.getLeftBlood() - damage);
            target.getLeftBloodBar().setWidth(64 * target.getLeftBlood() / target.getBlood());
            if (target.getLeftBlood() <= 0) {
                root.getChildren().remove(target.getEnemyView());
                troop.getTroop().remove(target);
                troop.setNumber(troop.getNumber()-1);
            }
            x = tower.x;
            y = tower.y;
        } else {
            vecX = dx * moveSpeed / d;
            vecY = dy * moveSpeed / d;
            x += vecX;
            y += vecY;
            setCoordinate(x, y);
        }
        if (x != tower.x && y != tower.y) isMoving = true;
        else isMoving = false;
    }

    public ImageView getBulletView() {
        return bulletView;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

}
