package com.enterprise_sss.util;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;



public class MyTableModel extends AbstractTableModel{
		Object[] columnHeader1 = {"姓名","年龄","性别","选项"};
		Object[][] dataRow1 = {{"mmm","23","男",new Boolean(true)},{"hhh","24","男",new Boolean(false)}};
		Vector dataRow;
		Vector columnHeader;
		int tableModel ;
		
		public MyTableModel(Vector dataRow,Vector columnHeader)
		{
			this.dataRow = dataRow;
			this.columnHeader = columnHeader;
			tableModel = 1;
		}
		
		public MyTableModel()
		{

			tableModel =2;
		}
		
		public void setHeader(Object[] header)
		{
			this.columnHeader1 = header;
		}
		
		public void setData(Object[][] data)
		{
			this.dataRow1 = data;
		}
		
		
		public String getColumnName(int columnIndex)
		{	if (tableModel == 1)
				return columnHeader.get(columnIndex).toString();
			else
				return columnHeader1[columnIndex].toString();
			
		}
		
		public int getColumnCount() {
			// TODO Auto-generated method stub
			if (tableModel==1)
				return columnHeader.size();
			else
				return columnHeader1.length;
		}
		public int getRowCount() {
			// TODO Auto-generated method stub
			if(tableModel==1)
				return dataRow.size();
			else
				return dataRow1.length;
		}
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub	
			if(tableModel==1)
				return ((Vector)dataRow.get(rowIndex)).get(columnIndex);				
			else
				return dataRow1[rowIndex][columnIndex];
		}
		
	    public Class<?> getColumnClass(int columnIndex) {
	  
			if(tableModel==2)
	    		return getValueAt(0,columnIndex).getClass();
	    	else
	    		return getValueAt(0,columnIndex).getClass();
	    }
	    
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	    	return true;
	    }	
	    
	    public void setValueAt(Object aValue,int rowIndex,int columnIndex){
	    	if(tableModel==1){
		    	Vector v = ((Vector)dataRow.get(rowIndex));
		    	v.set(columnIndex, aValue);
	    	}
	    	else{
	    		dataRow1[rowIndex][columnIndex]= aValue;
	    	}	    		
	    	this.fireTableCellUpdated(rowIndex, columnIndex);
	    }
}

