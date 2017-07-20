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
    protected void paintComponent(Graphics g) {// 重写绘制控件外观
        if (icon != null) {
            int width = getWidth();// 获得控件宽度
            int height = getHeight();// 获得控件高度
            g.drawImage(icon.getImage(), 0, 0, width, height, this);// 绘制图片与控件大小相同
        }
        super.paintComponent(g);// 调用父类构造方法
    }
}