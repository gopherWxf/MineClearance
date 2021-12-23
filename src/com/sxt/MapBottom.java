package com.sxt;

import java.awt.*;

/*
绘制游戏相关的组件
 */
public class MapBottom {
    //绘制地图网格
    void paintSelf(Graphics graphics) {
        //设置线条颜色
        graphics.setColor(Color.red);
        //画竖线
        for (int i = 0; i <= GameUtil.MAP_W; i++) {
            graphics.drawLine(GameUtil.OFFSET + i * GameUtil.LatticeSideLength,
                    3 * GameUtil.OFFSET,
                    GameUtil.OFFSET + i * GameUtil.LatticeSideLength,
                    3 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.LatticeSideLength);
        }
        //画横线
        for (int i = 0; i <= GameUtil.MAP_H; i++) {
            graphics.drawLine(GameUtil.OFFSET,
                    3 * GameUtil.OFFSET + i* GameUtil.LatticeSideLength,
                    GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.LatticeSideLength,
                    3 * GameUtil.OFFSET + i * GameUtil.LatticeSideLength
            );
        }
    }
}
