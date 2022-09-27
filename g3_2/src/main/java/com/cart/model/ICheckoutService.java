package com.cart.model;

import java.util.List;

import com.orderdetail.model.OrderDetailVO;

public interface ICheckoutService {

	//邏輯必須寫在這層才對，專門處理結帳所有需要的動作，並針對結帳進行交易控制
	//所有動作，必須用同一連線
	//呼叫不同dao，各自完成任務
	//底層的處理，必須把例外全拋出，給service層來進行處理，因為此層在做交易控制，不然會無法rollback
	boolean allJobs(Integer memID, Integer coupNo, Double ordOriPrice, Double ordLastPrice, Integer ordFee,
			Integer ordStatus, String recName, String recAddress, String recPhone, Integer ordPick,
			List<OrderDetailVO> list, String sessionId);

}