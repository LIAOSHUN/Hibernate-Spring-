package com.product_type_details.model;

import java.util.*;
import java.sql.*;

public class ProductTypeDetailsJDBCDAO implements ProductTypeDetailsDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "871104";

	private static final String INSERT_STMT = 
			"INSERT INTO producttypedetails (PdID,PdTypeID) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT PdID,PdTypeID FROM producttypedetails order by PdID";
		private static final String GET_ONE_STMT = 
			"SELECT PdID,PdTypeID FROM producttypedetails where PdID = ?";
		private static final String DELETE = 
			"DELETE FROM producttypedetails where PdID = ?";
		private static final String UPDATE = 
			"UPDATE producttypedetails set PdTypeID=? where PdID = ?";

	@Override
	public void insert(ProductTypeDetailsVO productTypeDetailsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productTypeDetailsVO.getPdID());
			pstmt.setInt(2, productTypeDetailsVO.getPdTypeID());


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
	public void update(ProductTypeDetailsVO productTypeDetailsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productTypeDetailsVO.getPdTypeID());
			pstmt.setInt(2, productTypeDetailsVO.getPdID());
		

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
	public void delete(Integer PdID,Integer PdTypeID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, PdID);
			pstmt.setInt(2, PdTypeID);

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
	public ProductTypeDetailsVO findByPrimaryKey(Integer PdID,Integer PdTypeID) {

		ProductTypeDetailsVO productTypeDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, PdID);
			pstmt.setInt(2, PdTypeID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productTypeDetailsVO = new ProductTypeDetailsVO();
				productTypeDetailsVO.setPdID(rs.getInt("PdID"));
				productTypeDetailsVO.setPdTypeID(rs.getInt("PdTypeID"));
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
		return productTypeDetailsVO;
	}

	@Override
	public List<ProductTypeDetailsVO> getAll() {
		List<ProductTypeDetailsVO> list = new ArrayList<ProductTypeDetailsVO>();
		ProductTypeDetailsVO productTypeDetailsVO = null;

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
				productTypeDetailsVO = new ProductTypeDetailsVO();
				productTypeDetailsVO.setPdID(rs.getInt("PdID"));
				productTypeDetailsVO.setPdTypeID(rs.getInt("PdTypeID"));
				list.add(productTypeDetailsVO); // Store the row in the list
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

		ProductTypeDetailsJDBCDAO dao = new ProductTypeDetailsJDBCDAO();

		// �s�W
		ProductTypeDetailsVO productTypeDetailsVO1 = new ProductTypeDetailsVO();
		productTypeDetailsVO1.setPdID(21001);
		productTypeDetailsVO1.setPdTypeID(15);
		dao.insert(productTypeDetailsVO1);

		// �ק�
		ProductTypeDetailsVO productTypeDetailsVO2 = new ProductTypeDetailsVO();
		productTypeDetailsVO2.setPdID(7001);
		productTypeDetailsVO2.setPdTypeID(20);
		dao.update(productTypeDetailsVO2);

		// �R��
		dao.delete(7014,20);

		// �d��
		ProductTypeDetailsVO productTypeDetailsVO3 = dao.findByPrimaryKey(7001,20);
		System.out.print(productTypeDetailsVO3.getPdID() + ",");
		System.out.print(productTypeDetailsVO3.getPdTypeID() + ",");
		System.out.println("---------------------");

		// �d��
		List<ProductTypeDetailsVO> list = dao.getAll();
		for (ProductTypeDetailsVO aproductTypeDetailsVO : list) {
			System.out.print(aproductTypeDetailsVO.getPdID() + ",");
			System.out.print(aproductTypeDetailsVO.getPdTypeID() + ",");
			System.out.println();
		}
	}
}