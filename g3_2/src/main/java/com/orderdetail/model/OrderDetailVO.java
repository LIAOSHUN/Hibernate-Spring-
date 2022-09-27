package com.orderdetail.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cart.model.CartJDBCDAO;
import com.coupontype.model.CouponTypeVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;


@Entity
@Table(name = "orderdetail", catalog = "boardgame")
@IdClass(Pk.class)
public class OrderDetailVO  {
	
	@Id
	@Column
 	private Integer ordNo;
	@Id
	@Column
 	private Integer pdID;
 	
 	@Column
 	private Integer itemSales;
 	@Column
 	private Integer price;
 	
 	public OrderDetailVO() {
 		
 	}


	public OrderDetailVO(Integer ordNo, Integer pdID, Integer itemSales, Integer price) {
		super();
		this.ordNo = ordNo;
		this.pdID = pdID;
		this.itemSales = itemSales;
		this.price = price;
	}
	public Integer getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}
	public Integer getPdID() {
		return pdID;
	}
	public void setPdID(Integer pdID) {
		this.pdID = pdID;
	}
	public Integer getItemSales() {
		return itemSales;
	}
	public void setItemSales(Integer itemSales) {
		this.itemSales = itemSales;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	// for join pdName  from pdID
//	public ProductVO getProductVO(Integer pdID){
//		CartJDBCDAO cartJDBCDAO = new CartJDBCDAO();
//		ProductVO productVO =  cartJDBCDAO.getOne(pdID);
//		
//		return productVO;
//	}
	
	@ManyToOne
	@JoinColumn(name = "pdID", insertable = false, updatable = false)
	private ProductVO productVO;

	public ProductVO getProductVO() {
		return productVO;
	}


	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
 	
 	
}
