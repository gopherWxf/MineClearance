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
        for (int i = 0; i <= GameUtil.MAP_W; i++) {
            graphics.drawLine(GameUtil.OFFSET + i * GameUtil.LATTICE,
                    3 * GameUtil.OFFSET,
                    GameUtil.OFFSET + i * GameUtil.LATTICE,
                    3 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.LATTICE);
        }
        //画横线
        for (int i = 0; i <= GameUtil.MAP_H; i++) {
            graphics.drawLine(GameUtil.OFFSET,
                    3 * GameUtil.OFFSET + i * GameUtil.LATTICE,
                    GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.LATTICE,
                    3 * GameUtil.OFFSET + i * GameUtil.LATTICE
            );
        }
        //画地雷
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                //地雷
                if (GameUtil.BottomData[i][j] == -1) {
                    graphics.drawImage(GameUtil.lei,
                            GameUtil.OFFSET + (i - 1) * GameUtil.LATTICE + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.LATTICE + 1,
                            GameUtil.LATTICE - 2,
                            GameUtil.LATTICE - 2,
                            null
                    );
                }
                //数字
                if (GameUtil.BottomData[i][j] >= 0) {
                    graphics.drawImage(GameUtil.nums[GameUtil.BottomData[i][j]],
                            GameUtil.OFFSET + (i - 1) * GameUtil.LATTICE + 15,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.LATTICE + 5,

                            null
                    );
                }
            }
        }
        //绘制剩余雷数
        GameUtil.drawWord(graphics,
                "剩余雷数:" + (GameUtil.Mine_Max - GameUtil.FLAG_NUM) + "个",
                GameUtil.OFFSET,
                2 * GameUtil.OFFSET,
                20, Color.red);
        //绘制倒计时
        GameUtil.drawWord(graphics,
                "开始扫雷:" + (GameUtil.END_TIME - GameUtil.START_TIME) / 1000 + "秒",
                GameUtil.OFFSET + GameUtil.LATTICE * (GameUtil.MAP_W - 4),
                2 * GameUtil.OFFSET,
                20, Color.red);
        //游戏状态图片显示
        switch (GameUtil.state) {
            case 0:
                GameUtil.END_TIME = System.currentTimeMillis();
                graphics.drawImage(GameUtil.face,
                        GameUtil.OFFSET + GameUtil.LATTICE * GameUtil.MAP_W / 2,
                        GameUtil.OFFSET,
                        null);
                break;
            case 1:
                graphics.drawImage(GameUtil.win,
                        GameUtil.OFFSET + GameUtil.LATTICE * GameUtil.MAP_W / 2,
                        GameUtil.OFFSET,
                        null);
                break;
            case 2:
                graphics.drawImage(GameUtil.over,
                        GameUtil.OFFSET + GameUtil.LATTICE * GameUtil.MAP_W / 2,
                        GameUtil.OFFSET,
                        null);
                break;
        }
    }
    //重置游戏
    void reGame() {
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                GameUtil.BottomData[i][j] = 0;
            }
        }
        generateMine.newMine();
        bottomNum.newNum();
    }
}
