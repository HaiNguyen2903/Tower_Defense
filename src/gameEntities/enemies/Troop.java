package gameEntities.enemies;

import game.Point;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public abstract class Troop {
    protected ArrayList<Enemy> troop;
    protected Point[] troopInfos = new Point[]{
            new Point(0, 0),
            new Point(50, 3 * 64),
            new Point(50, 3 * 64),
            new Point(75, 1.5 * 64),
            new Point(75, 1.5 * 64),
            new Point(100, 64),
            new Point(100, 64)
    };
    protected double number;
    protected double space;

    public ArrayList<Enemy> getTroop() {
        return troop;
    }
    public void setTroopInfos(int level) {
        this.number = troopInfos[level].x;
        this.space = troopInfos[level].y;
    }
    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public abstract void createTroop();
    public abstract void checkExistance(AnchorPane root, Text tHP, int PlayerHP);
}
