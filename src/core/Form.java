package core;

import util.UtilVar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Form extends JFrame {
    ScreenBottom mapBottom = new ScreenBottom();
    ScreenTop mapTop = new ScreenTop();
    ScreenSelect gameSelect = new ScreenSelect();
    boolean begin = false;
    int windWight = 2 * UtilVar.OFFSET + UtilVar.MAP_W * UtilVar.LATTICE;
    int windHeight = 4 * UtilVar.OFFSET + UtilVar.MAP_H * UtilVar.LATTICE;

    Image ScreenCache = null;

    public void launch() {
        UtilVar.START_TIME = System.currentTimeMillis();
        this.setVisible(true);//设置窗口可见
        if (UtilVar.state == 3) {
            this.setSize(500, 650);
        } else {
            this.setSize(windWight, windHeight);//设置窗口大小
        }
        this.setLocationRelativeTo(null);//设置窗口位置（居中）
        this.setTitle("扫雷");//设置窗口标题
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//设置关闭窗口
        //鼠标事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (UtilVar.state) {
                    case 0:
                        if (e.getButton() == 1) {//左键点击
                            UtilVar.MOUSE_X = e.getX();
                            UtilVar.MOUSE_Y = e.getY();
                            UtilVar.MOUSE_LEFT = true;
                        }
                        if (e.getButton() == 3) {//右键点击
                            UtilVar.MOUSE_X = e.getX();
                            UtilVar.MOUSE_Y = e.getY();
                            UtilVar.MOUSE_RIGHT = true;
                        }
                        if (e.getButton() == 2) {//滚轮
                            UtilVar.state = 3;
                            begin = true;
                        }
                    case 1:
                    case 2:
                        if (e.getButton() == 1) {
                            if (e.getX() > UtilVar.OFFSET + UtilVar.LATTICE * UtilVar.MAP_W / 2
                                    && e.getX() < UtilVar.OFFSET + UtilVar.LATTICE * UtilVar.MAP_W / 2 + UtilVar.LATTICE
                                    && e.getY() > UtilVar.OFFSET
                                    && e.getY() < UtilVar.OFFSET + UtilVar.LATTICE) {
                                mapBottom.reGame();
                                mapTop.reGame();
                                UtilVar.state = 0;
                                UtilVar.FLAG_NUM = 0;
                                UtilVar.voice=0;
                                UtilVar.START_TIME = System.currentTimeMillis();
                            }
                        }
                        break;
                    case 3:
                        if (e.getButton() == 1) {//左键点击
                            UtilVar.MOUSE_X = e.getX();
                            UtilVar.MOUSE_Y = e.getY();
                            begin = gameSelect.hard();
                        }
                        break;
                }
            }
        });
        //实时绘制
        while (true) {
            repaint();
            begin();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void begin() {
        if (begin) {
            begin = false;
            gameSelect.hard(UtilVar.level);
            dispose();
            Form gameWin = new Form();
            UtilVar.FLAG_NUM=0;
            UtilVar.START_TIME = System.currentTimeMillis();
            mapTop.reGame();
            mapBottom.reGame();
            gameWin.launch();
        }
    }

    @Override
    public void paint(Graphics graphics) {
        if (UtilVar.state == 3) {
            gameSelect.paintSelf(graphics);
        } else {
            ScreenCache = this.createImage(windWight, windHeight);
            Graphics gScreen = ScreenCache.getGraphics();
            mapBottom.paintSelf(gScreen);
            mapTop.paintSelf(gScreen);
            graphics.drawImage(ScreenCache, 0, 0, null);
        }
    }
}
