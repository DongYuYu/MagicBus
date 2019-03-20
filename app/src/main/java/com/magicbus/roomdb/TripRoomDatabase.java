package com.magicbus.roomdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.magicbus.roomdb.Trip;
import com.magicbus.roomdb.TripDao;

@Database(entities = {Trip.class}, version = 1,exportSchema = true)
public abstract class TripRoomDatabase extends RoomDatabase {

        public abstract TripDao tripDao();
        private static TripRoomDatabase INSTANCE;

    public static TripRoomDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (TripRoomDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                TripRoomDatabase.class, "trip_database")
                                // Wipes and rebuilds instead of migrating
                                // if no Migration object.
                                // Migration is not part of this practical.
                                .fallbackToDestructiveMigration()
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
    }

