package com.magicbus.checkout.coupons;

import com.magicbus.data.DataStructure;
import com.magicbus.data.Repository;
import com.magicbus.data.entries.Coupons;

import java.util.List;

public class CouponsPresenter implements CouponsContract.Presenter, DataStructure.CouponsCallback {

    Repository repository;
    CouponsContract.View view;

    public CouponsPresenter(CouponsContract.View view) {
        repository = Repository.getRepository();
        this.view = view;
    }

    @Override
    public void sendCouponsRequest() {
        repository.getCoupons(this);
    }

    @Override
    public void couponsCallback(List<Coupons> response) {
        view.getCouponsResponse(response);
    }
}
