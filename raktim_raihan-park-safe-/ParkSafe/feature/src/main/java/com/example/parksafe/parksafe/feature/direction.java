package com.example.parksafe.parksafe.feature;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class direction extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback, GoogleMap.OnMyLocationChangeListener {


    private static final int ACCESS_FINE_LOCATION = 1;
    private GoogleMap mMap;
    private Marker marker;
    boolean firsttime= true;
    LocationManager locationManager;
    String json_string;

    private static final String api_key= "https://maps.googleapis.com/maps/api/directions/";
    Polyline currentPolyline;
    MarkerOptions marker2;
    Location location_user;
    String renter_id;
    ArrayList<String> renterIDlist;
    ArrayList<String> addressList;
    ArrayList<String> lattituteList;
    ArrayList<String> longituteList;
    ArrayList<String> rateList;
    String driver_id;
    LatLng ltng,latlng_marker;
    String[] array;
    String msg;

    double lattitude;
    double longitute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        renter_id= intent.getStringExtra("renter_id");
        renterIDlist= intent.getStringArrayListExtra("listOFRenter_id");
        addressList= intent.getStringArrayListExtra("address");
        lattituteList= intent.getStringArrayListExtra("lattitute");
        longituteList= intent.getStringArrayListExtra("longitute");
        rateList= intent.getStringArrayListExtra("rate");
        driver_id=intent.getStringExtra("driver_id");



        final BackgroundDirection back=new BackgroundDirection(getApplicationContext());
        msg= null;
        try {
            msg = back.execute(renter_id).get().toString();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    ACCESS_FINE_LOCATION);

        }
        else{
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    ACCESS_FINE_LOCATION);
        }

        switch(ACCESS_FINE_LOCATION) {

            case 1:
                //check network provider
                if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    final String finalMsg = msg;
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            lattitude = location.getLatitude();
                            longitute = location.getLongitude();
                            LatLng latLang = new LatLng(lattitude, longitute);
                            Geocoder geoCoder = new Geocoder(getApplicationContext());
                            try {
                                List<Address> addressList = geoCoder.getFromLocation(lattitude, longitute, 1);
                                String str = addressList.get(0).getLocality() + " ";
                                str = str + addressList.get(0).getAdminArea();
                                if(marker !=null){
                                    marker.remove();
                                }
                                //check arrival
                                onMyLocationChange(location);

                                marker=mMap.addMarker(new MarkerOptions().position(latLang).title("Your Position"));
                                marker.setPosition(latLang);

                                //build url

                                array= finalMsg.split(";.;");
                                Log.d("Lattitude=",array[0]);
                                ltng=new LatLng(Double.parseDouble(array[1]),Double.parseDouble(array[0]));
                                marker2= new MarkerOptions().position(ltng).title("here");
                                String url= getUrl(marker.getPosition(),marker2.getPosition(),"driving");
                                new FetchURL(direction.this).execute(url,"driving");

                                mMap.addMarker(new MarkerOptions().position(ltng).title(array[2]));


                                if(firsttime) {
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLang, 19.4f));
                                    firsttime=false;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onStatusChanged(String s, int i, Bundle bundle) {

                        }

                        @Override
                        public void onProviderEnabled(String s) {

                        }

                        @Override
                        public void onProviderDisabled(String s) {

                        }
                    });
                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    final String finalMsg1 = msg;
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            lattitude = location.getLatitude();
                            longitute = location.getLongitude();

                            LatLng latLang = new LatLng(lattitude, longitute);
                            Geocoder geoCoder = new Geocoder(getApplicationContext());
                            try {
                                List<Address> addressList = geoCoder.getFromLocation(lattitude, longitute, 1);
                                String str = addressList.get(0).getLocality() + " ";
                                str = str + addressList.get(0).getAdminArea();
                                if(marker !=null){
                                    marker.remove();
                                }

                                //check arrival
                                onMyLocationChange(location);

                                marker=mMap.addMarker(new MarkerOptions().position(latLang).title("Your Position"));
                                marker.setTag("User");
                                marker.setPosition(latLang);



                                //building url

                                array= finalMsg1.split(";.;");
                                latlng_marker=new LatLng(Double.parseDouble(array[1]),Double.parseDouble(array[0]));
                                Log.d("Lattitude=",array[0]);
                                ltng=new LatLng(Double.parseDouble(array[1]),Double.parseDouble(array[0]));
                                marker2= new MarkerOptions().position(ltng).title("here");
                                String url= getUrl(marker.getPosition(),marker2.getPosition(),"driving");
                                new FetchURL(direction.this).execute(url,"driving");

                                mMap.addMarker(new MarkerOptions().position(ltng).title(array[2]));
                                if(firsttime) {
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLang, 19.4f));
                                    firsttime=false;
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onStatusChanged(String s, int i, Bundle bundle) {

                        }

                        @Override
                        public void onProviderEnabled(String s) {

                        }

                        @Override
                        public void onProviderDisabled(String s) {

                        }
                    });
                }
                break;

            default:
                Toast.makeText(getApplicationContext(),"No Permission",Toast.LENGTH_SHORT);
                break;
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //mMap.addMarker(new MarkerOptions().position(ltng).title(array[2]));
        //mMap.addMarker(marker2);
        //mMap.addMarker(marker1);
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private String getUrl(LatLng origin, LatLng destination, String direction_mode){
        String origin_s="origin="+origin.latitude+","+origin.longitude;
        String destination_s="destination="+destination.latitude+","+destination.longitude;
        String mode= "mode="+direction_mode;
        String output= "json";
        String parameters= origin_s+"&"+destination_s+"&"+mode;

        String url= api_key+output+"?"+parameters+"&key="+"AIzaSyBBSDLSVGErJ54toDpzdF8sjchqOEr0IZI";

        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null){
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);}
        else{
            currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
        }
    }

    @Override
    public void onMyLocationChange(Location location) {


        array= msg.split(";.;");
        latlng_marker=new LatLng(Double.parseDouble(array[1]),Double.parseDouble(array[0]));
        Location target= new Location("target");
        target.setLatitude(Double.parseDouble(array[0]));
        target.setLongitude(Double.parseDouble(array[1]));

        if(location.distanceTo(target)< 100){
            arrivalProcess(getWindow().getDecorView().getRootView().findViewById(R.id.demo));
        }

    }

    public void arrivalProcess(View view) {

        BackGroundArrived b_arrive= new BackGroundArrived(direction.this);
        b_arrive.execute(renter_id,driver_id);
        array=msg.split(";.;");
        Intent intn= new Intent(direction.this,arrived.class);
        intn.putExtra("renter_id",renter_id);
        intn.putExtra("driver_id",driver_id);
        intn.putExtra("address",array[2]);
        intn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intn);
    }
}

