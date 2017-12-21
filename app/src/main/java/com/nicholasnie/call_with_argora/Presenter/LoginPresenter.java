package com.nicholasnie.call_with_argora.Presenter;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.nicholasnie.call_with_argora.Activity.LoginActivity;
import com.nicholasnie.call_with_argora.Base.IPresenter;
import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Model.UserModel;

/**
 * Created by NicholasNie on 2017/10/24.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> implements IPresenter {
    private final String TAG = "NicholasNie";

    private UserModel model;

    public LoginPresenter(IView iView) {
        mView = iView;
        mModel = UserModel.getInstance(iView.getActivity().getApplicationContext());
        model = mModel.getModel();
    }

    @Override
    void initData() {

    }

    public void LoginOrSignUp(String userId){
        Cursor cursor = model.query("user", null, null, null);
        if(0 == cursor.getCount()){
            ContentValues contentValues = new ContentValues();
            contentValues.put("userName",userId);
            model.add("user", contentValues);
        }else {
            int i;
            cursor.moveToFirst();
            for (i = 0; i < cursor.getCount(); i++){
                if (userId.equals(cursor.getString(cursor.getColumnIndex("userName")))){
                    break;
                }
                cursor.moveToNext();
            }
            if (i == cursor.getCount() - 1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("userName", userId);
                model.add("user", contentValues);
            }
        }
        Log.i(TAG,cursor.getCount()+"");
    }
}
