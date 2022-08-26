package game;

public interface InterAward {
    int DOUBLE_FIRE=0; // 獲得雙倍火力
    int LIFE=1; // 獲得生命值加一

    // 獲得0(雙倍火力)或1(生命值)
    int getType();

}
