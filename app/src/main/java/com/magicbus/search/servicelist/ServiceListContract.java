package com.magicbus.search.servicelist;

import com.magicbus.data.entries.ServiceList;

import java.util.List;

public interface ServiceListContract {

    interface View {
        void getResponse(List<ServiceList> response);

    }

    interface Presenter {
        void sendRequest(String startpoint_latitude, String startpoint_longitude,
                         String endpoint_latitude, String endpoint_longitude);

    }
}
