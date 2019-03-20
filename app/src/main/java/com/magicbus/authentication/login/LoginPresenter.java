package com.magicbus.authentication.login;

import android.util.Log;

import com.magicbus.authentication.login.LoginContract;
import com.magicbus.data.entries.Login;
import com.magicbus.data.DataStructure;
import com.magicbus.data.Repository;

import java.util.List;

public class LoginPresenter implements LoginContract.Presenter, DataStructure.LoginCallback {

    private Repository repository;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        repository = Repository.getRepository();
    }

    @Override
    public void sendRequest(String mobile, String password) {
        repository.login(mobile, password, this);
    }

    @Override
    public void loginCallback(List<Login> response) {
        view.getResponse(response);
    }
}
