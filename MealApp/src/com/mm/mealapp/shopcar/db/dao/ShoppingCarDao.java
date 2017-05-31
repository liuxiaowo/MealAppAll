package com.mm.mealapp.shopcar.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mm.mealapp.api.ShopCar;
import com.mm.mealapp.shopcar.db.ShoppingCarDb;

/**
 * 购物车数据量操作类
 */
public class ShoppingCarDao {

	private Context mContext;
	private volatile static ShoppingCarDao instance = null;
	private SQLiteDatabase db;

	public ShoppingCarDao(Context context) {
		this.mContext = context;
	}

	public static ShoppingCarDao getInstance(Context context) {
		if (instance == null) {
			synchronized (ShoppingCarDao.class) {
				if (instance == null) {
					instance = new ShoppingCarDao(context);
				}
			}
		}
		return instance;
	}

	private Cursor cursor;

	public void close() {
		if (db != null) {
			db.close();
		}
		if (cursor != null) {
			cursor.close();
		}
	}

	/**
	 * 添加商品信息到购物车
	 * 
	 * @param info
	 */
	public void addShoppingCar(ShopCar info) {
		String sql = "insert into " + ShoppingCarDb.TABLE_NAME + "(" +

		ShoppingCarDb.TAB_TITLE + "," + ShoppingCarDb.TAB_TIME + "," + ShoppingCarDb.TAB_NUMBER + "," + ShoppingCarDb.TAB_PRICE + ") values (?,?,?,?)";
		db = ShoppingCarDb.getInstance(mContext).getWritableDatabase();
		db.execSQL(sql, new Object[] { info.getTitle(), info.getTime(), info.getNumber

		(), info.getPrice() });
		db.close();
	}

	/**
	 * 查询购物车中的信息
	 * 
	 * @param title
	 *            商品标题
	 * @return
	 */
	public ShopCar showShoppingCarInfoByTitle(String title) {
		ShopCar list = new ShopCar();
		// String sql = "SELECT * FROM " + ShoppingCarDb.TABLE_NAME + " WHERE "
		// + ShoppingCarDb.TAB_TITLE + "=" + title;
		db = ShoppingCarDb.getInstance(mContext).getReadableDatabase();
		// Cursor c = db.rawQuery(sql, null);
		Cursor c = db.query(ShoppingCarDb.TABLE_NAME, null, ShoppingCarDb.TAB_TITLE + "=?", new String[] { title }, null, null, null);
		boolean moveToFirst = c.moveToFirst();
		if (moveToFirst) {
			list.set_id(c.getInt(0));
			list.setTitle(c.getString(1));
			list.setTime(c.getString(2));
			list.setNumber(c.getInt(3));
			list.setPrice(c.getInt(4));
			c.close();
			db.close();
			return list;
		} else {
			c.close();
			db.close();
			return null;
		}

	}

	/**
	 * 修改购物车商品数量
	 * 
	 * @param number
	 *            数量
	 * @param time
	 *            时间
	 * @param title
	 *            物品名称
	 */
	public void updateShoppingNumber(Integer number, String time, String title) {
		String sql = "UPDATE " + ShoppingCarDb.TABLE_NAME + " SET " +

		ShoppingCarDb.TAB_NUMBER + "= ?," + ShoppingCarDb.TAB_TIME + "=? WHERE " +

		ShoppingCarDb.TAB_TITLE + "= ?";
		db = ShoppingCarDb.getInstance(mContext).getWritableDatabase();
		db.execSQL(sql, new Object[] { number, time, title });
		db.close();

	}

	/**
	 * 显示所有的商品信息(根据时间进行排序)
	 */
	public List<ShopCar> showAllShoppingCar() {
		List<ShopCar> list = new ArrayList<ShopCar>();
		String sql = "SELECT * FROM " + ShoppingCarDb.TABLE_NAME + "  ORDER BY " +

		ShoppingCarDb.TAB_TIME + " DESC";
		db = ShoppingCarDb.getInstance(mContext).getReadableDatabase();
		Cursor c = db.rawQuery(sql, null);
		while (c.moveToNext()) {
			ShopCar car = new ShopCar();
			car.set_id(c.getInt(0));
			car.setTitle(c.getString(1));
			car.setTime(c.getString(2));
			car.setNumber(c.getInt(3));
			car.setPrice(c.getInt(4));
			list.add(car);
		}
		c.close();
		db.close();
		return list;
	}

	/**
	 * 数据库中是否存在该条信息
	 * 
	 * @param title
	 *            商品名称
	 * @return
	 */
	public boolean isExistGood(String title) {
		if (title == null) {
			return false;
		}
		db = ShoppingCarDb.getInstance(mContext).getReadableDatabase();
		Cursor c = db.query(ShoppingCarDb.TABLE_NAME, null, ShoppingCarDb.TAB_TITLE +

		"=?", new String[] { title }, null, null, null);
		boolean isExist = c.moveToFirst();
		c.close();
		return isExist;
	}

	public void deleteShoppingCarById() {
		db = ShoppingCarDb.getInstance(mContext).getReadableDatabase();
		db.delete(ShoppingCarDb.TABLE_NAME, null, null);
	}

}
