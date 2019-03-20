package com.magicbus.checkout;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.magicbus.R;
import com.magicbus.adapter.PassengerAdapter;
import com.magicbus.data.entries.Passenger;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckoutFragment extends Fragment {
    private PassengerAdapter adapter;
    private TextView boardingTextView, droppingTextView;
    private RecyclerView passengerDetailsRecyclerView;
    private RadioGroup genderRadioGroup;
    private Button maleButton, femaleButton, paymentButton;
    private EditText nameEditText, emailEditText, ageEditText;

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
        emailEditText = view.findViewById(R.id.et_email);
        ageEditText = view.findViewById(R.id.et_age);
        paymentButton = view.findViewById(R.id.button_payment);
        passengerDetailsRecyclerView = view.findViewById(R.id.rv_passengerDetails);
        passengerDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        passengerDetailsRecyclerView.setHasFixedSize(true);
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

                SharedPreferences.Editor editor = getActivity().getSharedPreferences("default", Context.MODE_PRIVATE).edit();


                editor.putString("passengeremail", emailEditText.getText().toString());

                editor.apply();
                Bundle bundle = new Bundle();
                bundle.putInt("quantity", (getArguments().getIntArray("adjustSeats")).length);

                getPassengers();
                Fragment fragment = new PaymentFragment();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        setAdapter(getArguments().getIntArray("adjustSeats"));

        // Inflate the layout for this fragment
        return view;
    }

    public void setAdapter(int[] adjustSeats) {
        adapter = new PassengerAdapter(adjustSeats);
        passengerDetailsRecyclerView.setAdapter(adapter);
    }




    public void getPassengers(){

        Log.d("Fragment", adapter.getPassengers().toString());




        Passenger[] passengers = adapter.getPassengers();
        StringBuilder sbSeat = new StringBuilder();
        StringBuilder sbName = new StringBuilder();
        StringBuilder sbGender = new StringBuilder();
        StringBuilder sbAge = new StringBuilder();





        for (int i = 0; i < passengers.length; i++) {
            sbSeat.append(passengers[i].getSelectedseat());
            sbSeat.append(",");






            sbName.append(passengers[i].getPassengername());
            sbName.append(",");

            sbGender.append(passengers[i].getPassengergender());

            sbGender.append(",");


            sbAge.append(passengers[i].getPassengerage());
            sbAge.append(",");

        }




        sbSeat.deleteCharAt(sbSeat.length() - 1);




        sbName.deleteCharAt(sbName.length() - 1);


        sbGender.deleteCharAt(sbGender.length() - 1);
        sbAge.deleteCharAt(sbAge.length() - 1);

        SharedPreferences.Editor editor = getActivity().getSharedPreferences("default", Context.MODE_PRIVATE).edit();




















        editor.putString("selectedseat", sbSeat.toString());
        editor.putString("passengername", sbName.toString());
        editor.putString("passengerage", sbAge.toString());
        editor.putString("passengergender", sbGender.toString());



















        editor.apply();
    }
}
