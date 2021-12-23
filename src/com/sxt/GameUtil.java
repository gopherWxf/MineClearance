package com.sxt;

import java.awt.*;

/*
工具类，存放静态参数
 */
public class GameUtil {
    static int MAP_W = 11;//地图宽
    static int MAP_H = 11;//地图高
    static int OFFSET = 45;//偏移量
    static int LatticeSideLength = 50;//格子边长
    static int[][] MAP_Mine = new int[MAP_W + 2][MAP_H + 2];//地雷元素含义-1雷 0空 1-8对应的数字
    //载入图片
    static Image lei = Toolkit.getDefaultToolkit().getImage("images/lei.png");
    //地雷个数
    static int Mine_Max = 121;
}
