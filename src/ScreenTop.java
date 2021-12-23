import java.awt.*;

public class ScreenTop {
    //格子位置
    int temp_x;
    int temp_y;

    //判断鼠标逻辑
    void logic() {
        temp_x = 0;
        temp_y = 0;
        if (UtilVar.MOUSE_X > UtilVar.OFFSET && UtilVar.MOUSE_Y > 3 * UtilVar.OFFSET) {
            temp_x = (UtilVar.MOUSE_X - UtilVar.OFFSET) / UtilVar.LATTICE + 1;
            temp_y = (UtilVar.MOUSE_Y - 3 * UtilVar.OFFSET) / UtilVar.LATTICE + 1;
        }
        if (temp_x >= 1 && temp_x <= UtilVar.MAP_W
                && temp_y >= 1 && temp_y <= UtilVar.MAP_H) {
            if (UtilVar.MOUSE_LEFT) {
                //覆盖，则翻开
                if (UtilVar.TopData[temp_x][temp_y] == 0) {
                    UtilVar.TopData[temp_x][temp_y] = -1;
                }
                UtilVar.MOUSE_LEFT = false;
                spaceOpen(temp_x, temp_y);
            }
            if (UtilVar.MOUSE_RIGHT) {
                //覆盖则插旗
                if (UtilVar.TopData[temp_x][temp_y] == 0) {
                    UtilVar.TopData[temp_x][temp_y] = 1;
                    UtilVar.FLAG_NUM++;
                }
                //插旗则取消
                else if (UtilVar.TopData[temp_x][temp_y] == 1) {
                    UtilVar.TopData[temp_x][temp_y] = 0;
                    UtilVar.FLAG_NUM--;
                }
                //数字则进行判断
                else if (UtilVar.TopData[temp_x][temp_y] == -1) {
                    numOpen(temp_x, temp_y);
                }
                UtilVar.MOUSE_RIGHT = false;
            }
        }
        boom();
        victory();
    }


    //绘制上层
    void paintSelf(Graphics graphics) {
        logic();
        //画覆盖
        for (int i = 1; i <= UtilVar.MAP_W; i++) {
            for (int j = 1; j <= UtilVar.MAP_H; j++) {
                //有覆盖
                if (UtilVar.TopData[i][j] == 0) {
                    graphics.drawImage(UtilVar.top,
                            UtilVar.OFFSET + (i - 1) * UtilVar.LATTICE + 1,
                            UtilVar.OFFSET * 3 + (j - 1) * UtilVar.LATTICE + 1,
                            UtilVar.LATTICE - 2,
                            UtilVar.LATTICE - 2,
                            null
                    );
                }
                //插旗
                if (UtilVar.TopData[i][j] == 1) {
                    graphics.drawImage(UtilVar.flag,
                            UtilVar.OFFSET + (i - 1) * UtilVar.LATTICE + 1,
                            UtilVar.OFFSET * 3 + (j - 1) * UtilVar.LATTICE + 1,
                            UtilVar.LATTICE - 2,
                            UtilVar.LATTICE - 2,
                            null
                    );
                }
                //插错旗
                if (UtilVar.TopData[i][j] == 2) {
                    graphics.drawImage(UtilVar.noflag,
                            UtilVar.OFFSET + (i - 1) * UtilVar.LATTICE + 1,
                            UtilVar.OFFSET * 3 + (j - 1) * UtilVar.LATTICE + 1,
                            UtilVar.LATTICE - 2,
                            UtilVar.LATTICE - 2,
                            null
                    );
                }

            }
        }
    }

    //打开空格
    void spaceOpen(int x, int y) {
        if (UtilVar.BottomData[x][y] == 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    //覆盖才递归
                    if (UtilVar.TopData[i][j] != -1) {
                        if (UtilVar.TopData[i][j] == 1) {
                            UtilVar.FLAG_NUM--;
                        }
                        UtilVar.TopData[i][j] = -1;
                        //必须在雷区当中
                        if (i >= 1 && j >= 1 && i < UtilVar.MAP_W && j <= UtilVar.MAP_H) {
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
        if (UtilVar.BottomData[x][y] > 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (UtilVar.TopData[i][j] == 1) {
                        count++;
                    }
                }
            }
            if (count == UtilVar.BottomData[x][y]) {
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (UtilVar.TopData[i][j] != 1) {
                            UtilVar.TopData[i][j] = -1;
                        }
                        if (i >= 1 && j >= 1 && i < UtilVar.MAP_W && j <= UtilVar.MAP_H) {
                            spaceOpen(i, j);
                        }
                    }
                }
            }
        }
    }

    //失败判定 true失败
    boolean boom() {
        if (UtilVar.FLAG_NUM == UtilVar.Mine_Max) {
            for (int i = 1; i <= UtilVar.MAP_W; i++) {
                for (int j = 1; j <= UtilVar.MAP_H; j++) {
                    if (UtilVar.BottomData[i][j] == 0) {
                        UtilVar.TopData[i][j] = -1;
                    }
                }
            }
        }
        for (int i = 1; i <= UtilVar.MAP_W; i++) {
            for (int j = 1; j <= UtilVar.MAP_H; j++) {
                if (UtilVar.BottomData[i][j] == -1 && UtilVar.TopData[i][j] == -1) {
                    UtilVar.state = 2;
                    seeBoom();
                    return true;
                }
            }
        }
        return false;
    }

    //显示雷区所有雷
    void seeBoom() {
        for (int i = 1; i <= UtilVar.MAP_W; i++) {
            for (int j = 1; j <= UtilVar.MAP_H; j++) {
                //是雷，但是有覆盖，则直接显示
                if (UtilVar.BottomData[i][j] == -1 && UtilVar.TopData[i][j] != -1) {
                    UtilVar.TopData[i][j] = -1;
                }
                //不是雷，但是有旗，显示插错旗
                if (UtilVar.BottomData[i][j] != -1 && UtilVar.TopData[i][j] == 1) {
                    UtilVar.TopData[i][j] = 2;
                }
            }
        }
    }

    //胜利判断true胜利
    boolean victory() {
        int count = 0;//统计未打开格子数
        for (int i = 1; i <= UtilVar.MAP_W; i++) {
            for (int j = 1; j <= UtilVar.MAP_H; j++) {
                if (UtilVar.TopData[i][j] != -1) {
                    count++;
                }
            }
        }
        if (count == UtilVar.Mine_Max) {
            UtilVar.state = 1;
            for (int i = 1; i <= UtilVar.MAP_W; i++) {
                for (int j = 1; j <= UtilVar.MAP_H; j++) {
                    if (UtilVar.TopData[i][j] == 0) {//未翻开的变成旗
                        UtilVar.TopData[i][j] = 1;
                    }
                }
            }
            int l = Math.toIntExact((UtilVar.END_TIME - UtilVar.START_TIME) / 1000);
            int r = UtilAPI.readRank();
            if (l < r) {
                UtilAPI.setRank(String.valueOf(l));
            }
            return true;
        }
        return false;
    }

    //重置游戏
    void reGame() {

        for (int i = 1; i <= UtilVar.MAP_W; i++) {
            for (int j = 1; j <= UtilVar.MAP_H; j++) {
                UtilVar.TopData[i][j] = 0;
            }
        }
    }
}
