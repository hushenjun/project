package com.java.Dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.java.model.User;
import com.mysql.jdbc.PreparedStatement;


public class UserDao {
	
	/**
	 * 登录验证
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con,User user) throws Exception {
		User resultUser = null;
		String sql ="select * from t_user where userName= ? and password = ?";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1,user.getUseName());
		pstmt.setString(2,user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUseName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
		
	}
	/**
	 * 添加用户
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int addUser(Connection con,User user) throws Exception{
		String sql = "insert into t_user values(null,?,?)";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, user.getUseName());
		pstmt.setString(2, user.getPassword());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 修改密码
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int updatePass(Connection con,User user)throws Exception {
		String sql = "update t_user set userName = ? , password= ? where id =?";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1,user.getUseName());
		pstmt.setString(2,user.getPassword());
		pstmt.setInt(3, user.getId());
		return pstmt.executeUpdate();
	}

}
