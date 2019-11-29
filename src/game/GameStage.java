package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameStage extends Application {
    static String name;
    static Scene menu, score, playscreen;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage) throws Exception {
        theStage.setTitle("Tower Defense");

        Menu.draw(theStage);

        theStage.setScene(menu);
        theStage.show();
    }
}