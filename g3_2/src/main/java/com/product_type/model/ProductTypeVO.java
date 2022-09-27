package com.product_type.model;
import java.sql.Date;

public class ProductTypeVO implements java.io.Serializable{
	private Integer PdTypeID;
	private String PdTypeName;
	
	public Integer getPdTypeID() {
		return PdTypeID;
	}
	public void setPdTypeID(Integer pdTypeID) {
		PdTypeID = pdTypeID;
	}
	public String getPdTypeName() {
		return PdTypeName;
	}
	public void setPdTypeName(String pdTypeName) {
		PdTypeName = pdTypeName;
	}
	
}
