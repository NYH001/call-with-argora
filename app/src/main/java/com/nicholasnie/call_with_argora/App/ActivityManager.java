package com.nicholasnie.call_with_argora.App;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by NicholasNie on 2017/10/24.
 */

public class ActivityManager {
    private static final String TAG = "NicholasNie";

    private static ActivityManager instance = null;

    private HashMap<String,Object> extraInfo = new HashMap<String, Object>();
    private Stack<Activity> activities = new Stack<Activity>();

    private ActivityManager(){

    }

    public static ActivityManager getInstance(){
        if (null == instance){
            instance = new ActivityManager();
        }
        return instance;
    }

    public Activity getPresentActivity(){
        return null;
    }

    public void startActivity(Context context, Activity activity){
        Intent intent = new Intent(context,activity.getClass());
        context.startActivity(intent);
    }

    public void finishActivity(Activity activity){
        activity.finish();
    }

    private void pushActivity(Activity activity){

    }

    private void pullActivity(Activity activity){

    }

    public void putExtra(String key, Object value){
        extraInfo.put(key,value);
    }

    public int getInt(String key){
        int value = 0;
        Object objectValue = extraInfo.get(key);
        if (null == objectValue){

        }else {
            value = Integer.parseInt(objectValue.toString());
        }
        Log.i(TAG,"the Int is:  " + value);
        return value;
    }
}
