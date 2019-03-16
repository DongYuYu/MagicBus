package com.magicbus.businformation;

import com.magicbus.data.entries.BusInformation;

import java.util.List;

public interface BusInfoContract {

    interface BusInfoView{
       void showBusInfo(List<BusInformation> busInformationList);
    }

    interface  BusInfoPresenter{
         void getBusInfo(String route_id);
    }

}
