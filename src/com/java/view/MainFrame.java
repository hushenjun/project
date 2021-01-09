package com.java.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	 private JDesktopPane table = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("管理主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 417);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("具体数据维护");
		mnNewMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/resizeAp22i.png")));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("水资源类别");
		mnNewMenu_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/resi1zeApi.png")));
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem MenuItem = new JMenuItem("水源类别添加");
		MenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				WaterTypeAddInterFrm javaIntenerFrame = new WaterTypeAddInterFrm();
				javaIntenerFrame.setVisible(true);
				table.add(javaIntenerFrame);
				
			}
		});
		MenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/resizeApi2.5.png")));
		mnNewMenu_1.add(MenuItem);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("水源类别维护");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WaterTypeManageInterFrm waterTypeManageInterFrm = new WaterTypeManageInterFrm();
				waterTypeManageInterFrm.setVisible(true);
				table.add(waterTypeManageInterFrm);				
				
			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/resizeAp5i.png")));
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("河流管理");
		mnNewMenu_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/河流管理i.png")));
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("水源信息添加");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiverAddInterFrm riverTypeAddInterFrm = new RiverAddInterFrm();   //RiverAddInterFrm是河流添加
				riverTypeAddInterFrm.setVisible(true);
				table.add(riverTypeAddInterFrm);
				
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/增加河流.png")));
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("水源信息维护");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				RiverManageInterFrm riverManageInterFrm = new RiverManageInterFrm();
				riverManageInterFrm.setVisible(true);
				table.add(riverManageInterFrm);			
				
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/河流维护.png")));
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("安全退出");
		
		//安全退出动作 
		
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否退出系统！");
				//System.out.println(result);
				if(result == 0) {
				dispose();
				}
				
			}
		});
		mntmNewMenuItem_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/resize44Api.png")));
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_4 = new JMenu("管理员添加");
		mnNewMenu_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/resizeApi6.png")));
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("用户注册");
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/resizeApi61.png")));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterInterFrm JavaInterFrm = new RegisterInterFrm();
				JavaInterFrm.setVisible(true);
				table.add(JavaInterFrm);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("修改密码");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PassUpdateInterFrm JavaInterFrm = new PassUpdateInterFrm();
				JavaInterFrm.setVisible(true);
				table.add(JavaInterFrm);
				
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_3 = new JMenu("备   注");
		mnNewMenu_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/resizeApi88.png")));
		mnNewMenu_3.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("关于图标");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JavaIntenerFrame javaIntenerFrame = new JavaIntenerFrame();
				javaIntenerFrame.setVisible(true);
				table.add(javaIntenerFrame);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		//里窗口
		table = new JDesktopPane();
		table.setBackground(new Color(100, 149, 237));
		contentPane.add(table, BorderLayout.CENTER);
		
		//设置Jframe最大化
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
