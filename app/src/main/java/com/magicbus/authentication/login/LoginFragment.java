package com.magicbus.authentication.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.magicbus.R;
import com.magicbus.data.Login;
import com.magicbus.data.network.ApiInterface;
import com.magicbus.data.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText mobileEditText, passwordEditText;
    private Button loginButton, createAccountButton;
    private String mobile, password;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_login, container, false);

        this.mobileEditText = view.findViewById(R.id.mobileEditText);
        this.passwordEditText = view.findViewById(R.id.passwordEditText);
        this.loginButton = view.findViewById(R.id.loginButton);
        this.createAccountButton = view.findViewById(R.id.createAccountButton);

        this.mobile = mobileEditText.getText().toString();
        this.password = passwordEditText.getText().toString();

        final ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Login>> call = apiInterface.getLoginResponse(mobile, password);
                call.enqueue(new Callback<List<Login>>() {
                    @Override
                    public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
                        Log.d("Login Response", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<List<Login>> call, Throwable t) {
                        Log.e("Login Response", t.getMessage());
                    }
                });
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
