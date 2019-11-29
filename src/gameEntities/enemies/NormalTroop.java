package gameEntities.enemies;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class NormalTroop extends Troop {

    public NormalTroop(int level) {
        setTroopInfos(level);
        createTroop();
    }

    @Override
    public void createTroop() {
        troop = new ArrayList<>();
        troop.add(new NormalEnemy());
        for (int i = 1; i < number; i++) {
            troop.add(new NormalEnemy());
            troop.get(i).setX(troop.get(i - 1).x - space);
        }
    }

    @Override
    public void checkExistance(AnchorPane root, Text tHP, int PlayerHP) {
        for (int i = 0; i < troop.size(); i++) {
            if (troop.get(i).x >= (21 * 64 - 32)) {
                root.getChildren().removeAll(troop.get(i).getEnemyView(), troop.get(i).getLeftBloodBar());
                PlayerHP -= 1;
                tHP.setText("Player HP: " + PlayerHP);
            }
        }
    }
}
