package com.member.model;

import java.util.List;

import com.cart.model.MemberJDBCDAO_cart;
import com.cart.model.MemberVO_cart;
public class MemberService {
	private MemberJDBCDAO_cart dao;

	public MemberService() {
		dao = new MemberJDBCDAO_cart();
	}

	public MemberVO addMember(Integer gradeID, String memName, String memAccount, String memPassWord, String memGender,
			String memPh, String memEmail, String memAddress, java.sql.Date memBirthday, byte[] memCard, Integer memVio, Integer memStatus) {

		MemberVO memberVO = new MemberVO();

		memberVO.setGradeID(gradeID);
		memberVO.setMemName(memName);
		memberVO.setMemAccount(memAccount);
		memberVO.setMemPassWord(memPassWord);
		memberVO.setMemGender(memGender);
		memberVO.setMemPh(memPh);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemAddress(memAddress);
		memberVO.setMemBirthday(memBirthday);
		memberVO.setMemCard(memCard);
		memberVO.setMemVio(memVio);
		memberVO.setMemStatus(memStatus);

		dao.insert(memberVO);

		return memberVO;
	}

	
	public MemberVO addMemberRegister(String memName,String memAccount,String memPassWord,String memGender,String memPh,String memEmail,String memAddress,java.sql.Date memBirthday,byte[] memCard) {
		MemberVO memberVO1 = new MemberVO();

		memberVO1.setMemName(memName);
		memberVO1.setMemAccount(memAccount);
		memberVO1.setMemPassWord(memPassWord);
		memberVO1.setMemGender(memGender);
		memberVO1.setMemPh(memPh);
		memberVO1.setMemEmail(memEmail);
		memberVO1.setMemAddress(memAddress);
		memberVO1.setMemBirthday(memBirthday);
		memberVO1.setMemCard(memCard);

		dao.registerInsert(memberVO1);

		return memberVO1;
	}
	
	
	
	public MemberVO updateMember(Integer memID, Integer gradeID, String memName, String memAccount, String memPassWord,
			String memGender, String memPh, String memEmail, String memAddress,java.sql.Date memBirthday,
			byte[] memCard, Integer memVio, Integer memStatus) {

		MemberVO memberVO = new MemberVO();
		memberVO.setMemID(memID);
		memberVO.setGradeID(gradeID);
		memberVO.setMemName("memName");
		memberVO.setMemAccount("memAccount");
		memberVO.setMemPassWord("memPassWord");
		memberVO.setMemGender("memGender");
		memberVO.setMemPh("memPh");
		memberVO.setMemEmail("memEmail");
		memberVO.setMemAddress("memAddress");
		memberVO.setMemBirthday(memBirthday);
		memberVO.setMemCard(memCard);
		memberVO.setMemVio(memVio);
		memberVO.setMemStatus(memStatus);

		dao.update(memberVO);

		return memberVO;
	}

	public void deleteMember(Integer memID) {
		dao.delete(memID);
	}

	public MemberVO_cart getOneMember(Integer memID) {
		return dao.findByPrimaryKey(memID);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();

	}
}
