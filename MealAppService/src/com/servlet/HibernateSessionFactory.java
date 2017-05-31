package com.servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * 在Hibernate中，Session负责完成对象持久化操作。该文件负责创建Session对象，以及关闭Session对象。从该文件可以看出，Session对象的创建大致需要以下3个步骤：

① 初始化Hibernate配置管理类Configuration。

② 通过Configuration类实例创建Session的工厂类SessionFactory。

③ 通过SessionFactory得到Session实例。
 */
public class HibernateSessionFactory {
	

	//hibernate 配置文件
	//jdbc升级版，专门用于连接数据库
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static Configuration configuration = new Configuration();
	private static org.hibernate.SessionFactory sessionFactory;
	private static String configFile = CONFIG_FILE_LOCATION;

	static {
		try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	private HibernateSessionFactory() {
	}

	public static Session getSession() throws HibernateException {
		Session session = threadLocal.get();

		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}

		return session;
	}

	public static void rebuildSessionFactory() {
		try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	public static void closeSession() throws HibernateException {
		Session session = threadLocal.get();
		threadLocal.set(null);

		if (session != null) {
			session.close();
		}
	}

	public static org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setConfigFile(String configFile) {
		HibernateSessionFactory.configFile = configFile;
		sessionFactory = null;
	}

	public static Configuration getConfiguration() {
		return configuration;
	}

	@SuppressWarnings("deprecation")
	public static ResultSet queryBySql(String sql) {
		ResultSet rs = null;
		try {
			Connection con = getSession().connection();
			Statement sta = con.createStatement();
			rs = sta.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return rs;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * ResultSet 转list
	 * @param rs
	 * @return
	 */
	public static List convertList(ResultSet rs) {
		List listOfRows = new ArrayList();
		try {
			ResultSetMetaData md = rs.getMetaData();
			int num = md.getColumnCount();
			while (rs.next()) {
				Map mapOfColValues = new HashMap(num);
				for (int i = 1; i <= num; i++) {
					BaseUtil.LogII(md.getColumnName(i));
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
				}
				listOfRows.add(mapOfColValues);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listOfRows;
	}

	public static int updateExecute(String sql) {
		int result = 0;
		try {
			Session session = getSession();
			Connection con = session.connection();
			Transaction tran = session.beginTransaction();
			tran.begin();
			Statement sta = con.createStatement();
			result = sta.executeUpdate(sql);
			tran.commit();
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return result;
	}

	public static String executeScalar(String sql) {
		ResultSet rs = queryBySql(sql);
		String s = "";
		try {
			while (rs.next()) {
				s = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

}