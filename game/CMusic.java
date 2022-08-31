package game;
// https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/308137/
public class CMusic {
    public CMusic(String name){
        outcome(name);
    }
    public void outcome(String name){
        CPlay apw=new CPlay(name);
        apw.start();
    }

}
