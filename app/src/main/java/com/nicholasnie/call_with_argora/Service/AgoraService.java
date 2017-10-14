package com.nicholasnie.call_with_argora.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.nicholasnie.call_with_argora.R;

import io.agora.AgoraAPI;
import io.agora.AgoraAPIOnlySignal;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;

/**
 * Created by NicholasNie on 2017/10/14.
 */

public class AgoraService extends Service {

    private final String appID = getString(R.string.agora_app_id);
    private final String appCertificate = getString(R.string.agora_app_certificate);

    private RtcEngine mRtcEngine;
    private AgoraAPIOnlySignal mAgoraAPI;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        initAgora();
        return new AgoraBinder();
    }

    public class AgoraBinder extends Binder{
        public AgoraService getService(){
            return AgoraService.this;
        }
    }

    public void call(String myId, String peerId, String ChannelId){

    }

    private void initAgora(){
        mAgoraAPI = AgoraAPIOnlySignal.getInstance(this,appID);
        mRtcEngine = RtcEngine.create(this, appID, new IRtcEngineEventHandler() {
        });

        mAgoraAPI.callbackSet(new AgoraAPI.CallBack(){
            @Override
            public void onLoginSuccess(int uid, int fd) {
                super.onLoginSuccess(uid, fd);
            }

            @Override
            public void onLoginFailed(int ecode) {
                super.onLoginFailed(ecode);
            }

            @Override
            public void onLogout(int ecode) {
                super.onLogout(ecode);
            }

            @Override
            public void onLog(String txt) {
                super.onLog(txt);
            }

            @Override
            public void onChannelJoined(String channelID) {
                super.onChannelJoined(channelID);
            }

            @Override
            public void onChannelJoinFailed(String channelID, int ecode) {
                super.onChannelJoinFailed(channelID, ecode);
            }

            @Override
            public void onChannelLeaved(String channelID, int ecode) {
                super.onChannelLeaved(channelID, ecode);
            }

            @Override
            public void onInviteReceived(String channelID, String account, int uid, String extra) {
                super.onInviteReceived(channelID, account, uid, extra);
            }

            @Override
            public void onChannelUserJoined(String account, int uid) {
                super.onChannelUserJoined(account, uid);
            }

            @Override
            public void onChannelUserLeaved(String account, int uid) {
                super.onChannelUserLeaved(account, uid);
            }

            @Override
            public void onChannelUserList(String[] accounts, int[] uids) {
                super.onChannelUserList(accounts, uids);
            }

            @Override
            public void onInviteMsg(String channelID, String account, int uid, String msgType, String msgData, String extra) {
                super.onInviteMsg(channelID, account, uid, msgType, msgData, extra);
            }

            @Override
            public void onInviteReceivedByPeer(String channelID, String account, int uid) {
                super.onInviteReceivedByPeer(channelID, account, uid);
            }

            @Override
            public void onInviteAcceptedByPeer(String channelID, String account, int uid, String extra) {
                super.onInviteAcceptedByPeer(channelID, account, uid, extra);
            }

            @Override
            public void onInviteEndByMyself(String channelID, String account, int uid) {
                super.onInviteEndByMyself(channelID, account, uid);
            }

            @Override
            public void onInviteEndByPeer(String channelID, String account, int uid, String extra) {
                super.onInviteEndByPeer(channelID, account, uid, extra);
            }
        });

        mRtcEngine.enableAudioVolumeIndication(1000,3);
    }
}
