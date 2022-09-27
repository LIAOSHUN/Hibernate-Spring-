package com.membergrade.model;

import java.util.List;


public class MemberGradeService {
	private MemberGradeDAO_interface dao;

	public MemberGradeService() {
		dao = new MemberGradeJDBCDAO();
	}

	public MemberGradeVO addMemberGrade(Integer gradeID, String gradeName, double gradeDiscount) {

		MemberGradeVO membergradeVO = new MemberGradeVO();

		membergradeVO.setGradeID(gradeID);
		membergradeVO.setGradeName(gradeName);
		membergradeVO.setGradeDiscount(gradeDiscount);
		
		dao.insert(membergradeVO);

		return membergradeVO;
	}

	public MemberGradeVO updateMemberGrade(Integer gradeID, String gradeName, double gradeDiscount) {

		MemberGradeVO membergradeVO = new MemberGradeVO();
		membergradeVO.setGradeID(gradeID);
		membergradeVO.setGradeName(gradeName);
		membergradeVO.setGradeDiscount(gradeDiscount);
		
		dao.update(membergradeVO);

		return membergradeVO;
	}

	public void deleteMemberGrade(Integer gradeID) {
		dao.delete(gradeID);
	}

	public MemberGradeVO getOneMemberGrade(Integer gradeID) {
		return dao.findByPrimaryKey(gradeID);
	}

	public List<MemberGradeVO> getAll() {
		return dao.getAll();

	}
}
