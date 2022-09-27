package com.orderdetail.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
	@Autowired
	private OrderDetailDAO_interface dao;
	
//	public OrderDetailServiceImpl() {
//		dao = new OrderDetailJDBCDAOH();
//	}
	
//	-- 新增一筆訂單明細
	@Transactional
	@Override
	public OrderDetailVO addOrderDetail(Integer ordNo, Integer pdID, Integer itemSales, Integer price) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrdNo(ordNo);
		orderDetailVO.setPdID(pdID);
		orderDetailVO.setItemSales(itemSales);
		orderDetailVO.setPrice(price);
		dao.insert(orderDetailVO);
		
		return orderDetailVO;
	}
//	-- 更新某一筆訂單明細的內容(ItemSales,Price)
	@Transactional
	@Override
	public OrderDetailVO updateOrderDetail(Integer ordNo, Integer pdID, Integer itemSales, Integer price) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrdNo(ordNo);
		orderDetailVO.setPdID(pdID);
		orderDetailVO.setItemSales(itemSales);
		orderDetailVO.setPrice(price);
		dao.update(orderDetailVO);
		
		return orderDetailVO;
	}
//	-- 作廢某一筆訂單明細的內容(ItemSales,Price)
	@Override
	public OrderDetailVO clearOrderDetail(Integer ordNo, Integer pdID, Integer itemSales, Integer price) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrdNo(ordNo);
		orderDetailVO.setPdID(pdID);
		orderDetailVO.setItemSales(itemSales);
		orderDetailVO.setPrice(price);
		dao.clear(orderDetailVO);
		
		return orderDetailVO;
	}
	
//	-- 秀出某一筆訂單明細中的每個遊戲
	@Transactional
	@Override
	public List<OrderDetailVO> showOneOrderDetail(Integer ordNo){
		return dao.findOneOrderDetail(ordNo);
	}
	
//	-- 秀出某一筆訂單明細中的某一個遊戲
	@Override
	public OrderDetailVO showOneOrderDetailPd(Integer ordNo, Integer pdID) {
		return dao.findOneOrderDetailPd(ordNo, pdID);
	}
//	-- 秀出所有訂單明細
	@Transactional
	@Override
	public List<OrderDetailVO> getAll(){
		return dao.getAll();
	}
	
}
