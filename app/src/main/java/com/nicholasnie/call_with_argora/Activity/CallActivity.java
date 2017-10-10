package com.nicholasnie.call_with_argora.Activity;

import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Presenter.CallPresenter;
import com.nicholasnie.call_with_argora.R;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public class CallActivity extends BaseActivity<CallPresenter> implements IView {

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

    }

    @Override
    void onPrepare() {

    }
}
