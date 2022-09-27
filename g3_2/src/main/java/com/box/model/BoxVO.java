package com.box.model;

import com.boxtype.model.BoxTypeService;
import com.store.model.StoreService;

public class BoxVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer boxID;
	private Integer storeID;
	private Integer boxTypeID;
	private Integer boxCapcity;
	private Integer boxPrice;
	private String boxDescription;
	private String boxBkStart;
	private String boxBkEnd;
	private byte[] boxImg;
	
	public Integer getBoxID() {
		return boxID;
	}
	public void setBoxID(Integer boxID) {
		this.boxID = boxID;
	}
	public Integer getStoreID() {
		return storeID;
	}
	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
	}
	public Integer getBoxTypeID() {
		return boxTypeID;
	}
	public void setBoxTypeID(Integer boxTypeID) {
		this.boxTypeID = boxTypeID;
	}
	public Integer getBoxCapcity() {
		return boxCapcity;
	}
	public void setBoxCapcity(Integer boxCapcity) {
		this.boxCapcity = boxCapcity;
	}
	public Integer getBoxPrice() {
		return boxPrice;
	}
	public void setBoxPrice(Integer boxPrice) {
		this.boxPrice = boxPrice;
	}
	public String getBoxDescription() {
		return boxDescription;
	}
	public void setBoxDescription(String boxDescription) {
		this.boxDescription = boxDescription;
	}
	public byte[] getBoxImg() {
		return boxImg;
	}
	public void setBoxImg(byte[] boxImg) {
		this.boxImg = boxImg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getBoxBkStart() {
		return boxBkStart;
	}
	public void setBoxBkStart(String boxBkStart) {
		this.boxBkStart = boxBkStart;
	}
	public String getBoxBkEnd() {
		return boxBkEnd;
	}
	public void setBoxBkEnd(String boxBkEnd) {
		this.boxBkEnd = boxBkEnd;
	}
	
	public com.store.model.StoreVO getStoreVO(){
		com.store.model.StoreService storeSvc = new StoreService();
		com.store.model.StoreVO storeVO = storeSvc.getOneStore(storeID);
		return storeVO;
	}
	
	public com.boxtype.model.BoxTypeVO getBoxTypeVO(){
		com.boxtype.model.BoxTypeService boxTypeSvc = new BoxTypeService();
		com.boxtype.model.BoxTypeVO boxTypeVO = boxTypeSvc.findOneBoxType(boxTypeID);
		return boxTypeVO;
	}


}
