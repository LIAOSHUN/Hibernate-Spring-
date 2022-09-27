package com.boxtype.model;

import java.util.List;

public class BoxTypeService {
	private BoxType_interface dao;
	
	public BoxTypeService() {
		dao = new BoxTypeDAO();
	}
	
	public BoxTypeVO addBoxType(String boxName) {
		
		BoxTypeVO bto = new BoxTypeVO();
		
		bto.setBoxName(boxName);
		dao.insert(bto);
		
		return bto;
	}

	public void deleteBoxType(Integer boxTypeID) {
		dao.delete(boxTypeID);
	}
	
	public void updateBoxType(BoxTypeVO boxTypeVO) {
		dao.update(boxTypeVO);
	}
	
	public BoxTypeVO findOneBoxType(Integer boxTypeID) {
		return dao.findPK(boxTypeID);
	}
	
	public List<BoxTypeVO> getAll(){
		return dao.getAll();
	}
}
