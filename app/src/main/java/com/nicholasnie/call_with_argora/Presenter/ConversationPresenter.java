package com.nicholasnie.call_with_argora.Presenter;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.nicholasnie.call_with_argora.Activity.ConversationActivity;
import com.nicholasnie.call_with_argora.App.ActivityManager;
import com.nicholasnie.call_with_argora.Base.IPresenter;
import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Model.UserModel;
import com.nicholasnie.call_with_argora.Service.AgoraService;

/**
 * Created by NicholasNie on 2017/12/28.
 */

public class ConversationPresenter extends BasePresenter<ConversationActivity> implements IPresenter {

    private final String TAG = "NicholasNie";

    private AgoraService mAgoraService;
    public ServiceConnection connection=  new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAgoraService = ((AgoraService.AgoraBinder) service).getService();
            Log.i(TAG, "Another Service Connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAgoraService = null;
            Log.i(TAG, "Another Service Disconnected!");
        }
    };

    private String peerName;
    private String roomName;

    public ConversationPresenter(IView view){
        mView = view;
        mModel = UserModel.getInstance(view.getActivity().getApplicationContext());
        initData();
    }

    @Override
    void initData() {
        ActivityManager activityManager = ActivityManager.getInstance();
        peerName = activityManager.getString("peerName");

        roomName = "TestRoom";
    }

    public void answer(){

    }

    public void reject(){

    }

    public void call(){

    }

    public void hangUp(){

    }

    public void handsFree(){

    }
}
