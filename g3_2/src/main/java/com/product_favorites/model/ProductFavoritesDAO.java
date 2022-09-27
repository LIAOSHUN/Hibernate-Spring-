package com.product_favorites.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductFavoritesDAO implements ProductFavoritesDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO productfavorites (PdID,MemID,PdFavDate) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT PdID,MemID,PdFavDate FROM productfavorites order by MemID";
	private static final String GET_ONE_STMT = 
		"SELECT PdID,MemID,PdFavDate FROM productfavorites where MemID = ?";
	private static final String DELETE = 
		"DELETE FROM productfavorites where MemID = ?";
	private static final String UPDATE = 
		"UPDATE productfavorites set PdID=?, MemID=?, PdFavDate=? where MemID = ?";

	@Override
	public void insert(ProductFavoritesVO productFavoritesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productFavoritesVO.getPdID());
			pstmt.setInt(2, productFavoritesVO.getMemID());
			pstmt.setDate(3, productFavoritesVO.getPdFavDate());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ProductFavoritesVO productFavoritesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productFavoritesVO.getPdID());
			pstmt.setInt(2, productFavoritesVO.getMemID());
			pstmt.setDate(3, productFavoritesVO.getPdFavDate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer PdId,Integer MemId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, PdId);
			pstmt.setInt(2, MemId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ProductFavoritesVO findByPrimaryKey(Integer PdId,Integer MemId) {

		ProductFavoritesVO productFavoritesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, PdId);
			pstmt.setInt(2, MemId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productFavoritesVO = new ProductFavoritesVO();
				productFavoritesVO.setPdID(rs.getInt("PdId"));
				productFavoritesVO.setMemID(rs.getInt("MemId"));
				productFavoritesVO.setPdFavDate(rs.getDate("PdFavDate"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return productFavoritesVO;
	}

	@Override
	public List<ProductFavoritesVO> getAll() {
		List<ProductFavoritesVO> list = new ArrayList<ProductFavoritesVO>();
		ProductFavoritesVO productFavoritesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				productFavoritesVO = new ProductFavoritesVO();
				productFavoritesVO.setPdID(rs.getInt("PdId"));
				productFavoritesVO.setMemID(rs.getInt("MemId"));
				productFavoritesVO.setPdFavDate(rs.getDate("PdFavDate"));
				list.add(productFavoritesVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}