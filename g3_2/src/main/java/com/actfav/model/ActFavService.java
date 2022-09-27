package com.actfav.model;

import java.time.LocalDateTime;
import java.util.List;

public class ActFavService {
	private ActFavDAO_interface dao;

	public ActFavService() {
		dao = new ActFavDAO();
//		dao = new ActFavJDBCDAO();
	}

	public ActFavVO addActFav(Integer memID, Integer actID, LocalDateTime actFavDate) {

		ActFavVO actFavVO = new ActFavVO();

		actFavVO.setMemID(memID);
		actFavVO.setActID(actID);
		actFavVO.setActFavDate(actFavDate);
		dao.insert(actFavVO);

		return actFavVO;
	}

	public ActFavVO updateActFav(Integer memID, Integer actID, LocalDateTime actFavDate) {

		ActFavVO actFavVO = new ActFavVO();

		actFavVO.setMemID(memID);
		actFavVO.setActID(actID);
		actFavVO.setActFavDate(actFavDate);
		dao.update(actFavVO);

		return actFavVO;
	}
	
	public void deleteActFav(Integer memID, Integer actID) {
		dao.delete(memID, actID);
	}
	
	public ActFavVO getOneFavByOneMem(Integer memID, Integer actID) {
		return dao.findOneFavByOneMem(memID, actID);
	}

	public List<ActFavVO> getByMem(Integer memID) {
		return dao.findByPrimaryKey(memID);
	}
	
	public List<Object> getByActJoinList(Integer memID) {
		return dao.findByActJoinList(memID);
	}

	public List<ActFavVO> getAll() {
		return dao.getAll();
	}
}
