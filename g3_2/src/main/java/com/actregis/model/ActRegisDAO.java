package com.actregis.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActRegisDAO implements ActRegisDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
		"insert into actregistered (MemID, ActID, RegisTime, ActNum, ActFee, FeeStatus, "
		+ "RegisStatus, ActReview, Satisfaction, ReviewDate) "
		+ "values (?, ?, Now(), ?, ?, ?, ?, ?, ?, Now())";
	private static final String GET_ALL_STMT = 
		"select MemID, ActID, RegisTime, ActNum, ActFee, FeeStatus, "
		+ "RegisStatus, ActReview, Satisfaction, ReviewDate "
		+ "from actregistered order by ActID";
	private static final String GET_ONEACT_STMT = 
		"select MemID, ActID, RegisTime, ActNum, ActFee, FeeStatus, "
		+ "RegisStatus, ActReview, Satisfaction, ReviewDate "
		+ "from actregistered where ActID = ?";
	private static final String GET_ONEMEM_STMT = 
		"select MemID, ActID, RegisTime, ActNum, ActFee, FeeStatus, "
		+ "RegisStatus, ActReview, Satisfaction, ReviewDate "
		+ "from actregistered where MemID = ?";
	private static final String GET_ONE_FORUPDATE = 
		"select MemID, ActID, RegisTime, ActNum, ActFee, FeeStatus, "
		+ "RegisStatus, ActReview, Satisfaction, ReviewDate "
		+ "from actregistered where MemID = ? and ActID = ?";
	private static final String UPDATE = 
		"update actregistered set RegisTime=Now(), ActNum=?, ActFee=?, FeeStatus=?, "
		+ "RegisStatus=?, ActReview=?, Satisfaction=?, ReviewDate=Now() where MemID = ? and ActID = ?";
	
	@Override
	public void insert(ActRegisVO actRegisVO) {
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);) {
			
			pstmt.setInt(1, actRegisVO.getMemID());
			pstmt.setInt(2, actRegisVO.getActID());
			pstmt.setInt(3, actRegisVO.getActNum());
			pstmt.setInt(4, actRegisVO.getActFee());
			pstmt.setInt(5, actRegisVO.getFeeStatus());
			pstmt.setInt(6, actRegisVO.getRegisStatus());
			pstmt.setString(7, actRegisVO.getActReview());
			pstmt.setInt(8, actRegisVO.getSatisfaction());

			pstmt.executeUpdate();
			
		// Handle any SQL errors	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}	
	}
	
	@Override
	public void update(ActRegisVO actRegisVO) {
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
			
			pstmt.setInt(1, actRegisVO.getActNum());
			pstmt.setInt(2, actRegisVO.getActFee());
			pstmt.setInt(3, actRegisVO.getFeeStatus());
			pstmt.setInt(4, actRegisVO.getRegisStatus());
			pstmt.setString(5, actRegisVO.getActReview());
			pstmt.setInt(6, actRegisVO.getSatisfaction());
			pstmt.setInt(7, actRegisVO.getMemID());
			pstmt.setInt(8, actRegisVO.getActID());

			pstmt.executeUpdate();
			
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}	
	}
	
	@Override
	public List<ActRegisVO> findByActPrimaryKey(Integer actID) {
		List<ActRegisVO> listRegistered = new ArrayList<ActRegisVO>();
		ActRegisVO actRegisVO = null;		
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ONEACT_STMT)) {
			pstmt.setInt(1, actID);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// actRegisVO 也稱為 Domain objects
				actRegisVO = new ActRegisVO();
				actRegisVO.setMemID(rs.getInt("memID"));
				actRegisVO.setActID(rs.getInt("actID"));
				actRegisVO.setRegisTime(rs.getTimestamp("regisTime"));
				actRegisVO.setActNum(rs.getInt("actNum"));
				actRegisVO.setActFee(rs.getInt("actFee"));
				actRegisVO.setFeeStatus(rs.getInt("feeStatus"));
				actRegisVO.setRegisStatus(rs.getInt("regisStatus"));
				actRegisVO.setActReview(rs.getString("actReview"));
				actRegisVO.setSatisfaction(rs.getInt("satisfaction"));
				actRegisVO.setReviewDate(rs.getDate("reviewDate"));	
				listRegistered.add(actRegisVO); // Store the row in the list
			}
			
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return listRegistered;
	}
	
	@Override
	public List<ActRegisVO> findByMemPrimaryKey(Integer memID) {
		List<ActRegisVO> listMemRegis = new ArrayList<ActRegisVO>();
		ActRegisVO actRegisVO = null;
		
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ONEMEM_STMT)) {
			pstmt.setInt(1, memID);
			ResultSet rs = pstmt.executeQuery();
					
			while (rs.next()) {
				// actRegisVO 也稱為 Domain objects
				actRegisVO = new ActRegisVO();
				actRegisVO.setMemID(rs.getInt("memID"));
				actRegisVO.setActID(rs.getInt("actID"));
				actRegisVO.setRegisTime(rs.getTimestamp("regisTime"));
				actRegisVO.setActNum(rs.getInt("actNum"));
				actRegisVO.setActFee(rs.getInt("actFee"));
				actRegisVO.setFeeStatus(rs.getInt("feeStatus"));
				actRegisVO.setRegisStatus(rs.getInt("regisStatus"));
				actRegisVO.setActReview(rs.getString("actReview"));
				actRegisVO.setSatisfaction(rs.getInt("satisfaction"));
				actRegisVO.setReviewDate(rs.getDate("reviewDate"));	
				listMemRegis.add(actRegisVO); // Store the row in the list
			}
		// Handle any SQL errors	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return listMemRegis;
	}
	@Override
	public ActRegisVO findByPrimaryKey(Integer memID, Integer actID) {
		ActRegisVO actRegisVO = null;
		
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_FORUPDATE)) {
			
			pstmt.setInt(1, memID);
			pstmt.setInt(2, actID);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// actRegisVO 也稱為 Domain objects
				actRegisVO = new ActRegisVO();
				actRegisVO.setMemID(rs.getInt("memID"));
				actRegisVO.setActID(rs.getInt("actID"));
				actRegisVO.setRegisTime(rs.getTimestamp("regisTime"));
				actRegisVO.setActNum(rs.getInt("actNum"));
				actRegisVO.setActFee(rs.getInt("actFee"));
				actRegisVO.setFeeStatus(rs.getInt("feeStatus"));
				actRegisVO.setRegisStatus(rs.getInt("regisStatus"));
				actRegisVO.setActReview(rs.getString("actReview"));
				actRegisVO.setSatisfaction(rs.getInt("satisfaction"));
				actRegisVO.setReviewDate(rs.getDate("reviewDate"));	
			}
			
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return actRegisVO;
	}
	
	@Override
	public List<ActRegisVO> getAll() {
		List<ActRegisVO> list = new ArrayList<ActRegisVO>();
		ActRegisVO actRegisVO = null;
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT)) {
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// actRegisVO 也稱為 Domain objects
				actRegisVO = new ActRegisVO();
				actRegisVO.setMemID(rs.getInt("memID"));
				actRegisVO.setActID(rs.getInt("actID"));
				actRegisVO.setRegisTime(rs.getTimestamp("regisTime"));
				actRegisVO.setActNum(rs.getInt("actNum"));
				actRegisVO.setActFee(rs.getInt("actFee"));
				actRegisVO.setFeeStatus(rs.getInt("feeStatus"));
				actRegisVO.setRegisStatus(rs.getInt("regisStatus"));
				actRegisVO.setActReview(rs.getString("actReview"));
				actRegisVO.setSatisfaction(rs.getInt("satisfaction"));
				actRegisVO.setReviewDate(rs.getDate("reviewDate"));
				list.add(actRegisVO); // Store the row in the list
			}
			
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
		return list;
	}
}
