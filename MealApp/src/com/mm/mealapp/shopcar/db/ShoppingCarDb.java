package com.mm.mealapp.shopcar.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 购物车数据库类
 */
public class ShoppingCarDb extends SQLiteOpenHelper {
	private static final String DB_NAME = "shoping_car.db";
	private static final int VER = 1;
	private static ShoppingCarDb helper;
	// ---------------------购物车-----------------------//
	/** 购物车表名 **/
	public static final String TABLE_NAME = "shoping_car_info";
	/* 购物车表格信息 */
	public static final String TAB_ID = "_id";
	public static final String TAB_TITLE = "title";
	public static final String TAB_TIME = "time";
	public static final String TAB_NUMBER = "number";
	public static final String TAB_PRICE = "price";

	public ShoppingCarDb(Context context, String name, CursorFactory factory, int version) {
		super(context, DB_NAME, null, VER);
	}

	public ShoppingCarDb(Context context) {
		super(context, DB_NAME, null, VER);
	}

	public static ShoppingCarDb getInstance(Context context) {
		if (helper == null) {
			helper = new ShoppingCarDb(context);
		}
		return helper;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + TABLE_NAME + "(" + TAB_ID + " integer primary key autoincrement," + TAB_TITLE + " text," + TAB_TIME + " text," + TAB_NUMBER + " integer,"
				+ TAB_PRICE + " integer)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String sql = "drop table " + TABLE_NAME + "";
		onCreate(db);
	}

}
