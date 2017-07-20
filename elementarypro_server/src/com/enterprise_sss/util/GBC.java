package com.enterprise_sss.util;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * ����GBC��ʵ�������ĳ��������GridBagLayout���ֹ����������ϵ�������� λ�á���С���������Ե�����
 * @author Wang ming 2009-10-12
 * @version 1.0
 * 
 */
public class GBC extends GridBagConstraints {

	public GBC() {
		super();
	}

	/**
	 * ���������ʼλ��(λ����������)
	 * 
	 * @param x
	 * @param y
	 */
	public GBC(int x, int y) {
		this.gridx = x;
		this.gridy = y;
	}

	public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}

	public GBC setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}

	/**
	 * �����������ʾ������������Сʱ�������С��������仯���
	 * 
	 * @param fill
	 * @return
	 */
	public GBC setFill(int fill) {
		this.fill = fill;
		return this;
	}

	/**
	 * ��������Ŀ��Ⱥ͸߶�
	 * 
	 * @param weightx
	 * @param weighy
	 * @return
	 */
	public GBC setWeight(double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}

	/**
	 * �������ǰ��������֮��ļ���С
	 * 
	 * @param distance
	 * @return
	 */
	public GBC setInset(int distance) {
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}

	/**
	 * �������ǰ��������֮��ļ���С
	 * 
	 * @param distance
	 * @return
	 */
	public GBC setInset(int top, int left, int bottom, int right) {
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}

	public GBC setIpad(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
	
	public GBC setGridwidth(int gridwidth)
	{
		this.gridwidth = gridwidth;
		return this;
	}
	
	public GBC getGridhight(int gridhight)
	{
		this.gridheight = gridhight;
		return this;
	}
}