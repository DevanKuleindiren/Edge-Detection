package com.devankuleindiren.edgedetection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private ImagePanel imagePanel;

    private JButton toGreyScale;
    private JButton edge;
    private JButton test;
    private JButton leftEdge;
    private JButton rightEdge;
    private JButton reset;

    public ControlPanel (final ImagePanel imagePanel) {
        super();

        this.imagePanel = imagePanel;

        toGreyScale = new JButton("Grey scale");
        edge = new JButton("Detect edge");
        test = new JButton("Test");
        leftEdge = new JButton("Left edge");
        rightEdge = new JButton("Right edge");
        reset = new JButton("Reset");

        toGreyScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.toGreyScale();
            }
        });
        edge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[][] kernel = {{ 0.0,  9.0,  0.0},
                                     { 9.0,-36.0,  9.0},
                                     { 0.0,  9.0,  0.0}};

                imagePanel.convolute(kernel);
            }
        });
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[][] kernel = {{-1.0, -1.0, -1.0},
                                     {-1.0,  8.0, -1.0},
                                     {-1.0, -1.0, -1.0}};

                imagePanel.convolute(kernel);
            }
        });
        leftEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[][] kernel = {{ 0.0,  0.0,  0.0},
                                     { 0.0,  1.0, -1.0},
                                     { 0.0,  0.0,  0.0}};

                imagePanel.convolute(kernel);
            }
        });
        rightEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[][] kernel = {{ 0.0,  0.0,  0.0},
                                     {-1.0,  1.0,  0.0},
                                     { 0.0,  0.0,  0.0}};

                imagePanel.convolute(kernel);
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.display(ImageLoader.getImage());
            }
        });

        add(toGreyScale);
        add(edge);
        //add(test);
        add(leftEdge);
        add(rightEdge);
        add(reset);
    }
}
