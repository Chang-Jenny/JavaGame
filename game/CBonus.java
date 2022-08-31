package game;

import java.util.Random;

public class CBonus extends AFlyObject implements InterAward{
    private int xSpeed=1;
    private int ySpeed=2;
    private int awardType;

    // constructor
    public CBonus(){
        // 取得圖片及寬、高
        this.image = vegetable_Bomb.coin;
        width = image.getWidth();
        height = image.getHeight();

        // 初始位置
        y=-height;
        Random rand = new Random();
        x=rand.nextInt(vegetable_Bomb.WIDTH - width);

        // 隨機產生獎勵類型
        awardType = rand.nextInt(2);
    }

    // 回傳獎勵類型
    public int getType(){
        return awardType;
    }

    // 界線
    @Override
    public boolean outOfBounds(){
        return y> vegetable_Bomb.HEIGHT;
    }

    // 移動
    @Override
    public void step(){
        x += xSpeed;
        y += ySpeed;

        if(x> vegetable_Bomb.WIDTH-width){
            xSpeed = -1;
        }
        if(x<0){
            xSpeed = 1;
        }
    }
}
