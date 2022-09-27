package com.boxtype.model;

public class BoxTypeVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer boxTypeID;
	private String boxName;
	
	public Integer getBoxTypeID() {
		return boxTypeID;
	}
	public void setBoxTypeID(Integer boxTypeID) {
		this.boxTypeID = boxTypeID;
	}
	public String getBoxName() {
		return boxName;
	}
	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	



}
