package com.orderdetail.model;

import java.util.List;

public interface IOrderDetailService {

	//	-- 新增一筆訂單明細
	OrderDetailVO addOrderDetail(Integer ordNo, Integer pdID, Integer itemSales, Integer price);

	//	-- 更新某一筆訂單明細的內容(ItemSales,Price)
	OrderDetailVO updateOrderDetail(Integer ordNo, Integer pdID, Integer itemSales, Integer price);

	//	-- 作廢某一筆訂單明細的內容(ItemSales,Price)
	OrderDetailVO clearOrderDetail(Integer ordNo, Integer pdID, Integer itemSales, Integer price);

	//	-- 秀出某一筆訂單明細中的每個遊戲
	List<OrderDetailVO> showOneOrderDetail(Integer ordNo);

	//	-- 秀出某一筆訂單明細中的某一個遊戲
	OrderDetailVO showOneOrderDetailPd(Integer ordNo, Integer pdID);

	//	-- 秀出所有訂單明細
	List<OrderDetailVO> getAll();

}