package com.cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.memcoupon.model.MemCouponDAO;
import com.memcoupon.model.MemCouponDAO_interface;
import com.memcoupon.model.MemCouponJDBCDAOH;
import com.orderdetail.model.OrderDetailDAO_interface;
import com.orderdetail.model.OrderDetailJDBCDAOH;
import com.orderdetail.model.OrderDetailVO;
import com.orderlist.model.OrderListDAO;
import com.orderlist.model.OrderListDAO_interface;
import com.orderlist.model.OrderListJDBCDAOH;
import com.orderlist.model.OrderListVO;

import util.HibernateUtil;



@Service
public class CheckoutServiceImpl implements ICheckoutService {
	private Gson gson = new Gson();
	
	@Autowired
	private CartDAO_interface dao;
	@Autowired
	private OrderListDAO_interface daoL;
	@Autowired
	private OrderDetailDAO_interface daoOD;
	@Autowired
	private MemCouponDAO_interface daoMC; 

	public CheckoutServiceImpl() {
//		dao = new CartJDBCDAO();
//		daoL = new OrderListJDBCDAOH();
//		daoOD = new OrderDetailJDBCDAOH();
//		daoMC = new MemCouponJDBCDAOH();
	}
	

	
	//邏輯必須寫在這層才對，專門處理結帳所有需要的動作，並針對結帳進行交易控制
	//所有動作，必須用同一連線
	//呼叫不同dao，各自完成任務
	//底層的處理，必須把例外全拋出，給service層來進行處理，因為此層在做交易控制，不然會無法rollback
	@Transactional
	@Override
	public boolean allJobs(Integer memID, Integer coupNo, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, String recName, String recAddress, String recPhone,
			Integer ordPick, List<OrderDetailVO> list, String sessionId) {
		
		Boolean transa = false;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
			
//			Transaction transaction = session.beginTransaction();
			
			//交易開始，若一個動作失敗，則全部回復
//			con.setAutoCommit(false);
			
			
			//--1.新增訂單同時新增訂單明細
			
			//傳入的參數若為0則代表未使用優惠券
			if(coupNo.equals(0)) {
				System.out.println("未使用優惠券");
				OrderListVO orderListVO = new OrderListVO();
				
				orderListVO.setMemID(memID);
//				orderListVO.setCoupNo(coupNo);
				orderListVO.setOrdOriPrice(ordOriPrice);
				orderListVO.setOrdLastPrice(ordLastPrice);
				orderListVO.setOrdFee(ordFee);
				orderListVO.setOrdStatus(ordStatus);
				orderListVO.setRecName(recName);
				orderListVO.setRecAddress(recAddress);
				orderListVO.setRecPhone(recPhone);
				orderListVO.setOrdPick(ordPick);
				Integer auto = daoL.insertWithOrderDetailsNoCoupon(orderListVO);
				daoOD.insert2(auto, list);
				
			}else {
				//有使用優惠券
				OrderListVO orderListVO = new OrderListVO();
				
				orderListVO.setMemID(memID);
				orderListVO.setCoupNo(coupNo);
				orderListVO.setOrdOriPrice(ordOriPrice);
				orderListVO.setOrdLastPrice(ordLastPrice);
				orderListVO.setOrdFee(ordFee);
				orderListVO.setOrdStatus(ordStatus);
				orderListVO.setRecName(recName);
				orderListVO.setRecAddress(recAddress);
				orderListVO.setRecPhone(recPhone);
				orderListVO.setOrdPick(ordPick);
				Integer auto = daoL.insertWithOrderDetails(orderListVO);
				daoOD.insert2(auto, list);
			}
			
			
			//--2.更改某樣商品庫存
			List<OrderDetailVO> listForUpdate = list;
			
			for (int index = 0; index < listForUpdate.size(); index++) {
				
				OrderDetailVO orderDetailVO = listForUpdate.get(index);
				Integer pdID = orderDetailVO.getPdID();
				
				List<String> cartItems = CartRedisDAO.getCart(sessionId);//先把他的車叫出來
				
				Integer rediscount = 0;
				Integer pdAmount = 0;
				for (int i = 0; i < cartItems.size(); i++) {
					CartItemVO orgItem = gson.fromJson(cartItems.get(i), CartItemVO.class);
					Integer checkedPdID = pdID;
					Integer orgItemId = orgItem.getPdID();
					// 找出選了哪個商品ID
					if (pdID.equals(orgItemId)) {
						rediscount = orgItem.getCount();
						pdAmount = dao.getOne(orgItemId).getPdAmount();
					}
				};
				dao.update((pdAmount - rediscount), pdID);
			};
			
			
			//--3.更改會員優惠券狀態
			if(!coupNo.equals(0)) {
				
				daoMC.updateStatus1(coupNo, 1);
				System.out.println("更改優惠券狀態0變成1");
			}
			
			//以上動作成功的話，才送出交易
			transa = true;
		
			return transa;
	
	
	
	}
}
