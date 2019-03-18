package com.magicbus.search.servicelist;


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
import com.magicbus.search.businformation.BusInfoFrag;

import java.util.List;

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


        Bundle args = getArguments();
        //presenter.sendRequest("41.914196", "-88.308685", "40.73061", "-73.935242");
        presenter.sendRequest(args.getString("start_lat"), args.getString("start_long"), args.getString("end_lat"), args.getString("end_long"));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        serviceListRecyclerView.setLayoutManager(layoutManager);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void getResponse(List<ServiceList> response) {
        Log.d("Response", response.toString());
        serviceListAdapter = new ServiceListAdapter(response);
        serviceListAdapter.setOnItemClickListener(new ServiceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                String id = serviceListAdapter.getItemIdString(position);

                Bundle arg = new Bundle();

                arg.putString("id", id);



                Fragment fg = new BusInfoFrag();

                fg.setArguments(arg);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fg)




                        .addToBackStack(null)





                        .commit();
            }
        });

        serviceListRecyclerView.setAdapter(serviceListAdapter);

    }
}
