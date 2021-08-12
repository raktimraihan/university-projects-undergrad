package com.example.parksafe.parksafe.feature;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.DoubleUnaryOperator;

public class renterMarkerInformation {
    ArrayList<String> renter_id= new ArrayList();
    ArrayList<String> address= new ArrayList();
    ArrayList<String> lattitude= new ArrayList<>();
    ArrayList<String> longitude= new ArrayList<>();
    ArrayList<String> rate= new ArrayList<>();

    public void setRenter_id(String renter_id) {
        this.renter_id.add(renter_id);
    }
    public void setAddress(String address){
        this.address.add(address);
    }
    public void setLattitude(String lattitude){
        this.lattitude.add(lattitude);
    }
    public void setLongitude(String longitude){
        this.longitude.add(longitude);
    }
    public void setRate(String rate){
        this.rate.add(rate);
    }

    public String getRenter_id(int index){
        return renter_id.get(index);
    }
    public String getAddress(int index){
        return address.get(index);
    }
    public String getLattitude(int index){
        return lattitude.get(index);
    }
    public String getLongitude(int index){
        return longitude.get(index);
    }
    public String getRate(int index){
        return rate.get(index);
    }


}
