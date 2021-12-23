package com.sxt;

/*
初始化地雷
 */
public class GenerateMine {
    //存放坐标
    int[] mines = new int[GameUtil.Mine_Max * 2];
    //地雷坐标
    int x, y;
    //是否放置 true可放
    boolean isPlace = true;

    void newMine() {
        for (int i = 0; i < GameUtil.Mine_Max * 2; i += 2) {
            x = (int) (Math.random() * GameUtil.MAP_W + 1);
            y = (int) (Math.random() * GameUtil.MAP_H + 1);
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
        for (int i = 0; i < GameUtil.Mine_Max * 2; i += 2) {
            GameUtil.MAP_Mine[mines[i]][mines[i + 1]] = -1;
        }
    }
}
