package com.nicholasnie.call_with_argora.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nicholasnie.call_with_argora.Base.IModel;

/**
 * Created by NicholasNie on 2017/10/29.
 */

public class DatabaseModel extends SQLiteOpenHelper implements IModel {

    private static String databaseName = "call_with_agora_database";
    private static int version = 1;
    private static DatabaseModel instance = null;

    private SQLiteDatabase db;
    private Context context;

    private DatabaseModel(Context context){
        super(context,databaseName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //@Override
    public IModel getInstance(Context context) {
        this.context = context;
        if(instance == null){
            instance = new DatabaseModel(context);
        }
        return instance;
    }

    private void initDatabase() {
        db = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().getAbsolutePath() + "/databases/user.db",null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user (userid integer primary key autoincrement, username varchar(20))");
    }

//    @Override
//    public void add(String tableName, Object[] args) {
//        String sql = "";
//        switch (tableName){
//            case "user":
//                sql = "insert into user(username) values(?)";
//                break;
//            default:
//                break;
//        }
//        db.execSQL(sql, args);
//    }

//    @Override
//    public Cursor query(String tableName, String[] args) {
//        return null;
//    }
}
