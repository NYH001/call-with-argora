package com.nicholasnie.call_with_argora.Presenter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.nicholasnie.call_with_argora.Activity.CallActivity;
import com.nicholasnie.call_with_argora.Base.IModel;
import com.nicholasnie.call_with_argora.Base.IPresenter;
import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Model.UserModel;
import com.nicholasnie.call_with_argora.Service.AgoraService;

import static android.R.attr.name;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public class CallPresenter extends BasePresenter<CallActivity> implements IPresenter {
    private final String TAG = "NicholasNie";

    private IView mView;
    private IModel mModel;

    private AgoraService mAgoraService;
    public ServiceConnection connection=  new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAgoraService = ((AgoraService.AgoraBinder) service).getService();
            Log.i(TAG, "Service Connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAgoraService = null;
            Log.i(TAG, "Service Disconnected!");
        }
    };

    public CallPresenter(IView iView){
        this.mView = iView;
        this.mModel = new UserModel();
        initData();
    }

    @Override
    void initData() {

    }

    public void loginOrOut(String id){
        if(mAgoraService.getIsLogin()){
            mAgoraService.logout();
        }else {
            mAgoraService.login(id);
        }
    }

    public void call(String peerId, String channelId){
        mAgoraService.call(peerId,channelId,mView.getActivity());
    }

    public void hangUp(String channelId){
        mAgoraService.hangUp(channelId);
    }
}
