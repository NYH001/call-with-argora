package com.nicholasnie.call_with_argora.Activity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.nicholasnie.call_with_argora.App.ActivityManager;
import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Presenter.LoginPresenter;
import com.nicholasnie.call_with_argora.R;

/**
 * Created by NicholasNie on 2017/10/24.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements IView {

    private Button btnLogin;

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
        btnLogin = (Button) findViewById(R.id.btn_login1);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.startActivity(getApplicationContext(),new CallActivity());
                finish();
            }
        });
    }

    @Override
    void onPrepare() {

    }

    @Override
    public Activity getActivity() {
        return null;
    }
}