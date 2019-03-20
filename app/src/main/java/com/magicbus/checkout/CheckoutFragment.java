package com.magicbus.checkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.magicbus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckoutFragment extends Fragment {

    private TextView boardingTextView, droppingTextView;
    private RecyclerView passengerDetailsRecyclerView;
    private RadioGroup genderRadioGroup;
    private Button maleButton, femaleButton, paymentButton;
    private EditText nameEditText, mobileEditText, ageEditText;

    private String gender;


    public CheckoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_checkout, container, false);

        boardingTextView = view.findViewById(R.id.tv_boarding);
        droppingTextView = view.findViewById(R.id.tv_dropping);
        passengerDetailsRecyclerView = view.findViewById(R.id.rv_passengerDetails);
        genderRadioGroup = view.findViewById(R.id.rg_gender);
        maleButton = view.findViewById(R.id.rb_male);
        femaleButton = view.findViewById(R.id.rb_female);
        nameEditText = view.findViewById(R.id.et_name);
        mobileEditText = view.findViewById(R.id.et_mobile);
        ageEditText = view.findViewById(R.id.et_age);
        paymentButton = view.findViewById(R.id.button_payment);

        boardingTextView.setText("St. Charles, IL - 07:15:00 AM");
        droppingTextView.setText("New York City, NY - 04:00:00 AM");

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "male";
            }
        });

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "female";
            }
        });

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PaymentFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCbeckout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
