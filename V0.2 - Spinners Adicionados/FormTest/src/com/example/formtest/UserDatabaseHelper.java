package com.example.formtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "usuarios.db";
	private static final String TABLE_NAME = "usuarios";
	private static final String COLUMN_NAME = "nome";
	private static final String COLUMN_PASSWORD = "password";
	protected SQLiteDatabase db;
	
	private static final String TABLE_CREATE = "create table "+TABLE_NAME+" ( "+COLUMN_NAME+" TEXT , "+
	COLUMN_PASSWORD+" TEXT);";
	
	public UserDatabaseHelper(Context context)
	{
		super(context , DATABASE_NAME , null , DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(TABLE_CREATE);
		this.db = db;
	}

	public void insertUsuario(Usuario c)
	{
		db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME , c.getNome());
		values.put(COLUMN_PASSWORD , c.getPassword());
		
		db.insert(TABLE_NAME, null, values);
		db.close();
	}
	
	public String searchPass(String uname)
	{
		db = this.getReadableDatabase();
		String query = "select nome, password from "+TABLE_NAME;
		Cursor cursor = db.rawQuery(query , null);
		
		String username, pass;
		
		pass = "not found";
		
		if(cursor.moveToFirst())
		{
			do{
				username = cursor.getString(0);				
				if(username.equals(uname)){
					pass = cursor.getString(1);
					break;
				}
				
			}while(cursor.moveToNext());
		}
		
		return pass;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
		db.execSQL(query);
		this.onCreate(db);	
	}
	
}