package gameEntities.towers;

import game.GameField;
public class NormalBullet extends Bullet {
    public NormalBullet(Tower tower) {
        bulletImage = GameField.split(baseImage, 11, 19);
        bulletView.setImage(bulletImage);
        moveSpeed = 8;
        damage = 40;
        x = tower.x;
        y = tower.y;
        setCoordinate(x, y);
        isMoving = false;
    }
}
