package com.sxt;
/*
绘制顶层组件
 */

import java.awt.*;

public class MapTop {
    //绘制地图网格
    void paintSelf(Graphics graphics) {
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
