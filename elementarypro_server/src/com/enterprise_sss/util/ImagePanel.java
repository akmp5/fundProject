package com.enterprise_sss.util;

import javax.swing.JPanel;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ImagePanel extends JPanel {
    public ImagePanel(String path) {
        try {
            File f = new File(path);
            img = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image img;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (img != null) {
            g2.drawImage(img, 0, 0, getWidth(), getHeight(),
                         0, 0, img.getWidth(null), img.getHeight(null), null);
        }
    }
} 


