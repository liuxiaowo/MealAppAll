package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.address;
import com.bean.comments;
import com.bean.breakfast;
import com.bean.orders;
import com.bean.orders_breakfast;
import com.bean.types;
import com.bean.users;

@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
public class ServletService extends HttpServlet {

	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	java.text.SimpleDateFormat formatdate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");
	java.util.Date currentTime = new java.util.Date();// 得到当前系统时间

	private Session session = null;
	private HttpServletRequest request;

	public ServletService() {
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
		this.request = request;
		session = HibernateSessionFactory.getSession();
		session.flush();
		session.clear();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("Action");
		System.out.println(action);
		String write = "";
		String sqlString = "";
		// 登录验证
		if (action.equals("login")) {
			write = login();

		}
		if (action.equals("getOneRow")) {
			write = getOneRow();
		}
		if (action.equals("Del")) {
			write = Del();
		}
		if (action.equals("cancelOrders")) {
			write = cancelOrders();
		}

		// 管理员登录验证
		if (action.equals("adminlogin")) {
			write = adminlogin();
		}
		if (action.equals("getbreakfastlist")) {
			write = getbreakfastlist();

		}
		if (action.equals("getmyorderslist")) {
			write = getmyorderslist();
		}
		if(action.equals("getorder_breakfast")){
			write = getorder_breakfast();
		}
		if (action.equals("ChangeStatus")) {
			write = changestatus();
		}
		if (action.equals("edit")) {
			write = edit();
		}
		if (action.equals("edituser")) {
			write = edituser();
		}
		if (action.equals("edittype")) {
			write = edittype();
		}
		if (action.equals("getComment")) {
			write = getComment();

		}
		if (action.equals("getAddress")) {
			write = getAddress();

		}
		
		System.out.println(write);
		out.println(write);
		out.flush();
		out.close();

	}




	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = HibernateSessionFactory.getSession();
		session.flush();
		session.clear();
		this.request = request;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("Action");
		String write = "";
		// 注册
		if (action.equals("register")) {
			System.out.println(request.getParameter("id"));
			users model = new users();
			if (request.getParameter("id") == null
					|| request.getParameter("id").equals("0")) {
				model = new users();

			} else {
				model = (users) (session.createQuery(
						" from users where id=" + request.getParameter("id"))
						.list().get(0));
			}
			model.setLoginid(request.getParameter("loginid"));
			model.setPasswords(request.getParameter("password"));
			model.setName(getChinese(request.getParameter("name")));

			Transaction tran = session.beginTransaction();
			session.save(model);
			tran.commit();
			write = "1";
		}
		if (action.equals("updatePwd")) {
			write = updatePwd();
		}

