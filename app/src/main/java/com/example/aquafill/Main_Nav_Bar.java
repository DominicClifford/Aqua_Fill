package com.example.aquafill;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class Main_Nav_Bar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private ImageView imageView;
    /* ---------
    Nav Bar Code
    --------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_bar_compiler);

        //get Permission
        getLocationPermission();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        imageView = (ImageView) findViewById(R.id.addLocation);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        connection();
    }

    /* ------------
    Adding Location
    ------------ */

    public void openDialog(){
        Add_Location_Page add_location_page = new Add_Location_Page();
        add_location_page.show(getSupportFragmentManager(), "add location");

    }

    /* -------------
    Connecting to dB
    ------------- */
    public void connection(){
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){

            case R.id.action_bar_refresh:
                initMap();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    /* --------
    Google Maps
    -------- */

    private static final String Fine_Location = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String Access_Coarse = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int Location_Permission_Code = 55435;
    private static final float DEFAULT_ZOOM = 16.5f;

    //variables
    private Boolean mLocationPermissionGranted = false;
    private static GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Marker mMarker;

    //Initialise Map
    public void initMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        mapFragment.getMapAsync(Main_Nav_Bar.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Toast.makeText(this, "Map is Ready", Toast.LENGTH_LONG).show();
        mMap = googleMap;


        if (mLocationPermissionGranted) {

            getDeviceLocation();


            //show location on map and check permissions again
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //get permissions again for not already granted
                return;
            }
            //shows location on map
            mMap.setMyLocationEnabled(true);
            //adds location marker to return to current location
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            //prevents zooming in and out
            mMap.getUiSettings().setZoomControlsEnabled(false);
            mMap.getUiSettings().setZoomGesturesEnabled(true);
        }

        /* ----------
        Get Locations
        ---------- */

        getInfoWindowRecycle();
        getInfoWindowWater();
    }


    //get current location and show on map
    private void getDeviceLocation(){
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Main_Nav_Bar.this);
        try{
            if (mLocationPermissionGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        //if location was found
                        if (task.isSuccessful()) {
                            Location currentLocation = (Location) task.getResult();
                            //get location long and lat and pass to move camera class
                            if(currentLocation != null){
                                moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                        DEFAULT_ZOOM);
                            }
                            //If Current Location is not found
                            else{
                                Toast.makeText(Main_Nav_Bar.this, "Location Not Found, Make Sure Device Location is on", Toast.LENGTH_LONG).show();
                            }
                        }
                        //if you can not find location
                        else {
                            Toast.makeText(Main_Nav_Bar.this, "Location Not Found", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        } catch (SecurityException e){
        }
    }

    //this class moves the camera
    private void moveCamera(LatLng latLng, float zoom){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    //Get app Permission
    public void getLocationPermission(){

        //create string array to check permissions before initializing map
        String [] permissions = new String[2];

        permissions[0] = Fine_Location;
        permissions[1] = Access_Coarse;

        /*string array of permissions
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        */

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Fine_Location) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    Access_Coarse) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionGranted = true;
                //if all permissions are granted the initialise map else request permissions
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        Location_Permission_Code);
            }
        }else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    Location_Permission_Code);
        }
    }

    //Looks to see if permissions have been granted
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //no permissions to start with
        mLocationPermissionGranted = false;

        //check to see if permissions have been granted
        switch (requestCode){
            case Location_Permission_Code:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionGranted = false;
                            //if not granted return and request permissions
                            return;
                        }
                    }
                  mLocationPermissionGranted = true;
                    // initialise map
                    initMap();
                }
            }
        }
    }

    /* ------------------
    Location Info Display
    ------------------ */

    private void getInfoWindowRecycle(){
        LatLng McD_UXB = new LatLng(51.548227, -0.481134);
        String upVotes = "30";
        String downVotes = "20";

        MarkerOptions options = new MarkerOptions()
                .title("McDonalds UXB")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.recycle_pin));

        InfoWindowData_Recycle info = new InfoWindowData_Recycle();
        info.setUpvotes(upVotes);
        info.setDownvotes(downVotes);

        //Set icon on map and implement Info Window
        mMap.setInfoWindowAdapter(new Map_CustomeInfoWindow_Adapter_Recycle(Main_Nav_Bar.this));

        mMarker = mMap.addMarker(options.position(McD_UXB));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(McD_UXB));

    }

    private void getInfoWindowWater(){

        //Get Latitude and Longitude
        double lat = 51.545670;
        double log = -0.477449;

        //Get Name/Title of place
        String title1 = "McDonalds Chimes";
        //Gets Down Votes
        String upVotes1 = "60";
        //Gets Down Votes
        String downVotes1 = "30";
        //Get Image Type
        Integer image1 = 2;

        LatLng McD_Chim = new LatLng(lat, log);

        if (image1 == 2){
            MarkerOptions options1 = new MarkerOptions()
                    .title(title1)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.water_pin));

            InfoWindowData_Water info1 = new InfoWindowData_Water();
            info1.setImage1(image1);
            info1.setUpvotes1(upVotes1);
            info1.setDownvotes1(downVotes1);

            //Set icon on map and implement Info Window
            mMap.setInfoWindowAdapter(new Map_CustomeInfoWindow_Adapter_Water(Main_Nav_Bar.this));

            mMarker = mMap.addMarker(options1.position(McD_Chim));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(McD_Chim));
        }else {
            MarkerOptions options1 = new MarkerOptions()
                    .title(title1)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.recycle_pin));

            InfoWindowData_Water info1 = new InfoWindowData_Water();
            info1.setImage1(image1);
            info1.setUpvotes1(upVotes1);
            info1.setDownvotes1(downVotes1);

            //Set icon on map and implement Info Window
            mMap.setInfoWindowAdapter(new Map_CustomeInfoWindow_Adapter_Water(Main_Nav_Bar.this));

            mMarker = mMap.addMarker(options1.position(McD_Chim));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(McD_Chim));
        }




    }


    /* ---------------
    Action for Nav Bar
    --------------- */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {

            /*
            Intent openMap = new Intent(this, Main_Nav_Bar.class);
            startActivity(openMap);*/

        } /*else if (id == R.id.nav_list) {

            //Intent openList = new Intent(this, Location_ListView.class);
            //startActivity(openList);

        }*/ else if (id == R.id.nav_about_plastic) {

            Intent openAboutPlastic = new Intent(this, About_Plastic_Page.class);
            startActivity(openAboutPlastic);

        }else if (id == R.id.nav_about_app) {

            Intent openAboutApp = new Intent(this, About_App_Page.class);
            startActivity(openAboutApp);

        } else if (id == R.id.nav_settings) {

            Intent openSettings = new Intent(this, Settings_Page.class);
            startActivity(openSettings);

        } else if (id == R.id.nav_closeApp) {

            finish();
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}