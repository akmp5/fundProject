package com.enterprise_sss.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.AbstractButton;

import javax.swing.plaf.basic.BasicButtonUI;

public class WButtonUI extends BasicButtonUI {

	/**
	 * @param args
	 */
    @Override
    
    public void paintButtonPressed(Graphics g, AbstractButton b){
        //g.setColor(new Color(240, 128, 128));//Ç³ºì
    	g.setColor(new Color(29, 97, 142));//Ç³ºì
        g.fillRect(0, 0, b.getWidth(), b.getHeight());
    }
}
