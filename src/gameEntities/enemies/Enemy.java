package gameEntities.enemies;

import game.GameField;
import gameEntities.gameTiles.EndPoint;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import player.Player;

import java.util.*;

public abstract class Enemy
{
    protected String direction;
    protected double blood;
    protected double speed;
    protected double distance;
    protected double currentX;
    protected double currentY;
    protected int number;
    protected Image enemy;
    protected Queue<Enemy> movingEnemies = new LinkedList<>();

    public double getCurrentX()
    {
        return this.currentX;
    }
    public void setCurrentX(double currentX)
    {
        this.currentX = currentX;
    }

    public double getCurrentY()
    {
        return this.currentY;
    }
    public void setCurrentY(double currentY)
    {
        this.currentY = currentY;
    }

    public int getNumber()
    {
        return this.number;
    }
    public void setNumber(int number)
    {
        this.number = number;
    }

    public double getSpeed()
    {
        return this.speed;
    }
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public Queue<Enemy> getMovingEnemies()
    {
        return this.movingEnemies;
    }
    public void setMovingEnemies(Queue<Enemy> movingEnemies)
    {
        this.movingEnemies = movingEnemies;
    }

    public double getDistance()
    {
        return this.distance;
    }
    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py)
    {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy()); // NO GC SCALE
        gc.setTransform(r.getMxx()*GameField.scaleX, r.getMyx()*GameField.scaleX,
                r.getMxy()*GameField.scaleX, r.getMyy()*GameField.scaleY,
                r.getTx()*GameField.scaleY, r.getTy()*GameField.scaleY);
    }

    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy)
    {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth()/2, tlpy + image.getHeight()/2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }

    public void draw(GraphicsContext gc)
    {
        switch (direction)
        {
            case "GO_RIGHT":
                gc.drawImage(enemy,currentX, currentY);
                break;
            case "GO_LEFT":
                drawRotatedImage(gc, enemy, 180, currentX, currentY);
                break;
            case "GO_DOWN":
                drawRotatedImage(gc, enemy, 90, currentX, currentY);
                break;
            case "GO_UP":
                drawRotatedImage(gc, enemy, -90, currentX, currentY);
                break;
        }
    }

    public void checkDirection(int[][] map)
    {
        if (currentY >= 64 && currentX >= 64)
        {
            switch (direction)
            {
                case "GO_RIGHT":
                    if (map[Math.round((float) ((currentY + 32) / 64 - 1))][Math.round((float) ((currentX) / 64))] == 4)
                    {
                        direction = "GO_DOWN";
                        break;
                    }
                    if (map[Math.round((float) ((currentY + 32) / 64))][Math.round((float) ((currentX) / 64))] == 9)
                    {
                        direction = "GO_UP";
                        break;
                    }
                case "GO_DOWN":
                    if (map[Math.round((float) ((currentY) / 64))][Math.round((float) ((currentX+32) / 64 - 1))] == 8)
                    {
                        direction = "GO_RIGHT";
                        break;
                    }
                case "GO_UP":
                    if (map[Math.round((float) ((currentY + 64) / 64 - 1))][Math.round((float) ((currentX) / 64 - 1))] == 3)
                    {
                        direction = "GO_RIGHT";
                        break;
                    }
            }
        }

    }

    public void update(int[][] map)
    {
        checkDirection(map);
        if (direction == "GO_RIGHT")
        {
            currentX += speed;
        }
        if (direction == "GO_DOWN")
        {
            currentY += speed;
        }
        if (direction == "GO_UP")
        {
            currentY -= speed;
        }
        distance += speed;
    }

    public void checkExist(ArrayList<Enemy> movingEnemies, EndPoint endPoint, Player player)
    {
        for (int i = 0; i < movingEnemies.size(); i++)
        {
            if (movingEnemies.get(i).currentX >= endPoint.getEndX())
            {
                player.setCurrentHP(player.getCurrentHP()-1);
                System.out.println("PLAYER HP: " + player.getCurrentHP());
                movingEnemies.remove(i);
                break;
            }
        }
    }
}
