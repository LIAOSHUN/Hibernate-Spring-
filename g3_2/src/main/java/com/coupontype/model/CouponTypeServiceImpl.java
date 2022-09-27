package com.coupontype.model;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponTypeServiceImpl implements ICouponTypeService {
	@Autowired
	private CouponTypeDAO_interface dao;
	
	
//	public CouponTypeServiceImpl() {
//		dao = new CouponTypeJDBCDAOH();
//	}
	
//	-- 新增資料
	@Transactional
	@Override
	public CouponTypeVO addCouponType(String coupName, Integer coupDiscount, Integer coupQuantity,
			String coupDesc, Date coupStart, Date coupEnd) {
		CouponTypeVO couponTypeVO = new CouponTypeVO();
		
		couponTypeVO.setCoupName(coupName);
		couponTypeVO.setCoupDiscount(coupDiscount);
		couponTypeVO.setCoupQuantity(coupQuantity);
		couponTypeVO.setCoupDesc(coupDesc);
		couponTypeVO.setCoupStart(coupStart);
		couponTypeVO.setCoupEnd(coupEnd);
		dao.insert(couponTypeVO);
		return couponTypeVO;
		
		
	}
	
//	-- 更改優惠券內容
	@Transactional
	@Override
	public CouponTypeVO updateCouponType(Integer coupTypeNo, String coupName, Integer coupDiscount, Integer coupQuantity,
			String coupDesc, Integer coupUpd) {
		CouponTypeVO couponTypeVO = new CouponTypeVO();
		
		couponTypeVO.setCoupTypeNo(coupTypeNo);
		couponTypeVO.setCoupName(coupName);
		couponTypeVO.setCoupDiscount(coupDiscount);
		couponTypeVO.setCoupQuantity(coupQuantity);
		couponTypeVO.setCoupDesc(coupDesc);
		couponTypeVO.setCoupUpd(coupUpd);
		dao.update(couponTypeVO);
		return couponTypeVO;
		
		
	}
//	-- 更改優惠券庫存
	@Transactional
	@Override
	public void updateQuantity(Integer coupTypeNo) {
		CouponTypeVO couponTypeVO = dao.findCouponTypeByType(coupTypeNo);
		Integer CoupQuantity = couponTypeVO.getCoupQuantity() - 1;//一次發一張
		
		dao.updateQuantity(coupTypeNo, CoupQuantity);
		
	}
//	-- 更改優惠券為下架
	@Transactional
	@Override
	public void updateDown(Integer coupTypeNo) {
		CouponTypeVO couponTypeVO = dao.findCouponTypeByType(coupTypeNo);
		Integer coupUpd = couponTypeVO.getCoupUpd();
		//如果商品仍上架中，才去改為已下架
		if(coupUpd == 0) {
			dao.updateDown(coupTypeNo);
		}
	}
	
//	-- 秀出某種類型的優惠券
	@Transactional
	@Override
	public CouponTypeVO showCouponTypeByType(Integer coupTypeNo) {
		return dao.findCouponTypeByType(coupTypeNo);
	}
	
//	-- 秀出所有優惠券
	@Transactional
	@Override
	public  List<CouponTypeVO> getAll(){
		return dao.getAll();
	}
	
//	-- 刪除某張優惠券
	@Override
	public void delete(Integer coupTypeNo) {
//		dao.delete(coupTypeNo);
	}
	
	
}
