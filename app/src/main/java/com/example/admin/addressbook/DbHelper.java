package com.example.admin.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(Context context) {
        super(context, "Contact.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contact_details(_id INTEGER primary key AUTOINCREMENT,NAME TEXT,PHONE TEXT,EMAIL TEXT,ADDRESS TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contact_details");
    }

    public boolean insertdata(String name, String phone, String email, String address) {
            System.out.println("insertanju");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("PHONE", phone);
        contentValues.put("EMAIL", email);
        contentValues.put("ADDRESS", address);
        db.insert("contact_details", null, contentValues);
        return true;

    }

    public ArrayList getAllContacts() {
        ArrayList  list= new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor set = db.rawQuery("select * from contact_details", null);
        set.moveToFirst();
        while (set.isAfterLast() == false) {
            list.add(set.getString(set.getColumnIndex("NAME")));
            System.out.println(set.getInt(set.getColumnIndex("_id")));
            set.moveToNext();

        }
        return list;

    }

    public Cursor getResult(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("get result");

        return db.rawQuery("select * from contact_details where _id=" + id, null);


    }


    public boolean update(int id, String name, String phone, String email, String address) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("PHONE", phone);
        contentValues.put("EMAIL", email);
        contentValues.put("ADDRESS", address);
        db.update("contact_details ", contentValues, "_id=?", new String[]{
                Integer.toString(id)
        });
        return true;

    }

    public Integer deletecontacts(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contact_details","_id=?",new String[]{Integer.toString(id)});



    }

    public int getId(String name){
        SQLiteDatabase db =this.getReadableDatabase();
        System.out.println("hello anju");
        Cursor set =db.rawQuery("select * from contact_details where NAME='"+name+"'", null);
        set.moveToFirst();
        System.out.println("id is="+set.getInt(set.getColumnIndex("_id"))
);
        return set.getInt(set.getColumnIndex("_id"));


    }
}



