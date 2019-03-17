package com.magicbus.reservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.magicbus.R;
import com.magicbus.adapter.AirplaneAdapter;
import com.magicbus.data.entries.ResponseSeatInfo;
import com.magicbus.data.entries.SeatInformation;

import java.util.ArrayList;
import java.util.List;

public class SeatActivity extends AppCompatActivity implements SeatSelectionContract.SeatSelectionView,OnSeatSelected{
    private static final String TAG = SeatActivity.class.getSimpleName();
    private List<SeatInformation> seatInfoList;
    private static final int COLUMNS = 5;
    private TextView txtSeatSelected;
    private SeatPresenter seatPresenter;
    private List<AbstractItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        seatPresenter = new SeatPresenter(this);
        seatPresenter.getSeatInformation("102");

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


        AirplaneAdapter adapter = new AirplaneAdapter(this, items, seatInfoList);
        recyclerView.setAdapter(adapter);
        //come back to this

       // seatInfoArray


    }

    @Override
    public void onSeatSelected(int count) {
        txtSeatSelected.setText("Book "+count+" seats");

    }
}
