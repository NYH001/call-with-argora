package com.nicholasnie.call_with_argora.Presenter;

import com.nicholasnie.call_with_argora.Activity.BaseActivity;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public abstract class BasePresenter<T extends BaseActivity> {
    abstract void initData();
}
