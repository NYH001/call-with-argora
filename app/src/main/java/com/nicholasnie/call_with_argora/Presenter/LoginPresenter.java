package com.nicholasnie.call_with_argora.Presenter;

import com.nicholasnie.call_with_argora.Activity.LoginActivity;
import com.nicholasnie.call_with_argora.Base.IPresenter;
import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Model.UserModel;

/**
 * Created by NicholasNie on 2017/10/24.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> implements IPresenter {

    private UserModel model;

    public LoginPresenter(IView iView) {
        mView = iView;
        mModel = UserModel.getInstance(iView.getActivity().getApplicationContext());
        model = mModel.getModel();
    }

    @Override
    void initData() {

    }

    public void LoginOrSignUp(String userId){

    }
}
