package project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class code extends JFrame implements KeyListener,Runnable{
    static code frm = new code();
    public code() {
        this.addKeyListener(this);
        Thread thread = new Thread(this); //創建線程
        thread.start(); //啟動線程
    }
    //static Panel pnlbg=new Panel();
    //static Container cp=frm.getContentPane();
    int move = 0;
    int personX=300,personY=420; //人物起始XY座標
    //int vegX=(int)(Math.random()*5); //水果起始座標

    int vegY=100;
    int[]rowy=new int[5]; //水果移動Y座標
    int x1=50,x2=150,x3=250,x4=350,x5=450; //水果起始X座標
    int y1=50,y2=50,y3=50,y4=50,y5=50; //水果起始Y座標
    int delay=5;
    int score=0;
    int r1i=0,r2i=1,r3i=2,r4i=3,r5i=4;
    int life=5; //碰到餅乾生命值減一
    //主要人物
    Image img = Toolkit.getDefaultToolkit().createImage
            ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\person2.png");

    Image firstbg = Toolkit.getDefaultToolkit().createImage
            ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\bg.jpg");
    Image endbg = Toolkit.getDefaultToolkit().createImage
            ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\bgend.jpg");
    static Image imgbg[]=new Image[2]; //循環背景
    int bgy=0;
    static Image veg[]=new Image[5]; //蔬果圖片
    static Image vegEng[]=new Image[5]; //蔬果英文字

    Image imgb;
    Graphics gb;

    static Label lab=new Label("Pressed Enter to start !",Label.CENTER);
    static Label scorelab=new Label("point",Label.LEFT);
    boolean game=false;
    boolean live=true;
    boolean isCollisionr1=false; //撞到水果
    boolean isCollisionr2=false; //撞到水果
    boolean isCollisionr3=false; //撞到水果
    boolean isCollisionr4=false; //撞到水果
    boolean isCollisionr5=false; //撞到水果
    boolean right,left; //人物移動
    boolean see=false;


    static Image row1[]=new Image[5]; //第一行
    static Image row2[]=new Image[5]; //第二行
    static Image row3[]=new Image[5]; //第三行
    static Image row4[]=new Image[5]; //第四行
    static Image row5[]=new Image[5]; //第五行


    public static void main(String arg[]) {
        lab.setBounds(150,150,450,250);
        lab.setBackground(new Color(245,245,245));
        Font fnt_l=new Font("Arial",Font.BOLD,32);
        lab.setFont(fnt_l);

        Font fnt_2=new Font("Serief",Font.BOLD,18);
        scorelab.setFont(fnt_2);
        scorelab.setBounds(650,400,100,100);

        scorelab.setBackground(new Color(255,255,255,0));

        frm.setTitle("Vegetable's Game");
        frm.setLayout(null);
        frm.setBackground(new Color(143,204,235));
        frm.setSize(750, 550);
        frm.setLocation(250, 40);
        frm.setResizable(false); //固定視窗大小
        frm.setVisible(true);

        frm.add(lab);
        frm.add(scorelab);
        frm.setVisible(true);

        frm.addKeyListener(frm);
        scorelab.setVisible(false);
        lab.setVisible(false);
        imgbg[0] = Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\background4.png");
        imgbg[1] = Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\backgroundr.png");

        veg[0]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_1.png");
        veg[1]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_2.png");
        veg[2]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_3.png");
        veg[3]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_4.png");
        veg[4]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_5.png");

        row1[0]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_1.png");
        row1[1]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_2.png");
        row1[2]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_3.png");
        row1[3]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_4.png");
        row1[4]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_5.png");

        row2[0]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_1.png");
        row2[1]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_2.png");
        row2[2]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_3.png");
        row2[3]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_4.png");
        row2[4]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_5.png");


        row3[0]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_1.png");
        row3[1]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_2.png");
        row3[2]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_3.png");
        row3[3]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_4.png");
        row3[4]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_5.png");


        row4[0]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_1.png");
        row4[1]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_2.png");
        row4[2]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_3.png");
        row4[3]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_4.png");
        row4[4]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_5.png");

        row5[0]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_1.png");
        row5[1]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_2.png");
        row5[2]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_3.png");
        row5[3]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_4.png");
        row5[4]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\veg_5.png");

        vegEng[0]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\1.png");
        vegEng[1]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\2.png");
        vegEng[2]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\3.png");
        vegEng[3]=Toolkit.getDefaultToolkit().createImage
                ("C:\\Users\\jenny\\IdeaProjects\\demo\\src\\project\\img\\4.png");


        //imgb= frm.createImage(frm.getWidth(),frm.getHeight());
        frm.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }


    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==10){
            //lab.setVisible(false);
            game=true;
            isCollisionr1=false;
            isCollisionr2=false;
            isCollisionr3=false;
            isCollisionr4=false;
            isCollisionr5=false;
            see=true;
            //scorelab.setVisible(true);
        }
