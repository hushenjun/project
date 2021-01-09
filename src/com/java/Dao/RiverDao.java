package com.java.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.River;
import com.java.util.DbUtil;
import com.java.util.Stringutil;


/**
 * 图书Dao类
 * @author 流至-胡申俊
 *
 */
public class RiverDao {
	
	private  Connection con = new DbUtil().getCon();
	/**
	 * 河流信息的添加
	 * @param con
	 * @param river
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,River river)throws Exception{
		String sql = "insert into t_river values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt =  (PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, river.getRiverName());
		pstmt.setString(2, river.getRiverLength());
		pstmt.setString(3, river.getRiverFrom());
		pstmt.setString(4, river.getRiverSeasonChange());
		pstmt.setInt(5,river.getRiverTypeId());
		pstmt.setString(6, river.getRiverDeac());
		return pstmt.executeUpdate();
		
		
	}
	/**
	 * 河流查询
	 * @param cno
	 * @return
	 */
	public ResultSet list(Connection cno,River river)throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_river b,t_watertype bt where b.riverTypeId = bt.id");
		if(Stringutil.isNotEmty(river.getRiverName())) {
			sb.append(" and b.riverName like '%" +river.getRiverName()+"%'");
			
		}
		
		if(Stringutil.isNotEmty(river.getRiverFrom())) {
			sb.append(" and b.riverFrom like '%" +river.getRiverFrom()+"%'");
		}
		
		
		if(river.getRiverTypeId() !=null && river.getRiverTypeId() != -1) {
			sb.append(" and b.riverTypeId="+river.getRiverTypeId());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		 return pstmt.executeQuery();
	}
	
	/**
	 * 河流信息删除
	 * 
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con,String id) throws Exception{
		String sql = "delete from t_river where id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		
		return 	pstmt.executeUpdate();
			
	}
	
	/**
	 * 河流信息修改
	 * @param con
	 * @param river
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con,River river)throws Exception {
		String sql = "update t_river set rivername= ?,riverLength = ?,riverFrom=?,riverSeasonChange=?,riverDesc= ?,riverTypeId = ? where id=?";
		PreparedStatement pstmt =con.prepareStatement(sql);
		pstmt.setString(1, river.getRiverName());
		pstmt.setString(2, river.getRiverLength());
		pstmt.setString(4,river.getRiverSeasonChange());
		pstmt.setString(3,river.getRiverFrom());
		pstmt.setString(5, river.getRiverDeac());
		pstmt.setLong(6, river.getRiverTypeId());
		pstmt.setInt(7, river.getId());
		return pstmt.executeUpdate();
		
	}

}
