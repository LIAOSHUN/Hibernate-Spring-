package com.memcoupon.model;

import java.sql.Timestamp;
import java.util.List;

public interface IMemCouponService {

	//	-- 新增一筆會員優惠券資料
	MemCouponVO addMemCoupon(Integer memID, Integer coupTypeNo);

	//	-- 更改 會員擁有的優惠券 資料內容
	MemCouponVO updateMemCoupon(Integer coupNo, Integer memID, Integer coupTypeNo, Integer coupStatus,
			Timestamp coupExpDate, Timestamp coupGetDate);

	//	-- 更改 會員擁有的優惠券 的 使用狀態(給排程器偵測用)
	void updateStatusRoutine(Integer coupNo, Integer coupStatus);

	//	-- 找出 某張優惠券的資訊	
	MemCouponVO getOne(Integer coupNo);

	//	-- 秀出 某個會員 擁有的所有優惠券
	List<MemCouponVO> showMemCouponByMemID(Integer memID);

	//	-- 秀出 某個會員 已使用(未使用、過期的)的所有優惠券
	List<MemCouponVO> showMemCouponByStatus(Integer memID, Integer coupStatus);

	//	-- 秀出 某個會員 擁有的某種優惠券
	List<MemCouponVO> showMemCouponByCoupTypeNo(Integer memID, Integer coupTypeNo);

	//	-- 秀出 所有會員 擁有的優惠券
	List<MemCouponVO> getAll();

}