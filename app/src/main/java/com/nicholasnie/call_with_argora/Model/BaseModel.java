package com.nicholasnie.call_with_argora.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NicholasNie on 2017/12/21.
 */

public abstract class BaseModel extends SQLiteOpenHelper {
    protected BaseModel(Context context, String databaseName, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context, databaseName, cursorFactory, version);
    }

//    protected String tableName;
    protected SQLiteDatabase database;
    protected static Context mContext;

    protected final static String databaseName = "call_with_agora_database";
    protected final static int version = 1;

    abstract void initDatabase();
    public abstract void add(String tableName, ContentValues contentValues);
    public abstract Cursor query(String tableName, String[] colNames, String conditions, String[] colValues);
}
