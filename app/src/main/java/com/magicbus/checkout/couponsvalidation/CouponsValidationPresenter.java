package com.magicbus.checkout.couponsvalidation;

import com.magicbus.data.DataStructure;
import com.magicbus.data.Repository;
import com.magicbus.data.entries.CouponsValidation;

import java.util.List;

public class CouponsValidationPresenter implements CouponsValidationContract.Presenter, DataStructure.CouponsValidationCallback {

    Repository repository;
    CouponsValidationContract.View view;

    public CouponsValidationPresenter(CouponsValidationContract.View view) {
        repository = Repository.getRepository();
        this.view = view;
    }

    @Override
    public void sendCouponsValidationRequest(String couponno) {
        repository.getCouponsValidation(couponno, this);

    }

    @Override
    public void couponsValidationCallback(List<CouponsValidation> response) {
        view.getCouponsValidationResponse(response);
    }
}
