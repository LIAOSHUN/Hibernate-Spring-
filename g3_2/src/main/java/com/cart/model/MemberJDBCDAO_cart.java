package com.cart.model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.member.model.MemberVO;

public class MemberJDBCDAO_cart  {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CGA103g3";

	private static final String INSERT_STMT = "INSERT INTO member (GradeID,MemName,MemAccount,MemPassword,MemGender,MemPh,MemEmail,MemAddress,MemBirthday,MemCard,MemVio,MemStatus) "
			+ "VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT MemID,GradeID,MemName,MemAccount,MemPassword,MemGender,MemPh,MemEmail,MemAddress,MemBirthday,MemCard,MemVio,MemStatus FROM member order by MemID";
	private static final String GET_ONE_STMT = "SELECT MemID,GradeID,MemName,MemAccount,MemPassword,MemGender,MemPh,MemEmail,MemAddress,MemBirthday,MemCard,MemVio,MemStatus FROM member where MemID = ?";
	private static final String DELETE = "DELETE FROM member where MemID = ?";
	private static final String UPDATE = "UPDATE member set GradeID=?, MemName=?, MemAccount=?, MemPassword=?, MemGender=?, MemPh=? ,MemEmail=?,MemAddress=?,"
			+ "MemBirthday=?,MemCard=?,MemVio=?=?,MemStatus=? where MemID = ?";
    private static final String INSERT_REGISTER ="INSERT INTO member(MemName,MemAccount,MemPassword,MemGender,MemPh,MemEmail,MemAddress,MemBirthday,Memcard) "+"VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_MEMBER_ACCOUNT = "SELECT MemAccount FROM member WHERE MemAccount=?";
	public void insert(MemberVO memberVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memberVO.getGradeID());
			pstmt.setString(2, memberVO.getMemName());
			pstmt.setString(3, memberVO.getMemAccount());
			pstmt.setString(4, memberVO.getMemPassWord());
			pstmt.setString(5, memberVO.getMemGender());
			pstmt.setString(6, memberVO.getMemPh());
			pstmt.setString(7, memberVO.getMemEmail());
			pstmt.setString(8, memberVO.getMemAddress());
			pstmt.setDate(9, memberVO.getMemBirthday());
			pstmt.setBytes(10, memberVO.getMemCard());
			pstmt.setInt(11, memberVO.getMemVio());
			pstmt.setInt(13, memberVO.getMemStatus());
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

	public void update(MemberVO memberVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, memberVO.getGradeID());
			pstmt.setString(2, memberVO.getMemName());
			pstmt.setString(3, memberVO.getMemAccount());
			pstmt.setString(4, memberVO.getMemPassWord());
			pstmt.setString(5, memberVO.getMemGender());
			pstmt.setString(6, memberVO.getMemPh());
			pstmt.setString(7, memberVO.getMemEmail());
			pstmt.setString(8, memberVO.getMemAddress());
			pstmt.setDate(9, memberVO.getMemBirthday());
			pstmt.setBytes(10, memberVO.getMemCard());
			pstmt.setInt(11, memberVO.getMemVio());
			pstmt.setInt(13, memberVO.getMemStatus());
			pstmt.setInt(14, memberVO.getMemID());
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

	public void delete(Integer memID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memID);

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

	public MemberVO_cart findByPrimaryKey(Integer memID) {
		// TODO Auto-generated method stub
		MemberVO_cart memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memberVO = new MemberVO_cart();
				memberVO.setMemID(rs.getInt("MemID"));
				memberVO.setGradeID(rs.getInt("GradeID"));
				memberVO.setMemName(rs.getString("MemName"));
				memberVO.setMemAccount(rs.getString("MemAccount"));
				memberVO.setMemPassWord(rs.getString("MemPassword"));
				memberVO.setMemGender(rs.getString("MemGender"));
				memberVO.setMemPh(rs.getString("MemPh"));
				memberVO.setMemEmail(rs.getString("MemEmail"));
				memberVO.setMemAddress(rs.getString("MemAddress"));
				memberVO.setMemBirthday(rs.getDate("MemBirthday"));
				memberVO.setMemCard(rs.getBytes("MemCard"));
				memberVO.setMemVio(rs.getInt("MemVio"));
				memberVO.setMemStatus(rs.getInt("MemStatus"));

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
		return memberVO;
	}

