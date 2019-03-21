package com.magicbus.checkout;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.magicbus.R;
import com.magicbus.checkout.coupons.CouponsContract;
import com.magicbus.checkout.coupons.CouponsPresenter;
import com.magicbus.checkout.couponsvalidation.CouponsValidationContract;
import com.magicbus.checkout.couponsvalidation.CouponsValidationPresenter;
import com.magicbus.data.entries.BusInformation;
import com.magicbus.data.entries.Coupons;
import com.magicbus.data.entries.CouponsValidation;
import com.magicbus.roomdb.Trip;
import com.magicbus.roomdb.TripDao;
import com.magicbus.roomdb.TripHistoryActivity;
import com.magicbus.roomdb.TripRoomDatabase;
import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */


public class PaymentFragment extends Fragment implements CouponsContract.View, CouponsValidationContract.View {

    TripRoomDatabase db;
    private TripDao mTripDao;
    private static final String TAG = "paymentExample";
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;
    private static final String CONFIG_CLIENT_ID = "credentials from developer.paypal.com";

    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final int REQUEST_CODE_PROFILE_SHARING = 3;


    private CouponsContract.Presenter couponsPresenter;
    private CouponsValidationContract.Presenter couponsValidationPresenter;


    private TextView tv_baseFare, tv_tax, tv_total, tv_coupon;
    private EditText et_coupon;
    private Button button_coupon;
    private ImageButton button_paypal;

    private double totalPrice = 0.0;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)
            // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("RJT Bus Reservation Pvt Ltd.")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.rjt.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.rjt.com/legal"));

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_payment, container, false);
        this.couponsPresenter = new CouponsPresenter(this);
        this.couponsValidationPresenter = new CouponsValidationPresenter(this);
        this.tv_baseFare = view.findViewById(R.id.tv_baseFare);
        this.tv_tax = view.findViewById(R.id.tv_tax);
        this.tv_total = view.findViewById(R.id.tv_total);
//        this.tv_coupon = view.findViewById(R.id.tv_coupon);
        this.et_coupon = view.findViewById(R.id.et_coupon);
        this.button_coupon = view.findViewById(R.id.button_coupon);
        this.button_paypal = view.findViewById(R.id.button_paypal);
        String bus = getActivity().getSharedPreferences("default", Context.MODE_PRIVATE).getString("busInfo", "");
        Gson gson = new Gson();
        BusInformation busInformation = gson.fromJson(bus, BusInformation.class);



        tv_baseFare.setText(String.valueOf(Integer.parseInt(busInformation.getFare()) * getArguments().getInt("quantity", 1)));
        tv_tax.setText(calculateTax(tv_baseFare.getText().toString()));
        tv_total.setText(calculateTotal(tv_baseFare.getText().toString(), tv_tax.getText().toString()));

