package club.banyuan.frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GameImage {
    private GameImage() {

    }

    public static Image getImage(String path) {
        BufferedImage u = null;
        try {
            URL url = GameImage.class.getClassLoader().getResource(path);
            u = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return u;
    }
}
