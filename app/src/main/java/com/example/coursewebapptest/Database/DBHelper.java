package com.example.coursewebapptest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserData.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserMaster.User.TABLE_NAME + " (" +
                    UserMaster.User._ID + " INTEGER PRIMARY KEY," +
                    UserMaster.User.COLUMN_NAME_USERNAME + " TEXT," +
                    UserMaster.User.COLUMN_NAME_PASSWORD + " TEXT," +
                    UserMaster.User.COLUMN_NAME_TYPE + " TEXT," +
                    UserMaster.User.COLUMN_NAME_VEHICLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserMaster.User.TABLE_NAME;


    public boolean addUser(String mUserName, String mPassword, String mType, String mVehicle) {

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserMaster.User.COLUMN_NAME_USERNAME, mUserName);
        values.put(UserMaster.User.COLUMN_NAME_PASSWORD, mPassword);
        values.put(UserMaster.User.COLUMN_NAME_TYPE, mType);
        values.put(UserMaster.User.COLUMN_NAME_VEHICLE, mVehicle);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserMaster.User.TABLE_NAME, null, values);

        if (newRowId >= 0) {
            return true;
        } else {
            return false;
        }


    }

    /*
     *
     * Read Data
     *
     */

    public List readAll() {
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserMaster.User.COLUMN_NAME_USERNAME
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = UserMaster.User.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {"My Title"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserMaster.User.COLUMN_NAME_USERNAME + " ASC";

        Cursor cursor = db.query(
                UserMaster.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List usersList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.User.COLUMN_NAME_USERNAME));
            usersList.add(itemId);
        }
        cursor.close();


        return usersList;

    }

    public List searchUser(String mUsername){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserMaster.User.COLUMN_NAME_USERNAME
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = UserMaster.User.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = { mUsername };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserMaster.User.COLUMN_NAME_USERNAME + " DESC";

        Cursor cursor = db.query(
                UserMaster.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserMaster.User.COLUMN_NAME_USERNAME));
            itemIds.add(itemId);
        }
        cursor.close();

        return itemIds;



    }

}

