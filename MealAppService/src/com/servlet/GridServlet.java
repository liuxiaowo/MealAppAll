package com.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.bean.PagesHelper;

@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
public class GridServlet extends HttpServlet {
	private Session session = null;

	public GridServlet() {
		super();
		session = HibernateSessionFactory.getSession();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = HibernateSessionFactory.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("Action");
		System.out.println("执行GridServlet:" + action);
		String sqlString = "";
		ResultSet rs = null;
		List list = new ArrayList();
		int pageSize = 10;
		int currentpage = 0;
		currentpage = Integer.valueOf(request.getParameter("currentpage"));
		currentpage = Math.max(currentpage, 1);

		//index。jsp中js的 search()方法传值
		if (action.equals("getlist")) {
			//msg--搜索输入框中内容
			String msg = "";
			if (request.getParameter("msg") != null) {
				//获取msg的参数值
				msg = request.getParameter("msg");
				System.out.println("msg  " + msg);
			}
			pageSize = 6;
			PagesHelper model = new PagesHelper();
			model.setTableName("breakfast ");
			model.setColumnName("*");
			model.setOrder("id");
			model.setFilter(" and title like '%" + msg + "%'");
			// 总共多少条
			int totalCount = Integer.valueOf(String
					.valueOf(HibernateSessionFactory.executeScalar(model
							.ToCountString())));
			// 多少页
			int pagecount = totalCount % pageSize == 0 ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
			currentpage = Math.min(currentpage, pagecount);
			int start = (currentpage - 1) * pageSize + 1;
			int limit = pageSize;
			model.setCurrentIndex(start);
			model.setPageSize(limit);

			rs = HibernateSessionFactory.queryBySql(model.ToListString());
			System.out.println(model.ToListString());
			request.setAttribute("datalist",
					HibernateSessionFactory.convertList(rs));
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("total", totalCount);
			request.getRequestDispatcher("../index.jsp").forward(request,
					response);
		}

		if (action.equals("getuserlist")) {
			String msg = "";
			if (request.getParameter("msg") != null) {
				msg = getChinese(request.getParameter("msg"));
				System.out.println("msg  " + msg);
			}
			pageSize = 6;
			PagesHelper model = new PagesHelper();
			model.setTableName("users ");
			model.setColumnName("*");
			model.setOrder("id");
			model.setFilter(" and name like '%" + msg + "%'");
			// 总共多少条
			int totalCount = Integer.valueOf(String
					.valueOf(HibernateSessionFactory.executeScalar(model
							.ToCountString())));
			// 多少页
			int pagecount = totalCount % pageSize == 0 ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
			currentpage = Math.min(currentpage, pagecount);
			int start = (currentpage - 1) * pageSize + 1;
			int limit = pageSize;
			model.setCurrentIndex(start);
			model.setPageSize(limit);

			rs = HibernateSessionFactory.queryBySql(model.ToListString());
			System.out.println(model.ToListString());
			request.setAttribute("datalist",
					HibernateSessionFactory.convertList(rs));
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("total", totalCount);
			request.getRequestDispatcher("../userlist.jsp").forward(request,
					response);
		}

		if (action.equals("getchieflist")) {
			String msg = "";
			if (request.getParameter("msg") != null) {
				msg = getChinese(request.getParameter("msg"));
				System.out.println("msg  " + msg);
			}
			pageSize = 6;
			PagesHelper model = new PagesHelper();
			model.setTableName("chief ");
			model.setColumnName("*");
			model.setOrder("id");
			model.setFilter(" and loginid like '%" + msg + "%'");
			// 总共多少条
			int totalCount = Integer.valueOf(String
					.valueOf(HibernateSessionFactory.executeScalar(model
							.ToCountString())));
			// 多少页
			int pagecount = totalCount % pageSize == 0 ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
			currentpage = Math.min(currentpage, pagecount);
			int start = (currentpage - 1) * pageSize + 1;
			int limit = pageSize;
			model.setCurrentIndex(start);
			model.setPageSize(limit);

			rs = HibernateSessionFactory.queryBySql(model.ToListString());
			System.out.println(model.ToListString());
			request.setAttribute("datalist",
					HibernateSessionFactory.convertList(rs));
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("total", totalCount);
			request.getRequestDispatcher("../chief.jsp").forward(request,
					response);
		}
		
		if (action.equals("getdeliverymanlist")) {
			String msg = "";
			if (request.getParameter("msg") != null) {
				msg = getChinese(request.getParameter("msg"));
				System.out.println("msg  " + msg);
			}
			pageSize = 6;
			PagesHelper model = new PagesHelper();
			model.setTableName("deliveryman ");
			model.setColumnName("*");
			model.setOrder("id");
			model.setFilter(" and loginid like '%" + msg + "%'");
			// 总共多少条
			int totalCount = Integer.valueOf(String
					.valueOf(HibernateSessionFactory.executeScalar(model
							.ToCountString())));
			// 多少页
			int pagecount = totalCount % pageSize == 0 ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
			currentpage = Math.min(currentpage, pagecount);
			int start = (currentpage - 1) * pageSize + 1;
			int limit = pageSize;
			model.setCurrentIndex(start);
			model.setPageSize(limit);

			rs = HibernateSessionFactory.queryBySql(model.ToListString());
			System.out.println(model.ToListString());
			request.setAttribute("datalist",
					HibernateSessionFactory.convertList(rs));
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("total", totalCount);
			request.getRequestDispatcher("../chief.jsp").forward(request,
					response);
		}
		
		if (action.equals("gettypelist")) {
			String msg = "";
			if (request.getParameter("msg") != null) {
				msg = request.getParameter("msg");
				System.out.println("msg  " + msg);
			}
			pageSize = 6;
			PagesHelper model = new PagesHelper();
			model.setTableName("types ");
			model.setColumnName("*");
			model.setOrder("id");
			model.setFilter(" and typename like '%" + msg + "%'");
			// 总共多少条
			int totalCount = Integer.valueOf(String.valueOf(HibernateSessionFactory.executeScalar(model.ToCountString())));
			// 多少页
			int pagecount = totalCount % pageSize == 0 ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
			currentpage = Math.min(currentpage, pagecount);
			int start = (currentpage - 1) * pageSize + 1;
			int limit = pageSize;
			model.setCurrentIndex(start);
			model.setPageSize(limit);

			rs = HibernateSessionFactory.queryBySql(model.ToListString());
			System.out.println(model.ToListString());
			request.setAttribute("datalist",
					HibernateSessionFactory.convertList(rs));
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("total", totalCount);
			request.getRequestDispatcher("../typelist.jsp").forward(request,
					response);
		}

		if (action.equals("getorderlist")) {
			String msg = "";
			if (request.getParameter("msg") != null) {
				msg = request.getParameter("msg");
				System.out.println("msg  " + msg);
			}
			pageSize = 6;
			PagesHelper model = new PagesHelper();
			model.setTableName("orders INNER JOIN users ON orders.userid=users.id");
			model.setColumnName("orders.id,orders.username,orders.deliveryway,orders.deliverytime,orders.payway,orders.addressid,orders.node,orders.price,users.name, case status when 1 then '已经完成' WHEN 0 then '进行中' else '已取消' end status1");
			model.setOrder("orders.id");
			model.setFilter(" and users.name like '%" + msg + "%'");
			
			// 总共多少条
			int totalCount = Integer.valueOf(String
					.valueOf(HibernateSessionFactory.executeScalar(model
							.ToCountString())));
			// 多少页
			int pagecount = totalCount % pageSize == 0 ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
			currentpage = Math.min(currentpage, pagecount);
			int start = (currentpage - 1) * pageSize + 1;
			int limit = pageSize;
			model.setCurrentIndex(start);
			model.setPageSize(limit);
BaseUtil.LogII(model.ToListString());
			rs = HibernateSessionFactory.queryBySql(model.ToListString());
			System.out.println(model.ToListString());
			request.setAttribute("datalist",
					HibernateSessionFactory.convertList(rs));
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("total", totalCount);
			request.getRequestDispatcher("../orderlist.jsp").forward(request,
					response);
		}
	}
	

	/**
	 * 取得中文
	 * 
	 * @param 原字符
	 * @return
	 */
	private String getChinese(String str) {
		if (str == null) {
			return "";
		}
		try {
			return new String(str.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";

		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("执行GridServlet");
	}

	@Override
	public void init() throws ServletException {

	}

}
