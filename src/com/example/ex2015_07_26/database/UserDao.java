package com.example.ex2015_07_26.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ex2015_07_26.model.User;

import java.util.List;

public class UserDao {

    private SQLiteDatabase database;

    public UserDao(SQLiteDatabase database) {
        this.database = database;
    }

    public User getUser(String userName, String password) {
        User user = null;

        Cursor c = database.query(DBHelper.USER_TABLE, new String[]{DBHelper.USER_NAME_COLUMN, DBHelper.USER_PASSWORD_COLUMN}
                ,DBHelper.USER_NAME_COLUMN + " = ? AND " +
                DBHelper.USER_PASSWORD_COLUMN + " = ? ", new String[]{userName, password}, null, null, null);

        if (c.moveToFirst()) {
            String userNam = c.getString(c.getColumnIndex(DBHelper.USER_NAME_COLUMN));
            String userPass = c.getString(c.getColumnIndex(DBHelper.USER_PASSWORD_COLUMN));
            user = new User(userNam, userPass);
        }
        c.close();
        return user;
    }

    public void insertUser(String name, String password) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.USER_NAME_COLUMN, name);
        cv.put(DBHelper.USER_PASSWORD_COLUMN, password);

        database.insert(DBHelper.USER_TABLE, null, cv);
    }

    public Cursor getCursor() {
        Cursor c = database.query(DBHelper.USER_TABLE, new String[]{DBHelper.USER_NAME_COLUMN, DBHelper.USER_PASSWORD_COLUMN}
                , null, null, null, null, null);

        return c;
    }

}
