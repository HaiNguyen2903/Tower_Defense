package gameEntities.towers;

import com.sun.scenario.effect.impl.BufferUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import game.GameField;
import gameEntities.enemies.Enemy;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import org.omg.IOP.ENCODING_CDR_ENCAPS;

import java.util.ArrayList;
import java.util.Queue;

public abstract class Tower
{
    protected Enemy target;
    protected double angle;
    protected double range;
    protected double shootingSpeed;
    protected double posX;
    protected double posY;
    protected Circle circleRange = new Circle();
    protected Image tower;
    protected Bullet bullet;

    public double getPosX() {
        return this.posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getRange()
    {
        return range;
    }

    public void setRange(double range)
    {
        this.range = range;
    }

    public abstract void add(ArrayList<Tower> towerList);

    public void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy()); // NO GC SCALE
        gc.setTransform(r.getMxx()*GameField.scaleX, r.getMyx()*GameField.scaleX,
                r.getMxy()*GameField.scaleX, r.getMyy()*GameField.scaleY,
                r.getTx()*GameField.scaleY, r.getTy()*GameField.scaleY);
    }

    public void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }

    public void drawRange(Group root)
    {
        root.getChildren().add(circleRange);
    }

    public boolean checkInRange(double enemyX, double enemyY)
    {
        return Math.sqrt((posX - enemyX) * (posX - enemyX) + (posY - enemyY) * (posY - enemyY)) < this.range;
    }


    public Enemy findTarget(ArrayList<Enemy> movingEnemies)
    {
        if (movingEnemies.size() != 0)
        {
            target = GameField.movingEnemies.get(0);
            if (checkInRange(target.getCurrentX(), target.getCurrentY()) == true)
            {
                return target;
            }
            if (checkInRange(target.getCurrentX(), target.getCurrentY()) == false)
                {
                for (int i = 1; i < movingEnemies.size(); i++)
                {
                   // if (target.getDistance() < movingEnemies.get(i).getDistance())
                    if (checkInRange(movingEnemies.get(i).getCurrentX(), movingEnemies.get(i).getCurrentY()))
                    {
                        target = movingEnemies.get(i);
                        return target;
                    }
                }
            //    return target;
            }
        }
        return target;
    }

    public void draw(GraphicsContext gc, Group root)
    {
  //      drawRange(root);
        bullet.draw(gc);
        drawRotatedImage(gc, tower, angle, posX, posY);
    }

    public void update(ArrayList<Enemy> movingEnemies, GraphicsContext gc)
    {
        if (movingEnemies.size() != 0)
        {
            target = findTarget(movingEnemies);
            double enemyX =  target.getCurrentX();
            double enemyY =  target.getCurrentY();
            if (enemyX > 0)
            {
                if (checkInRange(enemyX, enemyY))
                {
                    bullet.shoot(this, target);
                    double dx = Math.abs(enemyX - this.posX);
                    double dy = Math.abs(enemyY - this.posY);
                    double alpha = Math.atan2(dy, dx) * 180 / Math.PI;

                    if (this.posX <= enemyX)
                    {
                        if (this.posY > enemyY)
                        {
                            angle =  -(alpha + 90) + 180;
                        }
                        if (this.posY < enemyY)
                        {
                            angle =  -(90 - alpha) + 180;
                        }
                    }
                    if (this.posX > enemyX)
                    {
                        if (this.posY > enemyY)
                        {
                            angle = (90 + alpha) + 180;
                        }
                        if (this.posY < enemyY)
                        {
                            angle = (90 - alpha) + 180;
                        }
                    }
                }
            }
        //    System.out.print(enemyX + "\t" + enemyY + "\t\t" + posX + "\t" + posY + "\t" + angle + "\n");
        }
    }
}
