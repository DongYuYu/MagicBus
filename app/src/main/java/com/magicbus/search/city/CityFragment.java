package com.magicbus.search.city;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.magicbus.MapFragment;
import com.magicbus.R;
import com.magicbus.data.entries.City;
import com.magicbus.search.servicelist.ServiceListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CityFragment extends Fragment implements CityInterface.View, AdapterView.OnItemSelectedListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private CityInterface.Presenter presenter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner spinner;

    Spinner spinner2;
    Button button;

    Button dateButton;
    private OnCityFragmentInteractionListener mListener;
    Button sourceButton, destButton;

    public CityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CityFragment newInstance(String param1, String param2) {
        CityFragment fragment = new CityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);



        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }






        presenter = new CityPresenter(this);

        presenter.getCities();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_city, container, false);
        spinner = (Spinner) view.findViewById(R.id.planets_spinner);

        spinner.setOnItemSelectedListener(this);


        spinner2 = (Spinner) view.findViewById(R.id.planets_spinner1);






        spinner2.setOnItemSelectedListener(this);


        button = view.findViewById(R.id.btnSearch);
        button.setOnClickListener(this);

        // buttons to navigate to map fragment are initialized here
        sourceButton = view.findViewById(R.id.buttonSource);
        destButton = view.findViewById(R.id.buttonDest);
        sourceButton.setOnClickListener(this);
        destButton.setOnClickListener(this);




        dateButton = view.findViewById(R.id.btnDate);

        dateButton.setOnClickListener(this);

        return view;
    }



    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string + "/" + year_string);
       // Toast.makeText(getActivity(), dateMessage, Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("default", Context.MODE_PRIVATE).edit();


        editor.putString("journydate", dateMessage);
        editor.apply();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public void showDatePicker() {
        DialogFragment newFragment = new DatePickerFragment(this);
        newFragment.show(getActivity().getSupportFragmentManager(),"datePicker");
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCityFragmentInteractionListener) {
            mListener = (OnCityFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setCities(List<City> cities) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        List<City> fromCity = new ArrayList<>();
        City from = new City("Select Orientation");



        City to = new City("Select Destination");
        fromCity.add(from);
        fromCity.addAll(cities);
        List<City> toCity = new ArrayList<>();
        toCity.add(to);
        toCity.addAll(cities);
        ArrayAdapter<City> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, fromCity);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        ArrayAdapter<City> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, toCity);

        spinner2.setAdapter(adapter2);
    }
    @Override
    public void onResume() {
        super.onResume();
        presenter.getCities();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



        switch(parent.getId()) {
            case R.id.planets_spinner:
                City city = (City) parent.getItemAtPosition(position);
                //Toast.makeText(getActivity(), city.getCityname(), Toast.LENGTH_LONG).show();
                break;

            case R.id.planets_spinner1:
                City city3 = (City) parent.getItemAtPosition(position);
                //Toast.makeText(getActivity(), city3.getCitylatitude(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {





        switch(v.getId()) {
            case R.id.btnSearch:
            City from = (City) spinner.getSelectedItem();

            City to = (City) spinner2.getSelectedItem();

            String start_lat, start_long, end_lat, end_long;
            start_lat = from.getCitylatitude();

            start_long = from.getCitylongtitude();
            end_lat = to.getCitylatitude();
            end_long = to.getCitylongtitude();
                Bundle args = new Bundle();
                args.putString("start_lat", start_lat);

                args.putString("start_long", start_long);
                args.putString("end_lat", end_lat);

                args.putString("end_long", end_long);

                if (start_lat.equals("")) {
                    Toast.makeText(getActivity(), "Please Select Orientation.", Toast.LENGTH_LONG).show();
                } else if (end_lat.equals("")) {


                    Toast.makeText(getActivity(), "Please Select Destination.", Toast.LENGTH_LONG).show();

                } else if (start_lat.equals(end_lat)) {
                    Toast.makeText(getActivity(), "Orientation should not be the same as Destination.", Toast.LENGTH_LONG).show();
                } else {
                    Fragment fg = new ServiceListFragment();
                    fg.setArguments(args);


                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fg)


                            .addToBackStack(null)


                            .commit();
                }
                break;


            case R.id.buttonSource:
                City source = (City) spinner.getSelectedItem();

                String startlat, startLong;
                startlat = source.getCitylatitude();
                startLong = source.getCitylongtitude();
                Bundle source_args = new Bundle();
                source_args.putString("lat", startlat);
                source_args.putString("long", startLong);

                Fragment source_fg = new MapFragment();
                source_fg.setArguments(source_args);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, source_fg)
                        .addToBackStack(null)
                        .commit();
                break;


            case R.id.buttonDest:
                   City dest = (City) spinner2.getSelectedItem();
                   String  endLat, endlong;
                   endLat = dest.getCitylatitude();
                   endlong = dest.getCitylongtitude();

                Bundle dest_args = new Bundle();
                dest_args.putString("lat", endLat);
                dest_args.putString("long", endlong);

                Fragment dest_fg = new MapFragment();
                dest_fg.setArguments(dest_args);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, dest_fg)
                        .addToBackStack(null)
                        .commit();

                break;


            case R.id.btnDate:
              showDatePicker();
                break;
        }



    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCityFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
