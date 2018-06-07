package com.fun.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.fun.bean.NeiGong;
import com.fun.tool.GetIniData;

public class MainUI {

	public MainUI(){
		gData = new GetIniData();
		gData.getAllData();
		map = gData.getNeiGongList();
	}
	public JFrame frame=new JFrame();
	private GetIniData gData;
	private Map<String, String> map;
	public static void main(String[] args) {
		MainUI main=new MainUI();
        main.InitUI();
        main.sss();
		//main.loadingUI();
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
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
		frame.setTitle("内功查询v0.1");
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
		
		//********************
		JPanel jp1 = new JPanel();
		JLabel l1 = new JLabel("选择内功：");
		JComboBox<String> jComboBox = new JComboBox<String>();
		JLabel l2 = new JLabel("输入层数：");
		JTextField t1 = new JTextField(3);
		JButton b1 = new JButton("查询");
		JLabel jl1= new JLabel();
		//面板
		t1.setText("1");
	    ItemListener itemListener = new ItemListener() {
	      @Override
	      public void itemStateChanged(ItemEvent arg0) {
	        // TODO Auto-generated method stub
	        if(ItemEvent.SELECTED == arg0.getStateChange()){
	          String selectedItem = arg0.getItem().toString();
	          int level = gData.getMaxLevel(map.get(selectedItem));
	          t1.setText(level+"");
	        }
	        if(ItemEvent.DESELECTED == arg0.getStateChange()){
	          String selectedItem = arg0.getItem().toString();
	        }
	      }
	      };
	      
		
		jp1.setSize(100, 300);
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		for (String key : map.keySet()) {
			jComboBox.addItem(key);
		}
		jComboBox.addItemListener(itemListener);
		jp1.add(l1);
		jp1.add(jComboBox);
		jp1.add(l2);
		jp1.add(t1);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int level = gData.getMaxLevel(map.get(jComboBox.getSelectedItem().toString()));
				if (!Pattern.compile("^[-\\+]?[\\d]*$").matcher(t1.getText()).matches()||t1.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "内功层数输入错误", "提示",JOptionPane.WARNING_MESSAGE);  
					return;
				}
				if (Integer.parseInt(t1.getText())>level) {
					JOptionPane.showMessageDialog(frame, "内功层数超过最高层，最高层数为"+level, "提示",JOptionPane.WARNING_MESSAGE); 
					return;
				}
				NeiGong ng = gData.getNeiGongInfo(map.get(jComboBox.getSelectedItem().toString()), Integer.parseInt(t1.getText()));
				jl1.setText("<html>\n" + 
						"	<table border=\"1\">\n" + 
						"		<tr>\n" + 
						"			<th colspan=\"2\">内功基础属性</th><th colspan=\"2\">装备发挥属性上限</th>\n" + 
						"		</tr>\n" + 
						"		<tr>\n" + 
						"			<td>臂力</td><td>"+ng.getStrAdd()+"</td><td>臂力</td><td>"+ng.getStrAddE()+"</td>\n" + 
						"		</tr>\n" + 
						"		<tr>\n" + 
						"			<td>身法</td><td>"+ng.getDexAdd()+"</td><td>身法</td><td>"+ng.getDexAddE()+"</td>\n" + 
						"		</tr>\n" + 
						"		<tr>\n" + 
						"			<td>内息</td><td>"+ng.getIngAdd()+"</td><td>内息</td><td>"+ng.getIngAddE()+"</td>\n" + 
						"		</tr>\n" + 
						"		<tr>\n" + 
						"			<td>罡气</td><td>"+ng.getSpiAdd()+"</td><td>罡气</td><td>"+ng.getSpiAddE()+"</td>\n" + 
						"		</tr>\n" + 
						"		<tr>\n" + 
						"			<td>体魄</td><td>"+ng.getStaAdd()+"</td><td>体魄</td><td>"+ng.getStaAddE()+"</td>\n" + 
						"		</tr>\n" + 
						"        <tr>\n" + 
						"			<th colspan=\"4\">运行激活</th>\n" + 
						"		</tr>\n" + 
						"		<tr>\n" + 
						"			<td>气血上限</td><td colspan=\"3\">"+ng.getHP()+"</td>\n" + 
						"		</tr>\n" + 
						"		<tr>\n" + 
						"			<td>内力上限</td><td colspan=\"3\">"+ng.getMP()+"</td>\n" + 
						"		</tr>\n" + 
						"		<tr>\n" + 
						"			<td>招架上限</td><td colspan=\"3\">"+ng.getParry()+"</td>\n" + 
						"		</tr>\n" + 
						"		<tr>\n" + 
						"			<td>内功防御</td><td colspan=\"3\">"+ng.getMagicDef()+"</td>\n" + 
						"		</tr>"+
						"       <tr>\n" + 
						"			<td colspan=\"4\">内功突破所需"+ng.getFaculty()+"修为</td>\n" + 
						"		</tr>"+
						"	</table>\n" + 
						"</html>");
				System.out.println(t1.getText());
			}
		});
		jp1.add(b1);
		//********************
		
		jl1.setBounds(0, 0, 100, 200);
		JPanel jp2 = new JPanel();
		jp2.setSize(100, 300);
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp2.add(jl1);
	    //frame.add(new JButton("EAST"),BorderLayout.EAST) ;  
	    //frame.add(new JButton("WEST"),BorderLayout.WEST) ;  
	    frame.add(new JButton("SOUTH"),BorderLayout.SOUTH) ;  
	    frame.add(jp1,BorderLayout.NORTH) ;  
	    frame.add(jp2,BorderLayout.CENTER) ;  

		

		//设置窗口可见，此句一定要在窗口属性设置好了之后才能添加，不然无法正常显示
		frame.setVisible(true);
		
	}
}
