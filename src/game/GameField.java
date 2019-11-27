package game;

import gameEntities.enemies.BossEnemy;
import gameEntities.enemies.Enemy;
import gameEntities.enemies.NormalEnemy;
import gameEntities.gameTiles.EndPoint;
import gameEntities.gameTiles.Road;
import gameEntities.towers.Bullet;
import gameEntities.towers.NormalTower;
import gameEntities.towers.Tower;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import player.Player;
import player.PlayerHP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameField extends GameStage
{
    public static double scaleX = 0.75;
    public static double scaleY = 0.75;

    public static Scene menuScene;
    public static Menu menu;

    public static Image image_base;
    public static EndPoint endPoint;
    public static Road map;

    protected Queue<Enemy> startTroop;
    public static ArrayList<Enemy> movingEnemies;
    protected BossEnemy bossEnemy;
    protected NormalEnemy normalEnemy;

    protected int totalEnemies;
    protected int spamTime;

    protected ArrayList<Tower> towerList;
    protected NormalTower normalTower;

    protected Player player;
    protected PlayerHP playerHP;

    public GameField()
    {
        menu = new Menu();

        image_base = new Image("file:Sources/base.png");
        map = new Road();
        endPoint = new EndPoint();
        startTroop = new LinkedList<>();

        movingEnemies = new ArrayList<>();
        bossEnemy = new BossEnemy();
        normalEnemy = new NormalEnemy();
        totalEnemies = bossEnemy.getNumber() + normalEnemy.getNumber();
        spamTime = 0;
        towerList = new ArrayList<>();
        normalTower = new NormalTower();

        player = new Player();
        playerHP = new PlayerHP();
    }

    public static Image split(Image img, int i, int j)
    {
        ImageView imageView = new ImageView(img);
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);
        snapshotParameters.setViewport(new Rectangle2D(j*64, i*64, 64, 64));
        return imageView.snapshot(snapshotParameters, null);
    }

    public void addEnemies()
    {
        bossEnemy.add(startTroop);
        normalEnemy.add(startTroop);
    }

    public void addTowers()
    {
        normalTower.add(towerList);

    }

    public void spamEnemies(GraphicsContext gc)
    {
        if (startTroop.isEmpty() != true)
        {
            if (spamTime >= 200 / (startTroop.peek().getSpeed()))
            {
                movingEnemies.add(startTroop.peek());
                startTroop.poll();
                spamTime = 0;
            }
            spamTime++;
        }
    }

    public void draw(GraphicsContext gc, Group root)
    {
        map.draw(gc);
    //    playerHP.draw(root);
        spamEnemies(gc);

        for (int i = 0; i < movingEnemies.size(); i++)
        {
            movingEnemies.get(i).draw(gc);
            movingEnemies.get(i).update(map.getMap());
        }

        for (int i = 0; i < towerList.size(); i++)
        {
            towerList.get(i).draw(gc, root);
            towerList.get(i).update(movingEnemies, gc);
        }
    }

    public void check()
    {
        normalEnemy.checkExist(movingEnemies, endPoint, player);
        bossEnemy.checkExist(movingEnemies, endPoint, player);
//        smallerEnemy.checkExist();
    }

    public void initialize()
    {
        map.readFile();
        addEnemies();
        addTowers();
    }

    public void gameLoop(GraphicsContext gc, Group root)
    {
        draw(gc, root);
        check();
    }
}