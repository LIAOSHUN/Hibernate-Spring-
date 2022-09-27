package com.actfav.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActFavDAO implements ActFavDAO_interface {
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
			"insert into actfavorites (MemID, ActID, ActFavDate) "
			+ "values (?, ?, Now())";
	private static final String GET_ALL_STMT = 
			"select MemID, ActID, ActFavDate "
			+ "from actfavorites order by MemID";
	private static final String GET_BYMEM = 
			"select MemID, ActID, ActFavDate "
			+ "from actfavorites where MemID = ?";
	private static final String GET_BYJOIN =
			"select f.MemID, f.ActID, a.StoreID, a.ActTitle, a.ActTimeEnd, a.ActDate, f.ActFavDate "
			+ "from activity a JOIN actfavorites f ON a.ActID = f.ActID "
			+ "where f.MemID = ?";
	private static final String GET_ONEFAV_BYMEM = 
			"select MemID, ActID, ActFavDate "
			+ "from actfavorites where MemID = ? and ActID = ?";
	private static final String DELETE = 
			"delete from actfavorites where MemID = ? and ActID = ?";
	private static final String UPDATE = 
			"update actfavorites set ActFavDate=Now() where MemID = ? and ActID = ?";
	
	@Override
	public void insert(ActFavVO actFavVO) {
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);) {
			
			pstmt.setInt(1, actFavVO.getMemID());
			pstmt.setInt(2, actFavVO.getActID());
			pstmt.executeUpdate(); 
			
		// Handle any SQL errors	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
	}
	
	@Override
	public void update(ActFavVO actFavVO) {
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE);) {
			
			pstmt.setInt(1, actFavVO.getMemID());
			pstmt.setInt(2, actFavVO.getActID());
			pstmt.executeUpdate();
			
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
	}
	
	@Override
	public void delete(Integer memID, Integer actID) {
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(DELETE);) {
			
			pstmt.setInt(1, memID);
			pstmt.setInt(2, actID);
			pstmt.executeUpdate();
		
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}	
	}
	
	@Override
	public ActFavVO findOneFavByOneMem(Integer memID, Integer actID) {
		ActFavVO actFavVO = new ActFavVO();
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ONEFAV_BYMEM)) {
			
			pstmt.setInt(1, memID);
			pstmt.setInt(2, actID);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				actFavVO = new ActFavVO();
				actFavVO.setMemID(rs.getInt("memID"));
				actFavVO.setActID(rs.getInt("actID"));
				actFavVO.setActFavDate(rs.getObject("actFavDate", LocalDateTime.class));				
			}
			
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return actFavVO;
	}

	@Override
	public List<ActFavVO> findByPrimaryKey(Integer memID) {
		List<ActFavVO> listFav = new ArrayList<ActFavVO>();
		ActFavVO actFavVO = null;	
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_BYMEM);) {
			
			pstmt.setInt(1, memID);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// actFacVo 也稱為 Domain objects
				actFavVO = new ActFavVO();
				actFavVO.setMemID(rs.getInt("memID"));
				actFavVO.setActID(rs.getInt("actID"));
				actFavVO.setActFavDate(rs.getObject("actFavDate", LocalDateTime.class));
				listFav.add(actFavVO);
			}
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return listFav;
	}
	
	
	
	@Override
	public List<Object> findByActJoinList(Integer memID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActFavVO> getAll() {
		List<ActFavVO> list = new ArrayList<ActFavVO>();
		ActFavVO actFavVO = null;
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);) {
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// actFavVO 也稱為 Domain objects
				actFavVO = new ActFavVO();
				actFavVO.setMemID(rs.getInt("memID"));
				actFavVO.setActID(rs.getInt("actID"));
				actFavVO.setActFavDate(rs.getObject("actFavDate", LocalDateTime.class));
				list.add(actFavVO); // Store the row in the list
			}
			
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
		return list;
	}	
}
