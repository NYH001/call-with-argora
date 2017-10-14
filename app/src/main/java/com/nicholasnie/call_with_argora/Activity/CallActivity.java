package com.nicholasnie.call_with_argora.Activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Presenter.CallPresenter;
import com.nicholasnie.call_with_argora.R;
import com.nicholasnie.call_with_argora.Service.AgoraService;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public class CallActivity extends BaseActivity<CallPresenter> implements IView {
    private final String TAG = "NicholasNie";

    private final String roomName = "TestRoom";
    private String myId;
    private String peerId;

    private Button btnCall;
    private Button btnLogin;
    private EditText etMyId;
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
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnCall = (Button) findViewById(R.id.btn_test);
        etMyId = (EditText) findViewById(R.id.et_myId);
        etPeerId = (EditText) findViewById(R.id.et_peerId);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Clicked!");
                myId = etMyId.getText().toString();
                basePresenter.login(myId);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myId = etMyId.getText().toString();
                peerId = etPeerId.getText().toString();
            }
        });
    }

    @Override
    void onPrepare() {
        Intent intent = new Intent(this,AgoraService.class);
        bindService(intent,basePresenter.connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(basePresenter.connection);
    }
}
