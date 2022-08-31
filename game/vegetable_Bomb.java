package game;
// https://kknews.cc/code/l9vggo9.html
import java.awt.*;
import java.awt.event.*;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;


//import java.awt.event.KeyEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class vegetable_Bomb extends JPanel implements KeyListener{
    public static final int WIDTH = 700;
    public static final int HEIGHT = 650;

    private int state;
    private static final int START = 0;
    private static final int RUNNING = 1;
    private static final int PAUSE = 2;
    private static int LEVEL=1;
    private static final int GAME_OVER = 3;

    private int score = 0;
    private Timer timer;
    private int intervel = 1000/100;

    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage gameover;
    public static BufferedImage pause;

    static BufferedImage veg[]=new BufferedImage[5]; //蔬果圖片
    static BufferedImage vegEng[]=new BufferedImage[5]; //蔬果英文字
    public static BufferedImage coin;
    public static BufferedImage bullet;
    public static BufferedImage hero0;
    public static BufferedImage hero1;



    private AFlyObject[] flyings={}; // 敵人
    private CBullet[] bullets = {}; // 子彈
    private CHero hero = new CHero(); // 主角
    private CFruitsEng fruitEng = null; // 蔬果英文字

    // 初始化圖片資源
    static{
        try{
            background = ImageIO.read(vegetable_Bomb.class.getResource("images/start.png"));
            start = ImageIO.read(vegetable_Bomb.class.getResource("images/start.png"));
//            fruit = ImageIO.read(vegetable_Bomb.class.getResource("images\\fruit\\grapes.png"));
            veg[0] = ImageIO.read(vegetable_Bomb.class.getResource("images\\fruit\\apple.png"));
            veg[1] = ImageIO.read(vegetable_Bomb.class.getResource("images\\fruit\\banana.png"));
            veg[2] = ImageIO.read(vegetable_Bomb.class.getResource("images\\fruit\\carrot.png"));
            veg[3] = ImageIO.read(vegetable_Bomb.class.getResource("images\\fruit\\guava.png"));
            veg[4] = ImageIO.read(vegetable_Bomb.class.getResource("images\\fruit\\strawberry.png"));
            vegEng[0] = ImageIO.read(vegetable_Bomb.class.getResource("images\\english\\apple.png"));
            vegEng[1] = ImageIO.read(vegetable_Bomb.class.getResource("images\\english\\banana.png"));
            vegEng[2] = ImageIO.read(vegetable_Bomb.class.getResource("images\\english\\carrot.png"));
            vegEng[3] = ImageIO.read(vegetable_Bomb.class.getResource("images\\english\\guava.png"));
            vegEng[4] = ImageIO.read(vegetable_Bomb.class.getResource("images\\english\\strawberry.png"));
            coin = ImageIO.read(vegetable_Bomb.class.getResource("images/bee.png"));
            bullet = ImageIO.read(vegetable_Bomb.class.getResource("images/bullet.png"));
            hero0 = ImageIO.read(vegetable_Bomb.class.getResource("images/hero0.png"));
            hero1 = ImageIO.read(vegetable_Bomb.class.getResource("images/hero1.png"));
            pause = ImageIO.read(vegetable_Bomb.class.getResource("images/pause.png"));
            gameover = ImageIO.read(vegetable_Bomb.class.getResource("images/gameover.png"));
        }
        catch(Exception e){
            e.printStackTrace();;
        }
    }

    // draw
    @Override
    public void paint(Graphics g){
        if(state != GAME_OVER) {
            paintBackground(g);
            paintHero(g);
            paintBullets(g);
            paintFlyingObjects(g);
            paintScore(g);
            paintEng(g);
        }
        paintState(g);
    }
    // 畫背景
    private void paintBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    // 畫主角
    private void paintHero(Graphics g) {
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
    }
    // 畫子彈
    private void paintBullets(Graphics g) {
        for(int i =0; i<bullets.length; i++){
            CBullet b = bullets[i];
            g.drawImage(b.getImage(), b.getX()-b.getWidth()/2, b.getY(), null);
        }
    }
    // 畫飛行物體
    private void paintFlyingObjects(Graphics g) {
        for(int i =0; i<flyings.length; i++){
            AFlyObject f = flyings[i];
            g.drawImage(f.getImage(), f.getX(), f.getY(), null);
        }
    }
    // 畫英文字
    private void paintEng(Graphics g){
        if(fruitEng!=null) {
            CFruitsEng words = fruitEng;
            g.drawImage(words.getImage(), words.x, words.y, null);
        }
    }
    // 畫分數
    private void paintScore(Graphics g) {
        int x = 10;
        int y = 25;
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22);
        g.setColor(new Color(0xFF0000));
        g.setFont(font);
        g.drawString("SCORE: "+ score, x, y);
        y+=20;
        g.drawString("LIFE: "+ hero.getLife(), x, y);
    }
    // 畫遊戲狀態
    public void paintState(Graphics g){
        switch(state){
            case START:
                g.drawImage(start, 0, 0, null);
                break;
            case PAUSE:
                g.drawImage(pause, 0, 0, null);
                break;
            case GAME_OVER:
                g.drawImage(gameover, 0, 0, null);
                break;
        }
    }
    // main
    public static void main(String args[]){
        JFrame frame = new JFrame("Vegetable_Bomb");
        vegetable_Bomb game = new vegetable_Bomb();
        frame.add(game); // 將面板加到JFrame中
        frame.setSize(WIDTH, HEIGHT);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("images/bee.png").getImage());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.addKeyListener(game);

