package com.nicholasnie.call_with_argora.Presenter;

import com.nicholasnie.call_with_argora.Activity.BaseActivity;
import com.nicholasnie.call_with_argora.Base.IModel;
import com.nicholasnie.call_with_argora.Base.IView;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public abstract class BasePresenter<T extends BaseActivity> {

    protected IView mView;
    protected IModel mModel;

    abstract void initData();
}
