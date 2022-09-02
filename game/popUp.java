package game;
import javax.swing.*;


public class popUp {
    private String word="歡迎進入Vegetable Bomb小遊戲\n"+
                        "想要一邊享受射擊樂趣，一邊學習英文字嗎\n"+
                        "馬上點擊按鍵出發冒險吧!!!";
    private String playerName;
    public popUp(){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, word);

        playerName = JOptionPane.showInputDialog(jFrame, "Enter your name:", "Your name", JOptionPane.DEFAULT_OPTION);
    }
    public String getPlayerName(){
        if(playerName!=null){
            return playerName;
        }
        else{
            return null;
        }
    }
}
