package game.keyMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class CKeyboard extends JPanel implements KeyListener {
    int x = 10;
    int y = 10;

    // 重寫paint方法
    public void paint(Graphics g){
        //必須呼叫父類的構造方法
        super.paint(g);
        g.setColor(Color.blue);
        g.fillOval(x, y, 10, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            //點擊向下的鍵
            System.out.println("DOWN");
            y++;
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP){
            //點擊向上的鍵
            System.out.println("UP");
            y--;
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            //點擊向左的鍵
            System.out.println("LEFT");
            x--;
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            //點擊向右的鍵
            System.out.println("RIGHT");
            x++;
        }

        // 呼叫repaint方法實現重畫的功能
        this.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) { }
}
