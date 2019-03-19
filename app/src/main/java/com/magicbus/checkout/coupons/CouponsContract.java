package com.magicbus.checkout.coupons;

import com.magicbus.data.entries.Coupons;

import java.util.List;

public interface CouponsContract {

    interface View {
        void getResponse(List<Coupons> response);
    }

    interface Presenter{
        void sendRequest();
    }
}
