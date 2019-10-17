package Game_Stage;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameStage extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    public void start(Stage stage)
    {
        stage.setTitle("Tower Defense");
        stage.show();
    }
}
