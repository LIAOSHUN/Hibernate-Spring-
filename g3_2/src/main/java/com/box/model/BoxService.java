package com.box.model;

import java.util.List;

public class BoxService {
	//interface為降低相依性
	private Box_interface dao;
	
	public BoxService() {
		dao = new BoxDAO();
	}
	
	public BoxVO addBox(Integer storeID, Integer boxTypeID, Integer boxCapcity, 
			Integer boxPrice, String boxDescription, String boxBkStart, String boxBkEnd, byte[] boxImg) {
		
		BoxVO bvo = new BoxVO();
		
		bvo.setStoreID(storeID);
		bvo.setBoxTypeID(boxTypeID);
		bvo.setBoxCapcity(boxCapcity);
		bvo.setBoxPrice(boxPrice);
		bvo.setBoxDescription(boxDescription);
		bvo.setBoxBkStart(boxBkStart);
		bvo.setBoxBkEnd(boxBkEnd);
		bvo.setBoxImg(boxImg);
		dao.insert(bvo);
		
		return bvo;
	}
	
	public BoxVO updateBox(Integer boxID, Integer storeID, Integer boxTypeID, Integer boxCapcity, Integer boxPrice, 
			String boxDescription, byte[] boxImg, String boxBkStart, String boxBkEnd) {
		
		BoxVO bvo = new BoxVO();
		
		bvo.setBoxID(boxID);
		bvo.setStoreID(storeID);
		bvo.setBoxTypeID(boxTypeID);
		bvo.setBoxCapcity(boxCapcity);
		bvo.setBoxPrice(boxPrice);
		bvo.setBoxDescription(boxDescription);
		bvo.setBoxImg(boxImg);
		bvo.setBoxBkStart(boxBkStart);
		bvo.setBoxBkEnd(boxBkEnd);
		dao.update(bvo);
		
		return bvo;
	}
	
	public void deleteBox(Integer boxID) {
		dao.delete(boxID);
	}
	
	public List<BoxVO> getBoxOfStore(Integer boxVO) {
		return dao.findPK(boxVO);
	}
	
	public BoxVO getOneBox(Integer boxID) {
		return dao.getOneBox(boxID);
	}
	
	public List<BoxVO> getAll(){
		return dao.getAll();
	}
	
	public List<BoxVO> getAllInfo(){
		return dao.getAllInfo();
	}

	

}
