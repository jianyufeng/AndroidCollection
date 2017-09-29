package com.qf.androidday14_01sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManger {
	private Context context;
	private DBHelper dbhelder;
	private static SQLiteDatabase db;
	public DBManger(Context context){
		this.context=context;
		db = dbhelder.getReadableDatabase();
	}
	/**
	 * \����
	 * @param tablename  ����
	 * @param values  Ҫ�����   ���ݼ���
	 * @return  �����-1   ��ʧ��   ����ɾ��������
	 */
	public static Long insert(String tablename,ContentValues values){
		long insert = db.insert(tablename, null, values);
		db.close();
		return insert;
	}
	/**
	 * ɾ��
	 * @param tablename ����
	 * @param whereSQ ���� �磺 name=?
	 * @param whereArgs  ��Ӧ�����ļ���
	 */
	public static void delecte(String tablename,String whereSQ,String[] whereArgs){
		int delete = db.delete(tablename, whereSQ, whereArgs);
		Log.i(">>>>>>>>>", "delect"+delete);
		db.close();
	}
	/**
	 * �޸�
	 * @param tablename
	 * @param values
	 * @param whereSQ
	 * @param whereArgs
	 */
	public static void upData(String tablename,ContentValues values,String whereSQ,String[] whereArgs){
		db.update(tablename, values, whereSQ, whereArgs);
		db.close();
	}
	public static Cursor queryAll(String tablename){
		Cursor cursor = db.rawQuery("select * fron"+tablename, null);
		db.close();
		return cursor;
	}
	/**
	 * 
	 * @param sql
	 * @param selectionArgs
	 * @return
	 */
	public static Cursor query(String sql,String[] selectionArgs){
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		db.close();
		return cursor;
	}
	public static Cursor queryPage(int page,String tablename){
		Cursor cursor = queryAll(tablename);
		int count = cursor.getCount();
		int pagemax=0;
		if(count%5>0){
			pagemax = count/5+1;
		}else{
			pagemax = count/5;
		}
		if(page>pagemax){
			Log.i("++++++---page>pagemax", "��������");
		}else if(page>pagemax){
			String sql = "select * fron"+tablename+"limt"+(page-1)*5+","+5;
			Cursor cursor2 = db.rawQuery(sql, null);
		}else{
			String sql = "select * fron"+tablename+"limt"+(count-(page-1)*5)+","+5;
			db.rawQuery(sql, null);
		}
		return cursor;
	}
}
