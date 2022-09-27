package com.product_favorites.model;
import java.sql.Date;

public class ProductFavoritesVO implements java.io.Serializable{
	private Integer PdID;
	private Integer MemID;
	private Date PdFavDate;
	
	public Integer getPdID() {
		return PdID;
	}
	public void setPdID(Integer pdID) {
		PdID = pdID;
	}
	public Integer getMemID() {
		return MemID;
	}
	public void setMemID(Integer memID) {
		MemID = memID;
	}
	public Date getPdFavDate() {
		return PdFavDate;
	}
	public void setPdFavDate(Date pdFavDate) {
		PdFavDate = pdFavDate;
	}
	
	
	
	
}
