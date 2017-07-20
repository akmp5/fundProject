package com.enterprise_sss.view.dialog.testJFreeChar;

import java.awt.Font;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 
 * @author hp 创建时间：12:26:51 AM Oct 25, 2009
 */
public class BarChart {

	/**
	 * 柱状图
	 */
	JFreeChart chart;

	public JFreeChart getBarChart(String str, Vector data) {

		chart = ChartFactory.createBarChart3D(str, "类别分布", "相对百分比",
				getBarDataSet(str, data), PlotOrientation.VERTICAL, true,
				false, false);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();

		// 解决乱码问题
		Font font = new Font("SimSun", 10, 20);
		TextTitle tt = chart.getTitle();
		tt.setFont(font);

		Font font2 = new Font("SimSun", 8, 15);

		BarRenderer barrenderer = (BarRenderer) plot.getRenderer();
		barrenderer.setBaseItemLabelFont(font2);
		barrenderer.setSeriesItemLabelFont(1, font2);

		/** *********解决乱码问题******* */
		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setTickLabelFont(font2);

		barrenderer.setBaseItemLabelFont(font2);
		barrenderer.setSeriesItemLabelFont(1, font2);

		domainAxis.setLabelFont(font2);

		numberaxis.setTickLabelFont(font2);

		numberaxis.setLabelFont(font2);

		chart.getLegend().setItemFont(font2);

		return chart;
	}

	public DefaultCategoryDataset getBarDataSet(String str, Vector data) {

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (int i = 0; i < data.size(); i++) {
			dataSet
					.setValue(Integer.parseInt(((Vector) data.get(i)).get(0)
							.toString()), ((Vector) data.get(i)).get(1)
							.toString(), str);
		}

		return dataSet;
	}
}
