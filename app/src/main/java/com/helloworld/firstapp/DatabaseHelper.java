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
        super(context, "contactDb.db", null, 21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists contacts (id integer primary key autoincrement, name text, phone text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists contacts");
        onCreate(db);
    }

    public void insertName(Data data){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", data.getName());
        contentValues.put("phone", data.getPhone());
        long myNames = sqLiteDatabase.insert("contacts", null, contentValues);

        Log.e(TAG, "insertData: "+ myNames);
    }

    public ArrayList<String> fetchNames() {
        ArrayList<String> nameList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT name, phone FROM contacts", null);

        while (cursor.moveToNext()) {
            nameList.add(cursor.getString(0) + " <" +cursor.getString(1) + ">");
        }

//        cursor.close();

        return nameList;
    }
}
