package com.magicbus.roomdb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.magicbus.R;

import java.util.List;

public class TripHistoryActivity extends AppCompatActivity {

    private static final String TAG = TripHistoryActivity.class.getSimpleName();
    TripRoomDatabase db;
    private TripDao mTripDao;
    private List<Trip> tripList;
    private TripAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_history);

        db = TripRoomDatabase.getDatabase(this);
        mTripDao = db.tripDao();
        recyclerView = findViewById(R.id.rvTripHistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // just to test
        Trip trip = new Trip("dong230","chicago","102","$300","1343232",
                "7%","11/11/2019","11:00am","4:00pm","5","232",
                 "sm@we.com","+16775645343","43","chan","36","male");
             // insert Trip into database

           insert(trip);
           new getAsyncTask(mTripDao).execute();

    }

    public void insert (Trip trip) {
        new insertAsyncTask(mTripDao).execute(trip);
     //   adapter.notifyDataSetChanged();
    }


    private static class insertAsyncTask extends AsyncTask<Trip, Void, Void> {

        private TripDao mAsyncTaskDao;

        insertAsyncTask(TripDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Trip... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private class getAsyncTask extends AsyncTask<Void, Void ,Void>
    {

        private TripDao mAsyncTaskDao;

        public getAsyncTask( TripDao mTripDao) {

            this.mAsyncTaskDao = mTripDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tripList = mAsyncTaskDao.getAllTrips();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

//            super.onPostExecute(aVoid);

            adapter = new TripAdapter(TripHistoryActivity.this,tripList);
            recyclerView.setAdapter(adapter);

        }
    }

}
