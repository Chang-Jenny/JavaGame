package game;

import java.awt.image.BufferedImage;

public abstract class AFlyObject {
    // 飛行物基本狀態
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected BufferedImage image;

    // 設定
    public void setX(int x){ this.x=x; }
    public void setY(int y){ this.y=y; }
    public void setWidth(int width){ this.width=width; }
    public void setHeight(int height){ this.height=height; }
    public void setImage(BufferedImage image){ this.image=image; }
    // 取得數值
    public int getX(){ return this.x; }
    public int getY(){ return this.y; }
    public int getWidth(){ return this.width; }
    public int getHeight(){ return this.height; }
    public BufferedImage getImage(){ return this.image; }

    // 出視窗界線與否
    //@return true為出界
    public abstract boolean outOfBounds();

    // 移動
    public abstract void step();

    // 被子彈擊中
    public boolean isShoot(CBullet bullet){
        int x = bullet.x;
        int y = bullet.y;
        return this.x<x && x<this.x+this.width && this.y<y && y<this.y+this.height;

    }


}
