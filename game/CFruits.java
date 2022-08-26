package game;

import java.util.Random;

public class CFruits extends AFlyObject implements InterEnemy{
    private int speed = 3;

    // constructor
    public CFruits(){
        this.image = vegetable_Bomb.airplane;
        width = image.getWidth();
        height = image.getHeight();

        y = -height;
        Random rand = new Random();
        x = rand.nextInt(vegetable_Bomb.WIDTH-width);
    }

    // 分數
    @Override
    public int getScore(){
        return 5;
    }

    // 界線
    @Override
    public boolean outOfBounds(){
        return y> vegetable_Bomb.HEIGHT;
    }

    // 移動
    @Override
    public void step(){
        y+=speed;
    }

}
