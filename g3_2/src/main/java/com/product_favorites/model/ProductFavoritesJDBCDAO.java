package com.product_favorites.model;

import java.util.*;
import java.sql.*;

public class ProductFavoritesJDBCDAO implements ProductFavoritesDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "871104";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productFavoritesVO.getPdID());
			pstmt.setInt(2, productFavoritesVO.getMemID());
			pstmt.setDate(3, productFavoritesVO.getPdFavDate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productFavoritesVO.getPdID());
			pstmt.setInt(2, productFavoritesVO.getMemID());
			pstmt.setDate(3, productFavoritesVO.getPdFavDate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(Integer PdId,Integer MemId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, PdId);
			pstmt.setInt(2, MemId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public ProductFavoritesVO findByPrimaryKey(Integer PdId,Integer MemId) {

		ProductFavoritesVO productFavoritesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

		ProductFavoritesJDBCDAO dao = new ProductFavoritesJDBCDAO();

		// �s�W
		ProductFavoritesVO productFavoritesVO1 = new ProductFavoritesVO();
		productFavoritesVO1.setPdID(21008);
		productFavoritesVO1.setMemID(210009);
//		productFavoritesVO1.setPdFavDate();
		dao.insert(productFavoritesVO1);

		// �ק�
		ProductFavoritesVO productFavoritesVO2 = new ProductFavoritesVO();
		productFavoritesVO2.setPdID(21010);
		productFavoritesVO2.setMemID(21011);
//		productFavoritesVO2.setPdFavDate("");
		dao.update(productFavoritesVO2);

		// �R��
		dao.delete(21010,21011);

		// �d��
		ProductFavoritesVO productFavoritesVO3 = dao.findByPrimaryKey(21010,21011);
		System.out.print(productFavoritesVO3.getPdID() + ",");
		System.out.print(productFavoritesVO3.getMemID() + ",");
		System.out.print(productFavoritesVO3.getPdFavDate() + ",");
		System.out.println("---------------------");

		// �d��
		List<ProductFavoritesVO> list = dao.getAll();
		for (ProductFavoritesVO aproductFavorites : list) {
			System.out.print(aproductFavorites.getPdID() + ",");
			System.out.print(aproductFavorites.getMemID() + ",");
			System.out.print(aproductFavorites.getPdFavDate() + ",");
			System.out.println();
		}
	}
}