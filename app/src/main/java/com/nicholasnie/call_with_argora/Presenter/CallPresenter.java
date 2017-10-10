package com.nicholasnie.call_with_argora.Presenter;

import com.nicholasnie.call_with_argora.Activity.CallActivity;
import com.nicholasnie.call_with_argora.Base.IModel;
import com.nicholasnie.call_with_argora.Base.IPresenter;
import com.nicholasnie.call_with_argora.Base.IView;
import com.nicholasnie.call_with_argora.Model.UserModel;

/**
 * Created by NicholasNie on 2017/10/10.
 */

public class CallPresenter extends BasePresenter<CallActivity> implements IPresenter {

    private IView mView;
    private IModel mModel;

    public CallPresenter(IView iView){
        this.mView = iView;
        this.mModel = new UserModel();
    }

    @Override
    void initData() {

    }
}
