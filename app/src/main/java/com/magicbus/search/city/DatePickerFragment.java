package com.magicbus.search.city;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.magicbus.MainActivity;
import com.magicbus.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {







    private CityFragment cityFragment;


    public DatePickerFragment(CityFragment cityFragment) {
       this.cityFragment = cityFragment;
    }



//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//        return textView;
//    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //CityFragment fg = (CityFragment) getActivity().getSupportFragmentManager().findFragmentByTag("datePicker");

        cityFragment.processDatePickerResult(year, month, dayOfMonth);
    }








    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {





        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);





        DatePickerDialog date = new DatePickerDialog(getActivity(), this, year, month, day);
        date.getDatePicker().setMinDate(System.currentTimeMillis());


// Create a new instance of DatePickerDialog and return it.








        return date;
    }







}
