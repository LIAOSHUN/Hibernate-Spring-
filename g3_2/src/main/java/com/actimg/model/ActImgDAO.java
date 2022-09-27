package com.actimg.model;

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

public class ActImgDAO implements ActImgDAO_interface {
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
			"insert into actimg (ActID, ActImgFile) " 
			+ "values (?, ?)";
	private static final String GET_ALL_STMT = 
			"select ActImgID, ActID, ActImgFile " 
			+ "from actimg order by ActID";
	private static final String GET_ONE_STMT = 
			"select ActImgID, ActID, ActImgFile " 
			+ "from actimg where ActImgID = ?";
	private static final String DELETE = 
			"delete from actimg where ActImgID = ?";
	private static final String UPDATE = 
			"update actimg set ActID=?, ActImgFile=? where ActImgID = ?";
	@Override
	public void insert(ActImgVO actImgVO) {
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);) {
			
			pstmt.setInt(1, actImgVO.getActID());
			pstmt.setBytes(2, actImgVO.getActImgFile());
			pstmt.executeUpdate();

		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}	
	}
	
	@Override
	public void update(ActImgVO actImgVO) {
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE);) {
			
			pstmt.setInt(1, actImgVO.getActID());
			pstmt.setBytes(2, actImgVO.getActImgFile());
			pstmt.setInt(3, actImgVO.getActImgID());
			pstmt.executeUpdate();
			
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
	}
	
	@Override
	public void delete(Integer actImgID) {
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(DELETE);) {
			pstmt.setInt(1, actImgID);
			pstmt.executeUpdate();
			
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		
	}
	@Override
	public ActImgVO findByPrimaryKey(Integer actImgID) {
		ActImgVO actImgVO = null;
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);) {
			
			pstmt.setInt(1, actImgID);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// actImgVo 也稱為 Domain objects
				actImgVO = new ActImgVO();
				actImgVO.setActImgID(rs.getInt("actImgID"));
				actImgVO.setActID(rs.getInt("actID"));
				actImgVO.setActImgFile(rs.getBytes("actImgFile"));				
			}
		// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return actImgVO;
	}
	
	@Override
	public List<ActImgVO> getAll() {
		List<ActImgVO> list = new ArrayList<ActImgVO>();
		ActImgVO actImgVO = null;		
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);) {
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				actImgVO = new ActImgVO();
				actImgVO.setActImgID(rs.getInt("actImgID"));
				actImgVO.setActID(rs.getInt("actID"));
				actImgVO.setActImgFile(rs.getBytes("actImgFile"));
				list.add(actImgVO); // Store the row in the list
			}
		// Handle any SQL errors	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return list;
	}
	@Override
	public void insertfromAct(ActImgVO actImgVO, Connection con) {
		PreparedStatement pstmt = null;
		try  {
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, actImgVO.getActID());
			pstmt.setBytes(2, actImgVO.getActImgFile());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-actImg");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}		
	}
}
