package com.needyserve.android.needyserve.com.needyserve.android.instances;

import java.io.Serializable;

public class FoodItem implements Serializable {

    private String donorName;
    private String foodName, foodDescription;
    private String location, servingPerson;
    private String timeCollect, foodId;

    public FoodItem(String donorName, String foodName, String foodDescription, String location, String servingPerson, String timeCollect, String foodId) {
        this.donorName = donorName;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.location = location;
        this.servingPerson = servingPerson;
        this.timeCollect = timeCollect;
        this.foodId = foodId;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServingPerson() {
        return servingPerson;
    }

    public void setServingPerson(String servingPerson) {
        this.servingPerson = servingPerson;
    }

    public String getTimeCollect() {
        return timeCollect;
    }

    public void setTimeCollect(String timeCollect) {
        this.timeCollect = timeCollect;
    }
}
