package com.store.model;


public class StoreVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer storeID;
	private String storeName;
	private String storeAdd;
	private String storePhone1;
	private String storePhone2;
	private String storeEmail;
	private byte[] storeImg;
	private String storeOpen;
	private String storeClose;
	private String storeOff;
	private Integer empID;
	private String storeBokSet;
	private Integer storeStatus;
	
	public Integer getStoreID() {
		return storeID;
	}
	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAdd() {
		return storeAdd;
	}
	public void setStoreAdd(String storeAdd) {
		this.storeAdd = storeAdd;
	}
	public String getStorePhone1() {
		return storePhone1;
	}
	public void setStorePhone1(String storePhone1) {
		this.storePhone1 = storePhone1;
	}
	public String getStorePhone2() {
		return storePhone2;
	}
	public void setStorePhone2(String storePhone2) {
		this.storePhone2 = storePhone2;
	}
	public String getStoreEmail() {
		return storeEmail;
	}
	public void setStoreEmail(String storeEmail) {
		this.storeEmail = storeEmail;
	}
	public byte[] getStoreImg() {
		return storeImg;
	}
	public void setStoreImg(byte[] storeImg) {
		this.storeImg = storeImg;
	}
	public String getStoreOpen() {
		return storeOpen;
	}
	public void setStoreOpen(String storeOpen) {
		this.storeOpen = storeOpen;
	}
	public String getStoreClose() {
		return storeClose;
	}
	public void setStoreClose(String storeClose) {
		this.storeClose = storeClose;
	}
	public String getStoreOff() {
		return storeOff;
	}
	public void setStoreOff(String storeOff) {
		this.storeOff = storeOff;
	}
	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}
	public String getStoreBokSet() {
		return storeBokSet;
	}
	public void setStoreBokSet(String storeBokSet) {
		this.storeBokSet = storeBokSet;
	}
	public Integer getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(Integer storeStatus) {
		this.storeStatus = storeStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
