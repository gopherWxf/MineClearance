package util;

import java.awt.*;

public class UtilVar {
    public static int MAP_W = 36;//地图宽
    public static int MAP_H = 17;//地图高
    public static int OFFSET = 45;//偏移量
    public static int LATTICE = 50;//格子边长
    public static int[][] BottomData = new int[MAP_W + 2][MAP_H + 2];//地雷元素含义-1雷 0空 1-8对应的数字
    //载入地雷图片
    public static Image lei = Toolkit.getDefaultToolkit().getImage("images/lei.png");
    //地雷个数
    public static int Mine_Max = 100;
    //载入数字图片
    public static Image[] nums = new Image[9];

    static {
        for (int i = 1; i < 9; i++) {
            nums[i] = Toolkit.getDefaultToolkit().getImage("images/num/" + i + ".png");
        }
    }

    //顶层界面
    public static int[][] TopData = new int[MAP_W + 2][MAP_H + 2];//-1无覆盖，0覆盖，1插旗，2插错旗
    //载入顶层的图片
    public static Image top = Toolkit.getDefaultToolkit().getImage("images/top.gif");
    public static Image flag = Toolkit.getDefaultToolkit().getImage("images/flag.gif");
    public static Image noflag = Toolkit.getDefaultToolkit().getImage("images/noflag.png");
    //鼠标参数
    public static int MOUSE_X;
    public static int MOUSE_Y;
    public static boolean MOUSE_LEFT;
    public static boolean MOUSE_RIGHT;
    //游戏状态 0游戏中，1胜利，2失败,3游戏选择
    public static int state = 3;
    //载入游戏状态图片
    public static Image face = Toolkit.getDefaultToolkit().getImage("images/face.png");
    public static Image win = Toolkit.getDefaultToolkit().getImage("images/win.png");
    public static Image over = Toolkit.getDefaultToolkit().getImage("images/over.png");
    //旗数量
    public static int FLAG_NUM = 0;

    //倒计时
    public static long START_TIME = 0;
    public static long END_TIME = 0;
    //游戏难度
    public static int level = 0;
    //播放音乐
    public static int voice = 0;
}
