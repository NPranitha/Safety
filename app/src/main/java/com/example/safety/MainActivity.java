package com.example.safety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Date;

import static com.example.safety.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NotificationManagerCompat nm;
    ContactsDatabase db;
    private GPSTracker gpsTracker;
    public double latitude,longitude;
    Button detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detector = (Button)findViewById(R.id.detect);
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
//        if (ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)){
//                ActivityCompat.requestPermissions(MainActivity.this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//            }else{
//                ActivityCompat.requestPermissions(MainActivity.this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//            }
//        }
        nm = NotificationManagerCompat.from(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        db=new ContactsDatabase(this);
        db.open();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Record Audio", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                ContactsDatabase db=new ContactsDatabase(getApplicationContext());
//                db.open();
//                Contact contact= new Contact();
//                contact= db.getContact(1);
//
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:"+contact.getPhone()));
//
//                startActivity(intent);
                Intent i= new Intent(getApplicationContext(), RecordAudio.class);
                startActivity(i);
            }
        });

        detector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EarthquakeDetector.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        CountDownTimer newtimer = new CountDownTimer(1000000000, 1000) {

            public void onTick(long millisUntilFinished) {
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                EditText time=(EditText) findViewById(R.id.time);
                time.setText(currentDateTimeString);
            }
            public void onFinish() {

            }
        };
        newtimer.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.news) {
            Intent i= new Intent(this, NewsActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.home) {
            Intent i= new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.check) {
            Intent i= new Intent(this, Checklist.class);
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add_contact) {
            Intent i= new Intent(this, AddContactActivity.class);
            startActivity(i);
        } else if (id == R.id.my_contacts) {
            Intent i= new Intent(this, ContactsActivity.class);
            startActivity(i);

        } else if (id == R.id.where_am_i) {
            gpsTracker = new GPSTracker(MainActivity.this);
            if(gpsTracker.canGetLocation()){
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();
            }else{
                gpsTracker.showSettingsAlert();
            }
            //Uri gmmIntentUri = Uri.parse("geo:"+latitude+","+longitude);
            //Intent mapIntent = new Intent(Intent.ACTION_VIEW,gmmIntentUri);
            //mapIntent.setPackage("com.google.android.apps.maps");
//            if (mapIntent.resolveActivity(getPackageManager()) != null) {
//                startActivity(mapIntent);
//            }
            Intent mapIntent = new Intent(this,MapsActivity.class);
            startActivity(mapIntent);
        } else if (id == R.id.helpline) {

            Intent helpintent = new Intent(this, HelpLlineActivity.class);
            startActivity(helpintent);

        }  else if (id == R.id.precautions) {


            Intent precautionintent = new Intent(this, EmergencyPrecautions.class);
            startActivity(precautionintent);

        } else if (id == R.id.search) {
            String url = "https://www.downtoearth.org.in/category/natural-disasters/news";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                           int[] grantResults){
//        switch (requestCode){
//            case 1: {
//                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    if (ContextCompat.checkSelfPermission(MainActivity.this,
//                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
//                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
//        }
//    }

    public void alert(View v){
        Contact contact= new Contact();
        contact= db.getContact(1);
        if(contact!=null) {
        Notification n=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_stat_message)
                .setContentTitle("SOS")
                .setContentText("Sending message...")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.RED)
                .setAutoCancel(true)
                .build();
        nm.notify(1,n);



            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:" + contact.getPhone()));

            sendIntent.putExtra("sms_body", "Hey I'm in danger! Save Me!");

            startActivity(sendIntent);
        }
        else
            Toast.makeText(this, "No Contacts listed", Toast.LENGTH_SHORT).show();
    }
}