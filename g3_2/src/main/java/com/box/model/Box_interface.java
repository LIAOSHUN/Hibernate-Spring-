package com.box.model;

import java.util.List;

public interface Box_interface {
	//管理員可以新增包廂
	public void insert(BoxVO boxVO);
	//管理員可以修改包廂內容
	public void update(BoxVO boxVO);
	//管理員可以查詢一筆包廂內容
	public BoxVO getOneBox(Integer boxID);
	//管理員可以刪除包廂
	public void delete(Integer boxVO);
	//管理員可以指定門市包廂
	public List<BoxVO> findPK(Integer boxVO);
	//管理員可以查詢所有包廂
	public List<BoxVO> getAll();
	
	public List<BoxVO> getAllInfo();
}
