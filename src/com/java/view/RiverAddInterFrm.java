package com.java.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.java.Dao.RiverDao;
import com.java.Dao.WaterTypeDao;
import com.java.model.River;
import com.java.model.WaterType;
import com.java.util.DbUtil;
import com.java.util.Stringutil;

public class RiverAddInterFrm extends JInternalFrame {
	private JTextField riverNameTxt;
	private JTextField riverFromTxt;
	private JTextField riverSeasonChangeTxt;
	private JTextField riverLengthTxt;
	
	/**
	 * 河流描述文本框封装
	 */
	private JTextArea riverDescTxt;
	
	/**
	 * 下拉框封装
	 * 
	 */
	private JComboBox riverTypeJcb;
	
	
	private DbUtil dbUtil = new DbUtil();
	private WaterTypeDao waterTypeDao = new WaterTypeDao();
	private RiverDao riverDao = new RiverDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RiverAddInterFrm frame = new RiverAddInterFrm();
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
	public RiverAddInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("河流添加");
		setBounds(100, 100, 482, 480);
		
		JLabel lblNewLabel = new JLabel("河流名称：");
		
		JLabel lblNewLabel_1 = new JLabel("河流里程：");
		
		JLabel lblNewLabel_2 = new JLabel("河流来源：");
		
		JLabel lblNewLabel_3 = new JLabel("水量季节变化：");
		
		JLabel lblNewLabel_4 = new JLabel("河流描述：");
		
		//河流文本框描述
		riverDescTxt = new JTextArea();
		
		riverNameTxt = new JTextField();
		riverNameTxt.setColumns(10);
		
		riverFromTxt = new JTextField();
		riverFromTxt.setColumns(10);
		
		riverSeasonChangeTxt = new JTextField();
		riverSeasonChangeTxt.setColumns(10);
		
		riverLengthTxt = new JTextField();
		riverLengthTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("河流类别：");
		
		riverTypeJcb = new JComboBox();
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				riverAddActionPerformed(e);
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(RiverAddInterFrm.class.getResource("/images/resizeA14pi.png")));
		
		JButton btnNewButton_1 = new JButton("重置");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(RiverAddInterFrm.class.getResource("/images/resize25Api.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(69)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
							.addComponent(riverDescTxt, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
							.addGap(24))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblNewLabel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(riverTypeJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(riverFromTxt)
										.addComponent(riverNameTxt, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_3)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(riverSeasonChangeTxt, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(riverLengthTxt, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
									.addGap(25)))
							.addGap(15))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(riverNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(riverLengthTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(riverFromTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(riverSeasonChangeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(riverTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(riverDescTxt, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		
		//设置文本框边框
		
		riverDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		
		fillRiverType();

	}
	
	
	/**
	 * 重置事件
	 * @param e
	 */

	private void resetValueActionPerformed(ActionEvent e) {
		this.resetValue();
	}
	
	/**
	 * 重置表单
	 */
	private void resetValue() {
		this.riverNameTxt.setText("");
		this.riverLengthTxt.setText("");
		this.riverFromTxt.setText("");
		this.riverSeasonChangeTxt.setText("");
		this.riverDescTxt.setText("");
		//河流类别不存在时
		if(this.riverTypeJcb.getItemCount()>0);{
			this.riverTypeJcb.setSelectedIndex(0);
		}
	}

	/**
	 * 河流添加事件处理
	 * 
	 */
	private void riverAddActionPerformed(ActionEvent evt) {
		String riverName = this.riverNameTxt.getText();
		String riverLength = this.riverLengthTxt.getText();
		String riverFrom = this.riverFromTxt.getText();
		String riverSeasonChange = this.riverSeasonChangeTxt.getText();
		String riverDesc = this.riverDescTxt.getText();
		
		/**
		 * 判断信息条是否为空
		 */
		if(Stringutil.isEpty(riverName)) {
			JOptionPane.showInternalMessageDialog(null, "河流名称不能为空！");
			return;
		}
		
		if(Stringutil.isEpty(riverLength)) {
			JOptionPane.showInternalMessageDialog(null, "河流里程不能为空！");
			return;
		}
		
		
		if(Stringutil.isEpty(riverFrom)) {
			JOptionPane.showInternalMessageDialog(null, "河流补给来源不能为空！");
			return;
		}
		
		if(Stringutil.isEpty(riverSeasonChange)) {
			JOptionPane.showInternalMessageDialog(null, "河流水流季节变化不能为空！");
			return;
		}
		
		
		WaterType waterType = (WaterType) riverTypeJcb.getSelectedItem();
		int riverTypeId = waterType.getId();
			
		River river = new River( riverName,riverLength,riverFrom, riverSeasonChange,riverTypeId, riverDesc);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int addNum = riverDao.add(con,river);
			if(addNum == 1) {
				JOptionPane.showInternalMessageDialog(null, "河流添加成功！");
				resetValue();
			}else {
				JOptionPane.showInternalMessageDialog(null, "河流信息添加失败");
				return;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog(null, "河流信息添加失败！");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				 e.printStackTrace();
			}
		}
	
	}
	

	/**
	 * 初始化水源类型下拉框
	 * 
	 */
	private void fillRiverType() {
		Connection con = null;
		 WaterType waterType = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = waterTypeDao.list(con, new WaterType());
			while(rs.next()) {
				waterType= new WaterType();
				waterType.setId(rs.getInt("id"));
				waterType.setWaterTypeName(rs.getString("waterTypeName"));
				this.riverTypeJcb.addItem(waterType);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
}
