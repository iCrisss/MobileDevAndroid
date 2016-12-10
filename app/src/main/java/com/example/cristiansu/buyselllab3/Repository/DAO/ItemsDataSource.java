package com.example.cristiansu.buyselllab3.Repository.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.Item;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.User;
import com.example.cristiansu.buyselllab3.Repository.MySQLDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CristianSu on 12/6/2016.
 */

public class ItemsDataSource {
    private SQLiteDatabase database;
    private MySQLDatabase dbHelper;
    private String[] allColumns = {"_id", "user_id", "name", "price"};

    public ItemsDataSource(Context context) {
        dbHelper = new MySQLDatabase(context);
    }

    public void openReadable() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void openWriteable() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Item createItem(long userId, String name, double price) throws SQLException{
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", userId);
        contentValues.put("name", name);
        contentValues.put("price", price);

        long insertId = database.insert(MySQLDatabase.ITEMS_TABLE, null, contentValues);

        Cursor cursor = database.query(MySQLDatabase.ITEMS_TABLE, allColumns, "_id = " + insertId, null,null,null,null);
        cursor.moveToFirst();
        Item item = cursorToItem(cursor);
        cursor.close();
        return item;
    }

    public Item updateItem(Item item, String name, double price) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);

        database.update(MySQLDatabase.ITEMS_TABLE, contentValues, "_id = " + item.getId(),null);

        Cursor cursor = database.query(MySQLDatabase.ITEMS_TABLE, allColumns, "_id = " + item.getId(), null,null, null, null);
        cursor.moveToFirst();
        Item newItem = cursorToItem(cursor);
        cursor.close();
        return newItem;
    }

    public void deleteItem(Item item) throws SQLException{
        database.delete(MySQLDatabase.ITEMS_TABLE, "_id = " + item.getId(), null);
    }

    public List<Item> getAllItems() throws SQLException {
        List<Item> itemList = new ArrayList<>();
        Cursor cursor = database.query(MySQLDatabase.ITEMS_TABLE, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Item item = cursorToItem(cursor);
            itemList.add(item);
            cursor.moveToNext();
        }

        cursor.close();
        return itemList;
    }

    public List<Item> getItemsForUser(User user) throws SQLException{
        List<Item> itemList = new ArrayList<>();
        Cursor cursor = database.query(MySQLDatabase.ITEMS_TABLE, allColumns, "user_id = " + user.getId(), null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Item item = cursorToItem(cursor);
            itemList.add(item);
            cursor.moveToNext();
        }

        cursor.close();
        return itemList;
    }

    public Item getItem(User user, String name) throws SQLException,CursorIndexOutOfBoundsException {
        Cursor cursor = database.query(MySQLDatabase.ITEMS_TABLE, allColumns, "user_id = " + user.getId() + ", name = " + name, null ,null, null, null);
        cursor.moveToFirst();
        Item item = cursorToItem(cursor);
        cursor.close();
        return item;
    }





    private Item cursorToItem(Cursor cursor) throws SQLException {
        Item item = new Item();
        item.setId(cursor.getLong(0));
        item.setUser_id(cursor.getLong(1));
        item.setName(cursor.getString(2));
        item.setPrice(cursor.getDouble(3));
        return item;
    }
}
