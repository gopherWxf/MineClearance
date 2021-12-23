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
                if (GameUtil.MAP_Top[temp_x][temp_y] == 0) {
                    GameUtil.MAP_Top[temp_x][temp_y] = -1;
                }
                GameUtil.MOUSE_LEFT = false;
            }
            if (GameUtil.MOUSE_RIGHT == true) {
                System.out.println(GameUtil.MOUSE_X);
                System.out.println(GameUtil.MOUSE_Y);
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
}
