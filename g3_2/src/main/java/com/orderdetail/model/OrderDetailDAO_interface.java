package com.orderdetail.model;

import java.sql.SQLException;
import java.util.List;


public interface OrderDetailDAO_interface {

//	-- 新增一筆訂單明細
	public void insert(OrderDetailVO orderDetailVO);
//	-- 更新某一筆訂單明細的內容(ItemSales,Price)
	public void update(OrderDetailVO orderDetailVO);
//	-- 作廢某一筆訂單明細的內容(ItemSales,Price)
	public void clear(OrderDetailVO orderDetailVO);
//	-- 找出某一筆訂單明細中的每個遊戲
	public List<OrderDetailVO> findOneOrderDetail(Integer ordNo);
//	-- 找出某一筆訂單明細中的某一個遊戲
	public OrderDetailVO findOneOrderDetailPd(Integer ordNo, Integer pdID);
//	-- 找出所有訂單明細
	public List<OrderDetailVO> getAll();
	//新增訂單明細，承接訂單自動編號
	public void insert2 (Integer auto, List<OrderDetailVO> list) ;
	
}
