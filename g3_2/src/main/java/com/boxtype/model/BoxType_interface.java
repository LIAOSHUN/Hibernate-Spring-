package com.boxtype.model;

import java.util.List;

public interface BoxType_interface {
	// 管理員可以新增包廂類型
	public void insert(BoxTypeVO boxTypeVO);
	// 管理員可以刪除資料列
	public void delete(Integer boxTypeID);
	// 管理員可以修改類型名稱
	public void update(BoxTypeVO boxTypeVO);
	// 管理員可以查詢指定id資料
	public BoxTypeVO findPK(Integer boxTypeID);
	// 管理員可以查詢所有包廂類型
	public List<BoxTypeVO> getAll();
}
