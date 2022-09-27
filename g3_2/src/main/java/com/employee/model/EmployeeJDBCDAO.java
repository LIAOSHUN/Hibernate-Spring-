package com.employee.model;

//import static basic.util.JdbcConstants.URL;
//import static basic.util.JdbcConstants.USERNAME;
//import static basic.util.JdbcConstants.PASSWORD;
//import static basic.util.JdbcConstants.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJDBCDAO implements EmployeeDAO_interface {

	private static final String INSERT_STMT = 
			"INSERT INTO employee (EmpName,EmpPhone,EmpAvatar,EmpAccount,EmpPassword,EmpHiredate,EmpStatus) VALUES (?, ?, ?, ?, ?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT EmpID,EmpName,EmpPhone,EmpAvatar,EmpAccount,EmpPassword,EmpHiredate,EmpStatus FROM employee order by EmpID";
		private static final String GET_ONE_STMT = 
			"SELECT EmpID,EmpName,EmpPhone,EmpAvatar,EmpAccount,EmpPassword,EmpHiredate,EmpStatus FROM employee where EmpID = ?";
		private static final String DELETE = 
			"DELETE FROM employee where EmpID = ?";
		private static final String UPDATE = 
			"UPDATE employee set EmpName=?,EmpPhone=?,EmpAvatar=?,EmpAccount=?,EmpPassword=?,EmpHiredate=?,EmpStatus=? where EmpID = ?";

	
	

	
	@Override
	public void insert(EmployeeVO employeeVO) {
		// TODO Auto-generated method stub
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, employeeVO.getEmpName());
//			pstmt.setString(2, employeeVO.getEmpPhone());
//			pstmt.setBytes(3, employeeVO.getEmpAvatar());
//			pstmt.setString(4, employeeVO.getEmpAccount());
//			pstmt.setString(5, employeeVO.getEmpPassWord());
//			pstmt.setDate(6, employeeVO.getEmpHireDate());
//			pstmt.setInt(7, employeeVO.getEmpStatus());
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}

	}

	@Override
	public void update(EmployeeVO employeeVO) {
		// TODO Auto-generated method stub

//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, employeeVO.getEmpName());
//			pstmt.setString(2, employeeVO.getEmpPhone());
//			pstmt.setBytes(3, employeeVO.getEmpAvatar());
//			pstmt.setString(4, employeeVO.getEmpAccount());
//			pstmt.setString(5, employeeVO.getEmpPassWord());
//			pstmt.setDate(6, employeeVO.getEmpHireDate());
//			pstmt.setInt(7, employeeVO.getEmpStatus());
//			pstmt.setInt(8, employeeVO.getEmpID());
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
	}

	@Override
	public void delete(Integer empID) {
		// TODO Auto-generated method stub
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, empID);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
	}

	@Override
	public EmployeeVO findByPrimaryKey(Integer empID) {
		// TODO Auto-generated method stub
//		EmployeeVO employeeVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, empID);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				employeeVO = new EmployeeVO();
//				employeeVO.setEmpName(rs.getString("EmpName"));
//				employeeVO.setEmpPhone(rs.getString("EmpPhone"));
//				employeeVO.setEmpAvatar(rs.getBytes("EmpAvatar"));
//				employeeVO.setEmpAccount(rs.getString("EmpAccount"));
//				employeeVO.setEmpPassWord(rs.getString("EmpPassWord"));
//				employeeVO.setEmpHireDate(rs.getDate("EmpHireDate"));
//				employeeVO.setEmpStatus(rs.getInt("EmpStatus"));
//				employeeVO.setEmpID(rs.getInt("empID"));
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
		return null;
	}

	@Override
	public List<EmployeeVO> getAll() {
		return null;
		// TODO Auto-generated method stub
//		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
//		EmployeeVO employeeVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				employeeVO = new EmployeeVO();
//				employeeVO.setEmpID(rs.getInt("empID"));
//				employeeVO.setEmpName(rs.getString("EmpName"));
//				employeeVO.setEmpPhone(rs.getString("EmpPhone"));
//				employeeVO.setEmpAvatar(rs.getBytes("EmpAvatar"));
//				employeeVO.setEmpAccount(rs.getString("EmpAccount"));
//				employeeVO.setEmpPassWord(rs.getString("EmpPassWord"));
//				employeeVO.setEmpHireDate(rs.getDate("EmpHireDate"));
//				employeeVO.setEmpStatus(rs.getInt("EmpStatus"));
//			
//				list.add(employeeVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
	}

	public static void main(String[] args) {
//
//		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();
//
//		// 新增
//		EmployeeVO employeeVO1 = new EmployeeVO();
//		employeeVO1.setEmpName("杰霆林");
//		employeeVO1.setEmpPhone("0912348765");
//		employeeVO1.setEmpAvatar(null);
//		employeeVO1.setEmpAccount("ujmnj");
//		employeeVO1.setEmpPassWord("12345");
//		employeeVO1.setEmpHireDate(java.sql.Date.valueOf("2022-02-22"));
//		employeeVO1.setEmpStatus(0);
//		dao.insert(employeeVO1);

////		// 修改
//		EmployeeVO employeeVO2 = new EmployeeVO();
//		employeeVO2.setEmpID(91004);
//		employeeVO2.setEmpName("杰霆林");
//		employeeVO2.setEmpPhone("1234567890");
//		employeeVO2.setEmpAvatar(null);
//		employeeVO2.setEmpAccount("ujmnj");
//		employeeVO2.setEmpPassWord("54321");
//		employeeVO2.setEmpHireDate(java.sql.Date.valueOf("2022-05-23"));
//		employeeVO2.setEmpStatus(0);
//
//		dao.update(employeeVO2);
//
//		// 刪除
//		dao.delete(91004);
//
//		// 查詢
//		EmployeeVO employeeVO3 = dao.findByPrimaryKey(91001);
//		System.out.print(employeeVO3.getEmpName()+ ",");
//		System.out.print(employeeVO3.getEmpPhone() + ",");
//		System.out.print(employeeVO3.getEmpAvatar() + ",");
//		System.out.print(employeeVO3.getEmpAccount() + ",");
//		System.out.print(employeeVO3.getEmpHireDate() + ",");
//		System.out.print(employeeVO3.getEmpStatus());
//		System.out.println("---------------------");
//		
//		// 查詢
//		List<EmployeeVO> list = dao.getAll();
//		for (EmployeeVO employeeVO4 : list) {
//			System.out.print(employeeVO4.getEmpID()+",");
//			System.out.print(employeeVO4.getEmpName()+ ",");
//			System.out.print(employeeVO4.getEmpPhone() + ",");
//			System.out.print(employeeVO4.getEmpAvatar() + ",");
//			System.out.print(employeeVO4.getEmpAccount() + ",");
//			System.out.print(employeeVO4.getEmpHireDate() + ",");
//			System.out.print(employeeVO4.getEmpStatus());
//			System.out.println();
//		}
//	
	}
}
