package player;


import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Collections;

public class PlayerHP extends Player
{
    protected String strHP;
    protected Text text;
    protected Font font;
    protected double posX;
    protected double posY;

    public PlayerHP()
    {
        posX = 100;
        posY = 100;
        strHP = Integer.toString(currentHP);
        font = Font.loadFont("file:Sources/HP_font.ttf", 84);
        text = new Text(posX, posY, strHP);
        text.setFont(font);
        text.setFill(Color.WHITE);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(5);
    }

    public Text getText()
    {
        return text;
    }

    public void setText(Text text)
    {
        this.text = text;
    }


//    public void draw(Group root)
//    {
//        root.getChildren().add(this.text);
//    }
}
