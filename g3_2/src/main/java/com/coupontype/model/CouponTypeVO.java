package com.coupontype.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.memcoupon.model.MemCouponVO;



@Entity
@Table(name = "CouponType", catalog = "boardgame")
public class CouponTypeVO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer coupTypeNo;
	@Column
	private String coupName;
	@Column
	private Integer coupDiscount;
	@Column
	private Integer coupQuantity;
	@Column
	private String coupDesc;
	@Column(insertable = false)
	private Integer coupUpd;
	@Column(updatable = false)
	private Date coupStart;
	@Column(updatable = false)
	private Date coupEnd;
	
	public CouponTypeVO() {
		
	}
	
	public CouponTypeVO(Integer coupTypeNo, String coupName, Integer coupDiscount, Integer coupQuantity,
			String coupDesc, Integer coupUpd, Date coupStart, Date coupEnd) {
		super();
		this.coupTypeNo = coupTypeNo;
		this.coupName = coupName;
		this.coupDiscount = coupDiscount;
		this.coupQuantity = coupQuantity;
		this.coupDesc = coupDesc;
		this.coupUpd = coupUpd;
		this.coupStart = coupStart;
		this.coupEnd = coupEnd;
	}
	public Integer getCoupTypeNo() {
		return coupTypeNo;
	}
	public void setCoupTypeNo(Integer coupTypeNo) {
		this.coupTypeNo = coupTypeNo;
	}
	public String getCoupName() {
		return coupName;
	}
	public void setCoupName(String coupName) {
		this.coupName = coupName;
	}
	public Integer getCoupDiscount() {
		return coupDiscount;
	}
	public void setCoupDiscount(Integer coupDiscount) {
		this.coupDiscount = coupDiscount;
	}
	public Integer getCoupQuantity() {
		return coupQuantity;
	}
	public void setCoupQuantity(Integer coupQuantity) {
		this.coupQuantity = coupQuantity;
	}
	public String getCoupDesc() {
		return coupDesc;
	}
	public void setCoupDesc(String coupDesc) {
		this.coupDesc = coupDesc;
	}
	public Integer getCoupUpd() {
		return coupUpd;
	}
	public void setCoupUpd(Integer coupUpd) {
		this.coupUpd = coupUpd;
	}
	public Date getCoupStart() {
		return coupStart;
	}
	public void setCoupStart(Date coupStart) {
		this.coupStart = coupStart;
	}
	public Date getCoupEnd() {
		return coupEnd;
	}
	public void setCoupEnd(Date coupEnd) {
		this.coupEnd = coupEnd;
	}
//	@OneToMany
//	@JoinColumn(name = "coupTypeNo", referencedColumnName = "coupTypeNo")
//	private List<MemCouponVO>memCouponVOs;
	
}
