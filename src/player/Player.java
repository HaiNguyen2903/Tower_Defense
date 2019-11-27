package player;

import gameEntities.enemies.Enemy;

import java.util.ArrayList;

public class Player
{
    protected double score;
    protected double gold;
    protected int maxHP;
    protected int currentHP;
    protected double d;

    public Player()
    {
        maxHP = 100;
        currentHP = 100;
        d = (double) currentHP / maxHP;
    }

    public int getCurrentHP()
    {
        return this.currentHP;
    }
    public void setCurrentHP(int currentHP)
    {
        this.currentHP = currentHP;
    }

    public double getD()
    {
        return d;
    }
    public void setD(double d)
    {
        this.d = d;
    }

    public double getScore()
    {
        return score;
    }
    public void setScore(double score)
    {
        this.score = score;
    }
}
