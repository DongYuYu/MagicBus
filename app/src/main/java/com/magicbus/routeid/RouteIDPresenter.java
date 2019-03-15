package com.magicbus.routeid;

import com.magicbus.data.RouteID;
import com.magicbus.network.DataStructure;
import com.magicbus.network.Repository;

import java.util.List;

import retrofit2.Response;

public class RouteIDPresenter implements RouteIDContract.Presenter, DataStructure.RouteIDCallback {

    Repository repository;
    RouteIDContract.View view;

    public RouteIDPresenter(RouteIDContract.View view) {
        this.view = view;
        repository = Repository.getRepository();
    }

    @Override
    public void sendRequest(String startpoint_latitude, String startpoint_longitude, String endpoint_latitude, String endpoint_longitude) {
        repository.routeID(startpoint_latitude, startpoint_longitude, endpoint_latitude, endpoint_longitude, this);
    }

    @Override
    public void routeIDCallback(Response<List<RouteID>> response) {
        view.getResponse(response);
    }
}
