package com.magicbus.checkout.couponsvalidation;

import com.magicbus.data.entries.CouponsValidation;

import java.util.List;

public interface CouponsValidationContract {

    interface View {
        void getCouponsValidationResponse(List<CouponsValidation> response);
    }

    interface Presenter {
        void sendCouponsValidationRequest(String couponno);
    }
}
