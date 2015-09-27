package com.example.ex2015_07_26.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class UserContentProvider extends ContentProvider {

    private SQLiteOpenHelper helper;

    @Override
    public boolean onCreate() {
        try {
            helper = new DBHelper(getContext());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {


        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = helper.getWritableDatabase().insert(DBHelper.USER_TABLE, null, values);
        Uri uri1 = uri.buildUpon().appendPath(DBHelper.USER_TABLE).appendPath(String.valueOf(id)).build();
        return uri1;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
