
package com.example.akashkumar.mycabs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;


public class SixteenSeater extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    EditText Sourcepoint, Destinationpoint;
    TextView textview1,textview2;
    TextView priceperkm,totalprice1,waitingtime,distance;
    RadioGroup radioGroup1,radioGroup2;
    TimePicker timepicker;
    DatePicker picker;
    Button Bookingnow;
    int totalprice;
    String vehicle;
    int noofvehicle;

    EditText Noofvehicle;
    Double dis2;
    int price_km=0;
    String travelmethod,waitingtime1;
    RadioButton rd;
    String source,desti,distance1;


    private static final int GOOGLE_API_CLIENT_ID = 0;

    protected GoogleApiClient mGoogleApiClient;

    private PlaceAutocompleteAdapter mAdapter;


    private AutoCompleteTextView mAutocompleteView;

    private static final String TAG = "MainActivity";

    private static final LatLngBounds BOUNDS_GREATER_SYDNEY = new LatLngBounds(
            new LatLng(-34.041458, 150.790100), new LatLng(-33.682247, 151.383362));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        setContentView(R.layout.activity_sixteen_seater);
        Noofvehicle=findViewById(R.id.noofcar);

        Sourcepoint = findViewById(R.id.sourcepoint);
        Destinationpoint = findViewById(R.id.destinationpoint);
        rd=findViewById(R.id.normalcar);
        Bookingnow=findViewById(R.id.booknow);
        waitingtime=findViewById(R.id.waitingtime);
        priceperkm=findViewById(R.id.priceperkm);
        distance = findViewById(R.id.distance);
        totalprice1=findViewById(R.id.totalprice);
        radioGroup1=findViewById(R.id.radiogroup);
        radioGroup2=findViewById(R.id.radiogroup1);
        picker=(DatePicker)findViewById(R.id.datePicker);
        timepicker=findViewById(R.id.timepicker);

        waitingtime1="100/hour";
        waitingtime.setText(waitingtime1);

        Intent intent = getIntent();
        source = intent.getStringExtra("Sourcepoint");
        desti = intent.getStringExtra("Destinationpoint");
        distance1 = intent.getStringExtra("distance");


        distance.setText(distance1);
        Sourcepoint.setText(source);
        Destinationpoint.setText(desti);
        dis2=Double.parseDouble(distance1);
        mAutocompleteView = (AutoCompleteTextView)
                findViewById(R.id.autocomplete_places);
        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);
        mAdapter = new PlaceAutocompleteAdapter(this, android.R.layout.simple_list_item_1,
                mGoogleApiClient, BOUNDS_GREATER_SYDNEY, null);
        mAutocompleteView.setAdapter(mAdapter);
        Bookingnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book();
            }
        });


    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            final PlaceAutocompleteAdapter.PlaceAutocomplete item = mAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i(TAG, "Autocomplete item selected: " + item.description);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
              details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            Log.i(TAG, "Called getPlaceById to get Place details for " + item.placeId);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = places.get(0);



            Log.i(TAG, "Place details received: " + place.getName());

            places.release();
        }
    };








    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();;
       //month is 0 based
        builder.append(picker.getDayOfMonth()+"/");
        builder.append((picker.getMonth() + 1)+"/");
        builder.append(picker.getYear());
        return builder.toString();
    }



    public void onRadioButtonClicked(View view) {
        int radio=radioGroup1.getCheckedRadioButtonId();
        int radio1=radioGroup2.getCheckedRadioButtonId();


        boolean checked = ((RadioButton) view).isChecked();
        if(Noofvehicle.getText().toString().isEmpty())
        {
            Noofvehicle.setError("enter no of vehicle");
        }else {


            if ((radio >= 2131296490 && radio <= 2131296505) && (radio1 >= 2131296500 && radio1 <= 2131296510)) {
                vehicle = Noofvehicle.getText().toString();
                noofvehicle = Integer.parseInt(vehicle);

                price_km = 20;
                priceperkm.setText("" + price_km);
                totalprice = (int) (dis2 * price_km * noofvehicle);
                totalprice1.setText("" + totalprice);
                travelmethod = "Normalcar on Oneway";


            }
            if ((radio >= 2131296490 && radio <= 2131296505) && (radio1 >= 2131296530 && radio1 <= 2131296550)) {
                vehicle = Noofvehicle.getText().toString();
                noofvehicle = Integer.parseInt(vehicle);
                price_km = 16;
                priceperkm.setText("" + price_km);
                totalprice = (int) (dis2 * price_km * noofvehicle);
                totalprice1.setText("" + totalprice);
                travelmethod = "Normalcar on Roundtrip";
            }

            if ((radio >= 2131296444 && radio <= 2131296468) && (radio1 >= 2131296495 && radio1 <= 2131296510)) {
                vehicle = Noofvehicle.getText().toString();
                noofvehicle = Integer.parseInt(vehicle);
                price_km = 30;
                priceperkm.setText("" + price_km);
                totalprice = (int) (dis2 * price_km * noofvehicle);
                totalprice1.setText("" + totalprice);
                travelmethod = "Luxurycar on Oneway";

            }
            if ((radio >= 2131296444 && radio <= 2131296468) && (radio1 >= 2131296530 && radio1 <= 2131296550)) {
                vehicle = Noofvehicle.getText().toString();
                noofvehicle = Integer.parseInt(vehicle);
                price_km = 25;
                priceperkm.setText("" + price_km);
                totalprice = (int) (dis2 * price_km * noofvehicle);
                totalprice1.setText("" + totalprice);
                travelmethod = "Luxurycar on Roundtrip";
            }
        }

    }


    public String getCurrentTime(){
        String currentTime=""+timepicker.getCurrentHour()+":"+timepicker.getCurrentMinute();
        return currentTime;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    void book()
    {
        if(mAutocompleteView.getText().toString().isEmpty())

        {
            mAutocompleteView.setError("enter pin code");
        }
        else {
            if(price_km==0)
            {
                rd.setError("chose travelling ");

            }
            else {
                String s1=mAutocompleteView.getText().toString();
                Intent intent=new Intent(SixteenSeater.this,BookingActivity.class);
                intent.putExtra("source",source);
                intent.putExtra("destination",desti);
                intent.putExtra("distance",distance1);
                intent.putExtra("pincode",s1);
                intent.putExtra("priceperkm",""+price_km);
                intent.putExtra("totalprice",""+totalprice);
                intent.putExtra("travelmehod",travelmethod);
                intent.putExtra("waitingtiming",waitingtime1);
                intent.putExtra("date",getCurrentDate());
                intent.putExtra("time",getCurrentTime());
                intent.putExtra("noofvehicle",""+noofvehicle);
                intent.putExtra("seater","16 Seater Booking");
                startActivity(intent);
            }

        }



    }
}