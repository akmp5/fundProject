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
import com.enterprise_sss.view.panel.maintenance.PurchaseInPanel;
import com.enterprise_sss.vo.TableVO;



public class mainPanel3 extends ImagePanel {
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel fpanel = new JPanel();
	MainFrame frame;
	public mainPanel3(String path,MainFrame frame) {
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
				 frame.getPane().remove(frame.getPane().getRightComponent());
				 frame.getPane().setRightComponent(new mainPanel1("image/jpg/main3.jpg",frame));

			 }
	 
//			 public void mouseClicked(MouseEvent e) {
//				 System.out.println("===mo====");
//			 }
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
			TableVO tvo = new TableVO();
			 public void mouseEntered(MouseEvent e) {
			
			 }

			 public void mouseClicked(MouseEvent e) {
				 Vector title = new Vector();
					title.add("进货单编号");
					title.add("供货商编号");
					title.add("进货日期");
					title.add("业务员编号");
					title.add("制单人");
					title.add("验收员");
					title.add("保管员");
					title.add("采购订单编号");
					tvo.setTitle(title);
					frame.getPane().remove(frame.getPane().getRightComponent());
					frame.getPane().setRightComponent(new PurchaseInPanel("进货单",tvo));
			 }
			 
			 public void mouseExited(MouseEvent e) {
				 frame.getPane().remove(frame.getPane().getRightComponent());
				 frame.getPane().setRightComponent(new mainPanel("image/jpg/main.jpg",frame));
			 }
		});
		

	}
}
