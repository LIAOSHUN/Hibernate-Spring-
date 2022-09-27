package com.productimg.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;

public class ProductImgDAO implements ProductImgDAO_interface {

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
		"INSERT INTO productimg (PdImg,PdImgName) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT PdImgId,PdId,PdImg,PdImgName FROM productimg order by PdImgId";
	private static final String GET_ONE_STMT = 
		"SELECT PdImgId,PdId,PdImg,PdImgName FROM productimg where PdImgId = ?";
	private static final String DELETE = 
		"DELETE FROM productimg where PdImgId = ?";
	private static final String UPDATE = 
		"UPDATE productimg set PdImg=?, PdImgName=? where PdImgId = ?";

	@Override
	public void insert(ProductImgVO productImgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
//			"INSERT INTO productimg (PdImg,PdImgName) VALUES (?, ?)";
			pstmt.setBytes(1, productImgVO.getPdImg());
			pstmt.setString(2, productImgVO.getPdImgName());

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
	public void update(ProductImgVO productImgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
//			"UPDATE productimg set PdImg=?, PdImgName=? where PdImgId = ?";
			pstmt.setBytes(1, productImgVO.getPdImg());
			pstmt.setString(2, productImgVO.getPdImgName());
			pstmt.setInt(3, productImgVO.getPdImgID());


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
	public void delete(Integer pdImgId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pdImgId);

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
	public ProductImgVO findByPrimaryKey(Integer pdImgId) {

		ProductImgVO productImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pdImgId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productImgVO = new ProductImgVO();
				productImgVO.setPdImgID(rs.getInt("pdImgID"));
				productImgVO.setPdID(rs.getInt("pdID"));
				productImgVO.setPdImg(rs.getBytes("pdImg"));
				productImgVO.setPdImgName(rs.getString("pdImgName"));
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
		return productImgVO;
	}

	@Override
	public List<ProductImgVO> getAll() {
		List<ProductImgVO> list = new ArrayList<ProductImgVO>();
		ProductImgVO productImgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				productImgVO = new ProductImgVO();
				productImgVO.setPdImgID(rs.getInt("pdImgID"));
				productImgVO.setPdID(rs.getInt("pdID"));
				productImgVO.setPdImg(rs.getBytes("pdImg"));
				productImgVO.setPdImgName(rs.getString("pdImgName"));
				list.add(productImgVO); // Store the row in the list
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