package com.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbUtil {
	protected static String dbClassName = 
			"com.mysql.jdbc.Driver";//数据库连接驱动类
		protected  String dbUrl = "jdbc:mysql://localhost:3306/manage";//数据库连接URL
		protected  String dbUser = "root";				//数据库用户名
		protected  String dbPassword = "12345678";			//数据库密码
		private  String jdbcName  = "com.mysql.jdbc.Driver";//,驱动名称 数据库连接对象
		
		/**
		 * 获取连接
		 * @return
		 * @throws Exception
		 */
		public  Connection getCon(){
			     Connection con= null;//默认构造函数
				try {
					Class.forName(jdbcName)	;
					con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//获得连接对象						
				
				return con;
		}
		//关闭数据库连接
		
		/**
		 * 关闭数据库连接
		 * @param con
		 * @throws Exception
		 */
		public void closeCon(Connection con) throws Exception{
			if(con !=null) {
				con.close();
			}
		}
		
		  public static void main (String []args) { 
			  		DbUtil dbUtil = new DbUtil();
				try {
					dbUtil.getCon();
					System.out.println("数据库连接成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("数据库连接失败");
				}
				
				
				/*
				 * Connection c = getDBCon(); System.out.print(c); new ActionHandle();
				 */
		      
		  }
		  
			/*public static void close() {//关闭方法
				try {
					con.close();//关闭连接对象		
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					con = null;	//设置连接对象为null值
				}
			}*/
			
}
