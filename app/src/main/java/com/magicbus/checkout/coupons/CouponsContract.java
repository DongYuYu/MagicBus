package com.magicbus.checkout.coupons;

import com.magicbus.data.entries.Coupons;

import java.util.List;

public interface CouponsContract {

    interface View {
        void getCouponsResponse(List<Coupons> response);
    }

    interface Presenter{
        void sendCouponsRequest();
    }
}
