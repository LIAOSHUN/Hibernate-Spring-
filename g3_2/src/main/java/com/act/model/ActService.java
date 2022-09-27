package com.act.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.actimg.model.ActImgVO;

public class ActService {
	private ActDAO_interface dao;

	public ActService() {
		dao = new ActDAO();
//		dao = new ActJDBCDAO();
	}

	public ActVO addAct(Integer storeID, String actTitle, String actDescription,
			LocalDateTime actTimeStart, LocalDateTime actTimeEnd, LocalDateTime actDate, Integer regisMax, 
			Integer actFee, Integer actRegistration, Integer actStatus) {

		ActVO actVO = new ActVO();

		actVO.setStoreID(storeID);
		actVO.setActTitle(actTitle);
		actVO.setActDescription(actDescription);
		actVO.setActTimeStart(actTimeStart);
		actVO.setActTimeEnd(actTimeEnd);
		actVO.setActDate(actDate);
		actVO.setRegisMax(regisMax);
		actVO.setActFee(actFee);
		actVO.setActRegistration(actRegistration);
		actVO.setActStatus(actStatus);
		dao.insert(actVO);

		return actVO;
	}

	public ActVO updateAct(Integer actID, Integer storeID, String actTitle, String actDescription,
			LocalDateTime actTimeStart, LocalDateTime actTimeEnd, LocalDateTime actDate, Integer regisMax, 
			Integer actFee, Integer actRegistration, Integer actStatus) {

		ActVO actVO = new ActVO();
		
		actVO.setActID(actID);
		actVO.setStoreID(storeID);
		actVO.setActTitle(actTitle);
		actVO.setActDescription(actDescription);
		actVO.setActTimeStart(actTimeStart);
		actVO.setActTimeEnd(actTimeEnd);
		actVO.setActDate(actDate);
		actVO.setRegisMax(regisMax);
		actVO.setActFee(actFee);
		actVO.setActRegistration(actRegistration);
		actVO.setActStatus(actStatus);
		dao.update(actVO);

		return actVO;
	}

	public ActVO getOneAct(Integer actID) {
		return dao.findByPrimaryKey(actID);
	}

	public List<ActVO> getAll() {
		return dao.getAll();
	}
	
	public Set<ActImgVO> getImgsByAct(Integer actID) {
		return dao.getImgsByAct(actID);
	}
	
	public void insertWithActImgs(ActVO actVO , List<ActImgVO> imglist) {
		dao.insertWithActImgs(actVO, imglist);
	}
}
