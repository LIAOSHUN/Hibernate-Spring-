package com.memcoupon.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coupontype.model.CouponTypeVO;


@Entity
@Table(name = "memcoupon", catalog = "boardgame")
public class MemCouponVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer coupNo;
	@Column
	private Integer memID;
	@Column
	private Integer coupTypeNo;
	@Column (insertable = false)
	private Integer coupStatus;
	@Column (insertable = false)
	private Timestamp coupExpDate;
	@Column (insertable = false)
	private Timestamp coupGetDate;
	
	public MemCouponVO() {
		
	}
	
	public MemCouponVO(Integer coupNo, Integer memID, Integer coupTypeNo, Integer coupStatus, Timestamp coupExpDate,
			Timestamp coupGetDate) {
		super();
		this.coupNo = coupNo;
		this.memID = memID;
		this.coupTypeNo = coupTypeNo;
		this.coupStatus = coupStatus;
		this.coupExpDate = coupExpDate;
		this.coupGetDate = coupGetDate;
	}
	public Integer getCoupNo() {
		return coupNo;
	}
	public void setCoupNo(Integer coupNo) {
		this.coupNo = coupNo;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getCoupTypeNo() {
		return coupTypeNo;
	}
	public void setCoupTypeNo(Integer coupTypeNo) {
		this.coupTypeNo = coupTypeNo;
	}
	public Integer getCoupStatus() {
		return coupStatus;
	}
	public void setCoupStatus(Integer coupStatus) {
		this.coupStatus = coupStatus;
	}
	public Timestamp getCoupExpDate() {
		return coupExpDate;
	}
	public void setCoupExpDate(Timestamp coupExpDate) {
		this.coupExpDate = coupExpDate;
	}
	public Timestamp getCoupGetDate() {
		return coupGetDate;
	}
	public void setCoupGetDate(Timestamp coupGetDate) {
		this.coupGetDate = coupGetDate;
	}
	// for join coupName & coupDiscount & coupDuration from coupTypeNo
//	public CouponTypeVO getCouponTypeVO(){
//		CouponTypeService couponTypeSvc = new CouponTypeService();
//		CouponTypeVO couponTypeVO = couponTypeSvc.showCouponTypeByType(coupTypeNo);
//		return couponTypeVO;
//	}
	@ManyToOne
	@JoinColumn(name = "coupTypeNo", insertable = false, updatable = false)
	private CouponTypeVO couponTypeVO;

	public CouponTypeVO getCouponTypeVO() {
		return couponTypeVO;
	}

	public void setCouponTypeVO(CouponTypeVO couponTypeVO) {
		this.couponTypeVO = couponTypeVO;
	}
	
	
}
