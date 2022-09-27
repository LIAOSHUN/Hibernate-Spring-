package core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;

//@WebFilter("/*")
public class HibernateFilter extends HttpFilter{

//	private static final long serialVersionUID = 1L;
//
//	
//	@Override
//	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
//			throws IOException, ServletException {
//		
//		SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		
//		try {
//			
//			Transaction transaction = session.beginTransaction();
//			chain.doFilter(req, res);
//			transaction.commit();
//		} catch (ServletException e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
//	}
}
