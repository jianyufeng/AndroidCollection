package com.qf.androidday14_01sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "data.db";
	private static int DB_VERSION = 2;
	/**
 * 
 * @param context 环境
 * @param name   数据库名
 * @param factory  游标工厂
 * @param version   数据库版本号     要求(大于0的正整数)
 * 
 */
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}
	//建表
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql ="create table userinfo(_id integer primary key autoincrement, name text,nickname text,sex text,age integer);";
		db.execSQL(sql);
	}

	@Override  //数据库版本号发生改变时调用    上升
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i(">>>>>>>>>>>>>>>>>", "老:"+oldVersion+";-----新"+newVersion);
	}
//	@Override   //降低  // 一般不会让会报错
//	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		// TODO Auto-generated method stub
//		super.onDowngrade(db, oldVersion, newVersion);
//		Log.i(">>>>>>>>>>>>>>>>>", "fffff老:"+oldVersion+";fffff-----新"+newVersion);
//	}

}
