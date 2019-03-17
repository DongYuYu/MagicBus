package com.magicbus.servicelist;

import com.magicbus.data.entries.ServiceList;
import com.magicbus.data.DataStructure;
import com.magicbus.data.Repository;

import java.util.List;

public class ServiceListPresenter implements ServiceListContract.Presenter, DataStructure.ServiceListCallback {

    Repository repository;
//    DataStructure.Presenter presenter;
    ServiceListContract.View view;

    public ServiceListPresenter(ServiceListContract.View view) {
        this.view = view;
        repository = Repository.getRepository();
    }

    @Override
    public void sendRequest(String startpoint_latitude, String startpoint_longitude, String endpoint_latitude, String endpoint_longitude) {
        repository.serviceList(startpoint_latitude, startpoint_longitude, endpoint_latitude, endpoint_longitude, this);
    }

    @Override
    public void serviceListCallback(List<ServiceList> response) {
        view.getResponse(response);
    }
}
