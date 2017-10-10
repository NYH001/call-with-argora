package com.nicholasnie.call_with_argora.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.R;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public class CallActivity extends Activity implements IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_activity);
    }
}
