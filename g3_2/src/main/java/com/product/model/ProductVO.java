package com.product.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.orderlist.model.OrderListVO;

@Entity
@Table(name = "product", catalog = "boardgame")
public class ProductVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer pdID;
	@Column
	private String pdName;
	@Column
	private Integer pdTypeID;
	@Column
	private Integer pdPrice;
	@Column
	private Integer pdAmount;
	@Column
	private String pdDescription;
	@Column
	private Integer pdStatus;
	@Column
	private Integer pdStar;
	@Column
	private Timestamp pdUpdate;
	
	public Integer getPdID() {
		return pdID;
	}
	public void setPdID(Integer pdID) {
		this.pdID = pdID;
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
	public Integer getPdAmount() {
		return pdAmount;
	}
	public void setPdAmount(Integer pdAmount) {
		this.pdAmount = pdAmount;
	}
	public String getPdDescription() {
		return pdDescription;
	}
	public void setPdDescription(String pdDescription) {
		this.pdDescription = pdDescription;
	}
	public Integer getPdStatus() {
		return pdStatus;
	}
	public void setPdStatus(Integer pdStatus) {
		this.pdStatus = pdStatus;
	}
	public Integer getPdStar() {
		return pdStar;
	}
	public void setPdStar(Integer pdStar) {
		this.pdStar = pdStar;
	}
	public Timestamp getPdUpdate() {
		return pdUpdate;
	}
	public void setPdUpdate(Timestamp pdUpdate) {
		this.pdUpdate = pdUpdate;
	}
	public Integer getPdTypeID() {
		return pdTypeID;
	}
	public void setPdTypeID(Integer pdTypeID) {
		this.pdTypeID = pdTypeID;
	}
	
	
//	@ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
//	private List<OrderListVO> orderLists;
	
	

	}


