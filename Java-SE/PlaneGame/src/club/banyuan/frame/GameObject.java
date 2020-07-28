package club.banyuan.frame;

import java.awt.*;

public class GameObject {
    protected Image image;
    protected double x, y;
    protected int speed;
    protected int width, height;

    public void drawSelf(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }

    public GameObject(Image image, double x, double y, int speed, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public GameObject(Image image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public GameObject() {
    }

    // 返回每个对象外部的矩形,便于后期的碰撞检测
    public Rectangle getRectangle() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}
