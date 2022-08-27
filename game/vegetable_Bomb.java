package game;
// https://kknews.cc/code/l9vggo9.html
import java.awt.*;
import java.awt.event.*;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class vegetable_Bomb extends JPanel implements KeyListener{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 654;

    private int state;
    private static final int START = 0;
    private static final int RUNNING = 1;
    private static final int PAUSE = 2;
    private static int LEVEL=1;
    private static final int GAME_OVER = 3;

    private int score =0;
    private Timer timer;
    private int intervel = 1000/100;


    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage airplane;
    public static BufferedImage bee;
    public static BufferedImage bullet;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage pause;
    public static BufferedImage gameover;

    private AFlyObject[] flyings={}; // 敵人
    private CBullet[] bullets = {}; // 子彈
    private CHero hero = new CHero(); // 主角

    // 初始化圖片資源
    static{
        try{
            background = ImageIO.read(vegetable_Bomb.class.getResource("images/background.png"));
            start = ImageIO.read(vegetable_Bomb.class.getResource("images/start.png"));
            airplane = ImageIO.read(vegetable_Bomb.class.getResource("images/airplane.png"));
            bee = ImageIO.read(vegetable_Bomb.class.getResource("images/bee.png"));
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
        g.drawImage(background, 0, 0, null);
        paintHero(g);
        paintBullets(g);
        paintFlyingObjects(g);
        paintScore(g);
        paintState(g);
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
    private void paintFlyingObjects(Graphics g) {
        for(int i =0; i<flyings.length; i++){
            AFlyObject f = flyings[i];
            g.drawImage(f.getImage(), f.getX(), f.getY(), null);
        }
    }
    // 畫分數
    private void paintScore(Graphics g) {
        int x = 10;
        int y= 25;
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22);
        g.setColor(new Color(0xFF0000));
        g.setFont(font);
        g.drawString("SCORE: "+ score, x, y);
        y+=20;
        g.drawString("LIFE: "+hero.getLife(), x, y);
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


    public static void main(String args[]){
        JFrame frame = new JFrame("FIY");
        vegetable_Bomb game = new vegetable_Bomb();
        frame.add(game); // 將面板加到JFrame中
        frame.setSize(WIDTH, HEIGHT);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("images/icon.jpg").getImage());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 啟動
        game.action();
    }

    public void action(){

        MouseAdapter l = new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent e){
                if(state == RUNNING){
                    int x = e.getX();
                    int y = e.getY();
                    hero.moveTo(x, y);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e){
                if(state==PAUSE){
                    state = RUNNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e){
                if(state == RUNNING){
                    state = PAUSE;
                }
            }
            @Override
            public void mouseClicked(MouseEvent e){
                switch(state){
                    case START:
                        state = RUNNING;
                        break;
                    case GAME_OVER:
                        flyings = new AFlyObject[0];
                        bullets = new CBullet[0];
                        hero = new CHero();
                        score = 0;
                        state = START;
                        break;
                }
            }
        };
        this.addMouseListener(l);
        this.addMouseMotionListener(l);

        timer = new Timer();
        timer.schedule(new TimerTask(){
           @Override
           public void run(){
                if(state==RUNNING){
                    enterAction();
                    stepAction();
                    shootAction();
                    bangAction();
                    outOfBoundsAction();
                    checkGameOverAction();
                }
                repaint();
           }
        }, intervel, intervel);
    }

    int flyEnteredIndex = 0;


    public void enterAction(){
        flyEnteredIndex+=1;
        if(flyEnteredIndex % 40 ==0){
            // 隨機生成飛行物
            AFlyObject obj = nextOne();
            flyings = Arrays.copyOf(flyings, flyings.length+1);
            flyings[flyings.length-1]=obj;
        }
    }

    public void stepAction(){
        for(int i =0; i<flyings.length; i++){
            AFlyObject f = flyings[i];
            f.step();
        }

        for(int i =0; i<bullets.length; i++){
            CBullet b = bullets[i];
            b.step();
        }
        hero.step();
    }

    public void flyingStepAction(){
        for(int i=0; i<flyings.length;i++){
            AFlyObject f = flyings[i];
            f.step();
        }
    }

    int shootIndex =0;

    public void shootAction(){
        shootIndex+=1;
        if(shootIndex%30==0){
            CBullet[] bs = hero.shoot();
            bullets = Arrays.copyOf(bullets, bullets.length+bs.length);

            System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);
        }
    }

    public void bangAction(){
        for(int i=0; i<bullets.length; i++){
            CBullet b = bullets[i];
            bang(b);
        }
    }

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
        int index = -1;
        for(int i =0; i<flyings.length; i++){
            AFlyObject obj = flyings[i];
            if(obj.isShoot(bullet)){
                index=i;
                break;
            }
        }
        // 有擊中飛行物
        if(index!=-1){
            AFlyObject one = flyings[index];
            AFlyObject temp = flyings[index];
            flyings[index] = flyings[flyings.length-1];
            flyings[flyings.length -1] = temp;

            flyings = Arrays.copyOf(flyings, flyings.length-1);
            if(one instanceof InterEnemy){
                InterEnemy e = (InterEnemy) one;
                score += e.getScore();
            }
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

    public static AFlyObject nextOne(){
        Random random = new Random();
        int type = random.nextInt(20);
        if(type<4){
            return new CBonus();
        }
        else{
            return new CFruits();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
