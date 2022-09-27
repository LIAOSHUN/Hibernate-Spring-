package com.orderlist.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.orderdetail.model.OrderDetailVO;

public interface OrderListDAO_interface {
	
//	-- 新增訂單資料-使用優惠券
	public void insert(OrderListVO orderListVO);
//	-- 新增訂單資料-沒使用優惠券
	public void insertNoCoupon(OrderListVO orderListVO);
//	-- 更改訂單內容
    public void update(OrderListVO orderListVO);
//  -- 找出某一筆訂單的所有資料
    public OrderListVO findOneOrder(Integer ordNo);
//  -- 找出某位會員的所有訂單(我的訂單)
    public List<OrderListVO> findOrderByMemID(Integer memID);
//  -- 找出某種出貨狀態的訂單
    public List<OrderListVO> findOrderByStatus(Integer ordStatus);
//  -- 找出所有訂單
    public List<OrderListVO> getAll();
//  -- 找出所有訂單-複合查詢(傳入參數型態Map)(回傳 List)
    public List<OrderListVO> getAll(Map<String, String[]> map);
//  訂單主檔與明細檔一次新增成功
    public Integer insertWithOrderDetails(OrderListVO orderListVO) ;
//  訂單主檔與明細檔一次新增成功(無優惠券)
    public Integer insertWithOrderDetailsNoCoupon(OrderListVO orderListVO);
}
