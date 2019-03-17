package com.magicbus.login;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.magicbus.R;
import com.magicbus.data.entries.Login;
import com.magicbus.data.network.ApiInterface;
import com.magicbus.data.network.RetrofitInstance;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    private Button loginButton, createAccountButton;
//    private String mobile, password;
    private TextInputLayout mobileTextInputLayout, passwordTextInputLayout;

    private LoginContract.Presenter presenter;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_login, container, false);

        this.mobileTextInputLayout = view.findViewById(R.id.til_mobile);
        this.passwordTextInputLayout = view.findViewById(R.id.til_password);
        this.loginButton = view.findViewById(R.id.loginButton);
        this.createAccountButton = view.findViewById(R.id.createAccountButton);

        this.presenter = new LoginPresenter(this);

        final String mobile = mobileTextInputLayout.getEditText().getText().toString();
        final String password = passwordTextInputLayout.getEditText().getText().toString();

        final AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        awesomeValidation.addValidation(mobileTextInputLayout, Patterns.PHONE, "Invalid phone number");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Login Fragment", mobile);
                awesomeValidation.validate();
                presenter.sendRequest(mobile, password);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void getResponse(List<Login> response) {

    }
}
