package com.memcoupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.coupontype.model.CouponTypeVO;

public class MemCouponDAO implements MemCouponDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/boardgame");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	-- 新增一筆會員優惠券資料
	private static final String Insert=
		"INSERT INTO memcoupon(MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate) "
		+ "VALUES "
		+ "(?, ?, 0, now(), now())";
			
//	-- 更改 會員擁有的優惠券 資料內容
	private static final String Update=
		"update memcoupon "
		+ "set MemID=?, CoupTypeNo=?, CoupStatus=?, CoupExpDate=?, CoupGetDate=? "
		+ "where CoupNo=?";
//	-- 更改 會員擁有的優惠券 的 使用狀態
	private static final String UpdateStatus=
		"update memcoupon "
		+ "set CoupStatus=? "
		+ "where CoupNo=?";
//	-- 找出 某張優惠券的資訊	
	private static final String GetOne=
			"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
			+ "from memcoupon "
			+ "where CoupNo=?";
	
//	-- 找出 某個會員 擁有的所有優惠券
	private static final String FindMemCouponByMemID=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "where MemID=? "
		+ "order by CoupNo";
		
//	-- 找出 某個會員 已使用(未使用、過期的)的所有優惠券(x)
	private static final String FindMemCouponByStatus=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "where MemID=? and CoupStatus=? "
		+ "order by CoupNo";
	
//	-- 找出 某個會員 擁有的某種優惠券(x)
	private static final String FindMemCouponByCoupTypeNo=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "where MemID=? and CoupTypeNo=? "
		+ "order by CoupNo";
	
	
//	-- 找出 所有會員 擁有的優惠券
	private static final String GetAll=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "order by MemID";
	
	@Override
	public void insert(MemCouponVO memCouponVO) {
		
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(Insert)){
			
			ps.setInt(1, memCouponVO.getMemID());
			ps.setInt(2, memCouponVO.getCoupTypeNo());
	
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MemCouponVO memCouponVO) {
		try(Connection con = ds.getConnection();
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
	
//	-- 更改 會員擁有的優惠券 的 使用狀態(給結帳用，未使用改成已使用)
	public void updateStatus1(Connection con, Integer coupNo, Integer coupStatus) {
		try(
				PreparedStatement ps = con.prepareStatement(UpdateStatus)){
			
			ps.setInt(1, coupStatus);
			ps.setInt(2, coupNo);
			
			int rowcount = ps.executeUpdate();
			System.out.println("更改優惠券狀成功" + rowcount);
			
		} catch (SQLException e) {
			
			System.err.println("rolled back-由-update");
			throw new RuntimeException("rollback error occured. "
				+ e.getMessage());
			
		}
		
	}
//	-- 更改 會員擁有的優惠券 的 使用狀態 (給排程器偵測用)
	@Override
	public void updateStatusRoutine(Integer coupNo, Integer coupStatus) {
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(UpdateStatus)){
			
			ps.setInt(1, coupStatus);
			ps.setInt(2, coupNo);
	
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public MemCouponVO getOne(Integer coupNo) {
		MemCouponVO memCouponVO = null;
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GetOne)){
			ps.setInt(1, coupNo);
		
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				memCouponVO = new MemCouponVO();
				memCouponVO.setCoupNo(rs.getInt("CoupNo"));
				memCouponVO.setMemID(rs.getInt("MemID"));
				memCouponVO.setCoupTypeNo(rs.getInt("CoupTypeNo"));
				memCouponVO.setCoupStatus(rs.getInt("CoupStatus"));
				memCouponVO.setCoupExpDate(rs.getTimestamp("CoupExpDate"));
				memCouponVO.setCoupGetDate(rs.getTimestamp("CoupGetDate"));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memCouponVO;
	}

	
	
	@Override
	public List<MemCouponVO> findMemCouponByMemID(Integer memID) {
		List<MemCouponVO> list = new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO = null;
		
		try (Connection con = ds.getConnection();
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
		
		try (Connection con = ds.getConnection();
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
		
		try (Connection con = ds.getConnection();
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
		
		try (Connection con = ds.getConnection();
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

	@Override
	public void updateStatus1(Integer coupNo, Integer coupStatus) {
		// TODO Auto-generated method stub
		
	}


	


}
