package com.sxt;

import javax.swing.*;
import java.awt.*;

public class GameWin extends JFrame {
    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();
    int windWight = 2 * GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.LatticeSideLength;
    int windHeight = 4 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.LatticeSideLength;

    Image ScreenCache = null;

    void launch() {
        this.setVisible(true);//设置窗口可见
        this.setSize(windWight, windHeight);//设置窗口大小
        this.setLocationRelativeTo(null);//设置窗口位置（居中）
        this.setTitle("扫雷");//设置窗口标题
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//设置关闭窗口
        //实时绘制
        while (true) {
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        ScreenCache = this.createImage(windWight, windHeight);
        Graphics gScreen = ScreenCache.getGraphics();
        mapBottom.paintSelf(gScreen);
        mapTop.paintSelf(gScreen);
        graphics.drawImage(ScreenCache, 0, 0, null);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
