package com.magicbus.reservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.magicbus.R;
import com.magicbus.adapter.AirplaneAdapter;
import com.magicbus.checkout.CheckoutActivity;
import com.magicbus.data.entries.AbstractItem;
import com.magicbus.data.entries.CenterItem;
import com.magicbus.data.entries.EdgeItem;
import com.magicbus.data.entries.EmptyItem;
import com.magicbus.data.entries.Passenger;
import com.magicbus.data.entries.SeatInformation;
import com.magicbus.passenger.PassengerActivity;

import java.util.ArrayList;
import java.util.List;

public class SeatActivity extends AppCompatActivity implements SeatSelectionContract.SeatSelectionView,OnSeatSelected, View.OnClickListener {
    private static final String TAG = SeatActivity.class.getSimpleName();
    private List<SeatInformation> seatInfoList;
    private static final int COLUMNS = 5;
    private TextView txtSeatSelected;
    private SeatPresenter seatPresenter;
    private List<AbstractItem> items;

    private AirplaneAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        seatPresenter = new SeatPresenter(this);
        seatPresenter.getSeatInformation(getIntent().getStringExtra("busid"));

        txtSeatSelected = (TextView)findViewById(R.id.txt_seat_selected);

        items = new ArrayList<>();
        for (int i=0; i<59; i++) {

            if (i%COLUMNS==0 || i%COLUMNS==4) {
                items.add(new EdgeItem(String.valueOf(i)));
            } else if (i%COLUMNS==1 || i%COLUMNS==3) {
                items.add(new CenterItem(String.valueOf(i)));
            } else {
                items.add(new EmptyItem(String.valueOf(i)));
            }
        }
        txtSeatSelected.setOnClickListener(this);
    }


    @Override
    public void showSeatInfo(List<SeatInformation> seatInfoList) {
        /**
         * the seat information from the server is returned here.
         * the info is a one element list of SeatInformation Objects.
         * we will used the seat info to choose which seats is reserved or free
         *
         */

        this.seatInfoList = seatInfoList;
        Log.d(TAG, " showSeatInfo: " + seatInfoList.size()+ seatInfoList.get(0)) ;
       // String[] seatInfoArray = new String[47];
        SeatInformation[] arr = seatInfoList.toArray(new SeatInformation[seatInfoList.size()]);
        Log.d(TAG, "showSeatInfo: " + arr[0] + "   " );

        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        adapter = new AirplaneAdapter(this, items, seatInfoList);
        recyclerView.setAdapter(adapter);
        //come back to this

       // seatInfoArray


    }

    @Override
    public void passengerDetails(int[] adjustSeats) {



        Bundle bundle = new Bundle();
        bundle.putIntArray("adjustSeats", adjustSeats);
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onSeatSelected(int count) {

        txtSeatSelected.setText("Book "+count+" seats");

    }

    @Override
    public void onClick(View v) {
        seatPresenter.reserve(getIntent().getStringExtra("busid"), adapter.getSelectedItems());
    }
}
