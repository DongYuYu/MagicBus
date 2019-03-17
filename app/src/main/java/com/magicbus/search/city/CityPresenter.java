package com.magicbus.city;

import com.magicbus.data.DataStructure;
import com.magicbus.data.Repository;
import com.magicbus.data.entries.City;

import java.util.List;

public class CityPresenter implements CityInterface.Presenter, DataStructure.OnCityCallBack {
    CityInterface.View view;
    Repository repository;

    public CityPresenter(CityInterface.View view) {
        this.view = view;
        repository = Repository.getRepository();
    }

    @Override
    public void getCities() {
        repository.getCities(this);
    }

    @Override
    public void onCityReceived(List<City> cities) {





        view.setCities(cities);
    }
}
