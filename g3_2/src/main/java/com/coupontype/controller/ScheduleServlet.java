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
					
					java.util.Date date1 = new java.util.Date();//現在時間
					java.util.Date date2 = couponTypeVO.getCoupEnd();//優惠券類型的到期日
					Date dateModify = new Date(date1.getTime() - (1000 * 60 * 60 * 24));
					//如果date1晚於date2 將過期優惠券下架
					 if (dateModify.after(date2)) { 
//						 System.out.println("優惠券類型下架");
						 couponTypeService.updateDown(couponTypeVO.getCoupTypeNo());//改成已下架 
			         }

				}
				
				List<MemCouponVO> listMemCoupon = memCouponService.getAll();
				for(int i = 0; i < listMemCoupon.size(); i++) {
					MemCouponVO memCouponVO = new MemCouponVO();
					memCouponVO = listMemCoupon.get(i);
					 			
					 
					java.util.Date date1 = new java.util.Date();//現在時間
					java.util.Date date2 = memCouponVO.getCouponTypeVO().getCoupEnd();//會員擁有的優惠券，找出他是哪個類型，在找出此類型的到期日
					Date dateModify = new Date(date1.getTime() - (1000 * 60 * 60 * 24));
					//如果date1晚於date2 則將優惠券下架，會員優惠券改為已過期
					 if (dateModify.after(date2)) {
//						 System.out.println("我的優惠券過期");
						 memCouponService.updateStatusRoutine(memCouponVO.getCoupNo(), 2);//改過期(2:過期)
			         }
					 
					//dateRemind 三天到期日
					 Date dateRemind = new Date(date1.getTime() + (1000 * 60 * 60 * 24 * 3));
					 SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");//設定比較格式，到年月日
					 
					 //取得會員名字
					 MemberJDBCDAO_cart memberJDBCDAO_cart = new MemberJDBCDAO_cart();
					 MemberVO_cart memberVO_cart = memberJDBCDAO_cart.findByPrimaryKey(memCouponVO.getMemID());
					 String memName = memberVO_cart.getMemName();
					 //如果date2等於ateRemind 則會員優惠券到期前三天，寄信提醒
					 if (fmt.format(dateRemind).equals(fmt.format(date2))) {
//						 System.out.println("你的優惠券剩三天期限"); 
						 
						 // 發送 Email 通知
						 String to = "u5msaaay@gmail.com"; // 要抓會員 email 
						 String subject = "優惠券即將到期";
						 String ch_name =  memName + " 用戶"; //抓會員名
						 String messageText = "Hello! " + ch_name + "，您的優惠券剩三天到期，請盡快使用喔~";
						 
						 //"未使用"的優惠券才會寄信提醒
						 Integer status = memCouponVO.getCoupStatus();
						 if(status == 0) { 
							 OrderListMailService orderListMailService = new OrderListMailService();
							 orderListMailService.sendMail(to, subject, messageText);
						 }
						 
			         }

					
				}
			}
		};
		
		Calendar cal = new GregorianCalendar(2022, Calendar.SEPTEMBER, 12, 0, 0, 0);//9/12號 凌晨1200開始
		timer.scheduleAtFixedRate(task, cal.getTime(),24*60*60*1000);//從上面指定時間開始，並在每天執行一次
	}

}
