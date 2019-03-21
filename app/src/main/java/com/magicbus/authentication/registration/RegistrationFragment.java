package com.magicbus.authentication.registration;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.magicbus.R;
import com.magicbus.authentication.login.LoginFragment;

public class RegistrationFragment extends Fragment implements RegistrationContract.View, View.OnClickListener {

    private TextInputLayout firstname, lastname, address, email, mobile, password;
    private AwesomeValidation awesomeValidation;

    private Button button;

    RegistrationContract.Presenter presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_registration, container, false);

        firstname = view.findViewById(R.id.til_fName);
        lastname = view.findViewById(R.id.til_lName);
        address = view.findViewById(R.id.til_address);
        email = view.findViewById(R.id.til_email);
        mobile = view.findViewById(R.id.til_mobile);
        password = view.findViewById(R.id.til_password);
        button = view.findViewById(R.id.register);

        awesomeValidation = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        awesomeValidation.addValidation(firstname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Invalid name");
        awesomeValidation.addValidation(lastname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Invalid name");
        awesomeValidation.addValidation(email, Patterns.EMAIL_ADDRESS, "Invalid email address");
        awesomeValidation.addValidation(mobile, Patterns.PHONE, "Invalid mobile number");
        awesomeValidation.addValidation(password, "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}", "Invalid password");

        button.setOnClickListener(this);
        presenter = new RegistrationPresenter(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (awesomeValidation.validate()) {
            presenter.register(firstname.getEditText().getText().toString(),
                    lastname.getEditText().getText().toString(),
                    address.getEditText().getText().toString(),
                    email.getEditText().getText().toString(),
                    mobile.getEditText().getText().toString(),
                    password.getEditText().getText().toString());
        }
   }

    @Override
    public void login(String response) {
        if (response.equals("\nsuccessfully registered")) {

            Toast.makeText(getContext(), "Successful Registration", Toast.LENGTH_LONG).show();

            LoginFragment fg = new LoginFragment();

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fg)




                    .addToBackStack(null)





                    .commit();

        }
    }
}
