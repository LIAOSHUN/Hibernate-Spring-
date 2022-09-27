package com.cart.model;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.product.model.ProductVO;

public interface ICartService {

	//=========================================================================================================
	//	-- 找出某樣商品資訊
	ProductVO getOne(Integer pdID);

	//點擊購物車的按鈕，進入購物車
	List<CartItemVO> getCart(String sessionId);

	void addItem(String sessionId, Integer pdID, Integer count);

	//在購物車內改變商品數量
	void changeItemCount(String sessionId, Integer pdID, Integer count);

	//處理被勾選的商品，呈現在結帳頁面
	CartItemVO getOneChecked(String sessionId, ProductVO productVO);

	//結帳時刪除被選取的商品(有部分商品留在購物車裡面)
	void deleteItemChecked(String sessionId, Integer pdID);

	void deleteItem(String sessionId, Integer pdID);

	void deleteCart(String sessionId);

}