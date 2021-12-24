package core;

import Make.MakeBottomNum;
import Make.MakeMines;
import util.UtilAPI;
import util.UtilVar;

import java.awt.*;


public class ScreenBottom {
    //地雷类
    MakeMines generateMine = new MakeMines();
    //地雷对应的数字
    MakeBottomNum bottomNum = new MakeBottomNum();

    {
        generateMine.newMine();
        bottomNum.newNum();
    }

    //绘制地图网格
    void paintSelf(Graphics graphics) {
        //设置线条颜色
        graphics.setColor(Color.red);
        //画竖线
        for (int i = 0; i <= UtilVar.MAP_W; i++) {
            graphics.drawLine(UtilVar.OFFSET + i * UtilVar.LATTICE,
                    3 * UtilVar.OFFSET,
                    UtilVar.OFFSET + i * UtilVar.LATTICE,
                    3 * UtilVar.OFFSET + UtilVar.MAP_H * UtilVar.LATTICE);
        }
        //画横线
        for (int i = 0; i <= UtilVar.MAP_H; i++) {
            graphics.drawLine(UtilVar.OFFSET,
                    3 * UtilVar.OFFSET + i * UtilVar.LATTICE,
                    UtilVar.OFFSET + UtilVar.MAP_W * UtilVar.LATTICE,
                    3 * UtilVar.OFFSET + i * UtilVar.LATTICE
            );
        }
        //画地雷
        for (int i = 1; i <= UtilVar.MAP_W; i++) {
            for (int j = 1; j <= UtilVar.MAP_H; j++) {
                //地雷
                if (UtilVar.BottomData[i][j] == -1) {
                    graphics.drawImage(UtilVar.lei,
                            UtilVar.OFFSET + (i - 1) * UtilVar.LATTICE + 1,
                            UtilVar.OFFSET * 3 + (j - 1) * UtilVar.LATTICE + 1,
                            UtilVar.LATTICE - 2,
                            UtilVar.LATTICE - 2,
                            null
                    );
                }
                //数字
                if (UtilVar.BottomData[i][j] >= 0 && UtilVar.BottomData[i][j] <= 9) {
                    graphics.drawImage(UtilVar.nums[UtilVar.BottomData[i][j]],
                            UtilVar.OFFSET + (i - 1) * UtilVar.LATTICE + 12,
                            UtilVar.OFFSET * 3 + (j - 1) * UtilVar.LATTICE + 5,
                            null
                    );
                }
            }
        }
        //绘制剩余雷数
        UtilAPI.drawWord(graphics,
                "剩余雷数:" + (UtilVar.Mine_Max - UtilVar.FLAG_NUM) + "个",
                UtilVar.OFFSET,
                2 * UtilVar.OFFSET,
                20, Color.red);
        //绘制倒计时
        UtilAPI.drawWord(graphics,
                "开始扫雷:" + (UtilVar.END_TIME - UtilVar.START_TIME) / 1000 + "秒",
                UtilVar.OFFSET + UtilVar.LATTICE * (UtilVar.MAP_W - 4) + 50,
                2 * UtilVar.OFFSET,
                20, Color.red);
        //游戏状态图片显示
        switch (UtilVar.state) {
            case 0:
                UtilVar.END_TIME = System.currentTimeMillis();
                graphics.drawImage(UtilVar.face,
                        UtilVar.OFFSET + UtilVar.LATTICE * UtilVar.MAP_W / 2,
                        UtilVar.OFFSET + 5,
                        null);
                break;
            case 1:
                graphics.drawImage(UtilVar.win,
                        UtilVar.OFFSET + UtilVar.LATTICE * UtilVar.MAP_W / 2,
                        UtilVar.OFFSET + 5,
                        null);
                break;
            case 2:

                graphics.drawImage(UtilVar.over,
                        UtilVar.OFFSET + UtilVar.LATTICE * UtilVar.MAP_W / 2,
                        UtilVar.OFFSET + 5,
                        null);
                break;
        }
    }

    //重置游戏
    public void reGame() {
        for (int i = 1; i <= UtilVar.MAP_W; i++) {
            for (int j = 1; j <= UtilVar.MAP_H; j++) {
                UtilVar.BottomData[i][j] = 0;
            }
        }
        generateMine.newMine();
        bottomNum.newNum();
    }
}
