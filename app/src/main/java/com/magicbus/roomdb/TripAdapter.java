package com.magicbus.roomdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magicbus.R;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder>{

    private final LayoutInflater mInflater;
    Context context;
    List<Trip> mTrips;

    TripAdapter(Context context, List<Trip> mTrips) {

        this.context = context;
        this.mTrips = mTrips;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.item_list_trip_history, viewGroup, false);
        return new TripViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder tripViewHolder, int position) {

        if (mTrips != null) {
            Trip current = mTrips.get(position);
            tripViewHolder.tvToken_id.setText("Token Id: "+ current.getToken_id());
            tripViewHolder.tvPasName.setText("Passenger Name: " + current.getPassengername());
            tripViewHolder.tv_fare.setText("Trip Cost: " + current.getFare());
            tripViewHolder.tv_boardTime.setText("Boarding Time: " + current.getBoardingtime() + "," + current.getJournydate());
            tripViewHolder.tvSeat.setText(" " + "Seat No: " + current.getSelectedseat());


        } else {
            // Covers the case of data not being ready yet.
            tripViewHolder.tvToken_id.setText("No Trip");
        }

    }


    @Override
    public int getItemCount() {

        return mTrips.size();
    }

    class TripViewHolder extends RecyclerView.ViewHolder{

        TextView tvToken_id,tv_journydate,tvPasName, tv_fare, tv_boardTime,tvSeat;
        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            tvToken_id = itemView.findViewById(R.id.tvToken_id);
        //    tv_journydate =  itemView.findViewById(R.id.tv_journydate);
            tvPasName = itemView.findViewById(R.id.tvPassengerName);
            tv_fare = itemView.findViewById(R.id.tvFare);
            tv_boardTime = itemView.findViewById(R.id.tvBoardingTime);
            tvSeat = itemView.findViewById(R.id.tvSeatNo);
        }
    }
}
