package com.magicbus.authentication.login;

import com.magicbus.data.entries.Login;

import java.util.List;

public interface LoginContract {

    interface View {
        void getResponse(List<Login> response);
    }

    interface Presenter {
        void sendRequest(String mobile, String password);
    }
}
