package com.orderlist.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.member.model.MemberVO;
import com.product.model.ProductVO;
import com.cart.model.MemberJDBCDAO_cart;
import com.cart.model.MemberVO_cart;
import com.coupontype.model.CouponTypeVO;
import com.member.model.MemberService;

@Entity
@Table(name = "orderlist", catalog = "boardgame")
public class OrderListVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer ordNo;
	@Column
	private Integer memID;
	@Column
	private Integer coupNo;
	@Column
	private Double ordOriPrice;
	@Column
	private Double ordLastPrice;
	@Column
	private Integer ordFee;
	@Column
	private Integer ordStatus;
	@Column
	private Timestamp ordCreate;
	@Column
	private String recName;
	@Column
	private String recAddress;
	@Column
	private String recPhone;
	@Column
	private Integer ordPick;
	@ManyToOne
	@JoinColumn(name = "memID", insertable = false, updatable = false)
	private MemberVO_cart memberVO_cart;

	public OrderListVO() {
	}

	public OrderListVO(Integer ordNo, Integer memID, Integer coupNo, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, Timestamp ordCreate, String recName, String recAddress, String recPhone,
			Integer ordPick) {
		super();
		this.ordNo = ordNo;
		this.memID = memID;
		this.coupNo = coupNo;
		this.ordOriPrice = ordOriPrice;
		this.ordLastPrice = ordLastPrice;
		this.ordFee = ordFee;
		this.ordStatus = ordStatus;
		this.ordCreate = ordCreate;
		this.recName = recName;
		this.recAddress = recAddress;
		this.recPhone = recPhone;
		this.ordPick = ordPick;
	}

	public Integer getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public Integer getCoupNo() {
		return coupNo;
	}

	public void setCoupNo(Integer coupNo) {
		this.coupNo = coupNo;
	}

	public Double getOrdOriPrice() {
		return ordOriPrice;
	}

	public void setOrdOriPrice(Double ordOriPrice) {
		this.ordOriPrice = ordOriPrice;
	}

	public Double getOrdLastPrice() {
		return ordLastPrice;
	}

	public void setOrdLastPrice(Double ordLastPrice) {
		this.ordLastPrice = ordLastPrice;
	}

	public Integer getOrdFee() {
		return ordFee;
	}

	public void setOrdFee(Integer ordFee) {
		this.ordFee = ordFee;
	}

	public Integer getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(Integer ordStatus) {
		this.ordStatus = ordStatus;
	}

	public Timestamp getOrdCreate() {
		return ordCreate;
	}

	public void setOrdCreate(Timestamp ordCreate) {
		this.ordCreate = ordCreate;
	}

	public String getRecName() {
		return recName;
	}

	public void setRecName(String recName) {
		this.recName = recName;
	}

	public String getRecAddress() {
		return recAddress;
	}

	public void setRecAddress(String recAddress) {
		this.recAddress = recAddress;
	}

	public String getRecPhone() {
		return recPhone;
	}

	public void setRecPhone(String recPhone) {
		this.recPhone = recPhone;
	}

	public Integer getOrdPick() {
		return ordPick;
	}

	public void setOrdPick(Integer ordPick) {
		this.ordPick = ordPick;
	}
	
	public MemberVO_cart getMemberVO_cart() {
		return memberVO_cart;
	}

	public void setMemberVO_cart(MemberVO_cart memberVO_cart) {
		this.memberVO_cart = memberVO_cart;
	}

	// for join memName from memID
//	public MemberVO_cart getMemberVO() {
//		MemberJDBCDAO_cart dao = new MemberJDBCDAO_cart();
//		MemberVO_cart memberVO = dao.findByPrimaryKey(memID);
//		return memberVO;
//	}

//	@ManyToMany
//	@JoinTable(
//		joinColumns=@JoinColumn(
//			referencedColumnName = "ordNo",
//			name = "ordNo"),
//		name="orderdetail",
//		inverseJoinColumns=@JoinColumn(
//			name = "pdID",	
//			referencedColumnName = "pdID")
//	)
//	private List<ProductVO> products;

}
