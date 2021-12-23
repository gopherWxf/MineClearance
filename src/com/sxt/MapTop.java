package com.sxt;
/*
绘制顶层组件
 */

import java.awt.*;

public class MapTop {
    //格子位置
    int temp_x;
    int temp_y;

    //判断鼠标逻辑
    void logic() {
        temp_x = 0;
        temp_y = 0;
        if (GameUtil.MOUSE_X > GameUtil.OFFSET && GameUtil.MOUSE_Y > 3 * GameUtil.OFFSET) {
            temp_x = (GameUtil.MOUSE_X - GameUtil.OFFSET) / GameUtil.LatticeSideLength + 1;
            temp_y = (GameUtil.MOUSE_Y - 3 * GameUtil.OFFSET) / GameUtil.LatticeSideLength + 1;
        }
        if (temp_x >= 1 && temp_x <= GameUtil.MAP_W
                && temp_y >= 1 && temp_y <= GameUtil.MAP_H) {
            if (GameUtil.MOUSE_LEFT == true) {
                //覆盖，则翻开
                if (GameUtil.MAP_Top[temp_x][temp_y] == 0) {
                    GameUtil.MAP_Top[temp_x][temp_y] = -1;
                }
                GameUtil.MOUSE_LEFT = false;
                spaceOpen(temp_x, temp_y);
            }
            if (GameUtil.MOUSE_RIGHT == true) {
                //覆盖则插旗
                if (GameUtil.MAP_Top[temp_x][temp_y] == 0) {
                    GameUtil.MAP_Top[temp_x][temp_y] = 1;
                }
                //插旗则取消
                else if (GameUtil.MAP_Top[temp_x][temp_y] == 1) {
                    GameUtil.MAP_Top[temp_x][temp_y] = 0;
                }
                //数字则进行判断
                else if (GameUtil.MAP_Top[temp_x][temp_y] == -1) {
                    numOpen(temp_x, temp_y);
                }
                GameUtil.MOUSE_RIGHT = false;
            }
        }

    }


    //绘制上层
    void paintSelf(Graphics graphics) {
        logic();
        //画覆盖
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                //有覆盖
                if (GameUtil.MAP_Top[i][j] == 0) {
                    graphics.drawImage(GameUtil.top,
                            GameUtil.OFFSET + (i - 1) * GameUtil.LatticeSideLength + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.LatticeSideLength + 1,
                            GameUtil.LatticeSideLength - 2,
                            GameUtil.LatticeSideLength - 2,
                            null
                    );
                }
                //插旗
                if (GameUtil.MAP_Top[i][j] == 1) {
                    graphics.drawImage(GameUtil.flag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.LatticeSideLength + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.LatticeSideLength + 1,
                            GameUtil.LatticeSideLength - 2,
                            GameUtil.LatticeSideLength - 2,
                            null
                    );
                }
                //插错旗
                if (GameUtil.MAP_Top[i][j] == 2) {
                    graphics.drawImage(GameUtil.noflag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.LatticeSideLength + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.LatticeSideLength + 1,
                            GameUtil.LatticeSideLength - 2,
                            GameUtil.LatticeSideLength - 2,
                            null
                    );
                }

            }
        }
    }

    //打开空格
    void spaceOpen(int x, int y) {
        if (GameUtil.MAP_Mine[x][y] == 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    //覆盖才递归
                    if (GameUtil.MAP_Top[i][j] != -1) {
                        GameUtil.MAP_Top[i][j] = -1;
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
        if (GameUtil.MAP_Mine[x][y] > 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (GameUtil.MAP_Top[i][j] == 1) {
                        count++;
                    }
                }
            }
            if (count == GameUtil.MAP_Mine[x][y]) {
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (GameUtil.MAP_Top[i][j] != 1) {
                            GameUtil.MAP_Top[i][j] = -1;
                        }
                        if (i >= 1 && j >= 1 && i < GameUtil.MAP_W && j <= GameUtil.MAP_H) {
                            spaceOpen(i, j);
                        }
                    }
                }
            }
        }
    }
}
