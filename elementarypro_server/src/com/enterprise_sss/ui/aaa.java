/*package com.enterprise_sss.ui;

//ת��http://mxcyk.com/?post=191 
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
   * ת����ע����mxcyk.com
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
      // ��ô��屳��ͼƬ
      ImageIcon topPanelIcon = new ImageIcon(MyFrame.class.getResource("/images/frameTop.png"));
      ImageIcon centerPanelIcon = new ImageIcon(MyFrame.class.getResource("/images/frameCenter.png"));
      // ��ô��屳��ͼƬ��С
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
      topPanel.setPreferredSize(topPanelDimension);// ���������Ѵ�С
      contentPane.add(topPanel, BorderLayout.NORTH);
      // ������С����ť
      JButton iconifiedButton = new JButton("");// ������ť
      iconifiedButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              do_iconifiedButton_actionPerformed(e);
          }
      });
      iconifiedButton.setMargin(new Insets(1, 1, 1, 1));// ���ð�ť�ڲ��հ�
      iconifiedButton.setRolloverIcon(new ImageIcon(MyFrame.class.getResource("/images/minBH.jpg")));// �����������ʱ��ʾ��ͼƬ
      iconifiedButton.setBorderPainted(false);// ���������߽�
      iconifiedButton.setContentAreaFilled(false);// ��ʹ����ɫ�������
      iconifiedButton.setFocusPainted(false);// �����ƹ��
      iconifiedButton.setIcon(new ImageIcon(MyFrame.class.getResource("/images/minB.jpg")));// ���ð�ťͼ��
      topPanel.add(iconifiedButton);// Ӧ�ð�ť
      // ������󻯰�ť
      JToggleButton maximizedButton = new JToggleButton("");// ������ť
      maximizedButton.addItemListener(new ItemListener() {
          public void itemStateChanged(ItemEvent e) {
              do_maximizedButton_itemStateChanged(e);
          }
      });
      maximizedButton.setMargin(new Insets(1, 1, 1, 1));// ���ð�ť�ڲ��հ�
      maximizedButton.setRolloverIcon(new ImageIcon(MyFrame.class.getResource("/images/maxBH.jpg")));// �����������ʱ��ʾ��ͼƬ
      maximizedButton.setFocusPainted(false);// ���������߽�
      maximizedButton.setContentAreaFilled(false);// ��ʹ����ɫ�������
      maximizedButton.setBorderPainted(false);// �����ƹ��
      maximizedButton.setIcon(new ImageIcon(MyFrame.class.getResource("/images/maxB.jpg")));// ���ð�ťͼ��
      topPanel.add(maximizedButton);// Ӧ�ð�ť
      // ����رհ�ť
      JButton closedButton = new JButton("");// ������ť
      closedButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              do_closedButton_actionPerformed(e);
          }
      });
      closedButton.setMargin(new Insets(1, 1, 1, 1));// ���ð�ť�ڲ��հ�
      closedButton.setRolloverIcon(new ImageIcon(MyFrame.class.getResource("/images/closeBH.jpg")));// �����������ʱ��ʾ��ͼƬ
      closedButton.setBorderPainted(false);// ���������߽�
      closedButton.setContentAreaFilled(false);// ��ʹ����ɫ�������
      closedButton.setFocusPainted(false);// �����ƹ��
      closedButton.setIcon(new ImageIcon(MyFrame.class.getResource("/images/closeB.jpg")));// ���ð�ťͼ��
      topPanel.add(closedButton);// Ӧ�ð�ť
      BackgroundPanel centerPanel = new BackgroundPanel(centerPanelIcon);
      centerPanel.setPreferredSize(centerPanelDimension);// ���������Ѵ�С
      contentPane.add(centerPanel, BorderLayout.CENTER);
  }

  // ʵ�ִ�����С������
  protected void do_iconifiedButton_actionPerformed(ActionEvent e) {
      setExtendedState(JFrame.ICONIFIED);
  }

  // ʵ�ִ�����󻯹���
  protected void do_maximizedButton_itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
          setExtendedState(JFrame.MAXIMIZED_BOTH);// ��󻯴���
      } else {
          setExtendedState(JFrame.NORMAL);// �ָ���ͨ����״̬
      }
  }

  // ʵ�ֹرմ��幦��
  protected void do_closedButton_actionPerformed(ActionEvent e) {
      dispose();// ���ٴ���
  }

  protected void do_topPanel_mousePressed(MouseEvent e) {
      pressedPoint = e.getPoint();// ��¼�������ʱָ������Ļ�ϵ�λ��
  }

  protected void do_topPanel_mouseDragged(MouseEvent e) {
      Point currentPoint = e.getPoint();// �����ק���ʱ���ָ������Ļ��λ��
      int x = currentPoint.x - pressedPoint.x;// ������ָ�������仯ֵ
      int y = currentPoint.y - pressedPoint.y;// ������ָ��������仯ֵ
      Point frameLocation = getLocation();// ��ô��嵱ǰλ��
      setLocation(frameLocation.x + x, frameLocation.y + y);// �ı䴰��λ��
  }
}
//-------------------------------------------------------------------------------------------------------

*/