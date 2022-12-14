package com.coupontype.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.MemberJDBCDAO_cart;
import com.cart.model.MemberVO_cart;
import com.coupontype.model.CouponTypeVO;
import com.coupontype.model.ICouponTypeService;
import com.memcoupon.model.MemCouponDAO;
import com.memcoupon.model.MemCouponDAO_interface;
import com.memcoupon.model.MemCouponJDBCDAO;
import com.memcoupon.model.IMemCouponService;
import com.memcoupon.model.MemCouponVO;
import com.orderlist.model.OrderListMailService;

import util.SpringUtil;


//@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
       
	private static final long serialVersionUID = 1L;
	Timer timer = null;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
									throws ServletException, IOException {
		res.setContentType("text/plain");
		
		
	};
	
	public void destroy() {
		
		super.destroy();
		timer.cancel();
	}
	
	public void init() {
		timer = new Timer();
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				IMemCouponService memCouponService = SpringUtil.getBean(getServletContext(), IMemCouponService.class);
				ICouponTypeService couponTypeService = SpringUtil.getBean(getServletContext(), ICouponTypeService.class);
				
				List<CouponTypeVO> listCouponType = couponTypeService.getAll(); 
				
				for(int i = 0; i < listCouponType.size(); i++) {
					CouponTypeVO couponTypeVO = new CouponTypeVO();
					
					couponTypeVO = listCouponType.get(i);
					
					java.util.Date date1 = new java.util.Date();//ηΎε¨ζι
					java.util.Date date2 = couponTypeVO.getCoupEnd();//εͺζ εΈι‘εηε°ζζ₯
					Date dateModify = new Date(date1.getTime() - (1000 * 60 * 60 * 24));
					//ε¦ζdate1ζζΌdate2 ε°ιζεͺζ εΈδΈζΆ
					 if (dateModify.after(date2)) { 
//						 System.out.println("εͺζ εΈι‘εδΈζΆ");
						 couponTypeService.updateDown(couponTypeVO.getCoupTypeNo());//ζΉζε·²δΈζΆ 
			         }

				}
				
				List<MemCouponVO> listMemCoupon = memCouponService.getAll();
				for(int i = 0; i < listMemCoupon.size(); i++) {
					MemCouponVO memCouponVO = new MemCouponVO();
					memCouponVO = listMemCoupon.get(i);
					 			
					 
					java.util.Date date1 = new java.util.Date();//ηΎε¨ζι
					java.util.Date date2 = memCouponVO.getCouponTypeVO().getCoupEnd();//ζε‘ζζηεͺζ εΈοΌζΎεΊδ»ζ―εͺει‘εοΌε¨ζΎεΊζ­€ι‘εηε°ζζ₯
					Date dateModify = new Date(date1.getTime() - (1000 * 60 * 60 * 24));
					//ε¦ζdate1ζζΌdate2 εε°εͺζ εΈδΈζΆοΌζε‘εͺζ εΈζΉηΊε·²ιζ
					 if (dateModify.after(date2)) {
//						 System.out.println("ζηεͺζ εΈιζ");
						 memCouponService.updateStatusRoutine(memCouponVO.getCoupNo(), 2);//ζΉιζ(2:ιζ)
			         }
					 
					//dateRemind δΈε€©ε°ζζ₯
					 Date dateRemind = new Date(date1.getTime() + (1000 * 60 * 60 * 24 * 3));
					 SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");//θ¨­ε?ζ―θΌζ ΌεΌοΌε°εΉ΄ζζ₯
					 
					 //εεΎζε‘εε­
					 MemberJDBCDAO_cart memberJDBCDAO_cart = new MemberJDBCDAO_cart();
					 MemberVO_cart memberVO_cart = memberJDBCDAO_cart.findByPrimaryKey(memCouponVO.getMemID());
					 String memName = memberVO_cart.getMemName();
					 //ε¦ζdate2η­ζΌateRemind εζε‘εͺζ εΈε°ζεδΈε€©οΌε―δΏ‘ζι
					 if (fmt.format(dateRemind).equals(fmt.format(date2))) {
//						 System.out.println("δ½ ηεͺζ εΈε©δΈε€©ζι"); 
						 
						 // ηΌι Email ιη₯
						 String to = "u5msaaay@gmail.com"; // θ¦ζζε‘ email 
						 String subject = "εͺζ εΈε³ε°ε°ζ";
						 String ch_name =  memName + " η¨ζΆ"; //ζζε‘ε
						 String messageText = "Hello! " + ch_name + "οΌζ¨ηεͺζ εΈε©δΈε€©ε°ζοΌθ«η‘εΏ«δ½Ώη¨ε~";
						 
						 //"ζͺδ½Ώη¨"ηεͺζ εΈζζε―δΏ‘ζι
						 Integer status = memCouponVO.getCoupStatus();
						 if(status == 0) { 
							 OrderListMailService orderListMailService = new OrderListMailService();
							 orderListMailService.sendMail(to, subject, messageText);
						 }
						 
			         }

					
				}
			}
		};
		
		Calendar cal = new GregorianCalendar(2022, Calendar.SEPTEMBER, 12, 0, 0, 0);//9/12θ εζ¨1200ιε§
		timer.scheduleAtFixedRate(task, cal.getTime(),24*60*60*1000);//εΎδΈι’ζε?ζιιε§οΌδΈ¦ε¨ζ―ε€©ε·θ‘δΈζ¬‘
	}

}
