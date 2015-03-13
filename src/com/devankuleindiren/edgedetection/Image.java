package com.devankuleindiren.edgedetection;

import java.awt.*;

public class Image {

    private Color[][] image;

    public Image (Color[][] image) {
        this.image = image;
    }

    public int getWidth() {
        return image[0].length;
    }

    public int getHeight() {
        return image.length;
    }

    public Color getRGBPixel (int x, int y) {
        if (x >= 0 && x < image[0].length && y >=0 && y < image.length) {
            return image[y][x];
        }
        return null;
    }

    public void setRGBPixel (int x, int y, Color rgb) {
        if (x >= 0 && x < image[0].length && y >=0 && y < image.length) {
            image[y][x] = new Color(rgb.getRGB());
        }
    }

    public void draw (Graphics g, int width, int height) {
        int imageW = image[0].length;
        int imageH = image.length;

        double colScale = (double)width/(double)imageW;
        double rowScale = (double)height/(double)imageH;

        for(int col=0; col < imageW; ++col) {
            for(int row=0; row < imageH; ++row) {
                int colPos = (int)(col*colScale);
                int rowPos = (int)(row*rowScale);
                int nextCol = (int)((col+1)*colScale);
                int nextRow = (int)((row+1)*rowScale);

                if (g.hitClip(colPos,rowPos,nextCol-colPos,nextRow-rowPos)) {
                    g.setColor(getRGBPixel(col, row));
                    g.fillRect(colPos,rowPos,nextCol-colPos,nextRow-rowPos);
                }
            }
        }
    }
}
