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
		
		uppanel.setBorder(BorderFactory.createTitledBorder("��������ѯ��"));
		tablepanel.setBorder(BorderFactory.createTitledBorder("��ѯ���"));
	
	}
	
	public JPanel initialPanel() {
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label = new JLabel();
		ButtonGroup bg = new ButtonGroup();
		String[] str = new String[]{"��ѯȫ��","�ͻ����","��������","��Ʒ���","��Ʒƴ��","ҵ��Ա"};			
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
		label.setText("����Ҫ���ҵ����ݣ�");
		panel2.add(label); 
		panel2.add(tf = CommonComponent.buildTextField("txt", null, null, null, null, null, null, true, true));
		panel2.add(button = CommonComponent.buildButton("��ѯ", null, null, null, null, null, null, true));
		panel2.add(button1 = CommonComponent.buildButton("�ύ", null, null, null, null, null, null, true));
		
		rowData = new SaleQueryAction(this).getRowData();
		//��ѯʱ�趨�������¼�
		button.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e) {
				 string = tf.getText();
					rowData = new SaleQueryAction(UpdateSalePanel.this).getRowData();
					System.out.println("�¼��˲��ԣ�"+rowData);
					if((rowData==null||rowData.isEmpty())){
						JOptionPane.showMessageDialog(new JFrame(),"δ�ҵ��������!");
					}
					else{
					mytablemodel =new MyTableModel(rowData,title); 
					table.setModel(mytablemodel);
					}
			 }
		});
		button1.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e) {
				 int i = JOptionPane.showConfirmDialog(null, "ȷ���ύ��", "ȷ����Ϣ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);		 
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
					 JOptionPane.showMessageDialog(new JFrame(), "�ۼ��޸ĳɹ���");
				 }	
			 }
		});
		
		uppanel.setLayout(new GridLayout(2,1));
		uppanel.add(panel1);
		uppanel.add(panel2);
		return uppanel;
	}	
		
	
	
		

/**
 * @����ʹ��
 */
public static void main(String[] args) {
	// TODO Auto-generated method stub
	JFrame frame = new JFrame("����");		
	UpdateSalePanel us = new UpdateSalePanel();
	frame.getContentPane().add(us);
	frame.setSize(500,500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
}

}
