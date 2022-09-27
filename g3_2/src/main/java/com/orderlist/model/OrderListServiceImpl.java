package com.orderlist.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderdetail.model.OrderDetailVO;

@Service
public class OrderListServiceImpl implements IOrderListService {
	@Autowired
	private OrderListDAO_interface dao;
	
//	public OrderListServiceImpl() {
//		dao = new OrderListJDBCDAOH();
//	}
	
//	-- 新增訂單資料-使用優惠券
//	public OrderListVO addOrderList2(Integer memID, Integer coupNo, Double ordOriPrice, Double ordLastPrice,
//			Integer ordFee, Integer ordStatus, String recName, String recAddress, String recPhone,
//			Integer ordPick, List<OrderDetailVO> list) {
//		OrderListVO orderListVO = new OrderListVO();
//		
//		orderListVO.setMemID(memID);
//		orderListVO.setCoupNo(coupNo);
//		orderListVO.setOrdOriPrice(ordOriPrice);
//		orderListVO.setOrdLastPrice(ordLastPrice);
//		orderListVO.setOrdFee(ordFee);
//		orderListVO.setOrdStatus(ordStatus);
//		orderListVO.setRecName(recName);
//		orderListVO.setRecAddress(recAddress);
//		orderListVO.setRecPhone(recPhone);
//		orderListVO.setOrdPick(ordPick);
//		dao.insertWithOrderDetails(orderListVO, list);
//		return orderListVO;
//		
//	}
	
	
//	-- 新增訂單資料-沒使用優惠券
//	public OrderListVO addOrderListNc2(Integer ordNo, Integer memID, Double ordOriPrice, Double ordLastPrice,
//			Integer ordFee, Integer ordStatus, String recName, String recAddress, String recPhone,
//			Integer ordPick, List<OrderDetailVO> list) {
//		OrderListVO orderListVO = new OrderListVO();
//		
//		orderListVO.setOrdNo(ordNo);
//		orderListVO.setMemID(memID);
//		orderListVO.setOrdOriPrice(ordOriPrice);
//		orderListVO.setOrdLastPrice(ordLastPrice);
//		orderListVO.setOrdFee(ordFee);
//		orderListVO.setOrdStatus(ordStatus);
//		orderListVO.setRecName(recName);
//		orderListVO.setRecAddress(recAddress);
//		orderListVO.setRecPhone(recPhone);
//		orderListVO.setOrdPick(ordPick);
//		dao.insertWithOrderDetailsNoCoupon(orderListVO, list);
//		return orderListVO;
//		
//	}
	
//	-- 新增訂單資料-使用優惠券
	@Override
	public OrderListVO addOrderList(Integer ordNo, Integer memID, Integer coupNo, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, Timestamp ordCreate, String recName, String recAddress, String recPhone,
			Integer ordPick) {
		OrderListVO orderListVO = new OrderListVO();
		
		orderListVO.setOrdNo(ordNo);
		orderListVO.setMemID(memID);
		orderListVO.setCoupNo(coupNo);
		orderListVO.setOrdOriPrice(ordOriPrice);
		orderListVO.setOrdLastPrice(ordLastPrice);
		orderListVO.setOrdFee(ordFee);
		orderListVO.setOrdStatus(ordStatus);
		orderListVO.setOrdCreate(ordCreate);
		orderListVO.setRecName(recName);
		orderListVO.setRecAddress(recAddress);
		orderListVO.setRecPhone(recPhone);
		orderListVO.setOrdPick(ordPick);
		dao.insert(orderListVO);
		return orderListVO;
		
	}
//	-- 新增訂單資料-沒使用優惠券
	@Override
	public OrderListVO addOrderListNc(Integer ordNo, Integer memID, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, Timestamp ordCreate, String recName, String recAddress, String recPhone,
			Integer ordPick) {
		OrderListVO orderListVO = new OrderListVO();
		
		orderListVO.setOrdNo(ordNo);
		orderListVO.setMemID(memID);
		orderListVO.setOrdOriPrice(ordOriPrice);
		orderListVO.setOrdLastPrice(ordLastPrice);
		orderListVO.setOrdFee(ordFee);
		orderListVO.setOrdStatus(ordStatus);
		orderListVO.setOrdCreate(ordCreate);
		orderListVO.setRecName(recName);
		orderListVO.setRecAddress(recAddress);
		orderListVO.setRecPhone(recPhone);
		orderListVO.setOrdPick(ordPick);
		dao.insertNoCoupon(orderListVO);
		return orderListVO;
		
	}
//	-- 更改訂單內容
	@Override
	public OrderListVO updateOrderList(Integer ordNo, Integer ordStatus, String recName, String recAddress, String recPhone,
			Integer ordPick) {
		OrderListVO orderListVO = new OrderListVO();
		
		orderListVO.setOrdNo(ordNo);
		orderListVO.setOrdStatus(ordStatus);
		orderListVO.setRecName(recName);
		orderListVO.setRecAddress(recAddress);
		orderListVO.setRecPhone(recPhone);
		orderListVO.setOrdPick(ordPick);
		dao.update(orderListVO);
		return orderListVO;
		
	}
//  -- 秀出某一筆訂單的所有資料
	@Override
	public OrderListVO showOneOrder(Integer ordNo) {
		return dao.findOneOrder(ordNo);
	}
//  -- 找出某位會員的所有訂單
    @Override
	public List<OrderListVO> showOrderByMemID(Integer memID){
    	return dao.findOrderByMemID(memID);
    }
//  -- 秀出某種出貨狀態的訂單
	@Override
	public List<OrderListVO> showOrderByStatus(Integer ordStatus){
		return dao.findOrderByStatus(ordStatus);
	}
//  -- 秀出所有訂單
	@Override
	public List<OrderListVO> getAll(){
		return dao.getAll();
	}
//  -- 秀出所有訂單(複合查詢)
	@Override
	public List<OrderListVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}

}
