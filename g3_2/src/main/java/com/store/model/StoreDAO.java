package com.store.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StoreDAO implements Store_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql");
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("連線池錯誤");
		}
	}
	
	//管理員可以新增店面
	private static final String INSERT_STMT = 
			"INSERT INTO store (storeName, storeAdd, storePhone1, storePhone2, storeEmail, StoreImg, storeOpen, storeClose, storeOff, empID) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	//管理員可以修改店面資訊
	private static final String UPDATE = 
			"UPDATE store set StoreName=?, StoreAdd=?, StorePhone1=?, StorePhone2=?, StoreEmail=?, StoreImg=? "
					+ ", StoreOpen=?, StoreClose=?, StoreOff=?, EmpID=?, StoreStatus=?"
					+ " where StoreID = ?";
	
	//管理員可以查詢所有店面資訊
	private static final String GET_ALL_STMT = 
			"SELECT StoreID, StoreName, StoreAdd, StorePhone1, StorePhone2, "
			+ "StoreEmail, StoreOpen, StoreClose, StoreOff, EmpID, StoreStatus "
			+ "FROM store";

	//管理員可以查詢單一家店面資訊
	private static final String GET_ONE_STMT = 
			"SELECT StoreID, StoreName, StoreAdd, StorePhone1, StorePhone2, StoreEmail, StoreImg, StoreOpen, StoreClose, StoreOff, EmpID, StoreStatus "
			+ "FROM store "
			+ "where storeID = ?";
	
	private static final String GET_INFO = 
			"SELECT StoreID, StoreName FROM store";

	@Override
	public void insert(StoreVO storeVO) {
		try (
				Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT);
		) {

			pstmt.setString(1, storeVO.getStoreName());
			pstmt.setString(2, storeVO.getStoreAdd());
			pstmt.setString(3, storeVO.getStorePhone1());
			pstmt.setString(4, storeVO.getStorePhone2());
			pstmt.setString(5, storeVO.getStoreEmail());
			pstmt.setBytes(6, storeVO.getStoreImg());
			pstmt.setString(7, storeVO.getStoreOpen());
			pstmt.setString(8, storeVO.getStoreClose());
			pstmt.setString(9, storeVO.getStoreOff());
			pstmt.setInt(10, storeVO.getEmpID());
//			pstmt.setString(11, storeVO.getStoreBokSet());
//			pstmt.setInt(11, storeVO.getStoreStatus());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(StoreVO storeVO) {
		try (
				Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			) {

			pstmt.setString(1, storeVO.getStoreName());
			pstmt.setString(2, storeVO.getStoreAdd());
			pstmt.setString(3, storeVO.getStorePhone1());
			pstmt.setString(4, storeVO.getStorePhone2());
			pstmt.setString(5, storeVO.getStoreEmail());
			pstmt.setBytes(6, storeVO.getStoreImg());
			pstmt.setString(7, storeVO.getStoreOpen());
			pstmt.setString(8, storeVO.getStoreClose());
			pstmt.setString(9, storeVO.getStoreOff());
			pstmt.setInt(10, storeVO.getEmpID());
//			pstmt.setString(11, storeVO.getStoreBokSet());
			pstmt.setInt(11, storeVO.getStoreStatus());
			pstmt.setInt(12, storeVO.getStoreID());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public StoreVO findPK(Integer storeID) {
		StoreVO siv = null;
		try (
				Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT);
		) {
			pstmt.setInt(1, storeID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				siv = new StoreVO();
				siv.setStoreID(rs.getInt("storeID"));
				siv.setStoreName(rs.getString("storeName"));
				siv.setStoreAdd(rs.getString("storeAdd"));
				siv.setStorePhone1(rs.getString("storePhone1"));
				siv.setStorePhone2(rs.getString("storePhone2"));
				siv.setStoreEmail(rs.getString("StoreEmail"));
				siv.setStoreImg(rs.getBytes("StoreImg"));
				siv.setStoreOpen(rs.getString("storeOpen"));
				siv.setStoreClose(rs.getString("StoreClose"));
				String storeOff = rs.getString("StoreOff");
				switch (storeOff) {
				case "0": {
					siv.setStoreOff("日");
					break; 
					}
				case "1": {
					siv.setStoreOff("一");
					break; 
					}
				case "2": {
					siv.setStoreOff("二");
					break; 
				}
				case "3": {
					siv.setStoreOff("三");
					break; 
				}
				case "4": {
					siv.setStoreOff("四");
					break; 
				}
				case "5": {
					siv.setStoreOff("五");
					break; 
				}
				case "6": {
					siv.setStoreOff("六");
					break; 
					}
				}
//				siv.setStoreOff(rs.getString("StoreOff"));
				siv.setEmpID(rs.getInt("EmpID"));
//				siv.setStoreBokSet(rs.getString("StoreBokSet"));
				siv.setStoreStatus(rs.getInt("StoreStatus"));
			}
			return siv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<StoreVO> getAll() {
		try (
				Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ALL_STMT);
				ResultSet rs = pstmt.executeQuery();
			) {
			List<StoreVO> list = new ArrayList<>();
			while (rs.next()) {
				StoreVO siv = new StoreVO();
				siv.setStoreID(rs.getInt("StoreID"));
				siv.setStoreName(rs.getString("StoreName"));
				siv.setStoreAdd(rs.getString("StoreAdd"));
				siv.setStorePhone1(rs.getString("StorePhone1"));
				siv.setStorePhone2(rs.getString("StorePhone2"));
				siv.setStoreEmail(rs.getString("StoreEmail"));
				
				int start = Integer.valueOf(rs.getString("StoreOpen").trim());
				for(int i = 0;i <= start; i++) {
					if(i == start) {
						start = i;
					}
				}
				String StoreOpen = ((start < 10)? ("0"+ start) : start) + ":00";
				siv.setStoreOpen(StoreOpen);
				
				int end = Integer.valueOf(rs.getString("StoreClose").trim());
				for(int i = 0;i <= end; i++) {
					if(i == end) {
						end = i;
					}
				}
				String StoreClose = ((end < 10)? ("0"+ end) : end) + ":00";
				siv.setStoreClose(StoreClose);
				
				String storeOff = rs.getString("StoreOff");
				switch (storeOff) {
				case "0": {
					siv.setStoreOff("日");
					break; 
					}
				case "1": {
					siv.setStoreOff("一");
					break; 
					}
				case "2": {
					siv.setStoreOff("二");
					break; 
				}
				case "3": {
					siv.setStoreOff("三");
					break; 
				}
				case "4": {
					siv.setStoreOff("四");
					break; 
				}
				case "5": {
					siv.setStoreOff("五");
					break; 
				}
				case "6": {
					siv.setStoreOff("六");
					break; 
					}
				}
				siv.setEmpID(rs.getInt("EmpID"));
//				siv.setStoreBokSet(rs.getString("StoreBokSet"));
				siv.setStoreStatus(rs.getInt("StoreStatus"));
				list.add(siv);

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<StoreVO> getStoreInfo(){
		try (
				Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_INFO);
				ResultSet rs = pstmt.executeQuery();) {
			List<StoreVO> list = new ArrayList<>();
			while (rs.next()) {
				StoreVO siv = new StoreVO();
				siv.setStoreID(rs.getInt("StoreID"));
				siv.setStoreName(rs.getString("StoreName"));
				list.add(siv);

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
