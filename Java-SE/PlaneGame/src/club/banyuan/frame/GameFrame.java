package club.banyuan.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 飞机游戏的主窗口
 */
public class GameFrame extends JFrame {

    Image planeImage = GameImage.getImage("images/plane.png");
    Image backImage = GameImage.getImage("images/backImage.jpg");
    private Image offScreenImage = null;
    int planeX = 250;
    int planeY = 250;

    Plane plane = new Plane(planeImage, planeX, planeY, 3);
    List<Bullets> bulletsList = new ArrayList();

    @Override
    public void paint(Graphics g) {
        g.drawImage(backImage, 0, 0, 500, 500, null);
        plane.drawSelf(g);
        for (Bullets bullets : bulletsList) {
            bullets.draw(g);
            if (bullets.getRectangle().intersects(plane.getRectangle())) {
                System.out.println("相撞了");
            }
        }
//        Iterator bulletIterator = bulletsList.iterator();
//        while (bulletIterator.hasNext()) {
//            ((Bullets)bulletIterator.next()).draw(g);
//            if (((Bullets)bulletIterator.next()).getRectangle().intersects(plane.getRectangle())) {
//                // 飞机和子弹相撞
//                g.drawString("You died", 250, 250);
//                System.out.println("撞了");
//            }
//        }
    }

    // 帮助反复重画窗口
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 键盘监听内部类
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }

    // 双缓冲技术解决JFrame绘图时的闪烁问题
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        }

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    // 加载窗口
    public void launchFrame() {
        this.setTitle("躲避球游戏");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        // 添加窗口监听事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintThread().start();
        addKeyListener(new KeyMonitor());

        // 初始化子弹
        for (int i = 0; i < 1; i++) {
            bulletsList.add(new Bullets());
        }
    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.launchFrame();
    }
}
