package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key,password text,name text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");

    }

    //inserting into db
    public boolean insert(String email,String password,String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("name",name);
        long ins=db.insert("user",null,contentValues);
        if(ins==-1)
            return false;
        else
            return true;
    }

    //checking if the new user can be inserted
    public boolean ck_usr_exists(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from user where email=?",new String[]{email});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }

    //check valid user
    public boolean ck_val_usr(String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from user where email=? and password=?",new String[]{email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
