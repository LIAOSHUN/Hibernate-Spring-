package com.cart.model;

public class CartItemVO {
	
	
	private Integer pdID;
	private String pdName;
	private Integer count;
	private Integer pdPrice;
	
	
	
	public CartItemVO() {
	
	}
	
	
	public CartItemVO(Integer pdID, String pdName, Integer count,  Integer pdPrice) {
		super();
		
		this.pdID = pdID;
		this.pdName = pdName;
		this.count = count;
		this.pdPrice = pdPrice;
	}
	
	

	public Integer getPdID() {
		return pdID;
	}
	public void setPdID(Integer pdID) {
		this.pdID = pdID;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public Integer getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(Integer pdPrice) {
		this.pdPrice = pdPrice;
	}
	
	
	
	
	
	
}
