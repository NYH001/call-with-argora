package com.nicholasnie.call_with_argora.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nicholasnie.call_with_argora.App.ActivityManager;
import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Presenter.CallPresenter;
import com.nicholasnie.call_with_argora.R;
import com.nicholasnie.call_with_argora.Service.AgoraService;
import com.nicholasnie.call_with_argora.Service.NameAdapter;

import java.util.List;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public class CallActivity extends BaseActivity<CallPresenter> implements IView {
    private final String TAG = "NicholasNie";
    private final int AUDIO_REQUEST_CODE = 1;
    private final String roomName = "TestRoom";
    private String peerId;
    private List<String> friendNames;
    private NameAdapter nameAdapter;

    private Button btnCall;
    private Button btnLogout;
    private EditText etPeerId;
    private RecyclerView rvFriendName;

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
        btnLogout = (Button) findViewById(R.id.btn_logout);
        etPeerId = (EditText) findViewById(R.id.et_peerId);
        rvFriendName = (RecyclerView) findViewById(R.id.rv_friendName);

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
                basePresenter.call(peerId,roomName);
                ActivityManager activityManager = ActivityManager.getInstance();
                activityManager.putExtra("peerName",peerId);
                activityManager.putExtra("isHost",true);
                activityManager.startActivity(getApplicationContext(),new ConversationActivity());
            }
        });

    }

    @Override
    void onPrepare() {
        Intent intent = new Intent(this,AgoraService.class);
        bindService(intent,basePresenter.connection, Context.BIND_AUTO_CREATE);

        friendNames = basePresenter.getFriendNames();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFriendName.setLayoutManager(linearLayoutManager);
        nameAdapter = new NameAdapter(this,friendNames);
        rvFriendName.setAdapter(nameAdapter);
        Log.i(TAG,"Name count:   " + nameAdapter.getItemCount());

        nameAdapter.setOnItemClickListener(new NameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                etPeerId.setText(nameAdapter.getText(position));
            }
        });

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},AUDIO_REQUEST_CODE);
        }
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
