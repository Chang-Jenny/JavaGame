package game;

// 子彈
public class CBullet extends AFlyObject {
    // 子彈移動速度
    private int speed = 3;

    // constructor
    public CBullet(){}
    public CBullet(int x, int y){
        this.x = x;
        this.y = y;
        this.image = vegetable_Bomb.bullet;
    }

    // 移動
    @Override
    public void step(){
        y-=speed;
    }

    //界線
    @Override
    public boolean outOfBounds(){
        return y<-height;
    }

}
