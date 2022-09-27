package com.cart.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.product.model.ProductDAO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductVO;



// user cookie("cart", sessionId)
// Redis List (sessionId, {"itemId": "xxx", "count": "x"})
// 使用者進入首頁時，檢查是否有key為shoppingCart的cookie，有的話取得sessionId，沒有則用給予cookie

// 用sessionId自redis取得購物車內的資料：jedis.lrange(key, 0, -1)，用itemId比對mySQL取得商品價格、比對庫存
@Service
public class CartServiceImpl implements ICartService {
	private Gson gson = new Gson();
	@Autowired
	private CartDAO_interface dao; 
	private CartRedisDAO daoR; 

//	public CartServiceImpl() {
//		dao = new CartJDBCDAO();
//		daoR = new CartRedisDAO();
//	}
//=========================================================================================================
//	-- 找出某樣商品資訊
	@Override
	public ProductVO getOne(Integer pdID) {
		return dao.getOne(pdID);
	}

//	-- 更改某樣商品庫存
//	public void updatePdAmount(Integer count, Integer pdID) {
//		dao.update(count, pdID);
//	}
	
//	===============================================================================================================
	
	//點擊購物車的按鈕，進入購物車
	@Override
	@Transactional
	public List<CartItemVO> getCart(String sessionId) {
		List cartItemsList = new ArrayList();

		//要先找這個人的車，去redis裡找購物車
		List<String> cartItems = CartRedisDAO.getCart(sessionId); // {"pdID": "xxx", "count": "x"}
		for (int i = 0; i < cartItems.size(); i++) {
			CartItemVO cartItemVO = gson.fromJson(cartItems.get(i), CartItemVO.class);
			ProductVO productVO = dao.getOne(cartItemVO.getPdID());//從資料庫找出該商品名字及價錢，並設值到購物車的商品上
			
			cartItemVO.setPdName(productVO.getPdName());
			cartItemVO.setPdPrice(productVO.getPdPrice());

			cartItemsList.add(cartItemVO);
		}
		return cartItemsList; // 讓servlet取得後渲染於購物車頁面
	}

	@Override
	public void addItem(String sessionId, Integer pdID, Integer count) {

		CartItemVO cartItemVO = new CartItemVO();
		cartItemVO.setPdID(pdID);
		cartItemVO.setCount(count);

		daoR.addItem(sessionId, cartItemVO);
	}
	
	//在購物車內改變商品數量
	@Override
	public void changeItemCount(String sessionId, Integer pdID, Integer count) {

		if(count > 0) {
			
			CartItemVO cartItemVO = new CartItemVO();
			cartItemVO.setPdID(pdID);
			cartItemVO.setCount(count);
			
			daoR.changeItemCount(sessionId, cartItemVO);
		}else {
			daoR.deleteItem(sessionId, pdID);
		};
		
		
	}

	//處理被勾選的商品，呈現在結帳頁面
	@Override
	public CartItemVO getOneChecked(String sessionId, ProductVO productVO) {
		return daoR.getOneChecked(sessionId, productVO);
	}
	
	//結帳時刪除被選取的商品(有部分商品留在購物車裡面)
	@Override
	public void deleteItemChecked(String sessionId, Integer pdID) {
		//刪除購物車商品
		daoR.deleteItem(sessionId, pdID);
	}
	
	@Override
	public void deleteItem(String sessionId, Integer pdID) {
		daoR.deleteItem(sessionId, pdID);
	}
	
	@Override
	public void deleteCart(String sessionId) {
		daoR.deleteCart(sessionId);
	};
	
	//結帳時刪除被選取的商品(有部分商品留在購物車裡面) ----更改某樣商品庫存
//	public void updatePdAmount(String sessionId, Integer pdID) {	
//		
//		//更新資料庫數量
//		List<String> cartItems = CartRedisDAO.getCart(sessionId);//先把他的車叫出來
//		
//		Integer rediscount = 0;
//		Integer pdAmount = 0;
//		for (int i = 0; i < cartItems.size(); i++) {
//			CartItemVO orgItem = gson.fromJson(cartItems.get(i), CartItemVO.class);
//			Integer checkedPdID = pdID;
//			Integer orgItemId = orgItem.getPdID();
//			// 找出選了哪個商品ID
//			if (pdID.equals(orgItemId)) {
//				rediscount = orgItem.getCount();
//				pdAmount = dao.getOne(orgItemId).getPdAmount();
//				
////				Integer count = (pdAmount - rediscount);
//			}
//		};
//		dao.update((pdAmount - rediscount), pdID);
//	}
	

}
