package com.magicbus.data.entries;

import com.google.gson.annotations.SerializedName;

public class ServiceList {


    private String id;

    private String routename;


    @SerializedName("route-startfrom")
    private String route_startfrom;
    @SerializedName("route-destination")
    private String route_destination;

    @SerializedName("route-startpoint-latitude")
    private String route_startpoint_latitude;

    @SerializedName("route-startpoint-longitude")
    private String route_startpoint_longitude;
    @SerializedName("route-endpoint-latitude")
    private String route_endpoint_latitude;





    @SerializedName("route-endpoint-longiude")
    private String route_endpoint_longiude;




    public ServiceList(String id, String routename, String route_startfrom, String route_destination,
                       String route_startpoint_latitude, String route_startpoint_longitude,
                       String route_endpoint_latitude, String route_endpoint_longiude) {
        this.id = id;
        this.routename = routename;
        this.route_startfrom = route_startfrom;
        this.route_destination = route_destination;
        this.route_startpoint_latitude = route_startpoint_latitude;
        this.route_startpoint_longitude = route_startpoint_longitude;
        this.route_endpoint_latitude = route_endpoint_latitude;
        this.route_endpoint_longiude = route_endpoint_longiude;
    }

    public String getId() {
        return id;
    }

    public String getRoutename() {
        return routename;
    }

    public String getRoute_startfrom() {
        return route_startfrom;
    }

    public String getRoute_destination() {
        return route_destination;
    }

    public String getRoute_startpoint_latitude() {
        return route_startpoint_latitude;
    }

    public String getRoute_startpoint_longitude() {
        return route_startpoint_longitude;
    }

    public String getRoute_endpoint_latitude() {
        return route_endpoint_latitude;
    }

    public String getRoute_endpoint_longiude() {
        return route_endpoint_longiude;
    }
}