//        tv_coupon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        button_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCouponsValidationRequest(et_coupon.getText().toString());
            }
        });

        button_paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);

                /*
                 * See getStuffToBuy(..) for examples of some available payment options.
                 */

                Intent intent = new Intent(getActivity(), PaymentActivity.class);

                // send the same configuration for restart resiliency
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

                startActivityForResult(intent, REQUEST_CODE_PAYMENT);
            }
        });

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = TripRoomDatabase.getDatabase(getActivity());
        mTripDao = db.tripDao();
    }

    public String calculateTax(String baseFare) {
        double doubleBaseFare = Double.parseDouble(baseFare);
        double tax = doubleBaseFare * 0.1;
        return Double.toString(tax);
    }

    public String calculateTotal(String baseFare, String tax) {
        double doubleBaseFare = Double.parseDouble(baseFare);
        double doubleTax = Double.parseDouble(tax);
        double total = doubleBaseFare + doubleTax;
        this.totalPrice = total;
        return Double.toString(total);
    }

    @Override
    public void getCouponsResponse(List<Coupons> response) {

    }

    public void sendCouponsRequest() {
        couponsPresenter.sendCouponsRequest();
    }

    @Override
    public void getCouponsValidationResponse(List<CouponsValidation> response) {
        if (response.get(0).getMsg().equals("success")){
            Log.d("Coupons Validation", response.get(0).toString());
            String discount = response.get(0).getDiscount();
            double totalPriceAfterDiscount = (1 - (Double.parseDouble(discount) / 100)) * totalPrice;
            this.totalPrice = totalPriceAfterDiscount;
            String totalAfterDiscount = Double.toString(totalPriceAfterDiscount);
            tv_total.setText(totalAfterDiscount);
            totalPrice = totalPriceAfterDiscount;
            Toast.makeText(getContext(), discount + "% discount is applied", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(getContext(), "Invalid Coupon Number", Toast.LENGTH_SHORT).show();
    }

    public void sendCouponsValidationRequest(String couponno) {
        couponsValidationPresenter.sendCouponsValidationRequest(couponno);
    }


    private PayPalPayment getThingToBuy(String paymentIntent) {
        return new PayPalPayment(




                new BigDecimal(String.valueOf(totalPrice)), "USD", "Ticket Cost",
                paymentIntent);
    }

    protected void displayResultText(String result) {
//        findViewById(R.id.txtResult)).setText("Result : " + result+Long.toHexString(Double.doubleToLongBits(Math.random())));
        Toast.makeText(getContext().getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }

    public void storeHistory() {
        SharedPreferences sf = getActivity().getSharedPreferences("default", Context.MODE_PRIVATE);
        String token_id = sf.getString("token_id", "");





        String busInfo = sf.getString("busInfo", "");
        Gson gson = new Gson();

        BusInformation busInformation = gson.fromJson(busInfo, BusInformation.class);
        String route_name = busInformation.getBusregistrationno();
        String busid = busInformation.getBusid();
        String fare = String.valueOf(totalPrice);
        String coupondiscount = "";
        String servicetax = "";
        String journydate = sf.getString("journydate", "");
        String boardingtime = busInformation.getBoardingtime();
        String droppingtime = busInformation.getDropingtime();
        String duration = busInformation.getJournyduration();
        String passengerid = "";
        String passengeremail = sf.getString("passengeremail", "");
        String passengermobile = "";




        String selectedseat = sf.getString("selectedseat", "");
        String passengername = sf.getString("passengername", "");
        String passengerage = sf.getString("passengerage", "");
        String passengergender = sf.getString("passengergender", "");

        Trip trip = new Trip(token_id, route_name, busid, fare, coupondiscount, servicetax,
                journydate, boardingtime, droppingtime, duration, passengerid,
                passengeremail, passengermobile, selectedseat, passengername, passengerage,
                passengergender);
        insert(trip);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm =
                        data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        Log.i(TAG, confirm.toJSONObject().toString(4));
                        Log.i(TAG, confirm.getPayment().toJSONObject().toString(4));
                        /**
                         *  TODO: send 'confirm' (and possibly confirm.getPayment() to your server for verification
                         * or consent completion.
                         * See https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                         * for more details.
                         *
                         * For sample mobile backend interactions, see
                         * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
                         */
//                        Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",EMAIL ADDRESS HERE, null));
//                        email.putExtra(Intent.EXTRA_SUBJECT, "ORDER CONFIRMATION");
//                        email.putExtra(Intent.EXTRA_TEXT,"Thanks for using our Services. Below are your bus details /n" +
//                                        "Token number is " + token_id + "/n" + "route name is: " + route_name + "/n" +
//                                        "Journey Day is" + journydate + "/n" + "Boarding time is at " + boardingtime + "/n" +
//                                "Your passenger id is " + passengerid + "/n" + "your seat number is " + selectedSeat);
//                        startActivity(Intent.createChooser(email, "Send email..."));
                        displayResultText("PaymentConfirmation info received from PayPal");







                        SharedPreferences.Editor editor = getActivity().getSharedPreferences("default", Context.MODE_PRIVATE).edit();




                        editor.putString("token_id", confirm.getProofOfPayment().getTransactionId()).apply();


                        storeHistory();

                        Fragment fg = new ConfirmationFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frag_container, fg)
                                .addToBackStack(null)
                                .commit();



                    } catch (JSONException e) {
                        Log.e(TAG, "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i(TAG,"The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(TAG,"An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        } else if (requestCode == REQUEST_CODE_FUTURE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PayPalAuthorization auth =
                        data.getParcelableExtra(PayPalFuturePaymentActivity.EXTRA_RESULT_AUTHORIZATION);
                if (auth != null) {
                    try {
                        Log.i("PayPal", auth.toJSONObject().toString(4));

                        String authorization_code = auth.getAuthorizationCode();
                        Log.i("PayPal", authorization_code);

                        sendAuthorizationToServer(auth);
                        displayResultText("Future Payment code received from PayPal");

                    } catch (JSONException e) {
                        Log.e("PayPal", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("PayPal", "The user canceled.");
            } else if (resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                        "PayPal",
                        "Probably the attempt to previously start the PayPalService had an invalid PayPalConfiguration. Please see the docs.");
            }

        }
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
    private void sendAuthorizationToServer(PayPalAuthorization authorization) {

        /**
         * TODO: Send the authorization response to your server, where it can
         * exchange the authorization code for OAuth access and refresh tokens.
         *
         * Your server must then store these tokens, so that your server code
         * can execute payments for this user in the future.
         *
         * A more complete example that includes the required app-server to
         * PayPal-server integration is available from
         * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
         */

    }
}