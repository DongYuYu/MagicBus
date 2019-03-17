package com.magicbus.search.routeid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magicbus.R;
import com.magicbus.data.RouteID;

import java.util.List;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteIDFragment extends Fragment implements RouteIDContract.View {


    RouteIDContract.Presenter presenter;

    public RouteIDFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_route_id, container, false);

        presenter.sendRequest("41.914196", "-88.308685", "40.73061", "-73.935242");



        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void getResponse(Response<List<RouteID>> response) {

    }
}
