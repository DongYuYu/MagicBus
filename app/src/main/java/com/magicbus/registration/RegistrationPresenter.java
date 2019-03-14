package com.magicbus.registration;

import com.magicbus.network.DataStructure;
import com.magicbus.network.Repository;

public class RegistrationPresenter implements RegistrationContract.Presenter, DataStructure.OnRegisterCallBack {

    Repository repository;












    RegistrationContract.View view;
    public RegistrationPresenter(RegistrationContract.View view) {
        this.view = view;
        repository = Repository.getRepository();
    }

    @Override
    public void register(String firstName, String lastName, String address, String email, String mobile, String password) {
        repository.register(firstName, lastName, address, email, mobile, password, this);
    }

    @Override
    public void onRegisterReceived(String response) {




        view.login(response);
    }
}
