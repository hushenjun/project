package com.java.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java.Dao.WaterTypeDao;
import com.java.model.WaterType;
import com.java.util.DbUtil;
import com.java.util.Stringutil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WaterTypeManageInterFrm extends JInternalFrame {
	private JTable waterTypeTable;
	
	private DbUtil dbUtil = new DbUtil();
	private WaterTypeDao waterTypeDao = new WaterTypeDao();
	private JTextField s_waterTypeNameTxt;
	private JTextField idTxt;
	private JTextField waterTypeNameTxt;
	private JTextArea waterTypeDescTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaterTypeManageInterFrm frame = new WaterTypeManageInterFrm();
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
	public WaterTypeManageInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("水源类别管理");
		setBounds(100, 100, 539, 491);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 76, 433, 166);
		
		JLabel lblNewLabel = new JLabel("水源类别名称：");
		lblNewLabel.setBounds(65, 35, 88, 15);
		
		s_waterTypeNameTxt = new JTextField();
		s_waterTypeNameTxt.setBounds(163, 32, 178, 21);
		s_waterTypeNameTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.setBounds(359, 30, 97, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeSearchActionPerformed(e);
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(WaterTypeManageInterFrm.class.getResource("/images/resizeApi66.png")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(42, 252, 433, 180);
		
		waterTypeTable = new JTable();
		waterTypeTable.addMouseListener(new MouseAdapter() {
			
			//水源种类表鼠标点击
			@Override
			public void mousePressed(MouseEvent e) {
				waterTypeTableMousePressed(e);
			}
		});
		waterTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u6C34\u6E90\u7C7B\u522B\u540D\u79F0", "\u6C34\u6E90\u7C7B\u578B\u63CF\u8FF0"
			}
		));
		waterTypeTable.getColumnModel().getColumn(0).setPreferredWidth(107);
		waterTypeTable.getColumnModel().getColumn(1).setPreferredWidth(118);
		waterTypeTable.getColumnModel().getColumn(2).setPreferredWidth(179);
		scrollPane.setViewportView(waterTypeTable);
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("编号：");
		lblNewLabel_1.setBounds(22, 22, 42, 15);
		panel.add(lblNewLabel_1);
		
		idTxt = new JTextField();
		idTxt.setBounds(63, 19, 84, 21);
		panel.add(idTxt);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("水源类别名称：");
		lblNewLabel_2.setBounds(170, 22, 84, 15);
		panel.add(lblNewLabel_2);
		
		waterTypeNameTxt = new JTextField();
		waterTypeNameTxt.setBounds(264, 19, 138, 21);
		panel.add(waterTypeNameTxt);
		waterTypeNameTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("描述：");
		lblNewLabel_3.setBounds(22, 47, 36, 15);
		panel.add(lblNewLabel_3);
		
		waterTypeDescTxt = new JTextArea();
		waterTypeDescTxt.setBounds(49, 60, 353, 57);
		panel.add(waterTypeDescTxt);
		
		JButton btnNewButton_1 = new JButton("修改");
		//
		btnNewButton_1.addActionListener(new ActionListener() {  //水流类型修改事件
			public void actionPerformed(ActionEvent e) {
				waterTypeUpdateActionEvent(e);
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(WaterTypeManageInterFrm.class.getResource("/images/resizeAp5i.png")));
		btnNewButton_1.setBounds(49, 133, 98, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("删除");
		
		
		//删除水流类型事件
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				waterTypeDeleteActionEvent(e);
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(WaterTypeManageInterFrm.class.getResource("/images/删除（无圆圈）.png")));
		btnNewButton_2.setBounds(177, 133, 97, 23);
		panel.add(btnNewButton_2);
		getContentPane().add(scrollPane);
		getContentPane().add(lblNewLabel);
		getContentPane().add(s_waterTypeNameTxt);
		getContentPane().add(btnNewButton);
		
		this.fillTable(new WaterType());
		
		//设置文本框边框
		
		waterTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

	}
	
	/**
	 * 水源类型删除事件处理
	 * @param e
	 */
	private void waterTypeDeleteActionEvent(ActionEvent evt) {
		String id = idTxt.getText();
		String waterTypeName = waterTypeNameTxt.getText();
		String waterTypeDesc = waterTypeDescTxt.getText();
		if(Stringutil.isEpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除这条记录吗？");
		if(n==0) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				int  deleteNum = waterTypeDao.delete(con, id);
				if(deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new WaterType());
				}else {
					JOptionPane.showMessageDialog(null, "删除失败!");
				}
			}catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");
			}finally {
				try {
					dbUtil.closeCon(con);
				}catch(Exception e) {
					e.printStackTrace();
					
				}
			}
		}
	}

	/**
	 * 修改水源类型事件处理
	 * 
	 * @param e
	 */
	private void waterTypeUpdateActionEvent(ActionEvent e) {
		String id = idTxt.getText();
		String waterTypeName = waterTypeNameTxt.getText();
		String waterTypeDesc = waterTypeDescTxt.getText();
		if(Stringutil.isEpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
		}
		
		if(Stringutil.isEpty(waterTypeName)) {
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空");
		}
		
		WaterType waterType = new WaterType(Integer.parseInt( id),  waterTypeName,  waterTypeDesc);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int modifyNum = waterTypeDao.update(con, waterType);
			if(modifyNum == 1) {
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new WaterType());
			}else {
				JOptionPane.showMessageDialog(null, "修改失败!");
			}
		}catch(Exception ew) {
			ew.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败");
			
		}finally {
			try {
			dbUtil.closeCon(con);
		}catch(Exception ew) {
			ew.printStackTrace();
		}
		}
		
		
	}
	
	/**
	 * 重置表
	 */
	private void resetValue() {
		this.idTxt.setText("");
		this.waterTypeNameTxt.setText("");
		this.waterTypeDescTxt.setText("");
	}

	/**
	 * 表格行点击事件处理
	 * @param e
	 */
	private void waterTypeTableMousePressed(MouseEvent e) {
		int row = waterTypeTable.getSelectedRow();
		idTxt.setText((String) waterTypeTable.getValueAt(row, 0));
		waterTypeNameTxt.setText((String) waterTypeTable.getValueAt(row, 1));
		waterTypeDescTxt.setText((String) waterTypeTable.getValueAt(row, 2));
		
	}

	/**
	 * 图书类别搜索处理
	 * @param evt
	 */
	private void bookTypeSearchActionPerformed(ActionEvent evt) {
		String s_waterTypeName = this.s_waterTypeNameTxt.getText();
		WaterType waterType = new WaterType();
		
		waterType.setWaterTypeName(s_waterTypeName);
		this.fillTable(waterType);
		
	}

	/**
	 * 初始化表格
	 * @param waterType
	 */
	private void fillTable(WaterType waterType) {
		DefaultTableModel dtm = (DefaultTableModel) waterTypeTable.getModel();
		dtm.setRowCount(0);  //设置为0行
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = waterTypeDao.list(con,waterType);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("waterTypeName"));
				v.add(rs.getString("waterTypeDear"));
				dtm.addRow(v);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
