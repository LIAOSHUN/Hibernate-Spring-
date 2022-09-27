package com.actimg.model;

import java.util.List;


public class ActImgService {
	private ActImgDAO_interface dao;

	public ActImgService() {
		dao = new ActImgDAO();
//		dao = new ActImgJDBCDAO();
	}

	public ActImgVO addActImg(Integer actID, byte[] actImgFile) {

		ActImgVO actImgVO = new ActImgVO();

		actImgVO.setActID(actID);
		actImgVO.setActImgFile(actImgFile);
		dao.insert(actImgVO);

		return actImgVO;
	}

	public ActImgVO updateActImg(Integer actImgID, Integer actID, byte[] actImgFile) {

		ActImgVO actImgVO = new ActImgVO();

		actImgVO.setActImgID(actImgID);
		actImgVO.setActID(actID);
		actImgVO.setActImgFile(actImgFile);
		dao.update(actImgVO);

		return actImgVO;
	}
	
	public void deleteActImg(Integer actImgID) {
		dao.delete(actImgID);
	}

	public ActImgVO getOneActImg(Integer actImgID) {
		return dao.findByPrimaryKey(actImgID);
	}

	public List<ActImgVO> getAll() {
		return dao.getAll();
	}
}
