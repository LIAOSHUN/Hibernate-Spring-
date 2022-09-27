package com.actregis.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ActRegisVO implements Serializable{
	private Integer memID;
	private Integer actID;
	private Timestamp regisTime;
	private Integer actNum;
	private Integer actFee;
	private Integer feeStatus;
	private Integer regisStatus;
	private String actReview;
	private Integer satisfaction;
	private Date reviewDate;
	
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public Timestamp getRegisTime() {
		return regisTime;
	}
	public void setRegisTime(Timestamp regisTime) {
		this.regisTime = regisTime;
	}
	public Integer getActNum() {
		return actNum;
	}
	public void setActNum(Integer actNum) {
		this.actNum = actNum;
	}
	public Integer getActFee() {
		return actFee;
	}
	public void setActFee(Integer actFee) {
		this.actFee = actFee;
	}
	public Integer getFeeStatus() {
		return feeStatus;
	}
	public void setFeeStatus(Integer feeStatus) {
		this.feeStatus = feeStatus;
	}
	public Integer getRegisStatus() {
		return regisStatus;
	}
	public void setRegisStatus(Integer regisStatus) {
		this.regisStatus = regisStatus;
	}
	public String getActReview() {
		return actReview;
	}
	public void setActReview(String actReview) {
		this.actReview = actReview;
	}
	public Integer getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
}
