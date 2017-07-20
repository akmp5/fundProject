package com.enterprise_sss.view.panel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.enterprise_sss.util.GBC;
import com.enterprise_sss.util.ImagePanel;
import com.enterprise_sss.view.frame.MainFrame;
import com.enterprise_sss.view.panel.depotmanage.TransDepotBasePanel;
import com.enterprise_sss.view.panel.maintenance.PurchaseInPanel;
import com.enterprise_sss.vo.TableVO;

public class mainPanel1 extends ImagePanel{
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel fpanel = new JPanel();
	MainFrame frame;
	public mainPanel1(String path,MainFrame frame) {
		super(path);
		this.frame = frame;
	
		initial();
        this.setLayout(new GridLayout(0,1));
		this.add(fpanel);
		fpanel.setOpaque(false);
	}
	

	
	public void initial(){
		fpanel.setLayout(new GridBagLayout());
		panel.setPreferredSize(new Dimension(500,500));
		panel1.setPreferredSize(new Dimension(500,500));
		panel2.setPreferredSize(new Dimension(500,500));
		panel.setOpaque(false);
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder());
		panel1.setBorder(BorderFactory.createEmptyBorder());
		panel2.setBorder(BorderFactory.createEmptyBorder());
		fpanel.add(panel,new GBC(1,0).setInset(300,10,-40,0).setFill(GBC.BOTH).setIpad(110, 100));
		fpanel.add(panel1,new GBC(0,2).setInset(-40,300,250,0).setFill(GBC.BOTH).setIpad(100, 100));
		fpanel.add(panel2,new GBC(2,1).setInset(10,0,-10,200).setFill(GBC.BOTH).setIpad(140, 100));
		
		panel.addMouseListener(new MouseAdapter(){
			
			 public void mouseEntered(MouseEvent e) {
				 
			 }
	 
			 public void mouseClicked(MouseEvent e) {
				 frame.getPane().remove(frame.getPane().getRightComponent());
				 frame.getPane().setRightComponent(new TransDepotBasePanel());
			 }
			 
			 public void mouseExited(MouseEvent e) {
				 frame.getPane().remove(frame.getPane().getRightComponent());
				 frame.getPane().setRightComponent(new mainPanel("image/jpg/main.jpg",frame));
			 }
		});
		
		
		panel1.addMouseListener(new MouseAdapter(){
			 public void mouseEntered(MouseEvent e) {
				 frame.getPane().remove(frame.getPane().getRightComponent());
				 frame.getPane().setRightComponent(new mainPanel2("image/jpg/main2.jpg",frame));
			 }

//			 public void mouseClicked(MouseEvent e) {
//				 System.out.println("====hong===1");
//			 }
		});
		
		panel2.addMouseListener(new MouseAdapter(){
			 public void mouseEntered(MouseEvent e) {
				 frame.getPane().remove(frame.getPane().getRightComponent());
				 frame.getPane().setRightComponent(new mainPanel3("image/jpg/main1.jpg",frame));
			 }

//			 public void mouseClicked(MouseEvent e) {
//				 System.out.println("====fei===2");
//			 }
		});
		
//		fpanel.addMouseListener(new MouseAdapter(){
//			 public void mouseEntered(MouseEvent e) {
//				 frame.getPane().remove(frame.getPane().getRightComponent());
//				 frame.getPane().setRightComponent(new mainPanel("image/jpg/main.jpg",frame));
//			 }
//
//			 public void mouseClicked(MouseEvent e) {
//				 System.out.println("====fei===2");
//			 }
//		});
	}
}
