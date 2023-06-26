package com.helloworld.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "myDb.db", null, 21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists names (id integer primary key autoincrement, name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists names");
        onCreate(db);
    }

    public void insertName(Data data){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", data.getName());
        long myNames = sqLiteDatabase.insert("names", null, contentValues);

        Log.e(TAG, "insertData: "+ myNames);
    }

    public ArrayList<String> fetchNames() {
        ArrayList<String> nameList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT name FROM names", null);

        while (cursor.moveToNext()) {
            nameList.add(cursor.getString(0));
        }

//        cursor.close();

        return nameList;
    }
}
