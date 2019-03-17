package com.magicbus.servicelist;

import com.magicbus.data.entries.ServiceList;

import java.util.List;

import retrofit2.Response;

public interface ServiceListContract {

    interface View {
        void getResponse(List<ServiceList> response);

    }

    interface Presenter {
        void sendRequest(String startpoint_latitude, String startpoint_longitude,
                         String endpoint_latitude, String endpoint_longitude);

    }
}
