package club.banyuan.frame;

import java.awt.*;

public class Bullets extends GameObject{
    double degree;

    public void draw(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.orange);

        g.fillOval((int)x, (int)y, width, height);

        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);

        if (x < 0 || x > Constant.GAME_WIDTH - width) {
            degree = Math.PI - degree;
        }

        if (y < 30 || y > Constant.GAME_HEIGHT - height) {
            degree = -degree;
        }

        g.setColor(color);
    }

    public Bullets() {
        x = 200;
        y = 200;
        width = 10;
        height = 10;
        speed = 4;

        this.degree = Math.random() * Math.PI * 2;
    }


}
