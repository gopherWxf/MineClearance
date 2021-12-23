import java.awt.*;

public class UtilVar {
    static int MAP_W = 36;//地图宽
    static int MAP_H = 17;//地图高
    static int OFFSET = 45;//偏移量
    static int LATTICE = 50;//格子边长
    static int[][] BottomData = new int[MAP_W + 2][MAP_H + 2];//地雷元素含义-1雷 0空 1-8对应的数字
    //载入地雷图片
    static Image lei = Toolkit.getDefaultToolkit().getImage("images/lei.png");
    //地雷个数
    static int Mine_Max = 100;
    //载入数字图片
    static Image[] nums = new Image[9];

    static {
        for (int i = 1; i < 9; i++) {
            nums[i] = Toolkit.getDefaultToolkit().getImage("images/num/" + i + ".png");
        }
    }

    //顶层界面
    static int[][] TopData = new int[MAP_W + 2][MAP_H + 2];//-1无覆盖，0覆盖，1插旗，2插错旗
    //载入顶层的图片
    static Image top = Toolkit.getDefaultToolkit().getImage("images/top.gif");
    static Image flag = Toolkit.getDefaultToolkit().getImage("images/flag.gif");
    static Image noflag = Toolkit.getDefaultToolkit().getImage("images/noflag.png");
    //鼠标参数
    static int MOUSE_X;
    static int MOUSE_Y;
    static boolean MOUSE_LEFT;
    static boolean MOUSE_RIGHT;
    //游戏状态 0游戏中，1胜利，2失败,3游戏选择
    static int state = 3;
    //载入游戏状态图片
    static Image face = Toolkit.getDefaultToolkit().getImage("images/face.png");
    static Image win = Toolkit.getDefaultToolkit().getImage("images/win.png");
    static Image over = Toolkit.getDefaultToolkit().getImage("images/over.png");
    //旗数量
    static int FLAG_NUM = 0;

    //倒计时
    static long START_TIME = 0;
    static long END_TIME = 0;
    //游戏难度
    static int level = 0;
    //播放音乐
    static int voice = 0;
}
