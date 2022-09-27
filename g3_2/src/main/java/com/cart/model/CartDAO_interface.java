package com.cart.model;

import java.sql.Connection;
import java.util.List;

import com.product.model.ProductVO;

public interface CartDAO_interface {
//	-- 找出某樣商品資訊
	public ProductVO getOne(Integer pdID);
//	-- 更改某樣商品資訊
	public void update(Integer count, Integer pdID);
	
	
}
