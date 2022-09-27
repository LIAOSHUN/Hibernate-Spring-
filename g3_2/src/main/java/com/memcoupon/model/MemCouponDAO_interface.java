package com.memcoupon.model;

import java.sql.Connection;
import java.util.List;

public interface MemCouponDAO_interface {
	
//	-- 新增一筆會員優惠券資料  	(發放、領取)
	public void insert(MemCouponVO memCouponVO);
//	-- 更改 會員擁有的優惠券 資料內容	(暫不用)
	public void update(MemCouponVO memCouponVO);
//	-- 更改 會員擁有的優惠券 的 使用狀態(給結帳用，未使用改成已使用)
	public void updateStatus1(Integer coupNo, Integer coupStatus);
//	-- 更改 會員擁有的優惠券 的 使用狀態 (給排程器偵測用)
	public void updateStatusRoutine(Integer coupNo, Integer coupStatus);
//	-- 找出 某張優惠券的資訊	
	public MemCouponVO getOne(Integer coupNo);
//	-- 找出 某個會員 擁有的所有優惠券	(myCoupon)
	public List<MemCouponVO> findMemCouponByMemID(Integer memID);
//	-- 找出 某個會員 已使用(未使用、過期的)的所有優惠券		(搭配ajax 動態找出不同狀態的優惠券)
	public List<MemCouponVO> findMemCouponByStatus(Integer memID, Integer coupStatus);
//	-- 找出 某個會員 擁有的某種優惠券			(暫不用)
	public List<MemCouponVO> findMemCouponByCoupTypeNo(Integer memID, Integer coupTypeNo);
//	-- 找出 所有會員 擁有的優惠券			(暫不用)
	public List<MemCouponVO> getAll();
}
