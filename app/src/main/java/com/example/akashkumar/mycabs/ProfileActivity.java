package com.example.akashkumar.mycabs;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.ViewModelStore;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import static com.example.akashkumar.mycabs.R.layout.nav_header_profile;

public class ProfileActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener, GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener,NavigationView.OnNavigationItemSelectedListener {


    String mobilenumber,nameprofile,emailprofile;
    TextView profilenum,profilename,profileemail;

    private GoogleMap mMap;

    Polyline polyline1;
    String TotalDistance;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    Button Auto;
    double SourcePointlatitude;
    double DestinationPointlatitude;
    double Sourcepointlogtitude;
    int j;

    double Destinationlogtitute;
    int k;
    private static final int GOOGLE_API_CLIENT_ID = 0;
    protected GoogleApiClient mGoogleApiClient;
    private PlaceAutocompleteAdapter mAdapter;
    private PlaceAutocompleteAdapter1 mAdapter1;
    String Autilatitude, Autolongitude;
   int f;
    private AutoCompleteTextView mAutocompleteView, mAutocompleteView1;
    int i;
    GoogleApiClient mGoogleApiClient1;
    double autolat1;
    double lat21;
    double autolog;
    double lon21;
    String autlat;
    String autlog;
    String Deslat;
    String Deslong;
    String placeName;
    String placeName2;



    int PROXIMITY_RADIUS = 10000;
    Button Seater4, Seater8, Seater16, Bus, Pickup, Truck;
    String Sourcelongitude, Sourcelatitude, Destinationlongitude, Destinationlatitude1;

    //String mobilenumber,nameprofile,emailprofile;

    TextView Longitude;

    BetweenDistance distance;

    double dish,d15,totaldis1;
    String totaldis;
    String Sourcepoint, Destinationpoint;

    private static final String TAG = "MainActivity";
    String placeName1;
    String placeAttributuion1;

    private static final LatLngBounds BOUNDS_GREATER_SYDNEY = new LatLngBounds(
            new LatLng(-34.041458, 150.790100), new LatLng(-33.682247, 151.383362));





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        Toolbar toolbar =  findViewById(R.id.toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

            View headView=navigationView.getHeaderView(0);

        MyCoreDatabase Dm=new MyCoreDatabase(this);
        Dm.getAll();
        mobilenumber=Dm.getPhonenumber1();
        nameprofile=Dm.getName12();
        emailprofile=Dm.getGmail12();

            profilenum =headView.findViewById(R.id.profilenumber);
            profilename =headView.findViewById(R.id.profilename);


        profilenum.setText(mobilenumber);
        profilename.setText(nameprofile);


        navigationView.setNavigationItemSelectedListener(this);




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();





        // Retrieve the AutoCompleteTextView that will display Place suggestions.
        mAutocompleteView = (AutoCompleteTextView)
                findViewById(R.id.autocomplete_places);
        mAutocompleteView1 = (AutoCompleteTextView)
                findViewById(R.id.autocomplete_places1);
        Auto = findViewById(R.id.auto);


        Longitude = (TextView) findViewById(R.id.lang);

        // Register a listener that receives callbacks when a suggestion has been selected
        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);
        mAutocompleteView1.setOnItemClickListener(mAutocompleteClickListener1);

        // Retrieve the TextViews that will display details and attributions of the selected place.


        // CurrentLocation

        Seater4 = findViewById(R.id.Seater4);
        Seater8 = findViewById(R.id.Seater8);
        Seater16 = findViewById(R.id.Seater16);
        Pickup = findViewById(R.id.pickup);
        Truck = findViewById(R.id.truck);
        Bus = findViewById(R.id.bus);






        Toast.makeText(this,mobilenumber+nameprofile+emailprofile,Toast.LENGTH_SHORT).show();






        Seater4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Diailogbox();
                Intent intent = new Intent(ProfileActivity.this, FourSeater.class);
                intent.putExtra("Sourcepoint", mAutocompleteView.getText().toString());
                intent.putExtra("Destinationpoint", mAutocompleteView1.getText().toString());
                intent.putExtra("distance", ""+totaldis);

