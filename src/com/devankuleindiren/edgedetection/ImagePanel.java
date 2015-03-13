package com.devankuleindiren.edgedetection;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image image;

    private int zoom = 1; //Number of pixels used to represent a cell
    private int width = 1; //Width of game board in pixels
    private int height = 1;//Height of game board in pixels

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public ImagePanel (Image image) {
        super();

        this.image = image;

        display(image);
    }

    protected void paintComponent(Graphics g) {
        if (image == null) return;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        image.draw(g, width, height);
    }

    public void display(Image w) {
        image = w;
        int newWidth = image.getWidth() * zoom;
        int newHeight = image.getHeight() * zoom;
        if (newWidth != width || newHeight != height) {
            width = newWidth;
            height = newHeight;
            revalidate(); //trigger the DrawingBoard to re-layout its components
        }
        repaint();
    }

    public void toGreyScale() {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                try {
                    double red = image.getRGBPixel(x, y).getRed()  * 0.299;
                    double green = image.getRGBPixel(x, y).getGreen() * 0.587;
                    double blue = image.getRGBPixel(x, y).getBlue() * 0.114;

                    int average = (int) (red + green + blue);

                    Color color = new Color(average, average, average);

                    image.setRGBPixel(x, y, color);
                } catch (NullPointerException e) {
                    System.out.println(x + ", " + y);
                }

            }
        }

        repaint();
    }

    public void convolute (double[][] kernel) {
        int kWidth = kernel[0].length;
        int kHeight = kernel.length;

        Image result = new Image(new Color[image.getHeight() - kHeight + 1][image.getWidth() - kWidth + 1]);

        if (kWidth <= image.getWidth() && kHeight <= image.getHeight()) {
            for (int x = 0; x <= image.getWidth() - kWidth; x++) {
                for (int y = 0; y <= image.getHeight() - kHeight; y++) {


                    double sum = 0;
                    for (int i = 0; i < kWidth; i++) {
                        for (int j = 0; j < kHeight; j++) {
                            sum += kernel[j][i] * image.getRGBPixel(x + i, y + j).getRed();
                        }
                    }
                    result.setRGBPixel(x, y, new Color((int) (sum / (kWidth * kHeight))));


                }
            }

            this.image = result;

            repaint();
        }
    }


}
