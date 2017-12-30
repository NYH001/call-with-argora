package com.nicholasnie.call_with_argora.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nicholasnie.call_with_argora.App.ActivityManager;
import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Presenter.CallPresenter;
import com.nicholasnie.call_with_argora.R;
import com.nicholasnie.call_with_argora.Service.AgoraService;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public class CallActivity extends BaseActivity<CallPresenter> implements IView {
    private final String TAG = "NicholasNie";
    private final int AUDIO_REQUEST_CODE = 1;
    private final String roomName = "TestRoom";
//    private String myId;
    private String peerId;

    private Button btnCall;
//    private Button btnLogin;
    private Button btnLogout;
//    private Button btnHangUp;
//    private EditText etMyId;
    private EditText etPeerId;

    @Override
    CallPresenter initPresenter() {
        return new CallPresenter(this);
    }

    @Override
    int getLayout() {
        return R.layout.call_activity;
    }

    @Override
    void initView() {
//        btnLogin = (Button) findViewById(R.id.btn_login);
        btnCall = (Button) findViewById(R.id.btn_test);
        btnLogout = (Button) findViewById(R.id.btn_logout);
//        btnHangUp = (Button) findViewById(R.id.btn_hangUp);
//        etMyId = (EditText) findViewById(R.id.et_myId);
        etPeerId = (EditText) findViewById(R.id.et_peerId);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG,"Clicked!");
//                myId = etMyId.getText().toString();
////                basePresenter.loginOrOut(myId);
//                basePresenter.login(myId);
//            }
//        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basePresenter.logout();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myId = etMyId.getText().toString();
                peerId = etPeerId.getText().toString();
//                basePresenter.call(peerId,roomName);
                ActivityManager activityManager = ActivityManager.getInstance();
                activityManager.putExtra("peerName",peerId);
                activityManager.startActivity(getApplicationContext(),new ConversationActivity());
            }
        });

//        btnHangUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                basePresenter.hangUp();
//            }
//        });
    }

    @Override
    void onPrepare() {
        Intent intent = new Intent(this,AgoraService.class);
        bindService(intent,basePresenter.connection, Context.BIND_AUTO_CREATE);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},AUDIO_REQUEST_CODE);
        }

//        myId = basePresenter.getId();
//        basePresenter.login(myId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        basePresenter.hangUp();
        unbindService(basePresenter.connection);
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
