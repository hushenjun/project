package com.java.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.java.Dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.Stringutil;


public class PassUpdateInterFrm extends JInternalFrame {
	private JTextField text_name;
	private JTextField old_password;
	private JTextField new_password;
	private JTextField q_password;
	
	private DbUtil dbUtil  = new DbUtil();
	private UserDao userDao = new UserDao();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassUpdateInterFrm frame = new PassUpdateInterFrm();
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
	public PassUpdateInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("修改用户密码");
		setBounds(100, 100, 487, 457);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("修改密码");
		lblNewLabel.setIcon(new ImageIcon(PassUpdateInterFrm.class.getResource("/images/设置64.png")));
		lblNewLabel.setFont(new Font("华文细黑", Font.BOLD, 32));
		lblNewLabel.setBounds(106, 42, 259, 74);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("用户名：");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel_1.setBounds(67, 152, 74, 27);
		getContentPane().add(lblNewLabel_1);
		
		text_name = new JTextField();
		text_name.setEditable(false);
		text_name.setBounds(152, 152, 198, 21);
		getContentPane().add(text_name);
		text_name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("旧密码：");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel_2.setBounds(67, 189, 74, 27);
		getContentPane().add(lblNewLabel_2);
		
		old_password = new JTextField();
		old_password.setBounds(152, 193, 199, 21);
		getContentPane().add(old_password);
		old_password.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("新密码：");
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel_3.setBounds(67, 226, 74, 27);
		getContentPane().add(lblNewLabel_3);
		
		new_password = new JTextField();
		new_password.setBounds(152, 229, 198, 23);
		getContentPane().add(new_password);
		new_password.setColumns(10);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					updatePasswordActionPerformed(e);
			}
		});
		btnNewButton.setBounds(106, 342, 92, 39);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("确认密码：");
		lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_4.setBounds(67, 274, 84, 27);
		getContentPane().add(lblNewLabel_4);
		
		q_password = new JTextField();
		q_password.setBounds(152, 278, 198, 21);
		getContentPane().add(q_password);
		q_password.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetPasswordActionPerformed(e);
			}

		
		});
		
		btnNewButton_1.setBounds(252, 343, 97, 39);
		getContentPane().add(btnNewButton_1);
		
		this.text_name.setText(LoginOnFrame.getLogin_username());
	}
		
		//this.old_password.setText(LoginOnFrame.getLogin_password());;

	
	protected void resetPasswordActionPerformed(ActionEvent e) {
		this.resetValue();
		// TODO Auto-generated method stub
		
	}
	
	private void resetValue() {
		this.old_password.setText("");
		this.new_password.setText("");
		this.q_password.setText("");
	}

	/**
	 * 修改密码
	 * @param e
	 */

	private void updatePasswordActionPerformed(ActionEvent e) {
		String old_password = new String(this.old_password.getText());
		String new_password = new String(this.new_password.getText());
		String q_password = new String(this.q_password.getText());
		
		if(Stringutil.isEpty(new_password)) {
			JOptionPane.showMessageDialog(null, "新密码不能为空");
			return;
		}
		
		if(Stringutil.isEpty(old_password)) {
			JOptionPane.showMessageDialog(null, "旧密码不能为空");
			return;
		}
		if(Stringutil.isEpty(q_password)) {
			JOptionPane.showMessageDialog(null, "确认密码不能为空");
			return;
		}
		if(!(q_password.equals(new_password))) {
			JOptionPane.showMessageDialog(null, "俩次密码输入不一致，请重新输入");
			return;
		}
		if(!(old_password.equals(LoginOnFrame.getLogin_password()))) {
			JOptionPane.showMessageDialog(null, "原密码输入错误");
			return;
		}
		Connection con = null;
		try {
			con = (Connection) dbUtil.getCon();
			User user = new User(LoginOnFrame.getLogin_id(),LoginOnFrame.getLogin_username(),new_password);
			int updateNum = userDao.updatePass(con, user);
			if(updateNum ==1) {
				JOptionPane.showMessageDialog(null, "密码修改成功，下次生效");
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "修改失败");
				return;
			}
		}catch(Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败!");
		}
		
	}
}