//        CKeyboard board = new CKeyboard();
//        frame.add(board);
//        frame.addKeyListener(board); //註冊監聽

        // 啟動
        game.action();
    }

    public void action(){
        timer = new Timer();
        timer.schedule(new TimerTask(){
           @Override
           public void run(){
                if(state==RUNNING){
                    enterAction();
                    stepAction();
//                    shootAction();
                    bangAction();
                    outOfBoundsAction();
                    checkGameOverAction();
                }
                repaint();
           }
        }, intervel, intervel);
    }


    int flyEnteredIndex = 0; // 飛行物體計數
    // 產生飛行物體
    public void enterAction(){
        flyEnteredIndex+=1;
        if(flyEnteredIndex % 40 ==0){ // 400毫秒
            // 隨機生成飛行物
            AFlyObject obj = nextOne();
            // 加入進到flyings陣列中
            flyings = Arrays.copyOf(flyings, flyings.length+1);
            flyings[flyings.length-1]=obj;
        }
    }
    // 移動一步
    public void stepAction(){
        // 飛行物體移動一步
        for(int i =0; i<flyings.length; i++){
            AFlyObject f = flyings[i];
            f.step();
        }
        // 子彈移動一步
        for(int i =0; i<bullets.length; i++){
            CBullet b = bullets[i];
            b.step();
        }
        // 主角移動一步
        hero.step();
    }

    public void flyingStepAction(){
        for(int i=0; i<flyings.length;i++){
            AFlyObject f = flyings[i];
            f.step();
        }
    }

    int shootIndex =0; // 射擊計數

    // 射擊
    public void shootAction(){
//        shootIndex+=1;
        // 300ms產生一顆子彈
//        if(shootIndex%30==0){
            // 主角射出子彈
            CBullet[] bs = hero.shoot();
            bullets = Arrays.copyOf(bullets, bullets.length+bs.length);
            // 增加陣列長度
            System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);
