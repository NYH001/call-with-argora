package com.nicholasnie.call_with_argora.Presenter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;

import com.nicholasnie.call_with_argora.Activity.CallActivity;
import com.nicholasnie.call_with_argora.App.ActivityManager;
import com.nicholasnie.call_with_argora.Base.IModel;
import com.nicholasnie.call_with_argora.Base.IPresenter;
import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Model.BaseModel;
import com.nicholasnie.call_with_argora.Model.UserModel;
import com.nicholasnie.call_with_argora.Service.AgoraService;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public class CallPresenter extends BasePresenter<CallActivity> implements IPresenter {
    private final String TAG = "NicholasNie";

//    private IView mView;
//    private IModel mModel;

    private String myId;
    private int intId;
    private BaseModel model;
    private List<String> friendNames;

    private AgoraService mAgoraService;
    public ServiceConnection connection=  new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAgoraService = ((AgoraService.AgoraBinder) service).getService();
            mAgoraService.setMyActivity(mView.getActivity());
            Log.i(TAG, "Service Connected!");
            login(myId);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAgoraService = null;
            Log.i(TAG, "Service Disconnected!");
        }
    };

    public CallPresenter(IView iView){
        mView = iView;
        mModel = UserModel.getInstance(iView.getActivity().getApplicationContext());
        model = mModel.getModel();
        initData();
    }

    @Override
    void initData() {
        myId = getId();
//        login(myId);
    }

//    public void loginOrOut(String id){
//        if(mAgoraService.getIsLogin()){
//            mAgoraService.logout();
//        }else {
//            mAgoraService.login(id);
//        }
//    }

    public String getId(){
        String string = "";
        ActivityManager activityManager = ActivityManager.getInstance();
        int id = activityManager.getInt("userId");
        intId = id;
        Cursor cursor = model.query("user", new String[]{"userName"},"userId=?",new String[]{id+""});
        cursor.moveToFirst();
        string = cursor.getString(cursor.getColumnIndex("userName"));
        Log.i(TAG,"User Name: " + string);
        return string;
    }

    public void login(String id){
        mAgoraService.login(id);
    }

    public void logout(){
        mAgoraService.logout();
    }

    public void call(String peerId, String channelId){

        mAgoraService.call(peerId,channelId);

        for(int i = 0; i < friendNames.size(); i++){
            if( peerId.equals(friendNames.get(i))){
                return;
            }
        }
        Log.i(TAG,"add friend     " + peerId);
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId",intId);
        contentValues.put("friendName",peerId);
        model.add("friend",contentValues);
    }

    public void hangUp(){
        mAgoraService.hangUp();
    }

    public List<String> getFriendNames(){
        ArrayList<String> temp = new ArrayList<String>();
        Cursor cursor = model.query("friend",new String[]{"friendName"},"userId=?",new String[]{intId+""});
        if(cursor.moveToFirst()){
            for (int i = 0; i < cursor.getCount(); i++) {
                temp.add(cursor.getString(cursor.getColumnIndex("friendName")));
                cursor.moveToNext();
            }
        }
        friendNames = temp;
        return friendNames;
    }
}
