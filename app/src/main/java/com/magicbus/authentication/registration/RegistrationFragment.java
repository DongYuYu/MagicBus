package com.magicbus.registration;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.magicbus.R;

public class RegistrationFragment extends Fragment implements RegistrationContract.View, View.OnClickListener {
    EditText firstname, lastname, address, email, mobile, password;





    Button button;

    RegistrationContract.Presenter presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {







        View view = inflater.inflate(R.layout.frag_registration, container, false);



        firstname = view.findViewById(R.id.firstName);
        lastname = view.findViewById(R.id.lastName);
        address = view.findViewById(R.id.address);
        email = view.findViewById(R.id.email);
        mobile = view.findViewById(R.id.mobile);
        password = view.findViewById(R.id.password);
        button = view.findViewById(R.id.register);





        button.setOnClickListener(this);
        presenter = new RegistrationPresenter(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        presenter.register(firstname.getText().toString(),
                            lastname.getText().toString(),
                            address.getText().toString(),
                            email.getText().toString(),
                            mobile.getText().toString(),


                            password.getText().toString());



    }

    @Override
    public void login(String response) {

    }
}
