package com.needyserve.android.needyserve;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.DialogPreference;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.needyserve.android.needyserve.com.needyserve.android.instances.AdaptarForVolunteerCollect;
import com.needyserve.android.needyserve.com.needyserve.android.instances.FoodItem;
import com.needyserve.android.needyserve.com.needyserve.background.BackgroundCheckOutVolunteer;
import com.needyserve.android.needyserve.com.needyserve.background.BackgroundVolunteerCheckoutFinal;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

public class CheckoutVolunteer extends AppCompatActivity {

    private String id,position;
    private ArrayList<FoodItem> foodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_volunteer);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.activity_custom_action_title);
        TextView titleBar = findViewById(R.id.titleBar);
        titleBar.setText(" CheckOut Collectible Food ");

        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        position=intent.getStringExtra("position");
        Bundle args = intent.getBundleExtra("BUNDLE");
        foodItems = (ArrayList<FoodItem>) args.getSerializable("foodItems");

        TextView nameT = findViewById(R.id.nameCheck);
        TextView emailT = findViewById(R.id.emailCheck);
        TextView phoneT = findViewById(R.id.phoneCheck);
        TextView foodNameT = findViewById(R.id.foodName);
        TextView foodDesT = findViewById(R.id.foodDescription);
        TextView servT = findViewById(R.id.servingPerson);
        TextView timeT = findViewById(R.id.timeCheck);
        TextView locationT = findViewById(R.id.locationCheck);
        BackgroundCheckOutVolunteer backgroundCheckOutVolunteer  = new BackgroundCheckOutVolunteer(CheckoutVolunteer.this, nameT,emailT,phoneT );
        backgroundCheckOutVolunteer.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,foodItems.get(Integer.parseInt(position)).getFoodId());
        foodNameT.setText("Food Item Name: "+foodItems.get(Integer.parseInt(position)).getFoodName());
        foodDesT.setText("Food Item Description: "+foodItems.get(Integer.parseInt(position)).getFoodDescription());
        servT.setText("Approximate Servings: "+foodItems.get(Integer.parseInt(position)).getServingPerson()+" Persons");
        timeT.setText("Collect this Item Before: "+foodItems.get(Integer.parseInt(position)).getTimeCollect());
        locationT.setText("Pick up location: "+foodItems.get(Integer.parseInt(position)).getLocation());



    }

    public void checkout(View view) {
        AlertDialog.Builder window= new AlertDialog.Builder(CheckoutVolunteer.this);
        window.setTitle("Caution!!").setMessage("Once you checkout an item, It can't be undo.").setPositiveButton("Check out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BackgroundVolunteerCheckoutFinal backgroundVolunteerCheckoutFinal = new BackgroundVolunteerCheckoutFinal(CheckoutVolunteer.this);
                backgroundVolunteerCheckoutFinal.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                        foodItems.get(Integer.parseInt(position)).getFoodId(),
                        foodItems.get(Integer.parseInt(position)).getFoodName(),
                        foodItems.get(Integer.parseInt(position)).getFoodDescription(),
                        foodItems.get(Integer.parseInt(position)).getServingPerson(),
                        foodItems.get(Integer.parseInt(position)).getTimeCollect(),
                        foodItems.get(Integer.parseInt(position)).getLocation(),id);
            }
        }).setNegativeButton("Cancel",null).show();
    }
}
