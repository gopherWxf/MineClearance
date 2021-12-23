import java.awt.*;

public class ScreenTop {
    //格子位置
    int temp_x;
    int temp_y;

    //判断鼠标逻辑
    void logic() {
        temp_x = 0;
        temp_y = 0;
        if (GameUtil.MOUSE_X > GameUtil.OFFSET && GameUtil.MOUSE_Y > 3 * GameUtil.OFFSET) {
            temp_x = (GameUtil.MOUSE_X - GameUtil.OFFSET) / GameUtil.LATTICE + 1;
            temp_y = (GameUtil.MOUSE_Y - 3 * GameUtil.OFFSET) / GameUtil.LATTICE + 1;
        }
        if (temp_x >= 1 && temp_x <= GameUtil.MAP_W
                && temp_y >= 1 && temp_y <= GameUtil.MAP_H) {
            if (GameUtil.MOUSE_LEFT) {
                //覆盖，则翻开
                if (GameUtil.TopData[temp_x][temp_y] == 0) {
                    GameUtil.TopData[temp_x][temp_y] = -1;
                }
                GameUtil.MOUSE_LEFT = false;
                spaceOpen(temp_x, temp_y);
            }
            if (GameUtil.MOUSE_RIGHT) {
                //覆盖则插旗
                if (GameUtil.TopData[temp_x][temp_y] == 0) {
                    GameUtil.TopData[temp_x][temp_y] = 1;
                    GameUtil.FLAG_NUM++;
                }
                //插旗则取消
                else if (GameUtil.TopData[temp_x][temp_y] == 1) {
                    GameUtil.TopData[temp_x][temp_y] = 0;
                    GameUtil.FLAG_NUM--;
                }
                //数字则进行判断
                else if (GameUtil.TopData[temp_x][temp_y] == -1) {
                    numOpen(temp_x, temp_y);
                }
                GameUtil.MOUSE_RIGHT = false;
            }
        }
        boom();
        victory();
    }


    //绘制上层
    void paintSelf(Graphics graphics) {
        logic();
        //画覆盖
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                //有覆盖
                if (GameUtil.TopData[i][j] == 0) {
                    graphics.drawImage(GameUtil.top,
                            GameUtil.OFFSET + (i - 1) * GameUtil.LATTICE + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.LATTICE + 1,
                            GameUtil.LATTICE - 2,
                            GameUtil.LATTICE - 2,
                            null
                    );
                }
                //插旗
                if (GameUtil.TopData[i][j] == 1) {
                    graphics.drawImage(GameUtil.flag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.LATTICE + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.LATTICE + 1,
                            GameUtil.LATTICE - 2,
                            GameUtil.LATTICE - 2,
                            null
                    );
                }
                //插错旗
                if (GameUtil.TopData[i][j] == 2) {
                    graphics.drawImage(GameUtil.noflag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.LATTICE + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.LATTICE + 1,
                            GameUtil.LATTICE - 2,
                            GameUtil.LATTICE - 2,
                            null
                    );
                }

            }
        }
    }

    //打开空格
    void spaceOpen(int x, int y) {
        if (GameUtil.BottomData[x][y] == 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    //覆盖才递归
                    if (GameUtil.TopData[i][j] != -1) {
                        if (GameUtil.TopData[i][j] == 1) {
                            GameUtil.FLAG_NUM--;
                        }
                        GameUtil.TopData[i][j] = -1;
                        //必须在雷区当中
                        if (i >= 1 && j >= 1 && i < GameUtil.MAP_W && j <= GameUtil.MAP_H) {
                            spaceOpen(i, j);
                        }
                    }
                }
            }
        }
    }

    //数字翻开
    void numOpen(int x, int y) {
        int count = 0;//记录旗子数字
        if (GameUtil.BottomData[x][y] > 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (GameUtil.TopData[i][j] == 1) {
                        count++;
                    }
                }
            }
            if (count == GameUtil.BottomData[x][y]) {
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (GameUtil.TopData[i][j] != 1) {
                            GameUtil.TopData[i][j] = -1;
                        }
                        if (i >= 1 && j >= 1 && i < GameUtil.MAP_W && j <= GameUtil.MAP_H) {
                            spaceOpen(i, j);
                        }
                    }
                }
            }
        }
    }

    //失败判定 true失败
    boolean boom() {
        if (GameUtil.FLAG_NUM == GameUtil.Mine_Max) {
            for (int i = 1; i <= GameUtil.MAP_W; i++) {
                for (int j = 1; j <= GameUtil.MAP_H; j++) {
                    if (GameUtil.BottomData[i][j] == 0) {
                        GameUtil.TopData[i][j] = -1;
                    }
                }
            }
        }
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.BottomData[i][j] == -1 && GameUtil.TopData[i][j] == -1) {
                    GameUtil.state = 2;
                    seeBoom();
                    return true;
                }
            }
        }
        return false;
    }

    //显示雷区所有雷
    void seeBoom() {
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                //是雷，但是有覆盖，则直接显示
                if (GameUtil.BottomData[i][j] == -1 && GameUtil.TopData[i][j] != -1) {
                    GameUtil.TopData[i][j] = -1;
                }
                //不是雷，但是有旗，显示插错旗
                if (GameUtil.BottomData[i][j] != -1 && GameUtil.TopData[i][j] == 1) {
                    GameUtil.TopData[i][j] = 2;
                }
            }
        }
    }

    //胜利判断true胜利
    boolean victory() {
        int count = 0;//统计未打开格子数
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.TopData[i][j] != -1) {
                    count++;
                }
            }
        }
        if (count == GameUtil.Mine_Max) {
            GameUtil.state = 1;
            for (int i = 1; i <= GameUtil.MAP_W; i++) {
                for (int j = 1; j <= GameUtil.MAP_H; j++) {
                    if (GameUtil.TopData[i][j] == 0) {//未翻开的变成旗
                        GameUtil.TopData[i][j] = 1;
                    }
                }
            }
            return true;
        }
        return false;
    }

    //重置游戏
    void reGame() {
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                GameUtil.TopData[i][j] = 0;
            }
        }
    }
}
