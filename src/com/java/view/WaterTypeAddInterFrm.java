package com.java.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.java.Dao.WaterTypeDao;
import com.java.model.WaterType;
import com.java.util.DbUtil;
import com.java.util.Stringutil;

public class WaterTypeAddInterFrm extends JInternalFrame {
	private JTextField waterTypeNameTxt;
	private JTextArea waterTypeDescTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private WaterTypeDao waterTypeDao = new WaterTypeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaterTypeAddInterFrm frame = new WaterTypeAddInterFrm();
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
	public WaterTypeAddInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("水类别添加");
		setBounds(100, 100, 488, 404);
		
		JLabel lblNewLabel = new JLabel("水资源类别名称：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		
		waterTypeNameTxt = new JTextField();
		waterTypeNameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("该类别描述：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		
		waterTypeDescTxt = new JTextArea();
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.setIcon(new ImageIcon(WaterTypeAddInterFrm.class.getResource("/images/resizeA14pi.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				waterTypeAddActionPerformed(e);
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(WaterTypeAddInterFrm.class.getResource("/images/resize25Api.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addGap(29)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(waterTypeDescTxt)
							.addComponent(waterTypeNameTxt, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(68)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(waterTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(waterTypeDescTxt, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addGap(143))
		);
		getContentPane().setLayout(groupLayout);
		
		//设置文本域边框
		waterTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

	}
	
	/**
	 * 水源类型添加事件
	 * @param e
	 */
	private void waterTypeAddActionPerformed(ActionEvent evt) {
		String waterTypeName = this.waterTypeNameTxt.getText();
		String waterTypeDesc = this.waterTypeDescTxt.getText();
		if(Stringutil.isEpty(waterTypeName)) {
			JOptionPane.showInternalMessageDialog(null, "水源类型不能为空！");
			return;
			
		}
		WaterType waterType = new WaterType(waterTypeName,waterTypeDesc);
		Connection cno = null;
		try {
			cno = dbUtil.getCon();
			int n = 0;
			n = waterTypeDao.add(cno, waterType);
			if(n == 1) {
				JOptionPane.showMessageDialog(null, "水源类别添加成功");
				resetValue();
				
			}else {
				JOptionPane.showMessageDialog(null, "水源类型添加失败");
			}
		}catch(Exception e) {
			/**
			 * 错误依旧需要弹出提示失败
			 */
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "水源类型添加失败");
		}finally {
			try {
				dbUtil.closeCon(cno);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * 重置事件处理
	 * 
	 * @param evt
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		this.resetValue();
		
	}

	/**
	 * 重置
	 */
	  private void resetValue() {
		  this.waterTypeNameTxt.setText("");
		  this.waterTypeDescTxt.setText("");
	  }
}
