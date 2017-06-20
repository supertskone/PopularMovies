package com.example.android.miwok.FilmsModule.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

/**
 * Created by EXLRT-Nemanja on 1/24/2017.
 */

public class FavoriteDataSource extends SQLiteOpenHelper{

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_FAVORITE };

  //  public FavoriteDataSource(Context context) {
     //   super(context);
      // dbHelper = new MySQLiteHelper(context);
  //  }

    public FavoriteDataSource(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        dbHelper = new MySQLiteHelper(context);
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Favorite createFavorite(String favorite) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_FAVORITE, favorite);
        long insertId = database.insert(MySQLiteHelper.TABLE_FAVORITES, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_FAVORITES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Favorite newFavorite = cursorToFavorite(cursor);
        cursor.close();
        return newFavorite;
    }

    public void deleteFavorite(String favorite) {
        System.out.println("Favorite deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_FAVORITES, MySQLiteHelper.COLUMN_FAVORITE
                + " = " + favorite, null);
    }
    public void deleteFav(String name)
    {
        try {
            database.delete(MySQLiteHelper.TABLE_FAVORITES, MySQLiteHelper.COLUMN_FAVORITE + " LIKE " + "'" + name + "'", null);
        }
        catch(Exception e){
            String p = e.getMessage();
        }
    }

    public boolean isFavorite(String name) {
        String Query = "Select * from " + MySQLiteHelper.TABLE_FAVORITES + " where " + MySQLiteHelper.COLUMN_FAVORITE + " LIKE " + "'" + name + "'";
        Cursor cursor = database.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public List<Favorite> getAllFavorites() {
        List<Favorite> favorites = new ArrayList<Favorite>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_FAVORITES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Favorite favorite = cursorToFavorite(cursor);
            favorites.add(favorite);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return favorites;
    }

    public List<Favorite> getFavoriteByName(String favourite) {
        List<Favorite> favorites = new ArrayList<Favorite>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_FAVORITES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Favorite favorite = cursorToFavorite(cursor);
            favorites.add(favorite);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return favorites;
    }

    private Favorite cursorToFavorite(Cursor cursor) {
        Favorite favorite = new Favorite();
        favorite.setId(cursor.getLong(0));
        favorite.setFavorite(cursor.getString(1));
        return favorite;
    }
}
