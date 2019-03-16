package com.magicbus.businformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.magicbus.R;
import com.magicbus.data.entries.BusInformation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusInfoFrag extends Fragment implements BusInfoContract.BusInfoView {

   private List<BusInformation> busInformationList;
   private  String route_id;
    private static final String TAG = BusInfoFrag.class.getSimpleName();


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
    Unbinder unbinder;
    @BindView(R.id.btn_BusSeat)
    Button btnBusSeat;

    public BusInfoFrag() {
        // Required empty public constructor
    }

    private BusInfoPresenter busInfoPresenter = new BusInfoPresenter(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Fragment hasn't been linked with an activity yet
        // ((activity here)getActivity().setActionBarTitle("BUS INFORMATION");
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
        tvBusid.setText(busInformationList.get(0).getBusid());
        tvBusregistrationno.setText(busInformationList.get(0).getBusregistrationno());
        tvBustype.setText(busInformationList.get(0).getBustype());
        tvBusDeparturetime.setText(busInformationList.get(0).getBusdeparturetime());
        tvJournyduration.setText(busInformationList.get(0).getJournyduration());
        tvFare.setText(busInformationList.get(0).getFare());
        tvBoardingTime.setText(busInformationList.get(0).getBoardingtime());
        tvDropingTime.setText(busInformationList.get(0).getDropingtime());
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
    }
}
