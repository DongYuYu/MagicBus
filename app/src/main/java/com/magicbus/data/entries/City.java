package com.magicbus.data.entries;



import java.util.Map;



public class City {
    private String cityname;
    private String citylatitude;
    private String citylongtitude;

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCitylatitude() {
        return citylatitude;
    }

    public void setCitylatitude(String citylatitude) {
        this.citylatitude = citylatitude;
    }

    public String getCitylongtitude() {
        return citylongtitude;
    }

    public void setCitylongtitude(String citylongtitude) {
        this.citylongtitude = citylongtitude;
    }
    @Override
    public String toString() {return getCityname();}

}

