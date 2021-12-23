package com.sxt;

import javax.tools.Tool;
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
    //载入地雷图片
    static Image lei = Toolkit.getDefaultToolkit().getImage("images/lei.png");
    //地雷个数
    static int Mine_Max = 5;
    //载入数字图片
    static Image[] nums = new Image[9];

    static {
        for (int i = 1; i < 9; i++) {
            nums[i] = Toolkit.getDefaultToolkit().getImage("images/num/" + i + ".png");
        }
    }

    //顶层界面
    static int[][] MAP_Top = new int[MAP_W + 2][MAP_H + 2];//-1无覆盖，0覆盖，1插旗，2插错旗
    //载入顶层的图片
    static Image top = Toolkit.getDefaultToolkit().getImage("images/top.gif");
    static Image flag = Toolkit.getDefaultToolkit().getImage("images/flag.gif");
    static Image noflag = Toolkit.getDefaultToolkit().getImage("images/noflag.gif");

}
