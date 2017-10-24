package com.nicholasnie.call_with_argora.App;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.Stack;

/**
 * Created by NicholasNie on 2017/10/24.
 */

public class ActivityManager {

    private static Stack<Activity> activities = new Stack<Activity>();

    public Activity getPresentActivity(){
        return null;
    }

    public static void startActivity(Context context, Activity activity){
        Intent intent = new Intent(context,activity.getClass());
        context.startActivity(intent);
    }

    public static void finishActivity(Activity activity){
        activity.finish();
    }

    private void pushActivity(Activity activity){

    }

    private void pullActivity(Activity activity){

    }

}
