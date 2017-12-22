package com.nicholasnie.call_with_argora.Base;

import android.content.Context;
import android.database.Cursor;

import com.nicholasnie.call_with_argora.Model.BaseModel;
import com.nicholasnie.call_with_argora.Model.UserModel;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public interface IModel {
//    public IModel getInstance(Context context);
//    public void add(String tableName, Object[] args);
//    public Cursor query(String tableName, String[] args);

    public BaseModel getModel();
}
