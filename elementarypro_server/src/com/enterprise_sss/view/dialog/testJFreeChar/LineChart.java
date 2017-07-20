package com.enterprise_sss.view.dialog.testJFreeChar;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
 /**
 * 
 * @author hp 
 * 创建时间：12:49:33 AM  Oct 25, 2009
 */
public class LineChart {

	/**
	 * 趋势分析折线图  （由于我没有实现按时间查询的， 因此此折线图是定值——即其dataSet不是从数据库查询出来的）
	 */
	public JFreeChart getLineChart(){
		
		JFreeChart chart = ChartFactory.createXYLineChart("中南大学男女比例走势", "月份",   
                "点击率率百分点", createLineDataSet(), PlotOrientation.VERTICAL, true,   
                true, false);   
        
       
        //解决乱码问题
		 Font font = new Font("SimSun",10,20); 
        TextTitle tt = chart.getTitle(); 
        tt.setFont(font);
        
       XYPlot plot = (XYPlot) chart.getXYPlot();
      
        chart.getLegend().setItemFont(font);
        Font font2 = new Font("SimSun",8,15);
        

      /***********解决乱码问题********/
      NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
      ValueAxis domainAxis = plot.getDomainAxis(); 
      domainAxis.setTickLabelFont(font2);   
    
      domainAxis.setLabelFont(font2);   
    
      numberaxis.setTickLabelFont(font2);   
     
      numberaxis.setLabelFont(font2);   

        return chart;
	}
	
	public XYSeriesCollection createLineDataSet(){
		
		XYSeriesCollection seriesCollection = new XYSeriesCollection();   
        XYSeries series1 = new XYSeries("男的");   
        XYSeries series2 = new XYSeries("女的");   
        XYSeries series3 = new XYSeries("中性的");   
  
        series1.add(1, 7.25);   
        series1.add(2, 4.81);   
        series1.add(3, 3.69);   
        series1.add(4, 3.53);   
        series1.add(5, 2.95);   
        series1.add(6, 3.96);   
        
        series1.add(7, 5.23);   
        series1.add(8, 5.45);   
        series1.add(9, 3.69);   
        series1.add(10, 5.26);   
        series1.add(11, 4.95);   
        series1.add(12, 3.96);   
  
        series2.add(1, 10.57);   
        series2.add(2, 5.37);   
        series2.add(3, 4.87);   
        series2.add(4, 4.87);   
        series2.add(5, 3.63);   
        series2.add(6, 5.27);   
        
        series2.add(7, 7.27);   
        series2.add(8, 7.37);   
        series2.add(9, 6.17);   
        series2.add(10, 8.87);   
        series2.add(11, 7.63);   
        series2.add(12, 5.27); 
        
        
        series3.add(1, 4.76);   
        series3.add(2, 3.63);   
        series3.add(3, 2.82);   
        series3.add(4, 2.82);   
        series3.add(5, 2.37);   
        series3.add(6, 3.33);   
        
        series3.add(7, 4.76);   
        series3.add(8, 3.63);   
        series3.add(9, 2.82);   
        series3.add(10, 2.82);   
        series3.add(11, 2.37);   
        series3.add(12, 3.33); 
        
        
        seriesCollection.addSeries(series1);   
        seriesCollection.addSeries(series2);   
        seriesCollection.addSeries(series3); 
        
        return seriesCollection;   
        
	}
}
