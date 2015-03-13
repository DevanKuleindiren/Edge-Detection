package com.devankuleindiren.edgedetection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    public static Image getImage() {
        BufferedImage imageImport = null;
        try {
            imageImport = ImageIO.read(new File("45.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int height = imageImport.getHeight();
        int width = imageImport.getWidth();

        Color[][] image = new Color[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image[y][x] = new Color(imageImport.getRGB(x, y));
            }
        }

        return new Image(image);
    }

}
