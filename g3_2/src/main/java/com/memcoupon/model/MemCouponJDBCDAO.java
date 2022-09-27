package com.memcoupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemCouponJDBCDAO implements MemCouponDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CGA103g3";
//	-- 新增一筆會員優惠券資料
	private static final String Insert=
		"INSERT INTO memcoupon(MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate) "
		+ "VALUES "
		+ "(?, ?, ?, ?, now())";
			
//	-- 更改 會員擁有的優惠券 資料內容
	private static final String Update=
		"update memcoupon "
		+ "set MemID=?, CoupTypeNo=?, CoupStatus=?, CoupExpDate=?, CoupGetDate=? "
		+ "where CoupNo=?";
	
//	-- 找出 某個會員 擁有的所有優惠券
	private static final String FindMemCouponByMemID=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "where MemID=? "
		+ "order by CoupNo";
		
//	-- 找出 某個會員 已使用(未使用、過期的)的所有優惠券
	private static final String FindMemCouponByStatus=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "where MemID=? and CoupStatus=? "
		+ "order by CoupNo";
	
//	-- 找出 某個會員 擁有的某種優惠券
	private static final String FindMemCouponByCoupTypeNo=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "where MemID=? and CoupTypeNo=? "
		+ "order by CoupNo";
	
//	-- 找出 某個會員 剩3天要到期的優惠券
	
