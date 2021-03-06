package com.example.me;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Currency;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "CoreAppDB";
    private static final int DB_VER = 1;
    private static final String DB_TABLE = "Task";
    private static final String DB_COLUMN = "TaskName";
    private Gson gson = new Gson();

    public DBhelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL);", DB_TABLE, DB_COLUMN);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query  = String.format("DELETE TABLE IF EXISTS %s", DB_TABLE);
        db.execSQL(query);
        onCreate(db);

    }

    public void insertNewTask(RemindersData obj) {
        String task = gson.toJson(obj);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN,task);
        db.insertWithOnConflict(DB_TABLE,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTask(RemindersData obj) {
        String task = gson.toJson(obj);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE,DB_COLUMN + " - ?",new String[]{task});
        db.close();

    }

    public ArrayList<RemindersData> getTaskList() {
        ArrayList<RemindersData> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE,new String[] {DB_COLUMN},null,null,null,null,null);
        while(cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DB_COLUMN);
            taskList.add(gson.fromJson(cursor.getString(index), RemindersData.class));
        }
        cursor.close();
        db.close();
        return taskList;
    }
}
