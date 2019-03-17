package com.magicbus.login;

import android.util.Log;

import com.magicbus.data.entries.Login;
import com.magicbus.data.DataStructure;
import com.magicbus.data.Repository;

import java.util.List;

import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter, DataStructure.LoginCallback {

    private Repository repository;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        repository = Repository.getRepository();
    }

    @Override
    public void sendRequest(String mobile, String password) {
        Log.e("Login Presenter", mobile);
        repository.login(mobile, password, this);

    }

    @Override
    public void loginCallback(List<Login> response) {
        view.getResponse(response);
    }
}
