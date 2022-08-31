package game;

import java.util.Random;

public class CFruits extends AFlyObject implements InterEnemy{
    private int speed = 3;

    // constructor
    public CFruits(){
        // 取得圖片及寬、高
        Random random = new Random();
        which = random.nextInt(5);
        this.image = vegetable_Bomb.veg[which];

//        this.image = vegetable_Bomb.fruit;
        width = image.getWidth();
        height = image.getHeight();
        // 初始位置
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

    // 回傳哪一張蔬果圖片被打中
    public int getWhich(){
        int which = this.which;
        return which;
    }



}
