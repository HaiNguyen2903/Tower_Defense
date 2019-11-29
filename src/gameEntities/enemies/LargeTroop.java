package gameEntities.enemies;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class LargeTroop extends Troop{

    public LargeTroop(int level) {
        setTroopInfos(level);
        createTroop();
    }

    @Override
    public void createTroop() {
        troop = new ArrayList<>();
        troop.add(new LargeEnemy());
        for (int i = 1; i < number; i++) {
            troop.add(new LargeEnemy());
            troop.get(i).setX(troop.get(i - 1).x - space);
        }
    }

    @Override
    public void checkExistance(AnchorPane root, Text tHP, int PlayerHP) {
        for (int i = 0; i < troop.size(); i++) {
            if (troop.get(i).x >= (21 * 64 - 32)) {
                root.getChildren().removeAll(troop.get(i).getEnemyView(), troop.get(i).getLeftBloodBar());
                PlayerHP -= 2;
                tHP.setText("Player HP: " + PlayerHP);
            }
        }
    }
}
