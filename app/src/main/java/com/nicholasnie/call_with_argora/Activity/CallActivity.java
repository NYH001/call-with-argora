package com.nicholasnie.call_with_argora.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Presenter.CallPresenter;
import com.nicholasnie.call_with_argora.R;
import com.nicholasnie.call_with_argora.Service.AgoraService;

import io.agora.AgoraAPI;
import io.agora.AgoraAPIOnlySignal;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public class CallActivity extends BaseActivity<CallPresenter> implements IView {
    private final String TAG = "CallActivity";

    private final String roomName = "TestRoom";
    private String myId;
    private String peerId;

    private RtcEngine mRtcEngine;
    private AgoraAPIOnlySignal mAgoraAPI;

    private Button btnCall;
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
        btnCall = (Button) findViewById(R.id.btn_test);
        etMyId = (EditText) findViewById(R.id.et_myId);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myId = etMyId.getText().toString();
                peerId = etPeerId.getText().toString();
            }
        });

        Intent intent = new Intent(this,AgoraService.class);
    }

    @Override
    void onPrepare() {

    }
}
