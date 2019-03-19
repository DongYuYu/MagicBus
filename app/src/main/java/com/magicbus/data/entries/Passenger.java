package com.magicbus.data.entries;

public class Passenger {
    String selectedseat, passengername, passengerage, passengergender;


    public Passenger(String selectedseat, String passengername, String passengerage, String passengergender) {
        this.selectedseat = selectedseat;
        this.passengername = passengername;
        this.passengerage = passengerage;
        this.passengergender = passengergender;
    }
    public Passenger() {
        selectedseat = passengername = passengerage = passengergender = "";
    }
    public String getSelectedseat() {
        return selectedseat;
    }

    public void setSelectedseat(String selectedseat) {
        this.selectedseat = selectedseat;
    }

    public String getPassengername() {
        return passengername;
    }

    public void setPassengername(String passengername) {
        this.passengername = passengername;
    }

    public String getPassengerage() {
        return passengerage;
    }

    public void setPassengerage(String passengerage) {
        this.passengerage = passengerage;
    }

    public String getPassengergender() {
        return passengergender;
    }

    public void setPassengergender(String passengergender) {
        this.passengergender = passengergender;
    }
}
