package com.example.trial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

	Context ctx;
	String create_sql1;

	private static final String DATABASE_NAME = "mydatabase";
	private static final int DATABASE_VERSION = 1;

	SQLiteDatabase sdb;

	public MyDBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		sdb = getWritableDatabase();
		sdb = getReadableDatabase();
		ctx = context;
	}

	public void onCreate(SQLiteDatabase db) {
		create_sql1 = "CREATE TABLE usersearchdata(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "data VARCHAR NOT NULL," + "link VARCHAR NOT NULL UNIQUE);";
		db.execSQL(create_sql1);
		
		
	}

	

	
	
	
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int paramInt1, int paramInt2) {
		switch (paramInt1) {
		case 1:
			db.execSQL(create_sql1);
			// we want both updates, so no break statement here...
		
		}
		// db.execSQL("DROP TABLE  addcontact;");
		// db.execSQL("DROP TABLE smsdetails;");

		// onCreate(db);
	}

}