	public List<MemberVO> getAll() {
		// TODO Auto-generated method stub
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				memberVO = new MemberVO();
				memberVO.setMemID(rs.getInt("MemID"));
				memberVO.setGradeID(rs.getInt("GradeID"));
				memberVO.setMemName(rs.getString("MemName"));
				memberVO.setMemAccount(rs.getString("MemAccount"));
				memberVO.setMemPassWord(rs.getString("MemPassword"));
				memberVO.setMemGender(rs.getString("MemGender"));
				memberVO.setMemPh(rs.getString("MemPh"));
				memberVO.setMemEmail(rs.getString("MemEmail"));
				memberVO.setMemAddress(rs.getString("MemAddress"));
				memberVO.setMemBirthday(rs.getDate("MemBirthday"));
				memberVO.setMemCard(rs.getBytes("MemCard"));
				memberVO.setMemVio(rs.getInt("MemVio"));
				memberVO.setMemStatus(rs.getInt("MemStatus"));

				list.add(memberVO); // Store the row in the list
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

	public void registerInsert(MemberVO memberVO){

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_REGISTER);

			pstmt.setString(1, memberVO.getMemName());
			pstmt.setString(2, memberVO.getMemAccount());
			pstmt.setString(3, memberVO.getMemPassWord());
			pstmt.setString(4, memberVO.getMemGender());
			pstmt.setString(5, memberVO.getMemPh());
			pstmt.setString(6, memberVO.getMemEmail());
			pstmt.setString(7, memberVO.getMemAddress());
			pstmt.setDate(8, memberVO.getMemBirthday());
			pstmt.setBytes(9, memberVO.getMemCard());
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
	
	
	public MemberVO selectMemAccount(String memAccount) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(SELECT_MEMBER_ACCOUNT);

			pstmt.setString(1, memAccount);

			rs = pstmt.executeQuery();
				while (rs.next()) {
					//				
					memberVO = new MemberVO();
					memberVO.setMemID(rs.getInt("MemID"));
					memberVO.setGradeID(rs.getInt("GradeID"));
					memberVO.setMemName(rs.getString("MemName"));
					memberVO.setMemAccount(rs.getString("MemAccount"));
					memberVO.setMemPassWord(rs.getString("MemPassword"));
					memberVO.setMemGender(rs.getString("MemGender"));
					memberVO.setMemPh(rs.getString("MemPh"));
					memberVO.setMemEmail(rs.getString("MemEmail"));
					memberVO.setMemAddress(rs.getString("MemAddress"));
					memberVO.setMemBirthday(rs.getDate("MemBirthday"));
					memberVO.setMemCard(rs.getBytes("MemCard"));
					memberVO.setMemVio(rs.getInt("MemVio"));
					memberVO.setMemStatus(rs.getInt("MemStatus"));
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
		return memberVO;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
//
		MemberJDBCDAO_cart dao = new MemberJDBCDAO_cart();
//
		// 新增
//		MemberVO memberVO1 = new MemberVO();
//		memberVO1.setGradeID(1);
//		memberVO1.setMemName("rest");
//		memberVO1.setMemAccount("sun");
//		memberVO1.setMemPassWord("12345");
//		memberVO1.setMemGender("M");
//		memberVO1.setMemPh("0987654321");
//		memberVO1.setMemEmail("gdoldoi@hgslrghj");
//		memberVO1.setMemAddress("桃園市中壢區復興路46號9樓");
//		memberVO1.setMemBirthday(java.sql.Date.valueOf("1987-11-30"));
//		memberVO1.setMemCard(null);
//		memberVO1.setMemVio(0);
//		memberVO1.setMemStatus( 0);
//		dao.insert(memberVO1);

////		// 修改
//		MemberVO memberVO1 = new MemberVO();
//		memberVO1.setGradeID(1);
//		memberVO1.setMemName("abc");
//		memberVO1.setMemAccount("xyz");
//		memberVO1.setMemPassWord("12345");
//		memberVO1.setMemGender("M");
//		memberVO1.setMemPh("0987654321");
//		memberVO1.setMemEmail("gdoldoi@hgslrghj");
//		memberVO1.setMemAddress("桃園市中壢區復興路46號9樓");
//		memberVO1.setMemBirthday(java.sql.Date.valueOf("1987-11-30"));
//		memberVO1.setMemCard(null);
//		memberVO1.setMemVio(1);
//		memberVO1.setMemCredit(null);
//		memberVO1.setMemStatus( 0);
//		memberVO1.setMemID(11020);
//		dao.update(memberVO1);
//
//
//		// 刪除
//		dao.delete(11006);
//
//		// 查詢
//		MemberVO memberVO3 = dao.findByPrimaryKey(11003);
//		System.out.print(memberVO3.getGradeID()+ ",");
//		System.out.print(memberVO3.getMemName() + ",");
//		System.out.print(memberVO3.getMemPassWord() + ",");
//		System.out.print(memberVO3.getMemGender() + ",");
//		System.out.print(memberVO3.getMemPh() + ",");
//		System.out.print(memberVO3.getMemEmail() + ",");
//		System.out.print(memberVO3.getMemAddress() + ",");
//		System.out.print(memberVO3.getMemBirthday() + ",");
//		System.out.print(memberVO3.getMemCard() + ",");
//		System.out.print(memberVO3.getMemVio() + ",");
//		System.out.print(memberVO3.getMemStatus());
//		System.out.println("---------------------");
		
//		// 查詢
		List<MemberVO> list = dao.getAll();
		for (MemberVO aMember : list) {
			System.out.print(aMember.getMemID()+",");
			System.out.print(aMember.getGradeID()+ ",");
			System.out.print(aMember.getMemName() + ",");
			System.out.print(aMember.getMemAccount() + ",");
			System.out.print(aMember.getMemPassWord() + ",");
			System.out.print(aMember.getMemGender() + ",");
			System.out.print(aMember.getMemPh() + ",");
			System.out.print(aMember.getMemEmail() + ",");
			System.out.print(aMember.getMemAddress() + ",");
			System.out.print(aMember.getMemBirthday() + ",");
			System.out.print(aMember.getMemCard() + ",");
			System.out.print(aMember.getMemVio() + ",");
			System.out.print(aMember.getMemStatus());
			System.out.println();
//		}
	
	}
	}


}
