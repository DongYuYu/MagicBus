package com.magicbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magicbus.adapter.AirplaneAdapter;
import com.magicbus.data.entries.AbstractItem;
import com.magicbus.data.entries.BusInformation;
import com.magicbus.data.entries.SeatInformation;
import com.magicbus.reservation.SeatActivity;
import com.magicbus.reservation.SeatPresenter;
import com.magicbus.search.businformation.BusInfoContract;
import com.magicbus.search.businformation.BusInfoPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class testActivity extends AppCompatActivity implements BusInfoContract.BusInfoView {
    private static final String TAG = SeatActivity.class.getSimpleName();
    @BindView(R.id.tvBusid)
    TextView tvBusid;
    @BindView(R.id.tvBusregistrationno)
    TextView tvBusregistrationno;
    @BindView(R.id.tvBustype)
    TextView tvBustype;
    @BindView(R.id.tvBusDeparturetime)
    TextView tvBusDeparturetime;
    @BindView(R.id.tvJournyduration)
    TextView tvJournyduration;
    @BindView(R.id.tvFare)
    TextView tvFare;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.imageView12)
    ImageView imageView12;
    @BindView(R.id.imageView14)
    ImageView imageView14;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.imageView13)
    ImageView imageView13;
    @BindView(R.id.tvBoardingTime2)
    TextView tvBoardingTime2;
    @BindView(R.id.tvDropingTime2)
    TextView tvDropingTime2;
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.tvBoardingTime)
    TextView tvBoardingTime;
    @BindView(R.id.tvDropingTime)
    TextView tvDropingTime;
    @BindView(R.id.btn_BusSeat)
    Button btnBusSeat;
    @BindView(R.id.tvBoardTime)
    TextView tvBoardTime;
    @BindView(R.id.tvBoardTime2)
    TextView tvBoardTime2;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    private List<SeatInformation> seatInfoList;
    private static final int COLUMNS = 5;
    private TextView txtSeatSelected;
    private SeatPresenter seatPresenter;
    private List<AbstractItem> items;

    private AirplaneAdapter adapter;
    private List<BusInformation> busInformationList;
    private String route_id;
    private BusInfoPresenter busInfoPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_bus_info);
        ButterKnife.bind(this);

        busInfoPresenter = new BusInfoPresenter(this);

      //  route_id = getArguments().getString("id");
        // get Bus Info passing the route_id as argument
        // the routeid is passed through shared preference and stored in the global variable route_id declare in the fragment
        //
        busInfoPresenter.getBusInfo("1");



    }

    @Override
    public void showBusInfo(List<BusInformation> busInformationList) {
        this.busInformationList = busInformationList;
        Log.d("checking list element", "showBusInfo: " + busInformationList);
        tvBusid.setText("BUS ID: " + busInformationList.get(0).getBusid());
        tvBusregistrationno.setText("BUS REGISTRATION NUMBER: " + busInformationList.get(0).getBusregistrationno());
        tvBustype.setText("BUS TYPE: " + busInformationList.get(0).getBustype());
        tvBusDeparturetime.setText("BUS DEPARTURE TIME: " + busInformationList.get(0).getBusdeparturetime());
        tvJournyduration.setText(busInformationList.get(0).getJournyduration());
        tvFare.setText("TRIP COST: " + busInformationList.get(0).getFare());
        tvBoardingTime.setText(busInformationList.get(0).getBoardingtime());
        tvDropingTime.setText(busInformationList.get(0).getDropingtime());
        tvBoardingTime2.setText(busInformationList.get(0).getBoardingtime());
        tvDropingTime2.setText(busInformationList.get(0).getDropingtime());

    }
}