package com.magicbus.authentication.registration;

import com.magicbus.data.DataStructure;
import com.magicbus.data.Repository;

public class RegistrationPresenter implements RegistrationContract.Presenter, DataStructure.OnRegisterCallBack {

    Repository repository;












    RegistrationContract.View view;
    public RegistrationPresenter(RegistrationContract.View view) {
        this.view = view;
        repository = Repository.getRepository();
    }


    public void register(String firstName, String lastName, String address, String email, String mobile, String password) {

        repository.register(firstName, lastName, address, email, mobile, password, this);
    }


    @Override
    public void onRegisterReceived(String response) {




        view.login(response);
    }
}
