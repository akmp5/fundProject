package com.enterprise_sss.view.panel.salemanage;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.enterprise_sss.action.salemanage.SaleQueryAction;
import com.enterprise_sss.dao.salemanagedao.UpdateSaleDao;
import com.enterprise_sss.util.CommonComponent;
import com.enterprise_sss.util.MyTableModel;

public class UpdateSalePanel extends SaleQueryBasePanel{

	JButton button1;
	public UpdateSalePanel(){
		
		uppanel.setBorder(BorderFactory.createTitledBorder("按条件查询："));
		tablepanel.setBorder(BorderFactory.createTitledBorder("查询结果"));
	
	}
	
	public JPanel initialPanel() {
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label = new JLabel();
		ButtonGroup bg = new ButtonGroup();
		String[] str = new String[]{"查询全部","客户编号","销售日期","商品编号","商品拼音","业务员"};			
		JRadioButton jb1=null;
		for(int i=0;i<6;i++){
			if(i==0){
				jb1= new JRadioButton(str[i],true);
			}else{
				jb1= new JRadioButton(str[i],false);
			}		
		panel1.add(jb1); 	
		rb[i]=jb1;
		bg.add(rb[i]);
		}
		panel1.setBorder(BorderFactory.createEtchedBorder());
		label.setText("输入要查找的内容：");
		panel2.add(label); 
		panel2.add(tf = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true));
		panel2.add(button = CommonComponent.buildButton("查询", null, null, null, null, null, null, true));
		panel2.add(button1 = CommonComponent.buildButton("提交", null, null, null, null, null, null, true));
		
		rowData = new SaleQueryAction(this).getRowData();
		//查询时设定按键的事件
		button.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e) {
				 string = tf.getText();
					rowData = new SaleQueryAction(UpdateSalePanel.this).getRowData();
					System.out.println("事件端测试："+rowData);
					if((rowData==null||rowData.isEmpty())){
						JOptionPane.showMessageDialog(new JFrame(),"未找到相关数据!");
					}
					else{
					mytablemodel =new MyTableModel(rowData,title); 
					table.setModel(mytablemodel);
					}
			 }
		});
		button1.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e) {
				 int i = JOptionPane.showConfirmDialog(null, "确定提交？", "确认信息", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);		 
				 if(i==JOptionPane.OK_OPTION){	
					 int[] selectrows = table.getSelectedRows();
					 Vector updateRow = new Vector();
					 Vector updateKey = new Vector();
					 for(int j=0;j<selectrows.length;j++){
						 	updateKey.add(table.getModel().getValueAt(selectrows[j], 0));
							 updateRow.add(table.getModel().getValueAt(selectrows[j], 12));
							 							
					 }
					 System.out.println(updateKey);
					 System.out.println(updateRow);
					 new UpdateSaleDao(updateRow,updateKey).update();
					 JOptionPane.showMessageDialog(new JFrame(), "售价修改成功！");
				 }	
			 }
		});
		
		uppanel.setLayout(new GridLayout(2,1));
		uppanel.add(panel1);
		uppanel.add(panel2);
		return uppanel;
	}	
		
	
	
		

/**
 * @测试使用
 */
public static void main(String[] args) {
	// TODO Auto-generated method stub
	JFrame frame = new JFrame("测试");		
	UpdateSalePanel us = new UpdateSalePanel();
	frame.getContentPane().add(us);
	frame.setSize(500,500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
}

}
