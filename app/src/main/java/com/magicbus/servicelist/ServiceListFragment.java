package com.magicbus.servicelist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magicbus.R;
import com.magicbus.adapter.ServiceListAdapter;
import com.magicbus.data.entries.ServiceList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceListFragment extends Fragment implements ServiceListContract.View {

    RecyclerView serviceListRecyclerView;
    ServiceListContract.Presenter presenter;
    ServiceListAdapter serviceListAdapter;

    public ServiceListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_serivce_list, container, false);
        serviceListRecyclerView = view.findViewById(R.id.rv_serviceList);

        presenter = new ServiceListPresenter(this);
        presenter.sendRequest("41.914196", "-88.308685", "40.73061", "-73.935242");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        serviceListRecyclerView.setLayoutManager(layoutManager);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void getResponse(List<ServiceList> response) {
        Log.e("Response", response.toString());

        serviceListAdapter = new ServiceListAdapter(response);
        serviceListRecyclerView.setAdapter(serviceListAdapter);
    }
}
