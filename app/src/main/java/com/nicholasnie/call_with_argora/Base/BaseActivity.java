package com.nicholasnie.call_with_argora.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public abstract class BaseActivity<T extends BasePresenter> extends Activity {

    protected T basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        basePresenter = initPresenter();
        onPrepare();
    }

    abstract T initPresenter();

    abstract int getLayout();

    abstract void initView();

    abstract void onPrepare();
}