		if (action.equals("createorder")) {
			write = createorder();
		}
		if(action.equals("createorder_breakfast")){
			write = createorder_breakfast();
		}
		if (action.equals("createcomment")) {
			write = createcomment();
		}
		if(action.equals("createaddress")){
			write = createaddress();
		}
		out.println(write);
		out.flush();
		out.close();
	}

	// 提交订单
	private String createorder_breakfast() throws UnsupportedEncodingException {
		orders_breakfast model = null;
		List list = session.createQuery(
				" from orders_breakfast where id=" + request.getParameter("id")).list();
		if (list.size() == 0) {
			model = new orders_breakfast();
		} else {
			model = (orders_breakfast) list.get(0);
		}
		model.setBreakfastid(Integer.valueOf(request.getParameter("breakfastid")));
		model.setOrderid(Integer.valueOf(request.getParameter("orderid")));
		model.setTitle(request.getParameter("title"));
		model.setNumber(Integer.valueOf(request.getParameter("number")));
		model.setPrice(Double.parseDouble(request.getParameter("price")));
		Transaction tran = session.beginTransaction();
		session.save(model);
		tran.commit();
		return "1";
	}

	private String createaddress() {
		address model = new address();
		model.setUserid(Integer.valueOf(request.getParameter("userid")));
		model.setRealname(getChinese(request.getParameter("realname")));
		model.setSex(request.getParameter("sex"));
		model.setPhone(request.getParameter("phone"));
		model.setAddress(request.getParameter("address"));
		Transaction tran = session.beginTransaction();
		session.save(model);
		session.flush();
		tran.commit();
		return "1";
	}

	private String getAddress(){
		String write = "";
		String sqlString = "from address where 1=1 ";
		if (request.getParameter("userid") != null) {
			sqlString += " and userid =" + request.getParameter("userid");
		}
		sqlString += " order by id desc";
		List list = session.createQuery(sqlString).list();
		if (list.size() > 0) {
			JSONArray json = JSONArray.fromObject(list);
			write = json.toString();
		}
		return write;
	}
	
	private String getorder_breakfast(){
		String write = "";
		String sqlString = "from orders_breakfast where 1=1 ";
		if (request.getParameter("orderid") != null) {
			sqlString += " and orderid =" + request.getParameter("orderid");
		}
		sqlString += " order by id desc";
		List list = session.createQuery(sqlString).list();
		if (list.size() > 0) {
			JSONArray json = JSONArray.fromObject(list);
			write = json.toString();
		}
		return write;
	}
	private String login() {
		String write = "";
		String loginid = request.getParameter("loginid");
		String passwords = request.getParameter("passwords");

		List<users> list = session.createQuery(
				" from users where loginid='" + loginid + "' and passwords='"
						+ passwords + "'").list();
		if (list.size() > 0) {
			write = JSONArray.fromObject(list.get(0)).toString();
		}
		return write;
	}

	private String adminlogin() {
		String write = "";
		String loginid = request.getParameter("loginid");
		String passwords = request.getParameter("passwords");
		List<users> list = session.createQuery(
				" from admins where loginid='" + loginid + "' and passwords='"
						+ passwords + "'").list();
		if (list.size() > 0) {
			write = "1";
		} else {
			write = "0";
		}
		return write;
	}

	private String getComment() {
		String write = "";
		String sqlString = "from comments where 1=1 ";
		if (request.getParameter("breakfastid") != null) {
			sqlString += " and breakfastid =" + request.getParameter("breakfastid");
		}
		sqlString += " order by id desc";
		List list = session.createQuery(sqlString).list();
		if (list.size() > 0) {
			JSONArray json = JSONArray.fromObject(list);
			write = json.toString();
		}
		return write;
	}

	private String getmyorderslist() {

		String write = "";
		String sqlString = "select orders.status, orders.id,orders.userid,orders.username,orders.price,orders.createtime,orders.deliveryway,orders.deliverytime,orders.payway,orders.addressid,orders.node,orders.orderid FROM orders";
		sqlString += " where  userid =" + request.getParameter("userid");
		/*if (request.getParameter("breakfastid") != null) {
			sqlString += " and breakfastid =" + request.getParameter("breakfastid");
		}*/
		sqlString += " order by orders.id desc";
		ResultSet rs = HibernateSessionFactory.queryBySql(sqlString);
		List list = HibernateSessionFactory.convertList(rs);
		if (list.size() > 0) {
			JSONArray json = JSONArray.fromObject(list);
			write = json.toString();
		}
		return write;
	}

	private String getbreakfastlist() {
		String write = "";
		String sqlString = "from breakfast where 1=1 ";
		if (request.getParameter("msg") != null) {
			sqlString += " and title like '%"
					+ getChinese(request.getParameter("msg")) + "%'";
		}
		sqlString += " order by id desc";
		List list = session.createQuery(sqlString).list();
		if (list.size() > 0) {
			JSONArray json = JSONArray.fromObject(list);
			write = json.toString();
		}
		return write;
	}

	private String edit() throws UnsupportedEncodingException {
		int id = Integer.valueOf((request.getParameter("ID")));
		breakfast model;
		if (id == 0) {
			model = new breakfast();

		} else {
			model = (breakfast) (session
					.createQuery(" from breakfast where id=" + id).list().get(0));
		}

		if (request.getParameter("img_url") != null
				&& request.getParameter("img_url").length() > 0) {
			model.setImg_url(request.getParameter("img_url"));
		}

		model.setIntro(getChinese(request.getParameter("intro")));
		model.setTitle(getChinese(request.getParameter("title")));
		model.setPrice(Float.valueOf(request.getParameter("price")));
		model.setAmount(Integer.valueOf(request.getParameter("amount")));
		model.setTypeid(Integer.valueOf(request.getParameter("typeid")));
		model.setTypename(getChinese(request.getParameter("typename")));
		Transaction tran = session.beginTransaction();

		if (id != 0) {
			session.update(model);
		} else {
			session.save(model);
		}
		tran.commit();
		return "1";
	}

	private String cancelOrders() {
		int id = Integer.valueOf((request.getParameter("ID")));
		orders model;
		model = (orders) (session.createQuery(" from orders where id=" + id)
				.list().get(0));
		/*String[] ss = model.getSeat().split(",");
		for (int i = 0; i < ss.length; i++) {
			seats s = (seats) session
					.createQuery(" from seats where id=" + ss[i]).list().get(0);
			s.setState(0);
			session.save(s);
		}*/
		Transaction tran = session.beginTransaction();
		session.delete(model);
		tran.commit();
		return "1";
	}

	// 修改订单状态
	private String changestatus() {
		int id = Integer.valueOf((request.getParameter("ID")));
		int status = Integer.valueOf((request.getParameter("status")));
		orders model;
		model = (orders) (session.createQuery(" from orders where id=" + id)
				.list().get(0));
		model.setStatus(status);
		Transaction tran = session.beginTransaction();
		session.save(model);
		tran.commit();
		return "1";
	}

	private String edituser() throws UnsupportedEncodingException {
		int id = Integer.valueOf((request.getParameter("ID")));
		users model;
		if (id == 0) {
			model = new users();

		} else {
			model = (users) (session.createQuery(" from users where id=" + id)
					.list().get(0));
		}

		model.setLoginid(getChinese(request.getParameter("loginid")));
		model.setName(getChinese(request.getParameter("name")));
		model.setPasswords(request.getParameter("passwords"));
		Transaction tran = session.beginTransaction();
		if (id != 0) {
			session.update(model);
		} else {
			session.save(model);
		}
		tran.commit();
		return "1";
	}

	private String createcomment() {
		comments model = new comments();
		model.setBody(getChinese(request.getParameter("body")));
		model.setCreatetime(formatdate.format(currentTime));
		model.setBreakfastid(Integer.valueOf(request.getParameter("breakfastid")));
		model.setUsername(request.getParameter("username"));
		model.setUserid(Integer.valueOf(request.getParameter("userid")));
		Transaction tran = session.beginTransaction();
		session.save(model);
		session.flush();
		tran.commit();
		return "1";
	}

	private String edittype() throws UnsupportedEncodingException {
		int id = Integer.valueOf((request.getParameter("ID")));
		types model;
		if (id == 0) {
			model = new types();

		} else {
			model = (types) (session.createQuery(" from types where id=" + id)
					.list().get(0));
		}

		model.setTypename(getChinese(request.getParameter("typename")));

		Transaction tran = session.beginTransaction();
		if (id != 0) {
			session.update(model);
		} else {
			session.save(model);
		}
		tran.commit();
		return "1";
	}


	// 提交订单
	private String createorder() throws UnsupportedEncodingException {
		orders model = null;
		List list = session.createQuery(
				" from orders where id=" + request.getParameter("id")).list();
		if (list.size() == 0) {
			model = new orders();
			model.setCreatetime(formatdate.format(currentTime));
		} else {
			model = (orders) list.get(0);
		}
		/*breakfast dishesModel = (breakfast) session
				.createQuery(
						" from breakfast where id="
								+ request.getParameter("breakfastid")).list()
				.get(0);*/
		model.setUserid(Integer.valueOf(request.getParameter("userid")));
		model.setUsername(getChinese(request.getParameter("username")));
		/*model.setAmount(Integer.valueOf(request.getParameter("amount")));*/
		model.setDeliveryway(request.getParameter("deliveryway"));
		model.setPayway(request.getParameter("payway"));
		model.setNode(request.getParameter("node"));
		model.setAddressid(Integer.valueOf(request.getParameter("addressid")));
		model.setDeliverytime(request.getParameter("deliverytime"));
		model.setPrice(Double.parseDouble(request.getParameter("price")));
		model.setOrderid(Integer.valueOf(request.getParameter("orderid")));
		/*model.setPrice(dishesModel.getPrice());
		model.setSeat(request.getParameter("seat"));
		model.setBreakfastid(dishesModel.getId());
		String[] ss = model.getSeat().split(",");
		for (int i = 0; i < ss.length; i++) {
			seats s = (seats) session
					.createQuery(" from seats where id=" + ss[i]).list().get(0);
			s.setState(1);
			session.save(s);
		}*/
		Transaction tran = session.beginTransaction();
		session.save(model);
		tran.commit();
		return "1";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updatePwd() throws UnsupportedEncodingException {
		List list = session.createQuery(
				" from users where loginid='" + request.getParameter("loginid")
						+ "' and passwords='"
						+ request.getParameter("passwords") + "'").list();
		if (list.size() == 0) {
			return "-1";// 账号或密码错误
		} else {
			users model = (users) list.get(0);
			model.setPasswords(request.getParameter("passwords_new"));
			Transaction tran = session.beginTransaction();
			session.save(model);
			tran.commit();
			return "1";// 修改成功
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
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";

		}
	}

	/**
	 * 公用的获取一行数据方法
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getOneRow() throws UnsupportedEncodingException {
		List list = null;
		if (request.getParameter("ID") == null) {
			list = session
					.createQuery(" from " + request.getParameter("Table"))
					.list();
		} else {
			list = session.createQuery(
					" from " + request.getParameter("Table") + " where id="
							+ request.getParameter("ID")).list();
		}

		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}

	public String Del() {
		int ID = Integer.valueOf(request.getParameter("ID"));
		String Table = request.getParameter("Table");
		String PK_Name = "id";
		String sql = "delete from " + Table + " where " + PK_Name + "=" + ID;
		HibernateSessionFactory.updateExecute(sql);
		return "1";

	}

	@Override
	public void init() throws ServletException {

	}

}
