package com.coupontype.model;

import java.util.List;

public interface CouponTypeDAO_interface {
//	-- 新增資料
	public void insert(CouponTypeVO couponTypeVO);
//	-- 更改優惠券內容
	public void update(CouponTypeVO couponTypeVO);
//	-- 更改優惠券庫存
	public void updateQuantity(Integer coupTypeNo, Integer coupQuantity);
//	-- 更改優惠券為下架
	public void updateDown(Integer coupTypeNo);
//	-- 找出某種類型的優惠券
	public CouponTypeVO findCouponTypeByType(Integer coupTypeNo);
//	-- 找出所有優惠券
	public List<CouponTypeVO> getAll();
	
}
