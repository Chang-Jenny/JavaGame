package game;

import java.awt.image.BufferedImage;

public class CHero extends AFlyObject {
    // 主角 (使用者可操控之角色)

    public BufferedImage[] images = {};
    private int index = 0;

    private int doubleFire;
    private int life;

    // constructor
    public CHero(){
        life=3; // 初始生命值
        doubleFire=0; // 初始火力
        images = new BufferedImage[]{ vegetable_Bomb.hero0, vegetable_Bomb.hero1 };
        image = vegetable_Bomb.hero0;
        width = image.getWidth();
        height = image.getHeight();
    }
    // 得到雙倍火力數值
    public int isDoubleFire(){
        return doubleFire;
    }
    // 設置雙倍火力
    public void setDoubleFire(int doubleFire){
        this.doubleFire = doubleFire;
    }
    // 增加火力
    public void addDoubleFire(){
        doubleFire=40;
    }
    // 獲得生命值數值
    public int getLife(){
        return life;
    }
    // 加一生命值
    public void addLife(){
        life+=1;
    }
    // 減一生命值
    public void subtractLife(){
        life-=1;
    }
    // 物體移動到哪
    public void moveTo(int x, int y){
        this.x = x - width/2;
        this.y = y - height/2;
    }
    // 界線
    @Override
    public boolean outOfBounds(){
        return false;
    }
    // 發射子彈
    public CBullet[] shoot(){
        int xStep = width/4;
        int yStep= 20;

        // 雙倍火力狀態
        if(doubleFire>0){
            CBullet[] bullets = new CBullet[2];
            // y-yStep為子彈距離飛機的位置
            bullets[0] = new CBullet(x+xStep, y-yStep);
            bullets[1] = new CBullet(x+3*xStep, y-yStep);
            return bullets;
        }
        // 單倍火力狀態
        else{
            CBullet[] bullets = new CBullet[1];
            // y-yStep為子彈距離飛機的位置
            bullets[0] = new CBullet(x+2*xStep, y-yStep);
            return bullets;
        }
    }
    // 移動
    @Override
    public void step(){
        if(images.length>0){
            // 切換圖片
            image = images[ index++/10%images.length ];
        }
    }
    // 碰撞算法
    public boolean hit(AFlyObject other){
        int x1 = other.x - this.width/2; //
        int x2 = other.x + this.width/2 + other.width; //
        int y1 = other.y - this.height/2; //
        int y2 = other.y + this.height/2 + other.height; //

        int herox = this.x + this.width/2;
        int heroy = this.y + this.height/2;

        return herox>x1 && herox<x2 && heroy>y1 && heroy<y2;
    }
    //
    //
}
