package com.example.cristiansu.buyselllab3.Repository.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.User;
import com.example.cristiansu.buyselllab3.Repository.MySQLDatabase;

/**
 * Created by CristianSu on 12/5/2016.
 */

public class UsersDataSource {
    private SQLiteDatabase database;
    private MySQLDatabase dbHelper;
    private String[] allColumns = { "_id", "name", "password"};

    public UsersDataSource(Context context){
        dbHelper = new MySQLDatabase(context);
    }

    public void openReadable() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void openWriteable() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close () {
        dbHelper.close();
    }


    public User getUser(String name, String password) throws SQLException{
        Cursor cursor = database.query(MySQLDatabase.USERS_TABLE, allColumns, "name = '" + name + "' and password = '" + password + "'"
                , null, null,null,null);
        cursor.moveToFirst();
        User user = cursorToUser(cursor);
        cursor.close();
        return user;
    }

    public User createUser(String name, String password) throws SQLException{
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", password);
        long insertId = database.insert(MySQLDatabase.USERS_TABLE, null, contentValues);

        return new User(insertId, name, password);
    }

    public User updateUser(String name, String password, String newName, String newPassword) throws SQLException{
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", newName);
        contentValues.put("password", newPassword);
        database.update(MySQLDatabase.USERS_TABLE, contentValues, "name=? and password=?", new String[] {name, password});

        Cursor cursor = database.query(MySQLDatabase.USERS_TABLE, allColumns, "name=? and password=?"
                , new String[] {newName, newPassword}, null,null,null);
        cursor.moveToFirst();
        User user = cursorToUser(cursor);
        cursor.close();
        return user;
    }

    public User updateUser(User user, String newName, String newPassword) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", newName);
        contentValues.put("password", newPassword);
        database.update(MySQLDatabase.USERS_TABLE, contentValues, "_id=" + user.getId(),null);

        Cursor cursor = database.query(MySQLDatabase.USERS_TABLE, allColumns, "_id=" + user.getId()
                , null, null,null,null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public void deleteUser(String name, String password) throws SQLException {
        database.delete(MySQLDatabase.USERS_TABLE, "name=? and password=?", new String[] {name, password});
    }

    public void deleteUser(User user) throws SQLException {
        database.delete(MySQLDatabase.USERS_TABLE, "_id = " + user.getId(), null);
    }


    private User cursorToUser(Cursor cursor) throws SQLException, CursorIndexOutOfBoundsException {
        if(!cursor.moveToFirst()){
            throw new SQLException("Did not find specified user");
        }

        User user = new User();
        user.setId(cursor.getLong(0));
        user.setName(cursor.getString(1));
        user.setPassword(cursor.getString(2));

        return user;
    }
}
