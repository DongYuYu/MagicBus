package com.magicbus.roomdb;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.magicbus.roomdb.Trip;

import java.util.List;

@Dao
public interface TripDao {


    @Insert
    void insert(Trip trip);

    @Query("DELETE FROM Trip_Info")
    void deleteAll();

    @Query("SELECT * from Trip_Info ")
    List<Trip> getAllTrips();


}
