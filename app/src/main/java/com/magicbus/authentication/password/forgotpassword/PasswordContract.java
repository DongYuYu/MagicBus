package com.magicbus.authentication.password.forgotpassword;

import com.magicbus.data.entries.PasswordDetail;

import java.util.List;

public interface PasswordContract {

    interface ForgetPasswordView{
         void showPassword(List<PasswordDetail> passwordDetailList);
    }

    interface ForgetPasswordPresenter{
          void getPassword(String mobile);
          void returnPassword(List<PasswordDetail> passwordDetailList);
    };
}
