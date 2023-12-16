package com.example.try_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class UserDAO {
    //dao = data access object

    public ArrayList<User> getUsers(Context mCtx){
        ArrayList<User> results = new ArrayList<>();

        MySqliteHelper helper = new MySqliteHelper(mCtx);
        SQLiteDatabase db = helper.getReadableDatabase();

        String selectionString = null;
        String[] selectionArgs = null;

        Cursor resultCursor = db.query(
                        helper.TABLE_MS_USER, null,
                        selectionString, selectionArgs,
                        null, null, null);

        while(resultCursor.moveToNext()){
            User tempUser = new User();
            tempUser.username = resultCursor.getString(0);
            tempUser.password = resultCursor.getString(1);
            results.add(tempUser);
        }

        return  results;
    }

    public void insertUser(Context mCtx, User user){
        Log.v("insertUser","1");
        MySqliteHelper helper = new MySqliteHelper(mCtx);
        Log.v("insertUser","2");
        SQLiteDatabase db = helper.getWritableDatabase();
        Log.v("insertUser","3");
        ContentValues cv = new ContentValues();
        Log.v("insertUser","4");
        cv.put(helper.FIELD_MS_USER_USERNAME, user.username);
        Log.v("insertUser","5");
        cv.put(helper.FIELD_MS_USER_PASSWORD, user.password);
        Log.v("insertUser","6");
        db.insertWithOnConflict(helper.TABLE_MS_USER, null,
                cv, SQLiteDatabase.CONFLICT_REPLACE);
        Log.v("insertUser","7");
        db.close();
        Log.v("insertUser","8");
    }
}
