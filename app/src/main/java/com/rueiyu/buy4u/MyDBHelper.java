package com.rueiyu.buy4u;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RueiYu on 2018/2/5.
 */

public class MyDBHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static MyDBHelper instance;

    public static MyDBHelper getInstance(Context context){
        if(instance ==null){
            instance = new MyDBHelper(context, "buy4u.db", null, DB_VERSION);
        }
        return instance;
    }

    private MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE groups ( " +
            " _id INTEGER PRIMARY KEY, "+
            " name NVARCHAR )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
