package com.magicbus.reservation;

import com.magicbus.data.entries.ResponseSeatInfo;
import com.magicbus.data.entries.SeatInformation;

import java.util.List;

public interface SeatSelectionContract {

    interface  SeatSelectionView{
        void showSeatInfo(List<SeatInformation> seatInfoList);
    }

    interface SeatSelectionPresenter{
       void getSeatInformation(String bus_id);
    }
}
