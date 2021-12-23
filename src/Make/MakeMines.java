package Make;

import util.UtilVar;

public class MakeMines {
    //存放坐标
    static int[] mines = new int[UtilVar.Mine_Max * 2];
    //地雷坐标
    int x, y;
    //是否放置 true可放
    boolean isPlace = true;

    public void newMine() {
        for (int i = 0; i < UtilVar.Mine_Max * 2; i += 2) {
            x = (int) (Math.random() * UtilVar.MAP_W + 1);
            y = (int) (Math.random() * UtilVar.MAP_H + 1);
            //判断坐标是否存在
            for (int j = 0; j < i; j += 2) {
                if (x == mines[j] && y == mines[j + 1]) {
                    i -= 2;
                    isPlace = false;
                    break;
                }
            }
            if (isPlace) {
                //随机坐标
                mines[i] = x;
                mines[i + 1] = y;
            }
            //重置状态
            isPlace = true;
        }
        for (int i = 0; i < UtilVar.Mine_Max * 2; i += 2) {
            UtilVar.BottomData[mines[i]][mines[i + 1]] = -1;
        }
    }
}
