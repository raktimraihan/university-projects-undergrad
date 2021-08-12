package com.needyserve.android.needyserve.com.needyserve.android.instances;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.needyserve.android.needyserve.AboutUs;
import com.needyserve.android.needyserve.ContactUs;
import com.needyserve.android.needyserve.DonateLeftover;
import com.needyserve.android.needyserve.Infographic;
import com.needyserve.android.needyserve.MyDonation;
import com.needyserve.android.needyserve.R;
import com.needyserve.android.needyserve.VolunteerItemListAll;
import com.needyserve.android.needyserve.com.needyserve.background.BackGroundLoginRL;
import com.needyserve.android.needyserve.com.needyserve.background.BackgroundBeVolunteerRequest;
import com.needyserve.android.needyserve.com.needyserve.background.BackgroundItemFetchAll;
import com.needyserve.android.needyserve.com.needyserve.background.MyDonationFetchAll;

import java.util.ArrayList;

public class LandingNavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String id ="", name="", email="", phone="", gender="", donor_status="";
    private RecyclerView recyclerView, recyclerView1;
    private RecyclerView.LayoutManager layoutManager, layoutManager1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_navigation_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Left Over Food ");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.activity_custom_action_title);
        TextView titleBar = findViewById(R.id.titleBar);
        titleBar.setText(" Discover ");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        name=intent.getStringExtra("name");
        email=intent.getStringExtra("email");
        phone=intent.getStringExtra("phone");
        gender=intent.getStringExtra("gender");
        donor_status = intent.getStringExtra("donor_status");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.navBarHeaderUserName);
        nav_user.setText(name);
        TextView navEmail = (TextView) hView.findViewById(R.id.navMail);
        navEmail.setText("Mail: "+email);
        TextView phnAndGender = (TextView) hView.findViewById(R.id.navPhoneAndGender);
        phnAndGender.setText("Mobile: "+phone);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());


        BackgroundItemFetchAll backgroundItemFetchAll = new BackgroundItemFetchAll(LandingNavigationDrawer.this, recyclerView, layoutManager);
        backgroundItemFetchAll.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            BackgroundItemFetchAll backgroundItemFetchAll = new BackgroundItemFetchAll(LandingNavigationDrawer.this, recyclerView, layoutManager);
            backgroundItemFetchAll.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landing_navigation_drawer, menu);
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

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void leftOverFoodDonation(MenuItem item) {
        //Toast.makeText(LandingNavigationDrawer.this,"sdf",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LandingNavigationDrawer.this, DonateLeftover.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void requestBeVolunteer(MenuItem item) {
        if(Integer.parseInt(donor_status)==1){
            //Toast.makeText(LandingNavigationDrawer.this,"sdf",Toast.LENGTH_LONG).show();
            AlertDialog.Builder window= new AlertDialog.Builder(LandingNavigationDrawer.this);
            window.setTitle("You are already Registered as a Volunteer!").setMessage("Please go through Volunteer Panel. \nHelp under previledged people to " +
                    "have atleast a meal per day.").setPositiveButton("ok",null).show();
        }
        else{
            BackgroundBeVolunteerRequest backgroundBeVolunteerRequest = new BackgroundBeVolunteerRequest(this);
            backgroundBeVolunteerRequest.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,id);
        }
    }


    public void goToVolunteerMode(MenuItem item) {
        if(Integer.parseInt(donor_status)==1){
        Intent intent = new Intent(LandingNavigationDrawer.this, VolunteerItemListAll.class);
        intent.putExtra("id",id);
        startActivity(intent);
        }
        else{
            AlertDialog.Builder window= new AlertDialog.Builder(LandingNavigationDrawer.this);
            window.setTitle("Unauthorized Access!!").setMessage("If you have placed a request before. \nPlease wait" +
                    " until admin approves your request.\nAfter approval, Volunteer mode will be enabled.").setPositiveButton("ok",null).show();
        }

    }

    public void contactMe(MenuItem item) {
        Intent intent = new Intent(LandingNavigationDrawer.this, ContactUs.class);
        intent.putExtra("name",name);
        intent.putExtra("email",email);
        intent.putExtra("phone",phone);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void infoGraphic(MenuItem item){
        Intent intent = new Intent(this, Infographic.class);
        startActivity(intent);
    }

    public void tellAFriend(MenuItem item){
        String message = "Hey checkout this Needy Serve app, you can donate your leftover food with a single click, and at the same time" +
                "you can help the under privileged people in the society and save the environment. \nwww.needyserve.com";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Tell your friends about me."));
    }

    public void about(MenuItem item){
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }
    public void myDonation(MenuItem item){

        Intent intent = new Intent(this,MyDonation.class);
        intent.putExtra("id",id);
        startActivity(intent);

    }
}
