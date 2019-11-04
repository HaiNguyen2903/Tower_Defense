package gameStage;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameStage extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Tower Defense");

        GameField.showMenu(primaryStage);
    }
}