//        }
    }

    // 子彈與飛行物的碰撞偵測
    public void bangAction(){
        for(int i=0; i<bullets.length; i++){
            CBullet b = bullets[i];
            bang(b);
        }
    }
    // 刪除出界的飛行物及子彈
    public void outOfBoundsAction(){
        int index =0;
        AFlyObject[] flyingLives = new AFlyObject[flyings.length];
        for(int i=0; i<flyings.length; i++){
            AFlyObject f = flyings[i];
            if(!f.outOfBounds()){
                flyingLives[index++] = f;
            }
        }
        flyings = Arrays.copyOf(flyingLives, index);

        index=0;
        CBullet[] bulletLives = new CBullet[bullets.length];
        for(int i=0; i<bullets.length; i++){
            CBullet b = bullets[i];
            if(!b.outOfBounds()){
                bulletLives[index++] = b;
            }
        }
        bullets = Arrays.copyOf(bulletLives, index);
    }
    public void checkGameOverAction(){
        if(isGameOver()==true){
            state = GAME_OVER;
            flyings = null; // 敵人
            bullets = null; // 子彈
            hero = null; // 主角
            fruitEng = null; // 蔬果英文字

        }
    }
    public boolean isGameOver(){
        for(int i =0; i<flyings.length; i++){
            int index =-1;
            AFlyObject obj = flyings[i];
            if(hero.hit(obj)){
                hero.subtractLife();
                hero.setDoubleFire(0);
                index = i;
            }
            if(index !=-1){
                AFlyObject t = flyings[index];
                flyings[index] = flyings[flyings.length -1];
                flyings[flyings.length -1] = t;

                flyings = Arrays.copyOf(flyings, flyings.length -1);

            }
        }
        return hero.getLife() <=0;
    }

    public void bang(CBullet bullet){
        int index = -1; // 被擊中的飛行物在陣列中的索引值
        for(int i =0; i<flyings.length; i++){
            AFlyObject obj = flyings[i];
            // 依序檢查每一飛行物找到被擊中的那一個
            if(obj.isShoot(bullet)){
                index=i;
                break;
            }
        }
        // 有擊中飛行物 (index!=-1)
        if(index!=-1){
            AFlyObject one = flyings[index]; // one紀錄被擊中的飛行物體
            AFlyObject temp = flyings[index]; // 暫時紀錄
            flyings[index] = flyings[flyings.length-1]; // 被擊中的飛行物和陣列中最後一個飛行物體交換儲存位址
            flyings[flyings.length -1] = temp;
            flyings = Arrays.copyOf(flyings, flyings.length-1); // 刪除最後一個

            // 檢查one的 class
            // 是蔬果 (敵方)
            if(one instanceof InterEnemy){
                int whichShoot = one.getWhich();
                CFruitsEng Eng = new CFruitsEng(whichShoot);
                fruitEng = Eng;
                System.out.println("哪一個蔬果: "+whichShoot);

                // 強制轉型
                InterEnemy e = (InterEnemy) one;
                score += e.getScore();
            }
            // 是獎勵
            else{
                InterAward a = (InterAward) one;
                int type = a.getType();
                switch(type){
                    case InterAward.DOUBLE_FIRE:
                        hero.addDoubleFire();
                        break;
                    case InterAward.LIFE:
                        hero.addLife();
                        break;
                }
            }
        }
    }

    // 隨機產生飛行物
    public static AFlyObject nextOne(){
        Random random = new Random();
        int type = random.nextInt(30);
        if(type<5){
            return new CBonus();
        }
        else{
            return new CFruits();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            System.out.println("DOWN");
            if(state == RUNNING){
                int x = hero.getX();
                int y = hero.getY();
                y+=20;
                hero.moveTo(x, y);
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP){
            System.out.println("UP");
            if(state == RUNNING){
                int x = hero.getX();
                int y = hero.getY();
                y-=20;
                hero.moveTo(x, y);
            }

        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            System.out.println("LEFT");
            if(state == RUNNING){
                int x = hero.getX();
                int y = hero.getY();
                x-=20;
                hero.moveTo(x, y);
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            System.out.println("RIGHT");
            if(state == RUNNING){

                int x = hero.getX();
                int y = hero.getY();
                System.out.println(x+" "+y);
                x+=20;
                hero.moveTo(x, y);
            }
        }
        else if(e.getKeyCode()==10) {
            state = RUNNING;
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            shootAction();
            System.out.println("SPACE");
//            state = PAUSE;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}




