package com.act.model;

import java.time.LocalDateTime;

import com.store.model.StoreService;
import com.store.model.StoreVO;

public class ActVO implements java.io.Serializable {
	private Integer actID;
	private Integer storeID;
	private String actTitle;
	private String actDescription;
	private LocalDateTime actTimeStart;
	private LocalDateTime actTimeEnd;
	private LocalDateTime actDate;
	private Integer regisMax;
	private Integer actFee;
	private Integer actRegistration;
	private Integer actStatus;
	
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public Integer getStoreID() {
		return storeID;
	}
	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
	}
	public String getActTitle() {
		return actTitle;
	}
	public void setActTitle(String actTitle) {
		this.actTitle = actTitle;
	}
	public String getActDescription() {
		return actDescription;
	}
	public void setActDescription(String actDescription) {
		this.actDescription = actDescription;
	}
	public LocalDateTime getActTimeStart() {
		return actTimeStart;
	}
	public void setActTimeStart(LocalDateTime actTimeStart) {
		this.actTimeStart = actTimeStart;
	}
	public LocalDateTime getActTimeEnd() {
		return actTimeEnd;
	}
	public void setActTimeEnd(LocalDateTime actTimeEnd) {
		this.actTimeEnd = actTimeEnd;
	}
	public LocalDateTime getActDate() {
		return actDate;
	}
	public void setActDate(LocalDateTime actDate) {
		this.actDate = actDate;
	}
	public Integer getActStatus() {
		return actStatus;
	}
	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}
	public Integer getActFee() {
		return actFee;
	}
	public void setActFee(Integer actFee) {
		this.actFee = actFee;
	}
	public Integer getActRegistration() {
		return actRegistration;
	}
	public void setActRegistration(Integer actRegistration) {
		this.actRegistration = actRegistration;
	}
	public Integer getRegisMax() {
		return regisMax;
	}
	public void setRegisMax(Integer regisMax) {
		this.regisMax = regisMax;
	}
	
	
	public Integer getDateNum() {
		Integer dateNum = actDate.getHour();
		return dateNum;
	}
	
	// for join storeName from storeID
	public StoreVO getStoreVO() {
		StoreService storeSvc = new StoreService();
		StoreVO storeVO = storeSvc.getOneStore(storeID);
		return storeVO;
	}
	
}

