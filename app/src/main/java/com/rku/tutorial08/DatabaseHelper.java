package com.rku.tutorial08;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Struct;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "registration";
    public static final String TABLE = "student";
    public static final String COL_1 = "id";
    public static final String COL_2 = "fname";
    public static final String COL_3 = "lname";
    public static final String COL_4 = "username";
    public static final String COL_5 = "password";
    public static final String COL_6 = "branch";
    public static final String COL_7 = "gender";
    public static final String COL_8 = "city";
    public static final String COL_9 = "status";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE+
                "("+ COL_1 + " integer primary key autoincrement,"
                + COL_2+" text,"
                + COL_3+" text,"
                + COL_4+" text,"
                + COL_5+" text,"
                + COL_6+" text,"
                + COL_7+" text,"
                + COL_8+" text,"
                + COL_9+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    public boolean register(String fname, String lname, String email, String pass, String branch, String gender, String city, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2,fname);
        values.put(COL_3,lname);
        values.put(COL_4,email);
        values.put(COL_5,pass);
        values.put(COL_6,branch);
        values.put(COL_7,gender);
        values.put(COL_8,city);
        values.put(COL_9,status);
        long result = db.insert(TABLE,null,values);
        if(result==-1) return false;
        else return true;
    }

    public boolean validation(String e, String p) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Select * from table
        Cursor result = db.query(TABLE,
                new String[]{COL_4,COL_5},
                "username = ? and password = ?",
                new String[]{e,p},
                null,
                null,
                null);

        return ((result!=null && result.getCount()>0)?true:false);
    }

    public ArrayList<String> getUserList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE,
                new String[]{COL_4},
                null,
                null,
                null,
                null,
                null
        );
        ArrayList<String> list = new ArrayList<String>();
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                list.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        return list;
    }

    public Cursor getSingleUserData(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE,
                null,
                "username = ?",
                new String[]{username},
                null,
                null,
                null
        );

        return cursor;
    }
}
