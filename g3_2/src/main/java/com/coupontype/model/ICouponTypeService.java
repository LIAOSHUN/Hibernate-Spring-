package com.coupontype.model;

import java.sql.Date;
import java.util.List;

public interface ICouponTypeService {

	//	-- 新增資料
	CouponTypeVO addCouponType(String coupName, Integer coupDiscount, Integer coupQuantity, String coupDesc,
			Date coupStart, Date coupEnd);

	//	-- 更改優惠券內容
	CouponTypeVO updateCouponType(Integer coupTypeNo, String coupName, Integer coupDiscount, Integer coupQuantity,
			String coupDesc, Integer coupUpd);

	//	-- 更改優惠券庫存
	void updateQuantity(Integer coupTypeNo);

	//	-- 更改優惠券為下架
	void updateDown(Integer coupTypeNo);

	//	-- 秀出某種類型的優惠券
	CouponTypeVO showCouponTypeByType(Integer coupTypeNo);

	//	-- 秀出所有優惠券
	List<CouponTypeVO> getAll();

	//	-- 刪除某張優惠券
	void delete(Integer coupTypeNo);

}