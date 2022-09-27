package com.product_type.model;

import java.util.*;
import java.sql.*;

public class ProductTypeJDBCDAO implements ProductTypeDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "871104";

	private static final String INSERT_STMT = 
			"INSERT INTO producttype (PdTypeName) VALUES (?)";
		private static final String GET_ALL_STMT = 
			"SELECT PdTypeID,PdTypeName FROM producttype order by PdTypeID";
		private static final String GET_ONE_STMT = 
			"SELECT PdTypeID,PdTypeName FROM producttype where PdTypeID = ?";
		private static final String DELETE = 
			"DELETE FROM producttype where PdTypeID = ?";
		private static final String UPDATE = 
			"UPDATE producttype set PdTypeName=? where PdTypeID = ?";

	@Override
	public void insert(ProductTypeVO productTypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productTypeVO.getPdTypeName());


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
	public void update(ProductTypeVO productTypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productTypeVO.getPdTypeName());
			pstmt.setInt(2, productTypeVO.getPdTypeID() );


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
	public void delete(Integer PdTypeID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, PdTypeID);

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
	public ProductTypeVO findByPrimaryKey(Integer PdTypeID) {

		ProductTypeVO productTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, PdTypeID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productTypeVO = new ProductTypeVO();
				productTypeVO.setPdTypeID(rs.getInt("PdTypeID"));
				productTypeVO.setPdTypeName(rs.getString("PdTypeName"));
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
		return productTypeVO;
	}

	@Override
	public List<ProductTypeVO> getAll() {
		List<ProductTypeVO> list = new ArrayList<ProductTypeVO>();
		ProductTypeVO productTypeVO = null;

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
				productTypeVO = new ProductTypeVO();
				productTypeVO.setPdTypeID(rs.getInt("PdTypeID"));
				productTypeVO.setPdTypeName(rs.getString("PdTypeName"));
				list.add(productTypeVO); // Store the row in the list
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

		ProductTypeJDBCDAO dao = new ProductTypeJDBCDAO();

		// �s�W
//		ProductTypeVO productTypeVO1 = new ProductTypeVO();
//		productTypeVO1.setPdTypeName("單人");
//		dao.insert(productTypeVO1);

		// �ק�
		ProductTypeVO productTypeVO2 = new ProductTypeVO();
		productTypeVO2.setPdTypeID(41002);
		productTypeVO2.setPdTypeName("8人以上");
		dao.update(productTypeVO2);

		// �R��
//		dao.delete(7014);

		// �d��
//		ProductTypeVO productTypeVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(productTypeVO3.getPdTypeID() + ",");
//		System.out.print(productTypeVO3.getPdTypeName() + ",");
//		System.out.println("---------------------");

		// �d��
		List<ProductTypeVO> list = dao.getAll();
		for (ProductTypeVO aproductTypeVO : list) {
			System.out.print(aproductTypeVO.getPdTypeID() + ",");
			System.out.print(aproductTypeVO.getPdTypeName() + ",");
			System.out.println();
		}
	}
}