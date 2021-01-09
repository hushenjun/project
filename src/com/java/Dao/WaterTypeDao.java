package com.java.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 水源类别Dao类
 */
import com.java.model.WaterType;
import com.java.util.Stringutil;



public class WaterTypeDao {
	/**
	 * 水资源类别添加
	 * @param con
	 * @param waterType
	 * @return
	 * @throws Exception
	 */
	
	public int add(Connection con,WaterType waterType) throws Exception{
	  String sql ="insert into t_waterType values(null,?,?)";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, waterType.getWaterTypeName());
	  pstmt.setString(2, waterType.getWaterTypeDesc());
	  return pstmt.executeUpdate();
	}
	/**
	 * 
	 * 查询水源类别集合
	 */
	public  ResultSet list(Connection con,WaterType waterType) throws Exception{
		StringBuffer sb = new StringBuffer("select * from t_watertype");
		if(waterType.getWaterTypeName() != null) {
		sb.append(" and waterTypeName like '%" +waterType.getWaterTypeName()+"%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
		
		
	}
	
	/**
	 * 删除水源类别
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con,String id) throws Exception{
	 String sql="delete from t_watertype where id=?";
	 PreparedStatement pstmt = con.prepareStatement(sql);
	 pstmt.setString(1,id);
	 return pstmt.executeUpdate();
	}
	
	
	
	/**
	 * 修改水源类别
	 */
	public int update(Connection con,WaterType waterType)throws Exception {
		String sql = "update t_watertype set waterTypeName=?,waterTypeDear=? where id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, waterType.getWaterTypeName());
		pstmt.setString(2, waterType.getWaterTypeDesc());
		pstmt.setInt(3,waterType.getId());
		return pstmt.executeUpdate(); 
	}

}
