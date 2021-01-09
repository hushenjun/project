package com.java.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.java.Dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.Stringutil;
import com.mysql.jdbc.Connection;

public class RegisterInterFrm extends JInternalFrame {
	
	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	
	private JTextField textName;
	private JPasswordField text_pass;
	private JPasswordField text_pass_new;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					RegisterInterFrm frame = new RegisterInterFrm();
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
	public RegisterInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("用户注册");
		setBounds(100, 100, 476, 515);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户注册");
		lblNewLabel.setIcon(new ImageIcon(RegisterInterFrm.class.getResource("/images/resizeApi110.png")));
		lblNewLabel.setFont(new Font("华文细黑", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(101, 45, 290, 76);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("用户名：");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_1.setBounds(81, 157, 73, 28);
		getContentPane().add(lblNewLabel_1);
		
		textName = new JTextField();
		textName.setBounds(164, 157, 194, 28);
		getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("密码：");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_2.setBounds(81, 208, 62, 28);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("确认密码：");
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_3.setBounds(70, 271, 84, 28);
		getContentPane().add(lblNewLabel_3);
		
		text_pass = new JPasswordField();
		text_pass.setBounds(164, 211, 194, 26);
		getContentPane().add(text_pass);
		
		text_pass_new = new JPasswordField();
		text_pass_new.setBounds(164, 273, 194, 28);
		getContentPane().add(text_pass_new);
		
		JButton btnNewButton = new JButton("注册");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerActionPerformed(e);
			}
		});
		btnNewButton.setBounds(67, 332, 104, 34);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
				
			}
		});
		btnNewButton_1.setBounds(223, 332, 104, 34);
		getContentPane().add(btnNewButton_1);

	}
	

	/**
	 * 置空栏目
	 * @param e
	 */

	private void resetActionPerformed(ActionEvent e) {
		this.textName.setText("");
		this.text_pass.setText("");
		this.text_pass_new.setText("");
		
	}
	
	/**
	 * 注册写入
	 * @param e
	 */
	private void registerActionPerformed(ActionEvent e) {
		String userName = this.textName.getText();
		
		String password = new String(this.text_pass.getPassword());
		String q_password = new String(this.text_pass_new.getPassword());
		//判断是否为空
		if(Stringutil.isEpty(userName)) {
		JOptionPane.showMessageDialog(null, "用户名不能为空");
		return;
		}
		
		if(Stringutil.isEpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
			}
		if(Stringutil.isEpty(q_password)) {
			JOptionPane.showMessageDialog(null, "确认密码不能为空");
			return;
			}
		
		if(password.equals(q_password)) {
			Connection con = null;
			User user= new User(userName,password);
			try {
			con = (Connection) dbUtil.getCon();
			int addNum = userDao.addUser(con, user);
			if(addNum == 1) {
				JOptionPane.showMessageDialog(null, "注册成功");
				//dispose();
				
			}
			}catch(Exception ee){
				ee.printStackTrace();
				JOptionPane.showMessageDialog(null, "注册失败！");
				return;
			}finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "注册失败，前后密码输入不一致，请重新输入！");
			
			return;
		}
		
		
		
	}
}
