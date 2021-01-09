package com.java.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.java.Dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.Stringutil;
import com.mysql.jdbc.Connection;


public class LoginOnFrame extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	/**
	 * 修改密码时
	 */
	private static int login_id;
	private static String login_username;
	private static String login_password;
	
	private JPanel contentPane;
	private JPasswordField passWordTxt;
	private JTextField userNameTxt;
	
	
	private DbUtil dbUtil  = new DbUtil();
	private UserDao userDao = new UserDao();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginOnFrame frame = new LoginOnFrame();
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
	public LoginOnFrame() {
		setResizable(false);
		setTitle("水资源管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("水资源管理系统");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setIcon(new ImageIcon(LoginOnFrame.class.getResource("/images/resizeApi2.png")));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 29));
		
		JLabel lblNewLabel_1 = new JLabel("用户名");
		lblNewLabel_1.setIcon(new ImageIcon(LoginOnFrame.class.getResource("/images/resizeApi.png")));
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel lblNewLabel_2 = new JLabel("密 码");
		lblNewLabel_2.setIcon(new ImageIcon(LoginOnFrame.class.getResource("/images/resizeAp1i.png")));
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 16));
		
		passWordTxt = new JPasswordField();
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setIcon(new ImageIcon(LoginOnFrame.class.getResource("/images/resizeApi25.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(LoginOnFrame.class.getResource("/images/resizeApi62.png")));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(65)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(65)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(70)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(passWordTxt, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(101)
					.addComponent(btnNewButton)
					.addGap(72)
					.addComponent(btnNewButton_1))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(passWordTxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(btnNewButton_1))))
		);
		contentPane.setLayout(gl_contentPane);
		
		//设置居中显示
		this.setLocationRelativeTo(null);
		
	}
	
	
	/**
	 * 登陆事件
	 * @param e
	 */
	private void LoginActionPerformed(ActionEvent ev) {
		String userName = this.userNameTxt.getText();
		String password = new String(this.passWordTxt.getPassword());
		if(Stringutil.isEpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if(Stringutil.isEpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		User user = new User(userName,password);
		Connection con = null;
		try {
			con = (Connection) dbUtil.getCon();
			User currentUser = userDao.login(con, user);
			if(currentUser!=null) {
				//JOptionPane.showMessageDialog(null, "登陆成功！");
				dispose();
				new MainFrame().setVisible(true);
				login_id = currentUser.getId();
				login_username = currentUser.getUseName();
				login_password = currentUser.getPassword();
			}else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误！请重新输入");
				this.resetValueActionPerformed(null);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	 * 重置事件处理
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent ev) {
		this.userNameTxt.setText("");
		this.passWordTxt.setText("");
		
	}
	


	
	public static int getLogin_id() {
		return login_id;
	}

	public static void setLogin_id(int login_id) {
		LoginOnFrame.login_id = login_id;
	}

	public static String getLogin_username() {
		return login_username;
	}

	public static void setLogin_username(String login_username) {
		LoginOnFrame.login_username = login_username;
	}

	public static String getLogin_password() {
		return login_password;
	}

	public static void setLogin_password(String login_password) {
		LoginOnFrame.login_password = login_password;
	}
	
	/**
	 * 
	 */
	

}
