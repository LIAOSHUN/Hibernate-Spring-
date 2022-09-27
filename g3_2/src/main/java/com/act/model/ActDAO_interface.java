package com.act.model;

import java.util.List;
import java.util.Set;

import com.actimg.model.ActImgVO;

public interface ActDAO_interface {
	public void insert(ActVO actVO);
    public void update(ActVO actVO);
    public ActVO findByPrimaryKey(Integer actID);
    public List<ActVO> getAll();
  //查詢某活動的照片(一對多)(回傳 Set)
    public Set<ActImgVO> getImgsByAct(Integer actID);
  //同時新增活動與活動照片
    public void insertWithActImgs(ActVO actVO , List<ActImgVO> imglist);
}
