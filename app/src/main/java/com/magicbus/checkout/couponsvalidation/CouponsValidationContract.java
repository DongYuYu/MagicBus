package com.magicbus.checkout.couponsvalidation;

import com.magicbus.data.entries.CouponsValidation;

import java.util.List;

public interface CouponsValidationContract {

    interface View {
        void getResponse(List<CouponsValidation> response);
    }

    interface Presenter {
        void sendRequest(String couponno);
    }
}
