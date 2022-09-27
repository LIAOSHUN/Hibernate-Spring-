package com.actregis.model;

import java.util.List;

public interface ActRegisDAO_interface {
	public void insert(ActRegisVO actRegisVO);
    public void update(ActRegisVO actRegisVO);
    public List<ActRegisVO> findByActPrimaryKey(Integer actID);
    public List<ActRegisVO> findByMemPrimaryKey(Integer memID);
    public ActRegisVO findByPrimaryKey(Integer memID, Integer actID);
    public List<ActRegisVO> getAll();
}
