package com.actimg.model;

public class ActImgVO implements java.io.Serializable {
	private Integer actImgID;
	private Integer actID;
	private byte[] actImgFile;
	
	public Integer getActImgID() {
		return actImgID;
	}
	public void setActImgID(Integer actImgID) {
		this.actImgID = actImgID;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public byte[] getActImgFile() {
		return actImgFile;
	}
	public void setActImgFile(byte[] actImgFile) {
		this.actImgFile = actImgFile;
	} 
	
}
