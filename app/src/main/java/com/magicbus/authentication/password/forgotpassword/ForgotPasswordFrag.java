package com.magicbus.authentication.password.forgotpassword;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.magicbus.R;
import com.magicbus.data.entries.PasswordDetail;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFrag extends Fragment implements PasswordContract.ForgetPasswordView{


    private EditText mobileNumET;
    private Button submitPassword;
    private PasswordPresenter passwordPresenter  = new PasswordPresenter(this);
    private List<PasswordDetail> passwordDetailList;
    private AlertDialog.Builder builder;

    public ForgotPasswordFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
    View view =   inflater.inflate(R.layout.frag_password, container, false);

        mobileNumET = view.findViewById(R.id.etMobileNum);
        submitPassword = view.findViewById(R.id.buttonSubmit);

        submitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordPresenter.getPassword(mobileNumET.getText().toString());
//                Toast.makeText(getContext(), "Login Details have been sent to your email", Toast.LENGTH_LONG).show();
            }
        });
        /**
         * the user password is displayed in a dialog box after the user submits his/her mobile number
         */


        return view;
    }

    @Override
    public void showPassword(List<PasswordDetail> passwordDetailList) {
        this.passwordDetailList = passwordDetailList;

        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.dialog_message,passwordDetailList.get(0).getMobile(),
                passwordDetailList.get(0).getPassword()))
                .setTitle(getString(R.string.dialog_title, passwordDetailList.get(0).getMsg()))
                .setCancelable(true);
        AlertDialog alert = builder.create();
        alert.show();
        /**
         *  The user password is displayed in a dialog box
         */
//         AlertDialog alertDialog = new AlertDialog(getActivity());
        Toast.makeText(getContext(), "Your password is : " + passwordDetailList.get(0).getPassword(), Toast.LENGTH_SHORT).show();
    }

}
