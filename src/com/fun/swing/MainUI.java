package com.fun.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.fun.util.ReadConfigurationFile;

public class MainUI {

	public JFrame frame=new JFrame();
	public static void main(String[] args) {
		MainUI main=new MainUI();
        main.InitUI();
        //main.sss();
		main.loadingUI();
	}
	public void sss() {
		//获取当前已安装UI界面信息
        UIManager.LookAndFeelInfo[] infos=UIManager.getInstalledLookAndFeels();
        for(int i=0;i<infos.length;i++) {
        	System.out.println(infos[i].getName()+">>"+infos[i].getClassName());
        }
        

	}
	public void loadingUI() {
		JFrame frame1=new JFrame();
		frame1.setSize(400,300);
		//设置窗口位于屏幕中央
		frame1.setLocationRelativeTo(null);
		//参数为3时，表示关闭窗口则程序退出
		frame1.setDefaultCloseOperation(3);
		//1.2设置窗体上组件的布局，此处使用流式布局FlowLayout，流式布局类似于word的布局
		//frame窗口设置为f1的流式左对齐
		frame1.setLayout(new BorderLayout(3,3)) ;
		frame1.setUndecorated(true);
		JLabel jl1 = new JLabel();
		jl1.setFont(new java.awt.Font("Dialog",1,20));   
		jl1.setText("loading data.please wait...");
		frame1.add(jl1,BorderLayout.SOUTH) ;  
		frame1.setVisible(true);
		//ReadConfigurationFile.init();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jl1.setText("loading completed.");
		frame1.add(jl1,BorderLayout.SOUTH) ;  
		frame1.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame1.dispose();
		frame.setVisible(true);
	}
	public void InitUI()
	{
		// 用JFrame创建一个名为frame的顶级容器，需要添加的包名为javax.swing.JFrame
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SwingUtilities.updateComponentTreeUI(frame);
		//设置窗口名称
		frame.setTitle("测试版");
		//设置窗口大小
		frame.setSize(1020,800);
		//设置窗口位于屏幕中央
		frame.setLocationRelativeTo(null);
		//参数为3时，表示关闭窗口则程序退出
		frame.setDefaultCloseOperation(3);
		//1.2设置窗体上组件的布局，此处使用流式布局FlowLayout，流式布局类似于word的布局
		//用FlowLayout创建一个名为f1的对象,需要添加的包名为java.awt.FlowLayout，其中LEFT表示左对齐，CENTER表示居中对齐，RIGHT表示右对齐
		FlowLayout f1=new FlowLayout(FlowLayout.LEFT);
		//frame窗口设置为f1的流式左对齐
		frame.setLayout(f1);
		frame.setLayout(new BorderLayout(3,3)) ;
		
		//面板

		JPanel jp1 = new JPanel();
		jp1.setSize(100, 100);
		JComboBox<String> jComboBox = new JComboBox<String>();
		List<String> schools = ReadConfigurationFile.getNeiGongSchool();
		for(String school:schools) {
			jComboBox.addItem(school);
		}
		jp1.add(jComboBox);
	    frame.add(new JButton("西"),BorderLayout.EAST) ;  
	    frame.add(new JButton("北"),BorderLayout.WEST) ;  
	    frame.add(new JButton("南"),BorderLayout.SOUTH) ;  
	    frame.add(jp1,BorderLayout.NORTH) ;  
	    frame.add(new JButton("中"),BorderLayout.CENTER) ;  

		

		//设置窗口可见，此句一定要在窗口属性设置好了之后才能添加，不然无法正常显示
		frame.setVisible(false);
		
	}
}
