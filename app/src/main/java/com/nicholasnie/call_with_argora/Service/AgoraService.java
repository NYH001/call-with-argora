package com.nicholasnie.call_with_argora.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.nicholasnie.call_with_argora.Presenter.CallPresenter;
import com.nicholasnie.call_with_argora.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.agora.AgoraAPI;
import io.agora.AgoraAPIOnlySignal;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;

/**
 * Created by NicholasNie on 2017/10/14.
 */

public class AgoraService extends Service {

//    private final String appID = getString(R.string.agora_app_id);
    private final String appID = "4a27ad9224e241679edb455122bdb265";
//    private final String appCertificate = getString(R.string.agora_app_certificate);
    private final String appCertificate = "829b5f9b256d4d3580f5e6928bfcc204";

    private final String TAG = "NicholasNie";

    private RtcEngine mRtcEngine;
    private AgoraAPIOnlySignal mAgoraAPI;

    private String myId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        initAgora();
        return new AgoraBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public class AgoraBinder extends Binder{
        public AgoraService getService(){
            Log.i(TAG,"AgoraBinder");
            return AgoraService.this;
        }
    }

    public void login(String myId){
        this.myId = myId;
        String token = calculateToken();
        mAgoraAPI.login2(appID, this.myId, token, 0, "", 60, 5);
    }

    public void logout(){
        mAgoraAPI.logout();
    }

    public void call(String peerId, String ChannelId){

    }

    private void initAgora(){
        mAgoraAPI = AgoraAPIOnlySignal.getInstance(this,appID);
        mRtcEngine = RtcEngine.create(this, appID, new IRtcEngineEventHandler() {
        });

        mAgoraAPI.callbackSet(new AgoraAPI.CallBack(){
            @Override
            public void onLoginSuccess(int uid, int fd) {
                super.onLoginSuccess(uid, fd);
                CallPresenter.isLogin = true;
                Log.i(TAG,"Login Success");
            }

            @Override
            public void onLoginFailed(int ecode) {
                super.onLoginFailed(ecode);
                Log.i(TAG,"Login Failed");
            }

            @Override
            public void onLogout(int ecode) {
                super.onLogout(ecode);
                CallPresenter.isLogin = false;
                Log.i(TAG,"Logout Success");
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

    private String calculateToken(){
        long expiredTime = System.currentTimeMillis()/1000 + 3600;
        String sign = md5hex((myId + appID + appCertificate + expiredTime).getBytes());
        return "1:" + appID + ":" + expiredTime + ":" + sign;
    }

    public static String md5hex(byte[] s){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(s);
            return hexlify(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String hexlify(byte[] data){
        char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        /**
         * 用于建立十六进制字符的输出的大写字符数组
         */
        char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        char[] toDigits = DIGITS_LOWER;
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return String.valueOf(out);

    }
}
