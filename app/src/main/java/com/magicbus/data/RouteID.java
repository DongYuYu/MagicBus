package com.magicbus.data;

public class RouteID {

    private String startpoint_latitude, startpoint_longitude, endpoint_latitude, endpoint_longitude;

    public RouteID(String startpoint_latitude, String startpoint_longitude, String endpoint_latitude, String endpoint_longitude) {
        this.startpoint_latitude = startpoint_latitude;
        this.startpoint_longitude = startpoint_longitude;
        this.endpoint_latitude = endpoint_latitude;
        this.endpoint_longitude = endpoint_longitude;
    }

    public String getStartpoint_latitude() {
        return startpoint_latitude;
    }

    public void setStartpoint_latitude(String startpoint_latitude) {
        this.startpoint_latitude = startpoint_latitude;
    }

    public String getStartpoint_longitude() {
        return startpoint_longitude;
    }

    public void setStartpoint_longitude(String startpoint_longitude) {
        this.startpoint_longitude = startpoint_longitude;
    }

    public String getEndpoint_latitude() {
        return endpoint_latitude;
    }

    public void setEndpoint_latitude(String endpoint_latitude) {
        this.endpoint_latitude = endpoint_latitude;
    }

    public String getEndpoint_longitude() {
        return endpoint_longitude;
    }

    public void setEndpoint_longitude(String endpoint_longitude) {
        this.endpoint_longitude = endpoint_longitude;
    }
}
