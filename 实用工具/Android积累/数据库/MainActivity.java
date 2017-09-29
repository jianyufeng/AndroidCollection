package com.qf.androidday14_01sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper helper = new DBHelper(MainActivity.this);
        
        //�÷���ʹ�ú�Ŵ��������ݿ�
        SQLiteDatabase db = helper.getReadableDatabase();//���һ���ɶ������ݿ�
        String  insertsl = "insert into userinfo (name,nickname,sex,age) values ('³����','������','��',20)";
        db.execSQL(insertsl);
        Cursor cursor = DBManger.queryAll("userinfo");
        while(cursor.moveToNext()){
        	Log.i(">>>>>>>>",cursor.getString(0));
        	Log.i(">>>>>>>>",cursor.getString(1));
        }
//        db??????????????????????????????????????
//        db.insert(table, nullColumnHack, values);
//        db.delete(table, whereClause, whereArgs)
//        db.update(table, values, whereClause, whereArgs)
//        db.rawQuery(sql, selectionArgs)
//        db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
//        db.close()
//        String  inserts2 = "insert into userinfo (name,nickname,sex,age) values (?,?,?,?)";
//        db.execSQL(inserts2, new String[]{"������","ɫ��","��","20"});
//        
//        
//        
//        ContentValues value = new ContentValues();
//        value.put("name", "����");
//        value.put("nickname", "10��");
//        value.put("sex", "��");
//        value.put("age", 12);
//        db.insert("userinfo", null, value);
//        
//       
//        ContentValues values = new ContentValues();
//        value.put("name", "�Թ���");
//        value.put("nickname", "10��");
//        value.put("sex", "��");
//        value.put("age", 12);
//        db.update("userinfo", values, "name=?,sex=?", new String[]{"����","Ů"});
//        
//        String quqry = "select * from userinfo";
//        Cursor cursor = db.rawQuery(quqry, null);
//        while(cursor.moveToNext()){
//        	String name = cursor.getString(cursor.getColumnIndex("name"));
//        	String nickname = cursor.getString(2);
//        	String sex = cursor.getString(3);
//        	String age = cursor.getString(4);
//        	Log.i(">>>>>>>>>>>>>>>", name+"1"+nickname+"2"+age+"3");
//        	
//        }
//        cursor.getColumnCount();//����
//        cursor.getCount();//����
//        db.delete("userinfo", "name=?", new String[]{"�Թ���"});
        
//        db.query(false, "userinfo", columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
    }
}
