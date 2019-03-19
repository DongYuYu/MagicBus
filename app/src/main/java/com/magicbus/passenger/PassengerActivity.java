package com.magicbus.passenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.magicbus.R;
import com.magicbus.adapter.PassengerAdapter;
import com.magicbus.data.entries.Passenger;

import java.util.List;

public class PassengerActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rv;
    private PassengerAdapter adapter;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        rv = findViewById(R.id.rvPassenger);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PassengerAdapter(getIntent().getIntArrayExtra("adjustSeats"));
        rv.setAdapter(adapter);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Passenger[] passengers = adapter.getPassengers();
        Log.d("Passenger", passengers.toString());
    }
}
