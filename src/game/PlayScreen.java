package game;

import gameEntities.enemies.LargeTroop;
import gameEntities.enemies.NormalTroop;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import gameEntities.towers.NormalTower;
import javafx.stage.Stage;


public class PlayScreen extends GameStage {
    final static Font theFont = Font.loadFont("file:resources/fonts/Squirk-RMvV.ttf", 50);
    final static Image backGround3 = new Image("file:resources/images/base.png");

    static int level = 1;
    static int PlayerHP = 100;
    static int gold = 50;
    static NormalTroop normal = new NormalTroop(level);
    static LargeTroop large = new LargeTroop(level);

    public static void draw(Stage theStage) {
        try {
            // =============== CREATE SCENE =====================================//
            AnchorPane root3 = new AnchorPane();
            playscreen = new Scene(root3);

            Canvas canvas3 = new Canvas(64 * 21, 64 * 14);
            AnchorPane.setTopAnchor(canvas3, 0.0);
            AnchorPane.setLeftAnchor(canvas3, 0.0);
            AnchorPane.setRightAnchor(canvas3, 100.0);
            GraphicsContext gc3 = canvas3.getGraphicsContext2D();

            // =================== ADD IMAGES TO IMAGE ARRAY ====================//
            ArrayList<Image> imageArrayList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    Image img = GameField.split(backGround3, i + 6, j);
                    imageArrayList.add(img);
                }
            }
            Image img = GameField.split(backGround3, 4, 22);
            imageArrayList.add(img);

            // ================================= READ FILE TXT ==================================//
            Scanner sc = new Scanner(new File("map.txt"));
            int map[][] = new int[14][21];
            for (int i = 0; i < 14; i++) {
                for (int j = 0; j < 21; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            sc.close();

            // =============================== DRAW MAP =========================================//
            GameField.drawMap(gc3, imageArrayList, map);

            Button towerButton = new Button("Create a Tower");
            AnchorPane.setTopAnchor(towerButton, 0.0);
            AnchorPane.setLeftAnchor(towerButton, (double) 64 * 21 + 10);
            AnchorPane.setRightAnchor(towerButton, 10.0);

            ArrayList<NormalTower> normalTowers = new ArrayList<>();
            towerButton.setOnAction(event -> {
                int size = normalTowers.size();
                playscreen.setOnMouseClicked(event1 -> {
                    if (normalTowers.size() == size) {
                        int x = (int) event1.getSceneX();
                        int y = (int) event1.getSceneY();
                        NormalTower normalTower = new NormalTower((x / 64) * 64, (y / 64) * 64);
                        normalTower.setCoordinate();
                        normalTowers.add(normalTower);

                        root3.getChildren().addAll(normalTower.getBullet().getBulletView(), normalTower.getTowerView(), normalTower.getRangeCircle());
                    }
                });
            });

            Button normalEnemyButton = new Button("Create Normal Enemies");
            AnchorPane.setTopAnchor(normalEnemyButton, 40.0);
            AnchorPane.setLeftAnchor(normalEnemyButton, (double) 64 * 21 + 10);
            AnchorPane.setRightAnchor(normalEnemyButton, 10.0);

            Button largeEnemyButton = new Button("Create Large Enemies");
            AnchorPane.setTopAnchor(largeEnemyButton, 80.0);
            AnchorPane.setLeftAnchor(largeEnemyButton, (double) 64 * 21 + 10);
            AnchorPane.setRightAnchor(largeEnemyButton, 10.0);

            Text tName = new Text("Player Name: " + name);
            tName.setX(64 * 21 + 10);
            tName.setY(170);
            tName.setFont(theFont);
            tName.setFill(Color.WHITE);
            tName.setStroke(Color.BLACK);
            tName.setStrokeWidth(3);

            Text tHP = new Text("Player HP: " + PlayerHP);
            tHP.setX(64 * 21 + 10);
            tHP.setY(260);
            tHP.setFont(theFont);
            tHP.setFill(Color.WHITE);
            tHP.setStroke(Color.BLACK);
            tHP.setStrokeWidth(3);

            normalEnemyButton.setOnAction(event -> {
                normalEnemyButton.setDisable(true);

                AnimationTimer timer1 = new AnimationTimer() {

                    @Override
                    public void handle(long now) {
                        for (int i = 0; i < normalTowers.size(); i++) {
                            if (normalTowers.get(i).foundTarget(normal))
                                normalTowers.get(i).followEnemy(normal, root3);
                            else normalTowers.get(i).returnNormal();
                        }
                    }
                };
                timer1.start();

                AnimationTimer timer2 = new AnimationTimer() {

                    @Override
                    public void handle(long now) {
                        normal.checkExistance(root3, tHP, PlayerHP);
                        for (int i = 0; i < normal.getNumber(); i++) {
                            normal.getTroop().get(i).update();
                        }
                    }
                };
                timer2.start();

                for (int i = 0; i < normal.getNumber(); i++) {
                    root3.getChildren().addAll(normal.getTroop().get(i).getEnemyView(), normal.getTroop().get(i).getLeftBloodBar());
                }
            });

            largeEnemyButton.setOnAction(event -> {
                largeEnemyButton.setDisable(true);

                AnimationTimer timer1 = new AnimationTimer() {

                    @Override
                    public void handle(long now) {
                        for (int i = 0; i < normalTowers.size(); i++) {
                            if (normalTowers.get(i).foundTarget(large))
                                normalTowers.get(i).followEnemy(large, root3);
                            else normalTowers.get(i).returnNormal();
                        }
                    }
                };
                timer1.start();

                AnimationTimer timer2 = new AnimationTimer() {

                    @Override
                    public void handle(long now) {
                        large.checkExistance(root3, tHP, PlayerHP);
                        for (int i = 0; i < large.getNumber(); i++) {
                            large.getTroop().get(i).update();
                        }
                    }
                };
                timer2.start();

                for (int i = 0; i < large.getNumber(); i++) {
                    root3.getChildren().addAll(large.getTroop().get(i).getEnemyView(), large.getTroop().get(i).getLeftBloodBar());
                }
            });

            root3.getChildren().addAll(canvas3, tName, tHP, towerButton, normalEnemyButton, largeEnemyButton);
        } catch (Exception e) {
            System.out.println("Co loi: " + e);
        }
    }
}
