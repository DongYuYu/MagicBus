package com.magicbus.city;

import com.magicbus.data.entries.City;

import java.util.ArrayList;
import java.util.List;

public interface CityInterface {

    interface View {


        void setCities(List<City> cities);
    }



    interface Presenter {
        void getCities();
    }
}


