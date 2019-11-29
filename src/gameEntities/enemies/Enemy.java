package gameEntities.enemies;

<<<<<<< HEAD
import game.GameField;
import game.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class Enemy {
    //Unit infos//
    protected Image baseImage = new Image("file:resources/images/base.png");
    protected Image enemyImage;
    protected ImageView enemyView = new ImageView();
    protected double x;
    protected double y;
    protected Direction direction;
    protected double blood;
    protected double leftBlood;
    protected double speed;
    protected Rectangle leftBloodBar;
    public boolean newTarget = true;

    public void setCoordinate() {
        enemyView.setX(x);
        enemyView.setY(y);
    }

    public void rotate(double angle) {
        enemyView.setRotate(angle);
    }

    public static final Point[] wayPoints = new Point[]{
            new Point(4 * 64 - 32, 4 * 64 - 32),
            new Point(4 * 64 - 32, 10 * 64 - 32),
            new Point(9 * 64 - 32, 10 * 64 - 32),
            new Point(9 * 64 - 32, 4 * 64 - 32),
            new Point(15 * 64 - 32, 4 * 64 - 32),
            new Point(15 * 64 - 32, 10 * 64 - 32),
            new Point(22 * 64 - 32, 10 * 64 - 32)
    };

    protected int wayPointIndex = 0;

    public Point getNextWayPoint() {
        if (wayPointIndex < wayPoints.length - 1)
            return wayPoints[++wayPointIndex];
        return null;
    }

    public void checkDirection() {
        if (wayPointIndex >= wayPoints.length) {
            return;
        }
        Point currentWP = wayPoints[wayPointIndex];
        if (GameField.distance(x, y, currentWP.x, currentWP.y) < speed) {
            Point nextWayPoint = getNextWayPoint();
            if (nextWayPoint == null) return;
            double deltaX = nextWayPoint.x - x;
            double deltaY = nextWayPoint.y - y;
            if (deltaX > speed) {
                direction = Direction.RIGHT;
            } else if (deltaX < -speed) {
                direction = Direction.LEFT;
            } else if (deltaY > speed) {
                direction = Direction.DOWN;
            } else if (deltaY <= -speed) {
                direction = Direction.UP;
            }
        }
    }

    public void update() {
        checkDirection();
        switch (direction) {
            case UP:
                y -= speed;
                setCoordinate();
                leftBloodBar.setX(x);
                leftBloodBar.setY(y - 16);
                rotate(-90);
                break;
            case DOWN:
                y += speed;
                setCoordinate();
                leftBloodBar.setX(x);
                leftBloodBar.setY(y - 16);
                rotate(90);
                break;
            case LEFT:
                x -= speed;
                setCoordinate();
                leftBloodBar.setX(x);
                leftBloodBar.setY(y - 16);
                rotate(180);
                break;
            case RIGHT:
                x += speed;
                setCoordinate();
                leftBloodBar.setX(x);
                leftBloodBar.setY(y - 16);
                rotate(0);
                break;
        }
    }

    public ImageView getEnemyView() {
        return enemyView;
    }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getBlood() {
        return blood;
    }
    public double getLeftBlood() {
        return leftBlood;
    }
    public Rectangle getLeftBloodBar() {
        return leftBloodBar;
    }
    public boolean isNewTarget() {
        return newTarget;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setBlood(double blood) {
        this.blood = blood;
    }
    public void setLeftBlood(double leftBlood) {
        this.leftBlood = leftBlood;
    }
    public void setNewTarget(boolean newTarget) {
        this.newTarget = newTarget;
    }
=======
public interface Enemy
{
    public int blood = 0;
    public int speed = 0;
    public int armor = 0;
>>>>>>> parent of ec382e8... update
}
