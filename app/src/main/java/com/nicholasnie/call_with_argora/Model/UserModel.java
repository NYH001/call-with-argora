package com.nicholasnie.call_with_argora.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nicholasnie.call_with_argora.Base.IModel;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public class UserModel extends BaseModel implements IModel {
    private final String TAG = "NicholasNie";

//    private final static String databaseName = "call_with_agora_database";
//    private final static int version = 1;

    private static UserModel instance = null;

    private UserModel(Context context){
        super(context, databaseName, null, version);
        Log.i(TAG, "Constructor of UserModel");
        initDatabase();
    }

    //@Override
    public static UserModel getInstance(Context context) {
        mContext = context;
        if(null == instance){
            instance = new UserModel(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    void initDatabase() {
//        tableName = "user";
        database = getReadableDatabase();
        database.execSQL("CREATE TABLE IF NOT EXISTS user (userId integer primary key autoincrement, userName varchar(20))");
        database.execSQL("CREATE TABLE IF NOT EXISTS friend (userId integer, friendName varchar(20))");
        Log.i(TAG, "initDatabase of UserModel");
    }

    @Override
    public void add(String tableName, ContentValues contentValues) {
        Log.i(TAG, "Add of UserModel");
        database.insert("user", null, contentValues);
    }

    @Override
    public Cursor query(String table, String[] colNames, String conditions, String[] colValues) {
        Cursor cursor = database.query("user", colNames, conditions, colValues, null, null, null);
        Log.i(TAG,"Query of UserModel");
        return cursor;
    }

    @Override
    public UserModel getModel() {
        return this;
    }
}
