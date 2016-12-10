package com.example.cristiansu.buyselllab3.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by CristianSu on 12/5/2016.
 */

public class MySQLDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "buysell.db";

    public static final String USERS_TABLE = "users";
    public static final String ITEMS_TABLE = "items";

    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + USERS_TABLE + " ( _id integer primary key autoincrement, " +
            "name text not null, password text not null);";

    private static final String CREATE_ITEMS_TABLE = "CREATE TABLE " + ITEMS_TABLE + " ( _id integer primary key autoincrement, " +
            "user_id integer not null, name text not null, price real not null);";

    public MySQLDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLDatabase.class.getName(), "Upgrading database from version " + oldVersion +
                " to version " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
        onCreate(db);
    }
}
