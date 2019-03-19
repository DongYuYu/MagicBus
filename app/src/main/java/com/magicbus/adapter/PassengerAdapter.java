package com.magicbus.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.magicbus.R;
import com.magicbus.data.entries.Passenger;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.PassengerViewHolder> {

    private Passenger[] passengers;
    private int[] adjustSeats;


    public PassengerAdapter(int[] adjustSeats) {
        this.adjustSeats = adjustSeats;

        passengers = new Passenger[adjustSeats.length];
        for (int i = 0 ; i < adjustSeats.length; i++) {

            passengers[i] = new Passenger();
        }


    }

    @NonNull
    @Override
    public PassengerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_passenger, viewGroup, false);

        return new PassengerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PassengerViewHolder passengerViewHolder, int i) {


        passengerViewHolder.tvSeatNum.setText(String.valueOf(adjustSeats[i]));


        passengerViewHolder.etPname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passengers[i].setPassengername(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passengerViewHolder.etAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passengers[i].setPassengerage(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passengerViewHolder.etGender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passengers[i].setPassengergender(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return adjustSeats.length;
    }

    public class PassengerViewHolder extends RecyclerView.ViewHolder {

        TextView tvSeatNum;

        EditText etPname;
        EditText etGender;
        EditText etAge;
        public PassengerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSeatNum = itemView.findViewById(R.id.seatNum);

            etPname = itemView.findViewById(R.id.pName);
            etGender = itemView.findViewById(R.id.gender);
            etAge = itemView.findViewById(R.id.age);
        }
    }

    public Passenger[] getPassengers() {
        return passengers;
    }
}
