package com.enterprise_sss.view.panel;

import com.enterprise_sss.action.DataFindPanelAction;
import com.enterprise_sss.vo.TableVO;

public class DataFindPanel extends FindPanel {
	
	private int n = 0;
	
	private int type = 0;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public DataFindPanel(String title,String[] items,TableVO tvo){
		super(title,items,tvo);
		initAction();
	}
	
	public void initAction(){
		comboBox.addItemListener(new DataFindPanelAction(this));
	}
	
}