//	-- 找出 所有會員 擁有的優惠券
	private static final String GetAll=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "order by MemID";
	
	@Override
	public void insert(MemCouponVO memCouponVO) {
		
		try(Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(Insert)){
			
			ps.setInt(1, memCouponVO.getMemID());
			ps.setInt(2, memCouponVO.getCoupTypeNo());
			ps.setInt(3, memCouponVO.getCoupStatus());
			ps.setTimestamp(4, memCouponVO.getCoupExpDate());
	
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MemCouponVO memCouponVO) {
		try(Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(Update)){
			
			ps.setInt(1, memCouponVO.getMemID());
			ps.setInt(2, memCouponVO.getCoupTypeNo());
			ps.setInt(3, memCouponVO.getCoupStatus());
			ps.setTimestamp(4, memCouponVO.getCoupExpDate());
			ps.setTimestamp(5, memCouponVO.getCoupGetDate());
			ps.setInt(6, memCouponVO.getCoupNo());
	
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<MemCouponVO> findMemCouponByMemID(Integer memID) {
		List<MemCouponVO> list = new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO = null;
		
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(FindMemCouponByMemID)){
			ps.setInt(1, memID);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				memCouponVO = new MemCouponVO();
				memCouponVO.setCoupNo(rs.getInt("CoupNo"));
				memCouponVO.setMemID(rs.getInt("MemID"));
				memCouponVO.setCoupTypeNo(rs.getInt("CoupTypeNo"));
				memCouponVO.setCoupStatus(rs.getInt("CoupStatus"));
				memCouponVO.setCoupExpDate(rs.getTimestamp("CoupExpDate"));
				memCouponVO.setCoupGetDate(rs.getTimestamp("CoupGetDate"));
				list.add(memCouponVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<MemCouponVO> findMemCouponByStatus(Integer memID, Integer coupStatus) {
		List<MemCouponVO> list = new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO = null;
		
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(FindMemCouponByStatus)){
			ps.setInt(1, memID);
			ps.setInt(2, coupStatus);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				memCouponVO = new MemCouponVO();
				memCouponVO.setCoupNo(rs.getInt("CoupNo"));
				memCouponVO.setMemID(rs.getInt("MemID"));
				memCouponVO.setCoupTypeNo(rs.getInt("CoupTypeNo"));
				memCouponVO.setCoupStatus(rs.getInt("CoupStatus"));
				memCouponVO.setCoupExpDate(rs.getTimestamp("CoupExpDate"));
				memCouponVO.setCoupGetDate(rs.getTimestamp("CoupGetDate"));
				list.add(memCouponVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<MemCouponVO> findMemCouponByCoupTypeNo(Integer memID, Integer coupTypeNo) {
		List<MemCouponVO> list = new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO = null;
		
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(FindMemCouponByCoupTypeNo)){
			ps.setInt(1, memID);
			ps.setInt(2, coupTypeNo);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				memCouponVO = new MemCouponVO();
				memCouponVO.setCoupNo(rs.getInt("CoupNo"));
				memCouponVO.setMemID(rs.getInt("MemID"));
				memCouponVO.setCoupTypeNo(rs.getInt("CoupTypeNo"));
				memCouponVO.setCoupStatus(rs.getInt("CoupStatus"));
				memCouponVO.setCoupExpDate(rs.getTimestamp("CoupExpDate"));
				memCouponVO.setCoupGetDate(rs.getTimestamp("CoupGetDate"));
				list.add(memCouponVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<MemCouponVO> getAll() {
		List<MemCouponVO> list = new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO = null;
		
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(GetAll)){
			
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				memCouponVO = new MemCouponVO();
				memCouponVO.setCoupNo(rs.getInt("CoupNo"));
				memCouponVO.setMemID(rs.getInt("MemID"));
				memCouponVO.setCoupTypeNo(rs.getInt("CoupTypeNo"));
				memCouponVO.setCoupStatus(rs.getInt("CoupStatus"));
				memCouponVO.setCoupExpDate(rs.getTimestamp("CoupExpDate"));
				memCouponVO.setCoupGetDate(rs.getTimestamp("CoupGetDate"));
				list.add(memCouponVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		MemCouponJDBCDAO dao = new MemCouponJDBCDAO();
		
//		-- 新增一筆會員優惠券資料
		MemCouponVO memCouponVO1 = new MemCouponVO();
		memCouponVO1.setMemID(11001);
		memCouponVO1.setCoupTypeNo(8);
		memCouponVO1.setCoupStatus(2);
		memCouponVO1.setCoupExpDate(java.sql.Timestamp.valueOf("2022-08-01 00:00:00"));
		dao.insert(memCouponVO1);
				
//		-- 更改 會員擁有的優惠券 資料內容
//		MemCouponVO memCouponVO2 = new MemCouponVO();
//		memCouponVO2.setMemID(11001);
//		memCouponVO2.setCoupTypeNo(9);
//		memCouponVO2.setCoupStatus(3);
//		memCouponVO2.setCoupExpDate(java.sql.Timestamp.valueOf("2022-08-01 00:00:00"));
//		memCouponVO2.setCoupGetDate(java.sql.Timestamp.valueOf("2022-07-01 00:00:00"));
//		memCouponVO2.setCoupNo(27010);
//		dao.update(memCouponVO2);
		
//		-- 找出 某個會員 擁有的所有優惠券
//		List<MemCouponVO> list = dao.findMemCouponByMemID(11001);
//		for(MemCouponVO m : list) {
//			System.out.println(m.getCoupNo() + ",");
//			System.out.println(m.getMemID() + ",");
//			System.out.println(m.getCoupTypeNo() + ",");
//			System.out.println(m.getCoupStatus() + ",");
//			System.out.println(m.getCoupExpDate() + ",");
//			System.out.println(m.getCoupGetDate() );
//			System.out.println();
//			
//		}
		
				
//		-- 找出 某個會員 已使用(未使用、過期的)的所有優惠券
//		List<MemCouponVO> list2 = dao.findMemCouponByStatus(11001, 0);
//		for(MemCouponVO m : list2) {
//			System.out.println(m.getCoupNo() + ",");
//			System.out.println(m.getMemID() + ",");
//			System.out.println(m.getCoupTypeNo() + ",");
//			System.out.println(m.getCoupStatus() + ",");
//			System.out.println(m.getCoupExpDate() + ",");
//			System.out.println(m.getCoupGetDate() );
//			System.out.println();
//			
//		}
		
		
//		-- 找出 某個會員 擁有的某種優惠券
//		List<MemCouponVO> list3 = dao.findMemCouponByCoupTypeNo(11001, 1);
//		for(MemCouponVO m : list3) {
//			System.out.println(m.getCoupNo() + ",");
//			System.out.println(m.getMemID() + ",");
//			System.out.println(m.getCoupTypeNo() + ",");
//			System.out.println(m.getCoupStatus() + ",");
//			System.out.println(m.getCoupExpDate() + ",");
//			System.out.println(m.getCoupGetDate() );
//			System.out.println();
//			
//		}
//		-- 找出 某個會員 剩3天要到期的優惠券
		
//		-- 找出 所有會員 擁有的優惠券
//		List<MemCouponVO> list5 = dao.getAll();
//		for(MemCouponVO m : list5) {
//			System.out.println(m.getCoupNo() + ",");
//			System.out.println(m.getMemID() + ",");
//			System.out.println(m.getCoupTypeNo() + ",");
//			System.out.println(m.getCoupStatus() + ",");
//			System.out.println(m.getCoupExpDate() + ",");
//			System.out.println(m.getCoupGetDate() );
//			System.out.println();
			
//		}
	}

	@Override
	public void updateStatus1(Integer coupNo, Integer coupStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStatusRoutine(Integer coupNo, Integer coupStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemCouponVO getOne(Integer coupNo) {
		// TODO Auto-generated method stub
		return null;
	}



}
