package game.keyMonitor;

//import game.CKeyboard;
import javax.swing.*;

public class KeyboardDemo extends JFrame{

    //定義變數
    CKeyboard ck = null;

    public static void main(String[] args) {
        new KeyboardDemo();
    }

    //建構函式
    public KeyboardDemo(){

        ck = new CKeyboard();
        this.add(ck);
        this.addKeyListener(ck);//註冊監聽

        this.setSize(300,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
