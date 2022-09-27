package com.product.model;

import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;

public class ProductJDBCDAO implements ProductDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CGA103g3";

	private static final String INSERT_STMT = 
			"INSERT INTO product (PdName,PdPrice,PdAmount,PdDescription,PdStatus,PdStar) VALUES (?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT PdID,PdName,PdPrice,PdAmount,PdDescription,PdStatus,PdStar,PdUpdate FROM product order by PdID";
		private static final String GET_ONE_STMT = 
			"SELECT PdID,PdName,PdPrice,PdAmount,PdDescription,PdStatus,PdStar,PdUpdate FROM product where PdID = ?";
		private static final String DELETE = 
			"DELETE FROM product where PdID = ?";
		private static final String UPDATE = 
			"UPDATE product set PdName=?, PdPrice=?, PdAmount=?, PdDescription=?, PdStatus=?, PdStar=? where PdID = ?";

	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//"INSERT INTO product (PdName,PdPrice,PdAmount,PdDescription,PdStatus,PdStar) VALUES (?, ?, ?, ?, ?, ?)";
			pstmt.setString(1, productVO.getPdName());
			pstmt.setInt(2, productVO.getPdPrice());
			pstmt.setInt(3, productVO.getPdAmount());
			pstmt.setString(4, productVO.getPdDescription());
			pstmt.setInt(5, productVO.getPdStatus());
			pstmt.setInt(6, productVO.getPdStar());
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
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			"UPDATE product set PdName=?, PdPrice=?, PdAmount=?, PdDescription=?, PdStatus=?, PdStar=? where PdID = ?";
			pstmt.setString(1, productVO.getPdName());
			pstmt.setInt(2, productVO.getPdPrice());
			pstmt.setInt(3, productVO.getPdAmount());
			pstmt.setString(4, productVO.getPdDescription());
			pstmt.setInt(5, productVO.getPdStatus());
			pstmt.setInt(6, productVO.getPdStar());
			pstmt.setInt(7, productVO.getPdID());

			
			
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
	public void delete(Integer pdId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pdId);

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
	public ProductVO findByPrimaryKey(Integer pdId) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pdId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productVO = new ProductVO();
				productVO.setPdID(rs.getInt("pdID"));
				productVO.setPdName(rs.getString("pdName"));
				productVO.setPdPrice(rs.getInt("pdPrice"));
				productVO.setPdAmount(rs.getInt("pdAmount"));
				productVO.setPdDescription(rs.getString("pdDescription"));
				productVO.setPdStatus(rs.getInt("pdStatus"));
				productVO.setPdStar(rs.getInt("pdStar"));
				productVO.setPdUpdate(rs.getTimestamp("pdUpdate"));
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

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
				productVO = new ProductVO();
				productVO.setPdID(rs.getInt("pdID"));
				productVO.setPdName(rs.getString("pdName"));
				productVO.setPdPrice(rs.getInt("pdPrice"));
				productVO.setPdAmount(rs.getInt("pdAmount"));
				productVO.setPdDescription(rs.getString("pdDescription"));
				productVO.setPdStatus(rs.getInt("pdStatus"));
				productVO.setPdStar(rs.getInt("pdStar"));
				productVO.setPdUpdate(rs.getTimestamp("pdUpdate"));
				list.add(productVO); // Store the row in the list
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

		ProductJDBCDAO dao = new ProductJDBCDAO();

		// �s�W
		ProductVO productVO1 = new ProductVO();
		productVO1.setPdName("大富翁8+9");
		productVO1.setPdPrice(89);
		productVO1.setPdAmount(89);
		productVO1.setPdDescription("這是89");
		productVO1.setPdStatus(0);
		productVO1.setPdStar(3);
		productVO1.setPdUpdate(Timestamp.valueOf(LocalDateTime.now())
);
		dao.insert(productVO1);
//		"UPDATE product set PdName=?, PdPrice=?, PdAmount=?, PdDescription=?, PdStatus=?, PdStar=? where PdID = ?";

		// �ק�
		ProductVO productVO2 = new ProductVO();
		productVO2.setPdID(21015);
		productVO2.setPdName("吃你媽的飯");
		productVO2.setPdPrice(11);
		productVO2.setPdAmount(11);
		productVO2.setPdDescription("這是你媽");
		productVO2.setPdStatus(0);
		productVO2.setPdStar(3);
		dao.update(productVO2);

		// �R��
		dao.delete(21006);

		// �d�� 沒有打PDUPDATE
		ProductVO productVO3= dao.findByPrimaryKey(21001);
		System.out.print(productVO3.getPdID() + ",");
		System.out.print(productVO3.getPdName() +",");
		System.out.print(productVO3.getPdPrice() + ",");
		System.out.print(productVO3.getPdAmount() + ",");
		System.out.print(productVO3.getPdDescription() + ",");
		System.out.print(productVO3.getPdStatus() + ",");
		System.out.println(productVO3.getPdStar() + ",");
		System.out.println(productVO3.getPdUpdate() + ",");
		System.out.println("---------------------");

		// �d��
		List<ProductVO> list = dao.getAll();
		for (ProductVO aproduct : list) {
			System.out.print(aproduct.getPdID() + ",");
			System.out.print(aproduct.getPdName() + ",");
			System.out.print(aproduct.getPdPrice() + ",");
			System.out.print(aproduct.getPdAmount() + ",");
			System.out.print(aproduct.getPdDescription() + ",");
			System.out.print(aproduct.getPdStatus() + ",");
			System.out.print(aproduct.getPdStar());
			System.out.println(aproduct.getPdUpdate());
			System.out.println();
		}
	}
}