package com.magicbus.authentication.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.magicbus.R;
import com.magicbus.authentication.registration.RegistrationFragment;
import com.magicbus.data.entries.Login;
import com.magicbus.data.network.ApiInterface;
import com.magicbus.data.network.RetrofitInstance;
import com.magicbus.search.city.CityFragment;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    private Button loginButton, createAccountButton;
//    private String mobile, password;
    private TextInputLayout mobileTextInputLayout, passwordTextInputLayout;
    private LoginButton facebookLoginButton;

    private LoginContract.Presenter presenter;
    private static final String EMAIL = "email";

    CallbackManager callbackManager;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getContext());
        View view = inflater.inflate(R.layout.frag_login, container, false);

        callbackManager = CallbackManager.Factory.create();

        this.mobileTextInputLayout = view.findViewById(R.id.til_mobile);
        this.passwordTextInputLayout = view.findViewById(R.id.til_password);
        this.loginButton = view.findViewById(R.id.loginButton);
        this.createAccountButton = view.findViewById(R.id.createAccountButton);
        this.presenter = new LoginPresenter(this);
        facebookLoginButton = view.findViewById(R.id.login_button);
        facebookLoginButton.setReadPermissions(Arrays.asList(EMAIL));
        facebookLoginButton.setFragment(this);

//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getContext(), "Facebook Login Success", Toast.LENGTH_SHORT).show();
                Fragment fg = new CityFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_container, fg)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), "Facebook Login Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getContext(), "Facebook Login Error", Toast.LENGTH_SHORT).show();
            }
        });


        final String mobile = mobileTextInputLayout.getEditText().getText().toString();
        final String password = passwordTextInputLayout.getEditText().getText().toString();

        final AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        awesomeValidation.addValidation(mobileTextInputLayout, Patterns.PHONE, "Invalid phone number");
        awesomeValidation.addValidation(passwordTextInputLayout, "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}", "Invalid password");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    presenter.sendRequest(mobile, password);
                }
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fg = new RegistrationFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_container, fg)
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void getResponse(List<Login> response) {
        Log.e("Login Response 1", response.get(0).getMessage());
        if(response.get(0).getMessage().equals("success")) {
            Fragment fg = new CityFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_container, fg)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
