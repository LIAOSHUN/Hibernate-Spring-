package com.cart.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.product.model.ProductVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.SpringUtil;

public class CartRedisDAO  {


	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	//List<String> cartItems:購物車內所有商品
//	orgItem :購物車裡面原有的商品
//	wantAddItem:欲加入購物車的商品
	
	//取得現在購物車狀況
	public static List<String> getCart(String sessionId) {
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(1);
		

		List<String> cartItems = jedis.lrange(sessionId, 0, -1);
		jedis.close();
		return cartItems;
	}
	//被打勾選取的商品呈現在結帳頁
	public static CartItemVO getOneChecked(String sessionId, ProductVO productVO) {
		Gson gson = new Gson();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(1);
		
		List<String> cartItems = getCart(sessionId);//先把他的車叫出來
		
		
			CartItemVO cartItemVO = new CartItemVO();
			for (int i = 0; i < cartItems.size(); i++) {
				CartItemVO orgItem = gson.fromJson(cartItems.get(i), CartItemVO.class);//將他的車的商品一個一個取出來

				Integer checkedPdID = productVO.getPdID();
				Integer orgItemId = orgItem.getPdID();
				// 找出選了哪個商品ID
				if (checkedPdID.equals(orgItemId)) {
					
					String pdName = productVO.getPdName();
					Integer price = productVO.getPdPrice();
					Integer count = orgItem.getCount();//從redis取出此商品ID，現在的數量，代表前面購物車買的數量
					
					//將所得到的商品資訊，設值給此購物車商品
					cartItemVO.setPdID(checkedPdID);
					cartItemVO.setPdName(pdName);
					cartItemVO.setCount(count);
					cartItemVO.setPdPrice(price);
					
					
					jedis.close();
				};
			}
			return cartItemVO;
		
	}
	//在商城中:點擊加入購物車，將商品加入購物車中
	public static void addItem(String sessionId, CartItemVO cartItemVO) {

		Gson gson = new Gson();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(1);
		

		List<String> cartItems = getCart(sessionId);//先把他的車叫出來

		if (cartItems != null) {
			for (int i = 0; i < cartItems.size(); i++) {
				CartItemVO orgItem = gson.fromJson(cartItems.get(i), CartItemVO.class);//將他的車的商品一個一個取出來

				Integer wantAddItemId = cartItemVO.getPdID();
				Integer orgItemId = orgItem.getPdID();

				// 若購物車內已有該商品ID則增加數量
				if (wantAddItemId.equals(orgItemId)) {
					Integer count = orgItem.getCount();

					count += cartItemVO.getCount(); 
					orgItem.setCount(count);
					
					//更新後的數量再存回redis
					String str = gson.toJson(orgItem);
					jedis.lset(sessionId, i, str);
					jedis.expire(sessionId, 60 * 60 * 24 * 3);
					jedis.close();
					return;
				}
			}

			// 若沒有該商品ID則新增
			String strVO = gson.toJson(cartItemVO);
			jedis.rpush(sessionId, strVO);
			jedis.expire(sessionId, 60 * 60 * 24 * 3);
			jedis.close();
		}

	}
	
	
	//在購物車內改變商品數量
	public static void changeItemCount(String sessionId, CartItemVO cartItemVO) {
		Gson gson = new Gson();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(1);
		
		
		List<String> cartItems = getCart(sessionId);//先把他的車叫出來

			for (int i = 0; i < cartItems.size(); i++) {
				CartItemVO orgItem = gson.fromJson(cartItems.get(i), CartItemVO.class);//將他的車的商品一個一個取出來

				Integer wantAddItemId = cartItemVO.getPdID();
				Integer orgItemId = orgItem.getPdID();

				// 將購物車內原有商品，改變數量
				if (wantAddItemId.equals(orgItemId)) {
					Integer count = orgItem.getCount();

					count = cartItemVO.getCount(); 
					orgItem.setCount(count);
					
					//更新後的數量再存回redis
					String str = gson.toJson(orgItem);
					jedis.lset(sessionId, i, str);
					jedis.close();
					return;
				}
			}
	}
	
	//在購物車內商品刪除商品
	public static void deleteItem(String sessionId, Integer pdID) {
		Gson gson = new Gson();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(1);

		List<String> cartItems = getCart(sessionId);

		for (int i = 0; i < cartItems.size(); i++) {
			CartItemVO orgItem = gson.fromJson(cartItems.get(i), CartItemVO.class);

			if (orgItem.getPdID().equals(pdID)) {
				jedis.lrem(sessionId, 0, cartItems.get(i)); // 刪除該商品
				jedis.close();
				return;
			}
		}
	}
	
	
	
	//成立訂單，殺掉購物車
	public static void deleteCart(String sessionId) {
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.select(1);
		
		
		jedis.del(sessionId); // 清空購物車刪除key
		jedis.close();
	}







	
	
	
	
	
	
	
	
	
	
	
//增加商品到購物車
//	public List<ProductVO> addCart(Integer memID, String pdName, Integer ccount) {
//
//		List<ProductVO> list = new ArrayList<ProductVO>();
//		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.select(1);// 更換資料庫
////			jedis.lpush("", "{pdName:ccount}");
////			jedis.lpush("11001", "{"阿瓦隆":2}");
//
//		jedis.lpush("11001", "[{阿瓦隆:1}]");
//
//		// JSON to List
//
//		jedis.close();
//		return list;
//	}
//		
//		
//		
//		
//		
////		商品1{"阿瓦隆":2}
////		商品2{"狼人殺":2}
//		
//		

////商品數量減到0，移除商品
////移除商品
////清空購物車
////共幾樣商品
////購物車存活七天
//		
//		
//		jedis.close();
//		
//	
}
