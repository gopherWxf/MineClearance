package com.sxt;

import java.awt.*;

/*
绘制游戏相关的组件
 */
public class MapBottom {
    //绘制地图网格
    void paintSelf(Graphics graphics) {
        for (int i = 0; i < 500; i += 50) {
            graphics.setColor(Color.red);
            graphics.drawLine(0, i, 500, i);
            graphics.drawLine(i, 0, i, 500);
        }
    }
}
