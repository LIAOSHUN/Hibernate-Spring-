package com.cart.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.membergrade.model.MemberGradeVO;


public class MemberGradeJDBCDAO_cart   {

	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CGA103g3";
	
	private static final String INSERT_STMT = "INSERT INTO membergrade (gradeID,gradeName,gradeDiscount) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT gradeID,gradeName,gradeDiscount FROM membergrade order by gradeID";
	private static final String GET_ONE_STMT = "SELECT gradeID,gradeName,gradeDiscount FROM membergrade where gradeID = ?";
	private static final String DELETE = "DELETE FROM membergrade where gradeID = ?";
	private static final String UPDATE = "UPDATE membergrade set gradeName=?,gradeDiscount=? where gradeID = ?";

	
	public void insert(MemberGradeVO membergradeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, membergradeVO.getGradeID());
			pstmt.setString(2, membergradeVO.getGradeName());
			pstmt.setDouble(3, membergradeVO.getGradeDiscount());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	
	public void update(MemberGradeVO membergradeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, membergradeVO.getGradeName());
			pstmt.setDouble(2, membergradeVO.getGradeDiscount());
			pstmt.setInt(3, membergradeVO.getGradeID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	
	public void delete(Integer gradeID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, gradeID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	
	public MemberGradeVO findByPrimaryKey(Integer gradeID) {
		// TODO Auto-generated method stub
		MemberGradeVO membergradeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gradeID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				membergradeVO = new MemberGradeVO();
				membergradeVO.setGradeID(rs.getInt("gradeID"));
				membergradeVO.setGradeName(rs.getNString("GradeName"));
				membergradeVO.setGradeDiscount(rs.getDouble("GradeDiscount"));

			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return membergradeVO;
	}

	
	public List<MemberGradeVO> getAll() {
		// TODO Auto-generated method stub
		List<MemberGradeVO> list = new ArrayList<MemberGradeVO>();
		MemberGradeVO membergradeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				membergradeVO = new MemberGradeVO();
				membergradeVO.setGradeID(rs.getInt("gradeID"));
				membergradeVO.setGradeName(rs.getNString("GradeName"));
				membergradeVO.setGradeDiscount(rs.getDouble("GradeDiscount"));

				list.add(membergradeVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//
		MemberGradeJDBCDAO_cart dao = new MemberGradeJDBCDAO_cart();
//
//		// 新增
//		MemberGradeVO membergradeVO1 = new MemberGradeVO();
//		membergradeVO1.setGradeID(5);
//		membergradeVO1.setGradeName("翡翠會員");
//		membergradeVO1.setGradeDiscount(7.5);
//	
//		dao.insert(membergradeVO1);
//      //修改
//		MemberGradeVO membergradeVO2 = new MemberGradeVO();
//		membergradeVO2.setGradeName("翡翠會員");
//		membergradeVO2.setGradeDiscount(0.5);
//		membergradeVO2.setGradeID(5);
//		dao.update(membergradeVO2);
//		// 刪除
//		dao.delete(5);
//
//		// 查詢
//		MemberGradeVO membergradVO3 = dao.findByPrimaryKey(1);
//		System.out.print(membergradVO3.getGradeID() + ",");
//		System.out.print(membergradVO3.getGradeName() + ",");
//		System.out.println(membergradVO3.getGradeDiscount());
//		System.out.println("---------------------");
//		
//		// 查詢
//		List<MemberGradeVO> list = dao.getAll();
//		for (MemberGradeVO membergradeVO4 : list) {
//		System.out.print(membergradeVO4.getGradeID() + ",");
//		System.out.print(membergradeVO4.getGradeName() + ",");
//		System.out.println(membergradeVO4.getGradeDiscount());
//		System.out.println("---------------------");
//		 }
//	
	}

}
