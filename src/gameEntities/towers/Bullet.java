package gameEntities.towers;

import com.sun.javafx.geom.Vec2d;
import game.GameField;
import gameEntities.enemies.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Bullet
{
    protected Image bullet;
    protected double speed;
    protected double damage;
    protected double vX;
    protected double vY;
    protected double posX;
    protected double posY;

    public Bullet(Tower tower)
    {
        bullet = GameField.split(GameField.image_base, 11, 19);
        speed = 10;
        damage = 10;
        this.posX = tower.getPosX();
        this.posY = tower.getPosY();
    }

    public double getPosX()
    {
        return this.posX;
    }

    public void setPosX(double posX)
    {
        this.posX = posX;
    }

    public double getPosY()
    {
        return posY;
    }

    public void setPosY(double posY)
    {
        this.posY = posY;
    }

    public void draw(GraphicsContext gc)
    {
        gc.drawImage(bullet, this.posX, this.posY);
    }

    public double getDistance(Tower tower, Enemy enemy)
    {
        return Math.sqrt((tower.getPosX() - enemy.getCurrentX())*(tower.getPosX()- enemy.getCurrentX())
                         +(tower.getPosY() - enemy.getCurrentY())*(tower.getPosY() - enemy.getCurrentY()));
    }



    public void shoot(Tower tower, Enemy target)
    {
        double d = Vec2d.distance(target.getCurrentX(), target.getCurrentY(), this.posX, this.posY); // giữa đạn và lính
        double dx = target.getCurrentX() - this.posX;
        double dy = target.getCurrentY() - this.posY;

        speed = getDistance(tower, target)/100 + 2.5;

        if (d < speed)
        {
            vX = dx;
            vY = dy;
            this.posX += vX;
            this.posY += vY;
            this.posX=tower.getPosX();
            this.posY=tower.getPosY();

        }
        else
        {
            vX = dx * this.speed / d;
            vY = dy * this.speed / d;
            this.posX += vX;
            this.posY += vY;
        }
    }
}
