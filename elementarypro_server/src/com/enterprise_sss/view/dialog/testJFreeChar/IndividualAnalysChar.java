package com.enterprise_sss.view.dialog.testJFreeChar;

import java.awt.Font;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;


 /**
 * 
 * @author hp 
 * 创建时间：7:18:17 PM  Oct 22, 2009
 */
public class IndividualAnalysChar {

	private JFreeChart chart;
    public JFreeChart getChart() {
		
		return chart;
	}
    

    
    /**
     * 饼状图
     */
	public JFreeChart getPieChart(String str,Vector data) {
		 chart = ChartFactory.createPieChart3D(str, getPieDataSet(data), true, false, false);
		 PiePlot plot = (PiePlot) chart.getPlot();
		 
		 //解决乱码问题
		 Font font = new Font("SimSun",10,20); 
         TextTitle tt = chart.getTitle(); 
         tt.setFont(font);
         plot.setLabelFont(font);
         chart.getLegend().setItemFont(font);
         
		 return chart;
	
	}

	public static DefaultPieDataset getPieDataSet(Vector data) {
		DefaultPieDataset dpd = new DefaultPieDataset();
		for (int i = 0; i < data.size(); i++) {
			dpd.setValue(((Vector) data.get(i)).get(1)
					.toString(), Integer.parseInt(((Vector) data.get(i)).get(0)
					.toString()));
		}
		return dpd;
	}
}
