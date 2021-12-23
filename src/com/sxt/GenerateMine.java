package com.sxt;

/*
初始化地雷
 */
public class GenerateMine {
    //存放坐标
    int[] mines = new int[GameUtil.Mine_Max * 2];
    //地雷坐标
    int x, y;

    {
        for (int i = 0; i < GameUtil.Mine_Max * 2; i += 2) {
            x = (int) (Math.random() * GameUtil.MAP_W + 1);
            y = (int) (Math.random() * GameUtil.MAP_H + 1);

            mines[i] = x;
            mines[i + 1] = y;
        }
        for (int i = 0; i < GameUtil.Mine_Max * 2; i += 2) {
            GameUtil.MAP_Mine[mines[i]][mines[i + 1]] = -1;
        }

    }

}
