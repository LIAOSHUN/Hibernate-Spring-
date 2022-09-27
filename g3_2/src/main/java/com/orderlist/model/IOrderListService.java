package com.orderlist.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface IOrderListService {

	//	-- 新增訂單資料-使用優惠券
	OrderListVO addOrderList(Integer ordNo, Integer memID, Integer coupNo, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, Timestamp ordCreate, String recName, String recAddress, String recPhone,
			Integer ordPick);

	//	-- 新增訂單資料-沒使用優惠券
	OrderListVO addOrderListNc(Integer ordNo, Integer memID, Double ordOriPrice, Double ordLastPrice, Integer ordFee,
			Integer ordStatus, Timestamp ordCreate, String recName, String recAddress, String recPhone,
			Integer ordPick);

	//	-- 更改訂單內容
	OrderListVO updateOrderList(Integer ordNo, Integer ordStatus, String recName, String recAddress, String recPhone,
			Integer ordPick);

	//  -- 秀出某一筆訂單的所有資料
	OrderListVO showOneOrder(Integer ordNo);

	//  -- 找出某位會員的所有訂單
	List<OrderListVO> showOrderByMemID(Integer memID);

	//  -- 秀出某種出貨狀態的訂單
	List<OrderListVO> showOrderByStatus(Integer ordStatus);

	//  -- 秀出所有訂單
	List<OrderListVO> getAll();

	//  -- 秀出所有訂單(複合查詢)
	List<OrderListVO> getAll(Map<String, String[]> map);

}