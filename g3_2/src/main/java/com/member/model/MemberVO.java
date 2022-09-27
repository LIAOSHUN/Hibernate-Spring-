package com.member.model;

import java.io.Serializable;
import java.sql.Date;


public class MemberVO implements Serializable {
	private Integer memID;
	private Integer gradeID;
	private String memName;
	private String memAccount;
	private String memPassWord;
	private String memGender;
	private String memPh;
	private String memEmail;
	private String memAddress;
	private Date memBirthday;
	private byte[] memCard;
	private Integer memVio;
	private Integer memStatus;
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public int getGradeID() {
		return gradeID;
	}
	public void setGradeID(Integer gradeID) {
		this.gradeID = gradeID;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemAccount() {
		return memAccount;
	}
	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}
	public String getMemPassWord() {
		return memPassWord;
	}
	public void setMemPassWord(String memPassWord) {
		this.memPassWord = memPassWord;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public String getMemPh() {
		return memPh;
	}
	public void setMemPh(String memPh) {
		this.memPh = memPh;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}
	public Date getMemBirthday() {
		return memBirthday;
	}
	public void setMemBirthday(Date memBirthday) {
		this.memBirthday = memBirthday;
	}
	public byte[] getMemCard() {
		return memCard;
	}
	public void setMemCard(byte[] memCard) {
		this.memCard = memCard;
	}
	public Integer getMemVio() {
		return memVio;
	}
	public void setMemVio(Integer memVio) {
		this.memVio = memVio;
	}
		public int getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}

}
