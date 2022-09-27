package com.membergrade.model;

import java.util.List;


public interface MemberGradeDAO_interface {
	 public void insert(MemberGradeVO membergradeVO);
	    public void update(MemberGradeVO membergradeVO);
	    public void delete(Integer gradeID);
	    public MemberGradeVO findByPrimaryKey(Integer gradeID);
	    public List<MemberGradeVO> getAll();
}
