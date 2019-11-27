//package gameEntities.enemies;
//
//import game.GameField;
//
//import java.util.ArrayList;
//
//public class SmallerEnemy extends Enemy
//{
//    public ArrayList<SmallerEnemy> troop;
//
//    public SmallerEnemy()
//
//    {
//        enemy = GameField.split(image, 10, 18);
//        speed = 2;
//        number = 50;
//        distance = 32;
//        direction = "GO_RIGHT";
//    }
//
//    public void createTroop()
//    {
//        troop = new ArrayList<>();
//        for (int i = 0; i < number; i++)
//        {
//            troop.add(new SmallerEnemy());
//            troop.get(i).currentX = ((-i-1)*distance + (-i-2)*distance)/2;
//            troop.get(i).currentY = (3*64 + 4*64)/2;
//        }
//    }
//
//    public void checkExist()
//    {
//        for (int i = 0; i < troop.size(); i++)
//        {
//            if (troop.get(i).currentX >= (21*64 - 40))
//            {
//                troop.remove(i);
//            }
//        }
//    }
//}