//        if(!game&&!live&&!see&&!value){
//            if(e.getKeyCode()==32){
//                game=true;
//                isCollisionr1=false;
//                isCollisionr2=false;
//                isCollisionr3=false;
//                isCollisionr4=false;
//                isCollisionr5=false;
//                see=true;
//                live=true;
//                value=true;
//            }
//        }
        if(game){
            if (personX >= 50 && personX <= 450) {
                if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
                    move =20; //移動
                    personX+=move;
                    //vegX=(int)(Math.random()*5)*100;

                }
                if (e.getKeyCode()==KeyEvent.VK_LEFT) {
                    move = -20;
                    personX+=move;
                }
                //Graphics g = getGraphics();
                //update(g);
                //paint(g);
            }else{
                if(personX<50){
                    if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
                        move = 20; //移動
                        personX+=move;
                    }
                    if (e.getKeyCode()==KeyEvent.VK_LEFT) {
                        move = 0;
                        personX+=move;
                    }
                }
                if(personX>450){
                    if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
                        move = 0; //移動
                        personX+=move;
                    }
                    if (e.getKeyCode()==KeyEvent.VK_LEFT) {
                        move = -20;
                       personX+=move;
                    }
                }


            }
        }

    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void paint(Graphics g) {
        imgb = createImage(getWidth(),getHeight());//建立圖形緩衝區
        gb = imgb.getGraphics();
        if(see==true&&value){
            gb.drawImage(imgbg[0],0,bgy,this);
            //gb.drawImage(imgbg[1],0,bgy,this);
            gb.setFont(new Font("Arial",Font.ITALIC+Font.BOLD,20));
            gb.setColor(Color.gray);
            gb.drawString("score: "+score,600,480);
            gb.drawString("life: "+life,600,520);
            gb.drawImage(img,personX, personY,this);
            gb.drawImage(row1[r1i],x1, y1,this);
            gb.drawImage(row2[r2i],x2, y2,this);
            gb.drawImage(row3[r3i],x3, y3,this);
            gb.drawImage(row4[r4i],x4, y4,this);
            gb.drawImage(row5[r5i],x5, y5,this);
            if(isCollisionr1||isCollisionr2||isCollisionr3||isCollisionr4||isCollisionr5){
                gb.drawImage(vegEng[z],600,300,this);
                //gb.drawImage(vegEng[z],getX(),getY(),this);
            }
        }
        if(see==false){
            gb.drawImage(firstbg,0,0,this);
            gb.setFont(new Font("Arial",Font.ITALIC+Font.BOLD,30));
            gb.setColor(Color.DARK_GRAY);
            gb.drawString("Welcome to vegetable game!",270,395);
            gb.drawString("Please press Enter to start it_",270,435);
            gb.setFont(new Font("Arial",Font.ITALIC+Font.BOLD,18));
            gb.setColor(Color.red);
            gb.drawString("note: life must > 0",350,460);
            gb.setColor(Color.black);
            gb.drawRect(240,340,470,150);
        }
        if(value==false){
            gb.drawImage(endbg,0,0,this);
            gb.setFont(new Font("Arial",Font.ITALIC+Font.BOLD,30));
            gb.setColor(Color.gray);
            if(score==30){
                gb.drawString("Your score: "+score,200,280);
                gb.drawString("Excellent!",200,350);
            }
            else{
                gb.drawString("Your score: "+score,200,280);
                gb.drawString("See you next time!",200,350);
            }

        }
        g.drawImage(imgb, 0, 0, this);//將圖形緩衝區繪製到螢幕上
    }

    public void run() {
        while (true){	//讓它一直執行
            if (game&&live){	//當沒有停止的時候
                rowy[0] = (int)(Math.random()*5);
                if(rowy[0]==0)
                    rowy[0]+=1;
                y1 += rowy[0];

                rowy[1] = (int)(Math.random()*5);
                if(rowy[1]==0)
                    rowy[1]+=1;
                y2 += rowy[1];

                rowy[2] = (int)(Math.random()*5);
                if(rowy[2]==0)
                    rowy[2]+=1;
                y3 += rowy[2];

                rowy[3] = (int)(Math.random()*5);
                if(rowy[3]==0)
                    rowy[3]+=1;
                y4 += rowy[3];

                rowy[4] = (int)(Math.random()*5);
                if(rowy[4]==0)
                    rowy[4]+=1;
                y5 += rowy[4];

                if(y1>500) {
                    y1=0;
                    r1i=(int)(Math.random()*5);
                }
                if(y2>500){
                    y2=0;
                    r2i=(int)(Math.random()*5);
                }
                if(y3>500){
                    y3=0;
                    r3i=(int)(Math.random()*5);
                }
                if(y4>500){
                    y4=0;
                    r4i=(int)(Math.random()*5);
                }
                if(y5>500){
                    y5=0;
                    r5i=(int)(Math.random()*5);
                }
                boolean collisionr1 =new Rectangle(personX,personY,70,120)
                        .intersects(new Rectangle(x1,y1,60,60));
                if(collisionr1){//若碰撞
                    isCollisionr1=true;
                    judge1(isCollisionr1);
                    System.out.println(isCollisionr1);
                }
                boolean collisionr2 =new Rectangle(personX,personY,70,120)
                        .intersects(new Rectangle(x2,y2,60,60));
                if(collisionr2){//若碰撞
                    isCollisionr2=true;
                    judge2(isCollisionr2);
                }
                boolean collisionr3 =new Rectangle(personX,personY,70,120)
                        .intersects(new Rectangle(x3,y3,60,60));
                if(collisionr3){//若碰撞
                    isCollisionr3=true;
                    judge3(isCollisionr3);
                }
                boolean collisionr4 =new Rectangle(personX,personY,70,120)
                        .intersects(new Rectangle(x4,y4,60,60));
                if(collisionr4){//若碰撞
                    isCollisionr4=true;
                    judge4(isCollisionr4);
                }
                boolean collisionr5 =new Rectangle(personX,personY,70,120)
                        .intersects(new Rectangle(x5,y5,60,60));
                if(collisionr5){//若碰撞
                    isCollisionr5=true;
                    judge5(isCollisionr5);
                }
                //scorelab.setText(Integer.toString(score));
                //scorelab.setText(scorelab.getText()+" "+Integer.toString(life));
                bgy+=1;

                if(bgy>550){
                    //bgchange=bgy;
                    bgy=-550;
                }
                isgameend();

                repaint();	//重畫畫板

            }
            try {
                Thread.sleep(delay);	//執行緒延緩delay毫秒
            } catch (InterruptedException e) {	//捕獲異常
                e.printStackTrace();	//處理異常
            }

        }
    }
    //判斷加分或減生命值
    int z;
    public void judge1(boolean x){
        if(x==true){
            y1=0;
            if(r1i!=4)
                score+=1;
            else
                life-=1;
            z=r1i;
            r1i=(int)(Math.random()*5);
            System.out.println(score);
            System.out.println(life);
        }
    }
    public void judge2(boolean x){
        if(x==true){
            y2=0;
            if(r2i!=4)
                score+=1;
            else
                life-=1;
            z=r2i;
            r2i=(int)(Math.random()*5);
            System.out.println(score);
        }
    }
    public void judge3(boolean x){
        if(x==true){
            y3=0;
            if(r3i!=4)
                score+=1;
            else
                life-=1;
            z=r3i;
            r3i=(int)(Math.random()*5);
            System.out.println(score);
        }
    }
    public void judge4(boolean x){
        if(x==true){
            y4=0;
            if(r4i!=4)
                score+=1;
            else
                life-=1;
            z=r4i;
            r4i=(int)(Math.random()*5);
            System.out.println(score);
        }
    }
    public void judge5(boolean x){
        if(x==true){
            y5=0;
            if(r5i!=4)
                score+=1;
            else
                life-=1;
            z=r5i;
            r5i=(int)(Math.random()*5);
            System.out.println(score);
        }
    }
    //判斷遊戲是否結束
    boolean value=true;
    public void isgameend(){
        if(life<=0){
            live=false;
            value=false;
        }
        if(score>=30){
            live=false;
            value=false;
        }
    }
}
