package com.enterprise_sss.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;

public class WJMenuBar extends JMenuBar{

	private ImageIcon backgroundImage;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(
					this.getWidth(), this.getHeight(), Image.SCALE_FAST));
			backgroundImage.paintIcon(this, g, 0, 0);
		}
	}

	public void setBackgroundImage(String img) {
		this.backgroundImage = new ImageIcon(img);
		this.repaint();
	}

	public void setBackgroundImage(ImageIcon icon) {
		this.backgroundImage = icon;
		this.repaint();
	}
}
