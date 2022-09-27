package com.productimg.model;
import java.sql.Blob;
import java.sql.Date;


public class ProductImgVO implements java.io.Serializable{
	private Integer pdImgID;
	private Integer pdID;
	private byte[] pdImg ;
	private String pdImgName;
	
	public Integer getPdImgID() {
		return pdImgID;
	}
	public void setPdImgID(Integer pdImgID) {
		this.pdImgID = pdImgID;
	}
	public Integer getPdID() {
		return pdID;
	}
	public void setPdID(Integer pdID) {
		this.pdID = pdID;
	}
	public byte[] getPdImg() {
		return pdImg;
	}
	public void setPdImg(byte[] pdImg) {
		this.pdImg = pdImg;
	}
	public String getPdImgName() {
		return pdImgName;
	}
	public void setPdImgName(String pdImgName) {
		this.pdImgName = pdImgName;
	}
	
	
	
	
	
}
