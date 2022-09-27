package com.coupontype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CouponTypeDAO2 implements CouponTypeDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/boardgame");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
//	-- 新增資料
	private static final String Insert=
		"insert into coupontype(CoupName, CoupDiscount, CoupQuantity, CoupDesc, CoupUpd, CoupStart, CoupEnd) "
		+ "values "
		+ "(?, ?, ?, ?, 0, ?, ?)";
//	-- 更改優惠券內容
	private static final String Update=
		"update coupontype "
		+ "set CoupName=?, CoupDiscount=?, CoupQuantity=?, CoupDesc=?, CoupUpd=? "
		+ "where CoupTypeNo=?";
//	-- 更改優惠券庫存
	private static final String UpdateQuantity=
			"update coupontype "
					+ "set CoupQuantity=? "
					+ "where CoupTypeNo=?";
//	-- 更改優惠券為下架
	private static final String UpdateDown=
		"update coupontype "
		+ "set CoupUpd=1 "
		+ "where CoupTypeNo=?";
//	-- 找出某種類型的優惠券
	private static final String FindCouponTypeByType=
		"select CoupTypeNo, CoupName, CoupDiscount, CoupQuantity, CoupDesc, CoupUpd, CoupStart, CoupEnd "
		+ "from coupontype "
		+ "where CoupTypeNo=?";
//	-- 找出所有優惠券
	private static final String GetAll=
		"select CoupTypeNo, CoupName, CoupDiscount, CoupQuantity, CoupDesc, CoupUpd, CoupStart, CoupEnd "
		+ "from coupontype "
		+ "order by CoupTypeNo";
//	-- 刪除某張優惠券
	private static final String Delete=
		"delete from coupontype where CoupTypeNo = ?";
	
	@Override
	public void insert(CouponTypeVO couponTypeVO) {
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(Insert)){
			ps.setString(1, couponTypeVO.getCoupName());
			ps.setInt(2, couponTypeVO.getCoupDiscount());
			ps.setInt(3, couponTypeVO.getCoupQuantity());
			ps.setString(4, couponTypeVO.getCoupDesc());
			ps.setDate(5, couponTypeVO.getCoupStart());
			ps.setDate(6, couponTypeVO.getCoupEnd());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(CouponTypeVO couponTypeVO) {
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(Update)){
			ps.setString(1, couponTypeVO.getCoupName());
			ps.setInt(2, couponTypeVO.getCoupDiscount());
			ps.setInt(3, couponTypeVO.getCoupQuantity());
			ps.setString(4, couponTypeVO.getCoupDesc());
			ps.setInt(5, couponTypeVO.getCoupUpd());
			ps.setInt(6, couponTypeVO.getCoupTypeNo());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateQuantity(Integer coupTypeNo, Integer coupQuantity) {
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(UpdateQuantity)){
			ps.setInt(1, coupQuantity);
			ps.setInt(2, coupTypeNo);
			
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateDown(Integer coupTypeNo) {
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(UpdateDown)){
			
			ps.setInt(1, coupTypeNo);
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public CouponTypeVO findCouponTypeByType(Integer coupTypeNo) {
		CouponTypeVO couponTypeVO = null;
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(FindCouponTypeByType)){
			ps.setInt(1, coupTypeNo);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				couponTypeVO = new CouponTypeVO();
				couponTypeVO.setCoupTypeNo(rs.getInt("CoupTypeNo"));
				couponTypeVO.setCoupName(rs.getString("CoupName"));
				couponTypeVO.setCoupDiscount(rs.getInt("CoupDiscount"));
				couponTypeVO.setCoupQuantity(rs.getInt("CoupQuantity"));
				couponTypeVO.setCoupDesc(rs.getString("CoupDesc"));
				couponTypeVO.setCoupUpd(rs.getInt("CoupUpd"));
				couponTypeVO.setCoupStart(rs.getDate("CoupStart"));
				couponTypeVO.setCoupEnd(rs.getDate("CoupEnd"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return couponTypeVO;
	}
	@Override
	public List<CouponTypeVO> getAll() {
		List<CouponTypeVO> list = new ArrayList<CouponTypeVO>();
		CouponTypeVO couponTypeVO = null;
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GetAll)){
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				couponTypeVO = new CouponTypeVO();
				couponTypeVO.setCoupTypeNo(rs.getInt("CoupTypeNo"));
				couponTypeVO.setCoupName(rs.getString("CoupName"));
				couponTypeVO.setCoupDiscount(rs.getInt("CoupDiscount"));
				couponTypeVO.setCoupQuantity(rs.getInt("CoupQuantity"));
				couponTypeVO.setCoupDesc(rs.getString("CoupDesc"));
				couponTypeVO.setCoupUpd(rs.getInt("CoupUpd"));
				couponTypeVO.setCoupStart(rs.getDate("CoupStart"));
				couponTypeVO.setCoupEnd(rs.getDate("CoupEnd"));
				list.add(couponTypeVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void delete(Integer coupTypeNo) {
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(Delete)){
			ps.setInt(1, coupTypeNo);
		
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	

	

}
