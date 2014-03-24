package com.haha.embarrassednews.utils;

import java.util.ArrayList;
import java.util.List;

import com.haha.embarrassednews.model.ModelNew;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static final String DBFILE = "embarrassednews.db";
	private SQLiteDatabase mDatabase = null;

	public DbHelper(Context paramContext) {
		super(paramContext, DBFILE, null, 2);
		SetDatabase(getReadableDatabase());
	}

	public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
		paramSQLiteDatabase.execSQL("CREATE TABLE if not exists  \"news\" (\"id\" integer PRIMARY KEY AUTOINCREMENT,\"title\" text(255,0),"
				+ "\"pic\" text(50,0),\"intro\" text(255,0),\"detail\" text(50,0),\"newId\" text(50,0);");
	}

	public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
		paramSQLiteDatabase.execSQL("drop table if exists history");
		this.onCreate(paramSQLiteDatabase);
	}

	public void Clear() {
		mDatabase.execSQL("delete from history");
	}

	public void Close() {
		mDatabase.close();
	}

	public void DeleteByID(Integer paramInteger) {
		mDatabase.execSQL("delete from history where id = " + String.valueOf(paramInteger));
	}

	public void DeleteByVid(String paramString) {
		mDatabase.execSQL("delete from history where playUrl = '" + paramString + "'");
	}

	public void closeSql(SQLiteDatabase db, Cursor cursor) {
		if (db != null && db.isOpen()) {
			db.close();
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
	}

	public SQLiteDatabase getDb() {
		return mDatabase;
	}

	public List<ModelNew> getNews() {
		ArrayList<ModelNew> localArrayList = new ArrayList<ModelNew>();
		Cursor cursor = mDatabase.query("history", null, null, null, null, null, "id desc");
		cursor.moveToFirst();
		while (cursor.moveToNext()) {
			
		}
		cursor.close();
		return localArrayList;
	}

	public void SavePlayHistory(ModelNew modelNew) {
		
	}

	public void SetDatabase(SQLiteDatabase paramSQLiteDatabase) {
		mDatabase = paramSQLiteDatabase;
	}
}