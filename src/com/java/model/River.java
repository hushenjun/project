package com.java.model;

/**
 * 图书实体
 * 
 * @author 流至-胡申俊
 *
 */
public class River {
	private int id; //编号
	private String riverName;   //河流名
	private String riverLength; //长度
	private String riverFrom;   //河流来源
	private Integer riverTypeId;  //河流类别Id
	private String riverSeasonChange;   //季节变化
	private String riverTypeName;     //河流类别名称
	private String riverDesc;     //备注
	public int getId() {
		return id;
	}
	
	
	
	public River() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public River(String riverName, String riverLength, String riverFrom,  String riverSeasonChange,Integer riverTypeId,String riverDesc) {
		super();
		this.riverName = riverName;
		this.riverLength = riverLength;
		this.riverFrom = riverFrom;
		this.riverTypeId = riverTypeId;
		this.riverSeasonChange = riverSeasonChange;
		this.riverDesc = riverDesc;
	}
	



	public River(String riverName, String riverFrom, Integer riverTypeId) {
		super();
		this.riverName = riverName;
		this.riverFrom = riverFrom;
		this.riverTypeId = riverTypeId;
		
	}
	



	public River(int id, String riverName, String riverLength, String riverFrom, Integer riverTypeId,
			String riverSeasonChange, String riverDesc) {
		super();
		this.id = id;
		this.riverName = riverName;
		this.riverLength = riverLength;
		this.riverFrom = riverFrom;
		this.riverTypeId = riverTypeId;
		this.riverSeasonChange = riverSeasonChange;
		this.riverDesc = riverDesc;
	}



	public void setId(int id) {
		this.id = id;
	}
	public String getRiverName() {
		return riverName;
	}
	public void setRiverName(String riverName) {
		this.riverName = riverName;
	}
	public String getRiverLength() {
		return riverLength;
	}
	public void setRiverLength(String riverLength) {
		this.riverLength = riverLength;
	}
	public String getRiverFrom() {
		return riverFrom;
	}
	public void setRiverFrom(String riverFrom) {
		this.riverFrom = riverFrom;
	}
	public Integer getRiverTypeId() {
		return riverTypeId;
	}
	public void setRiverTypeId(Integer riverTypeId) {
		this.riverTypeId = riverTypeId;
	}
	public String getRiverTypeName() {
		return getRiverTypeName();
	}
	public void setRiverTypeName(String riverTypeName) {
		this.setRiverSeasonChange(riverTypeName);
	}
	public String getRiverDeac() {
		return riverDesc;
	}
	public void setRiverDesc(String riverDeac) {
		this.riverDesc = riverDeac;
	}
	public String getRiverSeasonChange() {
		return riverSeasonChange;
	}
	public void setRiverSeasonChange(String riverSeasonChange) {
		this.riverSeasonChange = riverSeasonChange;
	}

	public String getWaterTypeName() {
		return riverTypeName;
	}



	public void setWaterTypeName(String waterTypeName) {
		this.riverTypeName = waterTypeName;
	}
	

}
