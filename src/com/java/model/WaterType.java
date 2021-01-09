package com.java.model;

/**
 * heliu实体
 * @author 流至-胡申俊
 *
 */
public class WaterType {
	private int id;
	private String waterTypeName;  //水类别名称
	
	private String waterTypeDesc; //备注
	
	public WaterType() {
		super();
	}
	
	
	public WaterType(String waterTypeName, String waterTypeDesc) {
		super();
		this.waterTypeName = waterTypeName;
		this.waterTypeDesc = waterTypeDesc;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWaterTypeName() {
		return waterTypeName;
	}
	public void setWaterTypeName(String waterTypeName) {
		this.waterTypeName = waterTypeName;
	}
	public String getWaterTypeDesc() {
		return waterTypeDesc;
	}
	public void setWaterTypeDesc(String waterTypeDesc) {
		this.waterTypeDesc = waterTypeDesc;
	}
	


	public WaterType(int id, String waterTypeName, String waterTypeDesc) {
		super();
		this.id = id;
		this.waterTypeName = waterTypeName;
		this.waterTypeDesc = waterTypeDesc;
	}


	@Override
	public String toString() {
		return waterTypeName;
	}
	
	
	

}
