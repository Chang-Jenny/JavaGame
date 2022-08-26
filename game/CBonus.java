package game;

import java.util.Random;

public class CBonus extends AFlyObject implements InterAward{
    private int xSpeed=1;
    private int ySpeed=2;
    private int awardType;

    // constructor
    public CBonus(){
        this.image = vegetable_Bomb.bee;
        width = image.getWidth();
        height = image.getHeight();

        y=-height;
        Random rand = new Random();
        x=rand.nextInt(vegetable_Bomb.WIDTH - width);

        // 獎勵類型
        awardType = rand.nextInt(2);
    }

    // 獲得獎勵類型
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
