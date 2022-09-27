package com.membergrade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "membergrade", catalog = "boardgame")
public class MemberGradeVO {
	@Id
	@Column
	private Integer gradeID;
	@Column
	private  String gradeName;
	@Column
	private  double gradeDiscount;
	public Integer getGradeID() {
		return gradeID;
	}
	public void setGradeID(Integer gradeID) {
		this.gradeID = gradeID;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public double getGradeDiscount() {
		return gradeDiscount;
	}
	public void setGradeDiscount(Double gradeDiscount) {
		this.gradeDiscount = gradeDiscount;
	}
	
}