                startActivity(intent);
            }
        });
        Seater8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ProfileActivity.this, EightSeater.class);
                intent.putExtra("Sourcepoint", mAutocompleteView.getText().toString());
                intent.putExtra("Destinationpoint", mAutocompleteView1.getText().toString());
                intent.putExtra("distance", ""+totaldis);
                startActivity(intent);
            }
        });
        Seater16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ProfileActivity.this, SixteenSeater.class);
                intent.putExtra("Sourcepoint", mAutocompleteView.getText().toString());
                intent.putExtra("Destinationpoint", mAutocompleteView1.getText().toString());
                intent.putExtra("distance", ""+totaldis);
                startActivity(intent);
            }
        });
        Bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ProfileActivity.this, Bus.class);
                intent.putExtra("Sourcepoint", mAutocompleteView.getText().toString());
                intent.putExtra("Destinationpoint", mAutocompleteView1.getText().toString());
                intent.putExtra("distance", ""+totaldis);
                startActivity(intent);
            }
        });

        Pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ProfileActivity.this, Pickup.class);
                intent.putExtra("Sourcepoint", mAutocompleteView.getText().toString());
                intent.putExtra("Destinationpoint", mAutocompleteView1.getText().toString());
                intent.putExtra("distance", ""+totaldis);
                startActivity(intent);
            }
        });
        Truck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ProfileActivity.this, Truck.class);
                intent.putExtra("Sourcepoint", mAutocompleteView.getText().toString());
                intent.putExtra("Destinationpoint", mAutocompleteView1.getText().toString());
                intent.putExtra("distance", ""+totaldis);
                startActivity(intent);
            }
        });


        // Set up the adapter that will retrieve suggestions from the Places Geo Data API that cover
        // the entire world.
        mAdapter = new PlaceAutocompleteAdapter(this, android.R.layout.simple_list_item_1,
                mGoogleApiClient, BOUNDS_GREATER_SYDNEY, null);
        mAutocompleteView.setAdapter(mAdapter);
        mAdapter1 = new PlaceAutocompleteAdapter1(this, android.R.layout.simple_list_item_1,
                mGoogleApiClient, BOUNDS_GREATER_SYDNEY, null);
        mAutocompleteView1.setAdapter(mAdapter1);

        Auto.setOnClickListener(mOnClickListener);

        location();

    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast.makeText(ProfileActivity.this, "auto button click", Toast.LENGTH_SHORT).show();

            if (ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                    .getCurrentPlace(mGoogleApiClient, null);
            result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                    if (!likelyPlaces.getStatus().isSuccess()) {
                        // Request did not complete successfully
                        Log.e(TAG, "Place query did not complete. Error: " + likelyPlaces.getStatus().toString());
                        likelyPlaces.release();
                        return;
                    }
                    placeName1 = String.format("%s", likelyPlaces.get(0).getPlace().getName());
                    placeAttributuion1 = String.format("%s", likelyPlaces.get(0).getPlace().getAddress());

                    Autilatitude = String.format("%s", likelyPlaces.get(0).getPlace().getLatLng().latitude);
                    Autolongitude = String.format("%s", likelyPlaces.get(0).getPlace().getLatLng().longitude);

                    LatLng sydney = new LatLng(Double.parseDouble(Autilatitude), Double.parseDouble(Autolongitude));
                    mMap.addMarker(new MarkerOptions().position(sydney).title(placeName1 + "  " + placeAttributuion1));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                    f = 1;
                    j=2;

                    mAutocompleteView.setText(placeName1 + " " + placeAttributuion1);
                    likelyPlaces.release();
                }
            });
        }
    };


    void location() {
        if (ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(mGoogleApiClient, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(PlaceLikelihoodBuffer likelyPlaces) {




                if (!likelyPlaces.getStatus().isSuccess()) {
                    // Request did not complete successfully
                    Log.e(TAG, "Place query did not complete. Error: " + likelyPlaces.getStatus().toString());
                    likelyPlaces.release();
                    return;
                }
                placeName1 = String.format("%s", likelyPlaces.get(0).getPlace().getName());
                placeAttributuion1 = String.format("%s", likelyPlaces.get(0).getPlace().getAddress());

                Autilatitude = String.format("%s", likelyPlaces.get(0).getPlace().getLatLng().latitude);
                Autolongitude = String.format("%s", likelyPlaces.get(0).getPlace().getLatLng().longitude);

                LatLng sydney = new LatLng(Double.parseDouble(Autilatitude), Double.parseDouble(Autolongitude));
                mMap.addMarker(new MarkerOptions().position(sydney).title(placeName1 + "  " + placeAttributuion1));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                f = 1;
                j=2;
                mAutocompleteView.setText(placeName1 + " " + placeAttributuion1+"akash");
                likelyPlaces.release();
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

    private AdapterView.OnItemClickListener mAutocompleteClickListener1
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a PlaceAutocomplete object from which we
             read the place ID.
              */
            final PlaceAutocompleteAdapter1.PlaceAutocomplete1 item = mAdapter1.getItem(position);
            final String placeId = String.valueOf(item.placeId1);
            Log.i(TAG, "Autocomplete item selected: " + item.description1);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
              details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback1);
            Log.i(TAG, "Called getPlaceById to get Place details for " + item.placeId1);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback;

    {
            mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
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


                j = 1;
                f=2;
                BetweenDistance bt = new BetweenDistance();

                String lag = String.format("%s", places.get(0).getLatLng().latitude);
                String lon = String.format("%s", places.get(0).getLatLng().longitude);

                bt.setLag(lag);
                bt.setLon(lon);
                Sourcelatitude = bt.getLag();
                Sourcelongitude = bt.getLon();


                String sourcenmae = String.format("%s", places.get(0).getName());
                String sourceaddress = String.format("%s", places.get(0).getAddress());
                String sourceplacetype = String.format("%s", places.get(0).getPlaceTypes());

                Sourcepoint = sourcenmae + " " + sourceaddress;

            /*    String placeName = String.format("%s", places.get(0).getAddress());

                LatLng sydney = new LatLng();
                mMap1.addMarker(new MarkerOptions().position(sydney).title(placeName));
                mMap1.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


                String placeName = String.format("%s", places.get(0).getName());

                String placeName2 = String.format("%s", places.get(0).getAddress());

                LatLng sydney = new LatLng(Double.parseDouble(lag), Double.parseDouble(lon));
                mMap.addMarker(new MarkerOptions().position(sydney).title(placeName + "  " + placeName2));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                if (k == 1) {


                    int Radius = 6371;


                    SourcePointlatitude = Double.parseDouble(Sourcelatitude);
                    DestinationPointlatitude = Double.parseDouble(Destinationlatitude1);
                    Sourcepointlogtitude = Double.parseDouble(Sourcelongitude);
                    Destinationlogtitute = Double.parseDouble(Destinationlongitude);
                    double dLat = Math.toRadians(DestinationPointlatitude - SourcePointlatitude);
                    double dLon = Math.toRadians(Destinationlogtitute - Sourcepointlogtitude);
                    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                            + Math.cos(Math.toRadians(SourcePointlatitude))
                            * Math.cos(Math.toRadians(DestinationPointlatitude)) * Math.sin(dLon / 2)
                            * Math.sin(dLon / 2);
                    double c = 2 * Math.asin(Math.sqrt(a));
                    double valueResult = Radius * c;
                    double km = valueResult / 1;
                    DecimalFormat newFormat = new DecimalFormat("####");
                    int kmInDec = Integer.valueOf(newFormat.format(km));
                    double meter = valueResult % 1000;
                    int meterInDec = Integer.valueOf(newFormat.format(meter));

                    String s1 = String.valueOf(km);
                    TotalDistance = s1.substring(0, 5);
                    dish = Double.parseDouble(TotalDistance);
                    d15 = (dish / 100) * 15;
                    totaldis1 = dish + d15;

                    String s2 = String.valueOf(totaldis1);
                    totaldis = s2.substring(0, 5);


                    Longitude.setText("" + totaldis + " KM");

                    Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .add(
                                    new LatLng(Double.parseDouble(lag), Double.parseDouble(lon)),

                                    new LatLng(DestinationPointlatitude, Destinationlogtitute)));
                    polyline1.setTag("A");


                }
                final CharSequence thirdPartyAttribution = places.getAttributions();


                Log.i(TAG, "Place details received: " + place.getName());

                places.release();
            }
        };
    }



    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback1
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places1) {
            if (!places1.getStatus().isSuccess()) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete. Error: " + places1.getStatus().toString());
                places1.release();
                return;
            }
            final Place place = places1.get(0);


            BetweenDistance bt = new BetweenDistance();


            String lag1 = String.format("%s", places1.get(0).getLatLng().latitude);
            String lon11 = String.format("%s", places1.get(0).getLatLng().longitude);

            String latitute11 = String.format("%s", places1.get(0).getLatLng().latitude);
            String lontitute11 = String.format("%s", places1.get(0).getLatLng().longitude);

            DidtancebetweenAutotodestination AD = new DidtancebetweenAutotodestination();
            AD.setDestinationlagtitude(latitute11);
            AD.setDestinationLongititude(lontitute11);

            bt.setLag1(lag1);
            bt.setLon1(lon11);


            placeName = String.format("%s", places1.get(0).getName());

            placeName2 = String.format("%s", places1.get(0).getAddress());


            String sourcenmae = String.format("%s", places1.get(0).getName());
            String sourceaddress = String.format("%s", places1.get(0).getAddress());


            Destinationpoint = sourcenmae + " " + sourceaddress;


            Destinationlatitude1 = bt.getLag1();
            Destinationlongitude = bt.getLon1();


            // radius of earth in Km



            if (f == 1) {

                auto();

            }
                if (j == 1) {

                    int Radius = 6371;
                    SourcePointlatitude = Double.parseDouble(Sourcelatitude);
                    DestinationPointlatitude = Double.parseDouble(Destinationlatitude1);
                    Sourcepointlogtitude = Double.parseDouble(Sourcelongitude);
                    Destinationlogtitute = Double.parseDouble(Destinationlongitude);
                    double dLat = Math.toRadians(DestinationPointlatitude - SourcePointlatitude);
                    double dLon = Math.toRadians(Destinationlogtitute - Sourcepointlogtitude);
                    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                            + Math.cos(Math.toRadians(SourcePointlatitude))
                            * Math.cos(Math.toRadians(DestinationPointlatitude)) * Math.sin(dLon / 2)
                            * Math.sin(dLon / 2);
                    double c = 2 * Math.asin(Math.sqrt(a));
                    double valueResult = Radius * c;
                    double km = valueResult / 1;
                    DecimalFormat newFormat = new DecimalFormat("####");
                    int kmInDec = Integer.valueOf(newFormat.format(km));
                    double meter = valueResult % 1000;
                    int meterInDec = Integer.valueOf(newFormat.format(meter));

                    String s1 = String.valueOf(km);
                    TotalDistance = s1.substring(0, 5);

                    k = 1;
                    dish=Double.parseDouble(TotalDistance);
                    d15=(dish/100)*15;
                    totaldis1=dish+d15;
                    String s2=String.valueOf(totaldis1);
                    totaldis = s2.substring(0, 5);


                    Longitude.setText(""+totaldis + " KM");

                    Longitude.setText(""+totaldis + " KM");
                    Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                            + " Meter   " + meterInDec);


                    LatLng sydney = new LatLng(DestinationPointlatitude, Destinationlogtitute);
                    mMap.addMarker(new MarkerOptions().position(sydney).title(placeName + "  " + placeName2));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                    Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .add(
                                    new LatLng(SourcePointlatitude, Sourcepointlogtitude),

                                    new LatLng(DestinationPointlatitude, Destinationlogtitute)));
// Store a data object with the polyline, used here to indicate an arbitrary type.
                    polyline1.setTag("A");

                }







            Log.i(TAG, "Place details received: " + place.getName());

            i = 1;

            places1.release();
        }
    };


    void auto() {
        BetweenDistance bt2 = new BetweenDistance();
        DidtancebetweenAutotodestination Ad = new DidtancebetweenAutotodestination();

        autlat = Ad.getAutolagnitute();
        autlog = Ad.getAutoLongnitude();
        Deslat = Ad.getDestinationlagtitude();
        Deslong = Ad.getDestinationLongititude();

        int Radius = 6371;
        autolat1 = Double.parseDouble(Autilatitude);
        lat21 = Double.parseDouble(Destinationlatitude1);
        autolog = Double.parseDouble(Autolongitude);
        lon21 = Double.parseDouble(Destinationlongitude);
        double dLat = Math.toRadians(lat21 - autolat1);
        double dLon = Math.toRadians(lon21 - autolog);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(autolat1))
                * Math.cos(Math.toRadians(lat21)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));

        String s1 = String.valueOf(km);
        TotalDistance = s1.substring(0, 5);
        dish=Double.parseDouble(TotalDistance);
        d15=(dish/100)*15;
        totaldis1=dish+d15;
        String s2=String.valueOf(totaldis1);
        totaldis = s2.substring(0, 5);


        Longitude.setText(""+totaldis + " KM");


        Longitude.setText(""+totaldis + " KM");
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);


        LatLng sydney = new LatLng(lat21, lon21);
        mMap.addMarker(new MarkerOptions().position(sydney).title(placeName + "  " + placeName2));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(autolat1, autolog),

                        new LatLng(lat21, lon21)));
// Store a data object with the polyline, used here to indicate an arbitrary type.
        polyline1.setTag("A");


    }


    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int POLYLINE_STROKE_WIDTH_PX = 12;

    Intent intent;
    //
// Create a stroke pattern of a gap followed by a dot.

    private static final PatternItem DOT = new Dot();
    private static final PatternItem GAP = new Gap(10);
    //
// Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);


    private void stylePolyline(Polyline polyline) {


        String type = "";
        // Get the data object stored with the polyline.
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "A":
                // Use a custom bitmap as the cap at the start of the line.
                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.cast_abc_scrubber_control_off_mtrl_alpha), 10));
                break;
            case "B":
                // Use a round cap at the start of the line.
                polyline.setStartCap(new RoundCap());
                break;
        }

        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
    }


    @Override
    public void onPolylineClick(Polyline polyline) {


        if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {
            polyline.setPattern(PATTERN_POLYLINE_DOTTED);
        } else {
            // The default pattern is a solid stroke.
            polyline.setPattern(null);
        }

        Toast.makeText(this, "Route type " + polyline.getTag().toString(),
                Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //   Longitude.setText(""+latitude1);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);


            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

        }
        updateMyLocation();

        mMap.setOnMarkerDragListener(this);
        mMap.setOnMarkerClickListener(this);

        mMap.isMyLocationEnabled();

        polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(SourcePointlatitude, Sourcepointlogtitude),

                        new LatLng(DestinationPointlatitude, Destinationlogtitute)));

        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(SourcePointlatitude, Sourcepointlogtitude), 4));

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
        polyline1.setTag("A");

        stylePolyline(polyline1);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient1 = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient1.connect();
    }


    @Override
    public void onPolygonClick(Polygon polygon) {

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


        Log.e(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        // TODO(Developer): Check error code and notify the user of error state and resolution.
        Toast.makeText(this,
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();
        ProfileActivity.this.finish();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.Aboutus) {
         Intent intent=new Intent(ProfileActivity.this,AboutUs.class);
         startActivity(intent);
        } else if (id == R.id.business) {
           Intent intent=new Intent(ProfileActivity.this,Bussinescabs.class);
           startActivity(intent);

        } else if (id == R.id.tracking) {
           Intent intent=new Intent(ProfileActivity.this,UserTracking.class);
           startActivity(intent);

        } else if (id == R.id.transaction) {
           Intent intent=new Intent(ProfileActivity.this,Transaction.class);
           startActivity(intent);

        } else if (id == R.id.complain) {
           Intent intent=new Intent(ProfileActivity.this,Complain.class);
           startActivity(intent);

        } else if (id == R.id.help) {
           Intent intent=new Intent(ProfileActivity.this,Help.class);
           startActivity(intent);

        }else if (id == R.id.share) {





       }else if (id == R.id.rateus) {



       }else if (id == R.id.contact) {
           Intent intent=new Intent(ProfileActivity.this,Contact.class);
           startActivity(intent);



       }else if (id == R.id.developer) {
           Intent intent=new Intent(ProfileActivity.this,Developer.class);
           startActivity(intent);



       }else if (id == R.id.updateus) {


       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void updateMyLocation() {



        // Enable the location layer. Request the location permission if needed.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Uncheck the box until the layer has been enabled and request missing permission.

            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, results,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);

        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();

    }



    void  Diailogbox()
    {

        ExampleDailog exampleDailog=new ExampleDailog();
        exampleDailog.show(getSupportFragmentManager(),"example dialog");

    }









}
