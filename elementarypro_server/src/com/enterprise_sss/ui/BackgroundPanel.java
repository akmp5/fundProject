package com.enterprise_sss.ui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
    private ImageIcon icon;
 
    public BackgroundPanel(ImageIcon icon) {
        this.icon = icon;
        setOpaque(false);
    }
 
    @Override
    protected void paintComponent(Graphics g) {// ��д���ƿؼ����
        if (icon != null) {
            int width = getWidth();// ��ÿؼ����
            int height = getHeight();// ��ÿؼ��߶�
            g.drawImage(icon.getImage(), 0, 0, width, height, this);// ����ͼƬ��ؼ���С��ͬ
        }
        super.paintComponent(g);// ���ø��๹�췽��
    }
}