package com.nicholasnie.call_with_argora.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Presenter.ConversationPresenter;
import com.nicholasnie.call_with_argora.R;
import com.nicholasnie.call_with_argora.Service.AgoraService;

/**
 * Created by NicholasNie on 2017/12/28.
 */

public class ConversationActivity extends BaseActivity<ConversationPresenter> implements IView {

    private final String TAG = "NicholasNie";
    private boolean isHost = false;

    private TextView tvPeerName;
    private Button btnAnswer;
    private Button btnReject;

    public void setHost(boolean b){
        this.isHost = b;
        setBtnAnswerText(b);
        setBtnRejectText(b);
    }

    private void setBtnAnswerText(boolean b){
        if(b){
            btnAnswer.setText("HandsFree");
        }
    }

    private void setBtnRejectText(boolean b){
        if(b){
            btnReject.setText("HangUp");
        }
    }

    @Override
    int getLayout() {
        return R.layout.conversation_activity;
    }

    @Override
    ConversationPresenter initPresenter() {
        return new ConversationPresenter(this);
    }

    @Override
    void onPrepare() {
        Intent intent = new Intent(this,AgoraService.class);
        bindService(intent,basePresenter.connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    void initView() {
        tvPeerName = (TextView)findViewById(R.id.tv_peerName);
        btnAnswer = (Button)findViewById(R.id.btn_answer);
        btnReject = (Button)findViewById(R.id.btn_reject);

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basePresenter.answer();
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basePresenter.reject();
            }
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        unbindService(basePresenter.connection);
        super.onDestroy();
    }
}
