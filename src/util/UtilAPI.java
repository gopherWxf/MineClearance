package util;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javazoom.jl.player.Player;

public class UtilAPI {
    //绘制文字
    public static void drawWord(Graphics graphics, String str, int x, int y, int size, Color color) {
        graphics.setColor(color);
        graphics.setFont(new Font("宋体", Font.BOLD, size));
        graphics.drawString(str, x, y);
    }

    static Player player = null;

    public static void play() {
        if (UtilVar.voice == 1) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        File file = new File("voice/boom.mp3");
                        FileInputStream fis = new FileInputStream(file);
                        BufferedInputStream stream = new BufferedInputStream(fis);
                        player = new Player(stream);
                        player.play();
                    } catch (Exception e) {
                    }
                }
            }).start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.close();
        }
    }

    public static void setRank(String rank) {
        String fileName = "Rank/rank.txt";
        // 从JDK1.7开始提供的方法
        // 使用Files.write创建一个文件并写入
        try {
            Files.write(Paths.get(fileName),
                    rank.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int readRank() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("Rank/rank.txt"));
            String s, s1 = new String();
            s = in.readLine();
            in.close();
            return Integer.valueOf(s).intValue();
        } catch (Exception e) {

        }
        return 99999;
    }
}
