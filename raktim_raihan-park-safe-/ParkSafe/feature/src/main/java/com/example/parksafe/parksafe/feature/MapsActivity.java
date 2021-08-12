package com.example.parksafe.parksafe.feature;

import android.Manifest;
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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int ACCESS_FINE_LOCATION = 1;
    private GoogleMap mMap;
    private Marker marker;
    private Marker mMarker;
    boolean firsttime= true;
    LocationManager locationManager;
    String json_string;
    renterMarkerInformation rntr = new renterMarkerInformation();
    List<Marker> list =(List<Marker>) new  ArrayList<Marker>();
    String driver_id="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Intent intntn= getIntent();
        driver_id=intntn.getStringExtra("driver_id");
        //Log.d("driver id 01",driver_id);
        mapFragment.getMapAsync(this);

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
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            double lattitude = location.getLatitude();
                            double longitute = location.getLongitude();
                            LatLng latLang = new LatLng(lattitude, longitute);
                            Geocoder geoCoder = new Geocoder(getApplicationContext());
                            try {
                                List<Address> addressList = geoCoder.getFromLocation(lattitude, longitute, 1);
                                String str = addressList.get(0).getLocality() + " ";
                                str = str + addressList.get(0).getAdminArea();
                                if(marker !=null){
                                    marker.remove();
                                }

                                marker=mMap.addMarker(new MarkerOptions().position(latLang).title(str));
                                marker.setPosition(latLang);
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
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            double lattitude = location.getLatitude();
                            double longitute = location.getLongitude();
                            LatLng latLang = new LatLng(lattitude, longitute);
                            Geocoder geoCoder = new Geocoder(getApplicationContext());
                            try {
                                List<Address> addressList = geoCoder.getFromLocation(lattitude, longitute, 1);
                                String str = addressList.get(0).getLocality() + " ";
                                str = str + addressList.get(0).getAdminArea();
                                if(marker !=null){
                                    marker.remove();
                                }

                                marker=mMap.addMarker(new MarkerOptions().position(latLang).title(str));
                                marker.setTag("User");
                                marker.setPosition(latLang);
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
    public void onMapReady(final GoogleMap googleMap) {
        ArrayList lat_List = new ArrayList();
        ArrayList long_List= new ArrayList();
        Toast.makeText(getApplicationContext(),"Fetching your Location.",Toast.LENGTH_LONG);
        mMap = googleMap;
        BackgroundMarkerJson bjs= new BackgroundMarkerJson(getApplicationContext());
        try {
            json_string= bjs.execute().get().toString();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //bjs.execute();

       try {

            JSONObject json_object= new JSONObject(json_string);
            JSONArray json_array= json_object.getJSONArray("response");
            int count = 0;
            String renter_id,address,lat,longitu,rate;
           rntr= new renterMarkerInformation();
            while(count<json_array.length()){
                JSONObject jo= json_array.getJSONObject(count);
                renter_id= jo.getString("renter_id");
                address= jo.getString("address");
                lat= jo.getString("latitude");
                longitu= jo.getString("longitute");
                rate=jo.getString("rateperhour");
                rntr.setRenter_id(renter_id);
                rntr.setAddress(address);
                rntr.setLattitude(lat);
                rntr.setLongitude(longitu);
                rntr.setRate(rate);
                LatLng sydney = new LatLng(Double.parseDouble(lat), Double.parseDouble(longitu));
                String str= address+" "+rate;
                mMarker=mMap.addMarker(new MarkerOptions().position(sydney).title(address).snippet("Rate: "+rate+" taka per half-hr"));
                mMarker.setTag(Integer.parseInt(renter_id));
                list.add(mMarker);

                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*renterMarkerInformation rnty= new renterMarkerInformation();
        int length= rnty.renter_id.size();
        for(int i=0;i<length;i++){
            LatLng sydney = new LatLng(rnty.getLattitude(i), rnty.getLongitude(i));
           mMap.addMarker(new MarkerOptions().position(sydney).title(rnty.getAddress(i)));
          // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,19.4f));
        }*/
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(23, 90);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,19.4f));


        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //Toast.makeText(getApplicationContext(), marker.getTag().toString(),Toast.LENGTH_SHORT).show();
                Intent inttn= new Intent(MapsActivity.this,view_space_option.class);
                inttn.putExtra("renter_id",marker.getTag().toString());
                inttn.putExtra("driver_id",driver_id);
                inttn.putStringArrayListExtra("listOFRenter_id",rntr.renter_id);
                inttn.putStringArrayListExtra("address",rntr.address);
                inttn.putStringArrayListExtra("lattitute",rntr.lattitude);
                inttn.putStringArrayListExtra("longitute",rntr.longitude);
                inttn.putStringArrayListExtra("rate",rntr.rate);
                startActivity(inttn);
            }
        });
    }

    public void setjson(String json){
        this.json_string=json;
    }

}
