package com.actimg.model;

import java.util.List;

public interface ActImgDAO_interface {
	public void insert(ActImgVO actImgVO);
    public void update(ActImgVO actImgVO);
    public void delete(Integer actImgID);
    public ActImgVO findByPrimaryKey(Integer actImgID);
    public List<ActImgVO> getAll();
  //同時新增活動與照片
    public void insertfromAct (ActImgVO actImgVO , java.sql.Connection con);
}
