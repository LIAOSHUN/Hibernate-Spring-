package com.store.model;

import java.util.List;

public interface StoreVO_interface {
	//管理員可以新增門市
	public void insert(StoreVO storeVO);
	//管理員可以修改門市內容
	public void update(StoreVO storeVO);
//	public void delete(StoreVO storeVO);
	//管理員可以查詢指定門市
	public StoreVO findPK(Integer storeID);
	//管理員可以查詢所有門市
	public List<StoreVO> getAll();

}
