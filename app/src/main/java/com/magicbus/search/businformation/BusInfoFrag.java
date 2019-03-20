package com.magicbus.search.businformation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magicbus.R;
import com.magicbus.data.entries.BusInformation;
import com.magicbus.reservation.SeatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusInfoFrag extends Fragment implements BusInfoContract.BusInfoView {
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
    Unbinder unbinder;
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

    //  ((MainActivity)getActivity()).setActionBarTitle("BUS INFORMATION");

    private List<BusInformation> busInformationList;
    private String route_id;
    private static final String TAG = BusInfoFrag.class.getSimpleName();


    public BusInfoFrag() {
        // Required empty public constructor
    }

    private BusInfoPresenter busInfoPresenter = new BusInfoPresenter(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Fragment hasn't been linked with an activity yet
        route_id = getArguments().getString("id");
        // get Bus Info passing the route_id as argument
        // the routeid is passed through shared preference and stored in the global variable route_id declare in the fragment
        //
        busInfoPresenter.getBusInfo(route_id);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_bus_info, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;

    }


    @Override
    public void showBusInfo(List<BusInformation> busInformationList) {
        this.busInformationList = busInformationList;
        Log.d("checking list element", "showBusInfo: " + busInformationList);
        tvBusid.setText("BUS ID: " + busInformationList.get(0).getBusid());
        tvBusregistrationno.setText("BUS REG No: " + busInformationList.get(0).getBusregistrationno());
        tvBustype.setText("BUS TYPE: " + busInformationList.get(0).getBustype());
        tvBusDeparturetime.setText("BUS DEPARTURE TIME: " + busInformationList.get(0).getBusdeparturetime());
        tvJournyduration.setText(busInformationList.get(0).getJournyduration());
        tvFare.setText("TRIP COST: " + busInformationList.get(0).getFare());
        tvBoardingTime.setText(busInformationList.get(0).getBoardingtime());
        tvDropingTime.setText(busInformationList.get(0).getDropingtime());
        tvBoardingTime2.setText(busInformationList.get(0).getBoardingtime());
        tvDropingTime2.setText(busInformationList.get(0).getDropingtime());



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();

        // ((MainActivity)getActivity()).setActionBarTitle("BUS INFORMATION");
    }


    @OnClick(R.id.btn_BusSeat)
    public void onViewClicked() {
        //when this button is clicked, it will navigate to the select bus seat activity.


        Intent intent = new Intent(getActivity(), SeatActivity.class);
        intent.putExtra("busid", busInformationList.get(0).getBusid());
        startActivity(intent);
    }
}
