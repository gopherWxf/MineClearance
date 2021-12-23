import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {
    ScreenBottom mapBottom = new ScreenBottom();
    ScreenTop mapTop = new ScreenTop();
    GameSelect gameSelect = new GameSelect();
    boolean begin = false;
    int windWight = 2 * GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.LATTICE;
    int windHeight = 4 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.LATTICE;

    Image ScreenCache = null;

    void launch() {
        GameUtil.START_TIME = System.currentTimeMillis();
        this.setVisible(true);//设置窗口可见
        if (GameUtil.state == 3) {
            this.setSize(500, 500);
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
                switch (GameUtil.state) {
                    case 0:
                        if (e.getButton() == 1) {//左键点击
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.MOUSE_LEFT = true;
                        }
                        if (e.getButton() == 3) {//右键点击
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.MOUSE_RIGHT = true;
                        }
                        if (e.getButton() == 2) {//滚轮
                            GameUtil.state = 3;
                            begin = true;
                        }
                    case 1:
                    case 2:
                        if (e.getButton() == 1) {
                            if (e.getX() > GameUtil.OFFSET + GameUtil.LATTICE * GameUtil.MAP_W / 2
                                    && e.getX() < GameUtil.OFFSET + GameUtil.LATTICE * GameUtil.MAP_W / 2 + GameUtil.LATTICE
                                    && e.getY() > GameUtil.OFFSET
                                    && e.getY() < GameUtil.OFFSET + GameUtil.LATTICE) {
                                mapBottom.reGame();
                                mapTop.reGame();
                                GameUtil.state = 0;
                                GameUtil.FLAG_NUM = 0;
                                GameUtil.START_TIME = System.currentTimeMillis();
                            }
                        }
                        break;
                    case 3:
                        if (e.getButton() == 1) {//左键点击
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
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
            gameSelect.hard(GameUtil.level);
            dispose();
            GameWin gameWin = new GameWin();
            GameUtil.FLAG_NUM=0;
            GameUtil.START_TIME = System.currentTimeMillis();
            mapTop.reGame();
            mapBottom.reGame();
            gameWin.launch();
        }
    }

    @Override
    public void paint(Graphics graphics) {
        if (GameUtil.state == 3) {
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
