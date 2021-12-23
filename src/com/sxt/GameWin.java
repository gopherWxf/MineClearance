package com.sxt;

import javax.swing.*;

public class GameWin extends JFrame {
    void launch() {
        this.setVisible(true);//设置窗口可见
        this.setSize(500, 500);//设置窗口大小
        this.setLocationRelativeTo(null);//设置窗口位置（居中）
        this.setTitle("扫雷");//设置窗口标题
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//设置关闭窗口
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
