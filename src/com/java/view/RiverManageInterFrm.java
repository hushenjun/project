package com.java.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.java.Dao.RiverDao;
import com.java.Dao.WaterTypeDao;
import com.java.model.River;
import com.java.model.WaterType;
import com.java.util.DbUtil;
import com.java.util.Stringutil;

import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RiverManageInterFrm extends JInternalFrame {
	private JTable riverTable;
	private JTextField s_riverNameTxt;
	private JTextField s_riverFromTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private WaterTypeDao waterTypeDao = new WaterTypeDao();
	private RiverDao riverDao = new RiverDao();
	
	private JComboBox s_riverTypeJcb;
	private JTextField idTxt;
	private JTextField riverNameTxt;
	private JTextField riverFromTxt;
	private JTextField riverSeasonChangeTxt;
	private JTextField riverLengthTxt;
	
	private JComboBox riverTypeJcb;
	
	private JTextArea riverDescTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RiverManageInterFrm frame = new RiverManageInterFrm();
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
	public RiverManageInterFrm() {
		setTitle("河流管理");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 666, 599);
		
		JScrollPane scrollPane =                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      new JScrollPane();
		scrollPane.setBounds(34, 145, 583, 165);
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		
		riverTable = new JTable();
		riverTable.addMouseListener(new MouseAdapter() {
			
			//鼠标点击事件
			@Override
			public void mousePressed(MouseEvent met) {
				riverTableMoousePressed(met);
				
			}
		});
		riverTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u6CB3\u6D41\u540D\u79F0", "\u603B\u91CC\u7A0B", "\u8865\u5145\u6E90", "\u5B63\u8282\u53D8\u5316", "\u6CB3\u6D41\u63CF\u8FF0", "\u6CB3\u6D41\u7C7B\u522B"
			}
		));
		riverTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		riverTable.getColumnModel().getColumn(2).setPreferredWidth(55);
		riverTable.getColumnModel().getColumn(3).setPreferredWidth(61);
		riverTable.getColumnModel().getColumn(5).setPreferredWidth(160);
		scrollPane.setViewportView(riverTable);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(34, 33, 583, 97);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("河流名称：");
		lblNewLabel.setBounds(21, 30, 67, 15);
		panel.add(lblNewLabel);
		
		s_riverNameTxt = new JTextField();
		s_riverNameTxt.setBounds(82, 27, 148, 21);
		panel.add(s_riverNameTxt);
		s_riverNameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("河流类别：");
		lblNewLabel_1.setBounds(21, 65, 60, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("河流补充类型：");
		lblNewLabel_2.setBounds(265, 30, 84, 15);
		panel.add(lblNewLabel_2);
		
		s_riverFromTxt = new JTextField();
		s_riverFromTxt.setBounds(359, 27, 142, 21);
		panel.add(s_riverFromTxt);
		s_riverFromTxt.setColumns(10);
		
		s_riverTypeJcb = new JComboBox();
		s_riverTypeJcb.setBounds(82, 61, 148, 23);
		panel.add(s_riverTypeJcb);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				riverSearchActionPerformed(e);
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(RiverManageInterFrm.class.getResource("/images/resizeApi66.png")));
		btnNewButton.setBounds(263, 61, 97, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(34, 320, 583, 220);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("编号：");
		lblNewLabel_3.setBounds(29, 33, 42, 15);
		panel_1.add(lblNewLabel_3);
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setBounds(89, 30, 88, 21);
		panel_1.add(idTxt);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("河流名称：");
		lblNewLabel_4.setBounds(211, 33, 61, 15);
		panel_1.add(lblNewLabel_4);
		
		riverNameTxt = new JTextField();
		riverNameTxt.setBounds(269, 30, 96, 21);
		panel_1.add(riverNameTxt);
		riverNameTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("补充来源：");
		lblNewLabel_5.setBounds(29, 75, 61, 15);
		panel_1.add(lblNewLabel_5);
		
		riverFromTxt = new JTextField();
		riverFromTxt.setBounds(89, 72, 105, 21);
		panel_1.add(riverFromTxt);
		riverFromTxt.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("季节变换：");
		lblNewLabel_6.setBounds(211, 75, 61, 15);
		panel_1.add(lblNewLabel_6);
		
		riverSeasonChangeTxt = new JTextField();
		riverSeasonChangeTxt.setBounds(269, 72, 96, 21);
		panel_1.add(riverSeasonChangeTxt);
		riverSeasonChangeTxt.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("河流类别：");
		lblNewLabel_7.setBounds(386, 75, 61, 15);
		panel_1.add(lblNewLabel_7);
		
		riverTypeJcb = new JComboBox();
		riverTypeJcb.setBounds(447, 70, 105, 23);
		panel_1.add(riverTypeJcb);
		
		JLabel lblNewLabel_8 = new JLabel("河流描述：");
		lblNewLabel_8.setBounds(29, 109, 61, 15);
		panel_1.add(lblNewLabel_8);
		
		riverDescTxt = new JTextArea();
		riverDescTxt.setBounds(89, 109, 463, 53);
		panel_1.add(riverDescTxt);
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				riverUpdateActioPerformed(evt);
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(RiverManageInterFrm.class.getResource("/images/画笔.png")));
		btnNewButton_1.setBounds(89, 187, 97, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				riverDeleteActionPerformed(evt);
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(RiverManageInterFrm.class.getResource("/images/删除（无圆圈）.png")));
		btnNewButton_2.setBounds(269, 187, 97, 23);
		panel_1.add(btnNewButton_2);
		
		//设置文本框边框
		
		riverDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		
		JLabel lblNewLabel_9 = new JLabel("河流里程：");
		lblNewLabel_9.setBounds(389, 33, 61, 15);
		panel_1.add(lblNewLabel_9);
		
		riverLengthTxt = new JTextField();
		riverLengthTxt.setBounds(457, 30, 105, 21);
		panel_1.add(riverLengthTxt);
		riverLengthTxt.setColumns(10);
		
		//调用搜索事件
		this.fillRiverType("search");
		this.fillRiverType("modify");
		this.fillTable(new River());

	}
	
	/**
	 * 河流删除事件处理
	 * @param evt
	 */


	private void riverDeleteActionPerformed(ActionEvent evt) {
		String id = idTxt.getText();
		String waterTypeName = riverNameTxt.getText();
		String waterTypeDesc = riverDescTxt.getText();
		if(Stringutil.isEpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除这条记录吗？");
		if(n==0) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				int  deleteNum = riverDao.delete(con, id);
				if(deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new River());
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
	 * 河流修改事件处理
	 * @param evt
	 */
	private void riverUpdateActioPerformed(ActionEvent evt) {
		String id = this.idTxt.getText();
		if(Stringutil.isEpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			
		}
		String riverName = this.riverNameTxt.getText();
		String riverLength = this.riverLengthTxt.getText();
		String riverFrom = this.riverFromTxt.getText();
		String riverSeasonChange = this.riverSeasonChangeTxt.getText();
		String riverDesc = this.riverDescTxt.getText();
		
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
		
		
		
		/**
		 * 和获取下拉框
		 */
		WaterType waterType = (WaterType) riverTypeJcb.getSelectedItem();
		int riverTypeId = waterType.getId();
		River river  = new River(Integer.parseInt(id),  riverName,  riverLength,  riverFrom,  riverTypeId,
				 riverSeasonChange,  riverDesc);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int addNum = riverDao.update(con,river);
			if(addNum == 1) {
				JOptionPane.showInternalMessageDialog(null, "河流修改成功！");
				resetValue();
				this.fillTable(new River());
			}else {
				JOptionPane.showInternalMessageDialog(null, "河流信息修改失败");
				return;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog(null, "河流信息修改失败！");
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
	 * 重置表单
	 */
	private void resetValue() {
		this.idTxt.setText("");
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
	 * 表格点击事件处理
	 * @param met
	 */
	private void riverTableMoousePressed(MouseEvent met) {
		int row = this.riverTable.getSelectedRow();
		this.idTxt.setText((String) riverTable.getValueAt(row, 0));
		this.riverNameTxt.setText((String) riverTable.getValueAt(row, 1));
		this.riverLengthTxt.setText((String) riverTable.getValueAt(row, 2));
		this.riverFromTxt.setText((String) riverTable.getValueAt(row, 3));
		this.riverSeasonChangeTxt.setText((String) riverTable.getValueAt(row, 4));
		this.riverDescTxt.setText((String) riverTable.getValueAt(row, 5));
		          //this.riverTable.getValueAt(row, 6);     获得类型名称
		String riverTypeName = (String) this.riverTable.getValueAt(row, 6);  //获得类型名
		int n = this.riverTypeJcb.getItemCount();   //得到所有类型总和
		for( int i = 0;i<n;i++) {
			WaterType item = (WaterType)this.riverTypeJcb.getItemAt(i);
			if(item.getWaterTypeName().equals(riverTypeName)) {
				this.riverTypeJcb.setSelectedIndex(i);
			}
		}
	}

	/**
	 * 河流查询事件处理
	 * @param e
	 */
	private void riverSearchActionPerformed(ActionEvent e) {
		String riverName = this.s_riverNameTxt.getText();
		String riverFrom = this.s_riverFromTxt.getText();
		WaterType waterType = (WaterType) this.s_riverTypeJcb.getSelectedItem();
		int waterTypeId = waterType.getId();
		
		River river = new River(riverName,riverFrom,waterTypeId);
		this.fillTable(river);
	}

	/**
	 * 初始化下拉框
	 * @param type 下拉框类型
	 */
	private void fillRiverType(String type) {
		WaterType waterType = null;
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = waterTypeDao.list(con, new WaterType());
			if("search".equals(type)) {
				waterType = new WaterType();
				waterType.setWaterTypeName("请选择....");
				waterType.setId(-1);
				this.s_riverTypeJcb.addItem(waterType);
			}
			while(rs.next()){
				waterType = new WaterType();
				waterType.setWaterTypeName(rs.getString("waterTypeName"));
				waterType.setId(rs.getInt("id"));
				if("search".equals(type)) {
					this.s_riverTypeJcb.addItem(waterType);
					
				}else if("modify".equals(type)){
					this.riverTypeJcb.addItem(waterType);
					
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 初始化表格数据
	 * @param river
	 */
	private void fillTable(River river) {
		DefaultTableModel dtm = (DefaultTableModel) riverTable.getModel();
		dtm.setRowCount(0);  //设置为0行
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = riverDao.list(con,river);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("riverName"));
				v.add(rs.getString("riverLength"));
				v.add(rs.getString("riverFrom"));
				v.add(rs.getString("riverSeasonChange"));
				v.add(rs.getString("riverDesc"));
				v.add(rs.getString("waterTypeName"));
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
