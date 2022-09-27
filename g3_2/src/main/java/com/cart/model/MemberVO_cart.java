package com.cart.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coupontype.model.CouponTypeVO;
import com.membergrade.model.MemberGradeVO;

@Entity
@Table(name = "member", catalog = "boardgame")
public class MemberVO_cart implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer memID;
	@Column
	private Integer gradeID;
	@Column
	private String memName;
	@Column
	private String memAccount;
	@Column
	private String memPassWord;
	@Column
	private String memGender;
	@Column
	private String memPh;
	@Column
	private String memEmail;
	@Column
	private String memAddress;
	@Column
	private Date memBirthday;
	@Column
	private byte[] memCard;
	@Column
	private Integer memVio;
	@Column
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
	
	// for join memgradediscount from memgrade
//	public MemberGradeVO getMemberGradeVO(Integer gradeID){
//		MemberGradeJDBCDAO_cart memberGradeJDBCDAO_cart = new MemberGradeJDBCDAO_cart();
//		MemberGradeVO memberGradeVO = memberGradeJDBCDAO_cart.findByPrimaryKey(gradeID);
//		return memberGradeVO;
//	}
	
	@ManyToOne
	@JoinColumn(name = "gradeID", insertable = false, updatable = false)
	private MemberGradeVO memberGradeVO;
	public MemberGradeVO getMemberGradeVO() {
		return memberGradeVO;
	}
	public void setMemberGradeVO(MemberGradeVO memberGradeVO) {
		this.memberGradeVO = memberGradeVO;
	}

}
