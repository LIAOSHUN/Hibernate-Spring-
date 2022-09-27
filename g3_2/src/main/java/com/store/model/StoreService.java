package com.store.model;

import java.util.List;


public class StoreService {

	private Store_interface dao;

	public StoreService() {
		dao = new StoreDAO();
	}
	
	public StoreVO addStroe(String storeName, String storeAdd, String storePhone1, String storePhone2, String storeEmail, 
			byte[] storeImg, String storeOpen, String storeClose, String storeOff, Integer empID) {
		
		StoreVO svo = new StoreVO();
		
		svo.setStoreName(storeName);
		svo.setStoreAdd(storeAdd);
		svo.setStorePhone1(storePhone1);
		svo.setStorePhone2(storePhone2);
		svo.setStoreEmail(storeEmail);
		svo.setStoreImg(storeImg);
		svo.setStoreOpen(storeOpen);
		svo.setStoreClose(storeClose);
		svo.setStoreOff(storeOff);
		svo.setEmpID(empID);
//		svo.setStoreBokSet(StoreBokSet);
//		svo.setStoreStatus(storeStatus);
		dao.insert(svo);
		
		return svo;
	}
	
	public StoreVO updateStoreInfo(String storeName, String storeAdd, String storePhone1, 
			String storePhone2, String storeEmail, byte[] storeImg, String storeOpen, 
			String storeClose, String storeOff, Integer empID, /*String storeBokSet,*/ Integer storeStatus, Integer storeID) {
		
		StoreVO svo = new StoreVO();
		
		svo.setStoreName(storeName);
		svo.setStoreAdd(storeAdd);
		svo.setStorePhone1(storePhone1);
		svo.setStorePhone2(storePhone2);
		svo.setStoreEmail(storeEmail);
		svo.setStoreImg(storeImg);
		svo.setStoreOpen(storeOpen);
		svo.setStoreClose(storeClose);
		svo.setStoreOff(storeOff);
		svo.setEmpID(empID);
//		svo.setStoreBokSet(storeBokSet);
		svo.setStoreStatus(storeStatus);
		svo.setStoreID(storeID);
		dao.update(svo);
		
		return svo;
	}
	
	public StoreVO getOneStore(Integer storeID) {
		return dao.findPK(storeID);
	}
	
	public List<StoreVO> getAll(){
		return dao.getAll();
	}

	public List<StoreVO> getStoreInfo() {
		return dao.getStoreInfo();
	}
}
