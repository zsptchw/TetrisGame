package com.chw.tetrisgame.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.chw.tetrisgame.model.UserRank;

public class SQLiteDateUtil extends SQLiteOpenHelper {
	// 增加一张Rank排名表，主键是ID，字段有name text类型，score 整型。
	public static final String CREATE_TABLE_RANK = "create table Rank("
			+ "score integer)";
	private static Context mContext;
	private static List<UserRank> mListRank;
	private static SQLiteDateUtil mSQLiteDateUtil;

	private SQLiteDateUtil(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "rank.db", null, version);
		mContext = context;
		mListRank = new ArrayList<UserRank>();
	}

	public synchronized static SQLiteDateUtil getInstance(Context context) {
		if (mSQLiteDateUtil == null) {
			mSQLiteDateUtil = new SQLiteDateUtil(context, "rank.db", null, 1);
		}
		return mSQLiteDateUtil;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_RANK);
		db.execSQL("INSERT INTO RANK VALUES (10)");
		Log.i("aaaaa","aaaa");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	//
	// public void insert(String name,int score){
	// SQLiteDatabase db=this.getWritableDatabase();
	// db.execSQL("insert into Rank (name,score) values("+name+","+score+")");
	// Log.i("aaaaa","aaaa");
	// }
	//
	// public void delete(){
	// SQLiteDatabase db = this.getWritableDatabase();
	// db.execSQL("delete from Rank");
	// }
	/**
	 * 得到一个排序好从大到小List<UserRank>
	 * 
	 * @return
	 */
	public int getListRank() {
		// mListRank.clear();
		int score = 0;
		SQLiteDatabase db = mSQLiteDateUtil.getWritableDatabase();
		Cursor c = db.query("Rank", null, null, null, null, null, "score desc");
		Log.i("qwe",c.getColumnCount()+"");
		c.moveToFirst();
		score = c.getInt(c.getColumnIndex("score"));
		Log.i("score", score + "");
		c.close();
		return score;

	}

	public void setMaxScore(long maxScore) {
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL("delete from Rank");
		db.execSQL("INSERT INTO RANK VALUES ("+(int)maxScore+")");
	}

}
