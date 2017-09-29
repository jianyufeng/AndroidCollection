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
 * @param context ����
 * @param name   ���ݿ���
 * @param factory  �α깤��
 * @param version   ���ݿ�汾��     Ҫ��(����0��������)
 * 
 */
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}
	//����
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql ="create table userinfo(_id integer primary key autoincrement, name text,nickname text,sex text,age integer);";
		db.execSQL(sql);
	}

	@Override  //���ݿ�汾�ŷ����ı�ʱ����    ����
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i(">>>>>>>>>>>>>>>>>", "��:"+oldVersion+";-----��"+newVersion);
	}
//	@Override   //����  // һ�㲻���ûᱨ��
//	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		// TODO Auto-generated method stub
//		super.onDowngrade(db, oldVersion, newVersion);
//		Log.i(">>>>>>>>>>>>>>>>>", "fffff��:"+oldVersion+";fffff-----��"+newVersion);
//	}

}
