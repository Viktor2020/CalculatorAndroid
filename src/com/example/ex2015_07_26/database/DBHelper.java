package com.example.ex2015_07_26.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "calculator";
    public static final String USER_TABLE = "users";
    public static final String USER_NAME_COLUMN = "name";
    public static final String USER_PASSWORD_COLUMN = "password";
    public static final String SELECTION_USER_NAME = DBHelper.USER_NAME_COLUMN + " = ?";
    public static final String SELECTION_USER_PASSWORD = DBHelper.USER_PASSWORD_COLUMN + " = ?";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_TABLE + " ( "
                + BaseColumns._ID + " integer primary key autoincrement, "
                + USER_NAME_COLUMN + " text not null, "
                + USER_PASSWORD_COLUMN + " text not null )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        System.err.println(" onUpgrade ");
    }



    public UserDao getUserDao() {
        return new UserDao(getWritableDatabase());
    }
}
