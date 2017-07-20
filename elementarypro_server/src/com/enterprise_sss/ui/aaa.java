/*package com.enterprise_sss.ui;

//转自http://mxcyk.com/?post=191 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class aaa extends JFrame {

  *//**
   * 转载请注明：mxcyk.com
   *//*
  private JPanel contentPane;
  private Point pressedPoint;

 
  public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
          public void run() {
              try {
                  MyFrame frame = new MyFrame();
                  frame.setVisible(true);
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      });
  }

  *//**
   * Create the frame.
   *//*
  public MyFrame() {
      setUndecorated(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 300);
      contentPane = new JPanel();
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      // 获得窗体背景图片
      ImageIcon topPanelIcon = new ImageIcon(MyFrame.class.getResource("/images/frameTop.png"));
      ImageIcon centerPanelIcon = new ImageIcon(MyFrame.class.getResource("/images/frameCenter.png"));
      // 获得窗体背景图片大小
      Dimension topPanelDimension = new Dimension(topPanelIcon.getIconWidth(), topPanelIcon.getIconHeight());
      Dimension centerPanelDimension = new Dimension(centerPanelIcon.getIconWidth(), centerPanelIcon.getIconHeight());

      BackgroundPanel topPanel = new BackgroundPanel(topPanelIcon);
      topPanel.addMouseMotionListener(new MouseMotionAdapter() {
          @Override
          public void mouseDragged(MouseEvent e) {
              do_topPanel_mouseDragged(e);
          }
      });
      topPanel.addMouseListener(new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
              do_topPanel_mousePressed(e);
          }
      });
      FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
      flowLayout.setAlignment(FlowLayout.RIGHT);
      topPanel.setPreferredSize(topPanelDimension);// 设置面板最佳大小
      contentPane.add(topPanel, BorderLayout.NORTH);
      // 定义最小化按钮
      JButton iconifiedButton = new JButton("");// 创建按钮
      iconifiedButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              do_iconifiedButton_actionPerformed(e);
          }
      });
      iconifiedButton.setMargin(new Insets(1, 1, 1, 1));// 设置按钮内部空白
      iconifiedButton.setRolloverIcon(new ImageIcon(MyFrame.class.getResource("/images/minBH.jpg")));// 设置鼠标悬浮时显示的图片
      iconifiedButton.setBorderPainted(false);// 不绘制鼠标边界
      iconifiedButton.setContentAreaFilled(false);// 不使用颜色填充区域
      iconifiedButton.setFocusPainted(false);// 不绘制光标
      iconifiedButton.setIcon(new ImageIcon(MyFrame.class.getResource("/images/minB.jpg")));// 设置按钮图标
      topPanel.add(iconifiedButton);// 应用按钮
      // 定义最大化按钮
      JToggleButton maximizedButton = new JToggleButton("");// 创建按钮
      maximizedButton.addItemListener(new ItemListener() {
          public void itemStateChanged(ItemEvent e) {
              do_maximizedButton_itemStateChanged(e);
          }
      });
      maximizedButton.setMargin(new Insets(1, 1, 1, 1));// 设置按钮内部空白
      maximizedButton.setRolloverIcon(new ImageIcon(MyFrame.class.getResource("/images/maxBH.jpg")));// 设置鼠标悬浮时显示的图片
      maximizedButton.setFocusPainted(false);// 不绘制鼠标边界
      maximizedButton.setContentAreaFilled(false);// 不使用颜色填充区域
      maximizedButton.setBorderPainted(false);// 不绘制光标
      maximizedButton.setIcon(new ImageIcon(MyFrame.class.getResource("/images/maxB.jpg")));// 设置按钮图标
      topPanel.add(maximizedButton);// 应用按钮
      // 定义关闭按钮
      JButton closedButton = new JButton("");// 创建按钮
      closedButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              do_closedButton_actionPerformed(e);
          }
      });
      closedButton.setMargin(new Insets(1, 1, 1, 1));// 设置按钮内部空白
      closedButton.setRolloverIcon(new ImageIcon(MyFrame.class.getResource("/images/closeBH.jpg")));// 设置鼠标悬浮时显示的图片
      closedButton.setBorderPainted(false);// 不绘制鼠标边界
      closedButton.setContentAreaFilled(false);// 不使用颜色填充区域
      closedButton.setFocusPainted(false);// 不绘制光标
      closedButton.setIcon(new ImageIcon(MyFrame.class.getResource("/images/closeB.jpg")));// 设置按钮图标
      topPanel.add(closedButton);// 应用按钮
      BackgroundPanel centerPanel = new BackgroundPanel(centerPanelIcon);
      centerPanel.setPreferredSize(centerPanelDimension);// 设置面板最佳大小
      contentPane.add(centerPanel, BorderLayout.CENTER);
  }

  // 实现窗体最小化功能
  protected void do_iconifiedButton_actionPerformed(ActionEvent e) {
      setExtendedState(JFrame.ICONIFIED);
  }

  // 实现窗体最大化功能
  protected void do_maximizedButton_itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
          setExtendedState(JFrame.MAXIMIZED_BOTH);// 最大化窗体
      } else {
          setExtendedState(JFrame.NORMAL);// 恢复普通窗体状态
      }
  }

  // 实现关闭窗体功能
  protected void do_closedButton_actionPerformed(ActionEvent e) {
      dispose();// 销毁窗体
  }

  protected void do_topPanel_mousePressed(MouseEvent e) {
      pressedPoint = e.getPoint();// 记录单击鼠标时指针在屏幕上的位置
  }

  protected void do_topPanel_mouseDragged(MouseEvent e) {
      Point currentPoint = e.getPoint();// 获得拖拽鼠标时鼠标指针在屏幕上位置
      int x = currentPoint.x - pressedPoint.x;// 获得鼠标指针横坐标变化值
      int y = currentPoint.y - pressedPoint.y;// 获得鼠标指针纵坐标变化值
      Point frameLocation = getLocation();// 获得窗体当前位置
      setLocation(frameLocation.x + x, frameLocation.y + y);// 改变窗体位置
  }
}
//-------------------------------------------------------------------------------------------------------

*/