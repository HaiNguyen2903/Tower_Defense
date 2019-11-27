package gameEntities.gameTiles;

import gameEntities.enemies.Enemy;

public class EndPoint
{
    protected double endX;
    protected double endY;

    public double getEndX()
    {
        return this.endX;
    }
    public void setEndX(double endX)
    {
        this.endX = endX;
    }

    public double getEndY()
    {
        return this.endY;
    }
    public void setEndY(double endY)
    {
        this.endY = endY;
    }

    public EndPoint()
    {
        endX = 21*64 - 40;
        endY = (10*64 + 11*64)/2;
    }
}
