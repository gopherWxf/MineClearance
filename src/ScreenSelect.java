import java.awt.*;

public class ScreenSelect {
    void paintSelf(Graphics g) {
        g.drawRect(100, 50, 300, 100);
        UtilAPI.drawWord(g, "简单", 220, 100, 30, Color.black);
        g.drawRect(100, 200, 300, 100);
        UtilAPI.drawWord(g, "中等", 220, 250, 30, Color.black);
        g.drawRect(100, 350, 300, 100);
        UtilAPI.drawWord(g, "困难", 220, 400, 30, Color.black);
        g.drawRect(100, 500, 300, 100);
        int fast = UtilAPI.readRank();
        UtilAPI.drawWord(g, "历史扫雷最快:" + fast + "秒", 115, 550, 30, Color.black);
    }

    //判断是否点击到
    boolean hard() {
        if (UtilVar.MOUSE_X > 100 && UtilVar.MOUSE_X < 400) {
            if (UtilVar.MOUSE_Y > 50 && UtilVar.MOUSE_Y < 150) {
                UtilVar.level = 1;
                UtilVar.state = 0;
                return true;
            }
            if (UtilVar.MOUSE_Y > 200 && UtilVar.MOUSE_Y < 300) {
                UtilVar.level = 2;
                UtilVar.state = 0;
                return true;
            }
            if (UtilVar.MOUSE_Y > 350 && UtilVar.MOUSE_Y < 450) {
                UtilVar.level = 3;
                UtilVar.state = 0;
                return true;
            }
        }
        return false;
    }

    void hard(int level) {
        switch (level) {
            case 1:
                UtilVar.Mine_Max = 10;
                UtilVar.MAP_W = 9;
                UtilVar.MAP_H = 9;
                break;
            case 2:
                UtilVar.Mine_Max = 40;
                UtilVar.MAP_W = 16;
                UtilVar.MAP_H = 16;
                break;
            case 3:
                UtilVar.Mine_Max = 99;
                UtilVar.MAP_W = 30;
                UtilVar.MAP_H = 16;
                break;
        }
    }
}
