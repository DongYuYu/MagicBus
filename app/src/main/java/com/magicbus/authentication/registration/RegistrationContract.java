package com.magicbus.registration;

public interface RegistrationContract {
    interface View {


        void login(String response);
    }
    interface Presenter {
        void register(String firstName, String lastName, String address, String email, String mobile, String password);
    }
}
