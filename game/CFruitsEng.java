package game;

import java.util.Random;

public class CFruitsEng extends AFlyObject implements InterEnemy{
    // constructor
    public CFruitsEng(){}
    public CFruitsEng(int num){
        // 取得圖片及寬、高
        this.image = vegetable_Bomb.vegEng[num];

//        this.image = vegetable_Bomb.fruit;
        width = image.getWidth();
        height = image.getHeight();
        // 初始位置
       x = 100;
       y = 100;
//        y = -height;
//        x = vegetable_Bomb.WIDTH-width;
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
    public void step(){  }

}
