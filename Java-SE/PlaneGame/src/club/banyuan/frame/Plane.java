package club.banyuan.frame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject {
    int speed;
    boolean up,down,right,left;
    int width, height;
    @Override
    public void drawSelf(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
        if (left) {
            x -= speed;
        }
        if (right) {
            x += speed;
        }

        if (down) {
            y += speed;
        }
        if (up) {
            y -= speed;
        }
    }

    // 给飞机添加方向
    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    // 给飞机取消方向
    public void minusDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }

    public Plane(Image image, double x, double y, int speed) {
        super(image, x, y);
        this.speed = speed;
        this.width = 30;
        this.height = 30;
        super.width = width;
        super.height = height;
    }


}
