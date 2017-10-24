package com.nicholasnie.call_with_argora.Activity;

import android.app.Activity;

import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Presenter.LoginPresenter;
import com.nicholasnie.call_with_argora.R;

/**
 * Created by NicholasNie on 2017/10/24.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements IView {
    @Override
    LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    int getLayout() {
        return R.layout.login_activity;
    }

    @Override
    void initView() {

    }

    @Override
    void onPrepare() {

    }

    @Override
    public Activity getActivity() {
        return null;
    }
}
