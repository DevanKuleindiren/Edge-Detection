package com.devankuleindiren.edgedetection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class Main extends JFrame {

    private ImagePanel imagePanel;
    private ControlPanel controlPanel;

    public static void main (String[] args) {
        Main gui = new Main();
        gui.imagePanel.display(ImageLoader.getImage());
        gui.setVisible(true);
    }

    public Main () {
        super ();
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JComponent iP = createImagePanel();
        add(iP, BorderLayout.CENTER);
        JComponent cP = createControlPanel(imagePanel);
        add(cP, BorderLayout.SOUTH);
    }

    private JComponent createImagePanel () {
        JPanel holder = new JPanel();
        addBorder(holder, "Image");

        Image image = ImageLoader.getImage();

        ImagePanel iP = new ImagePanel(image);
        imagePanel = iP;
        holder.add(iP);
        return holder;
    }

    private JComponent createControlPanel (ImagePanel iP) {
        JPanel holder = new JPanel();
        addBorder(holder, "Control Panel");
        ControlPanel cP = new ControlPanel(iP);
        controlPanel = cP;
        holder.add(cP);
        return holder;
    }

    private void addBorder(JComponent component, String title) {
        Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border tb = BorderFactory.createTitledBorder(etch,title);
        component.setBorder(tb);
    }
}
