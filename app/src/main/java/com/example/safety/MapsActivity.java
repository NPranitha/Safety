package com.example.safety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MapsActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton fire,hospital,police,helpline;
    private GPSTracker gpsTracker;
    public double latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        helpline = findViewById(R.id.helpline1);
        hospital = findViewById(R.id.hospital1);
        police = findViewById(R.id.police1);
        fire = findViewById(R.id.fire1);
        helpline.setOnClickListener(this);
        hospital.setOnClickListener(this);
        police.setOnClickListener(this);
        fire.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");

        switch (v.getId())
        {
            case R.id.helpline1: gmmIntentUri = Uri.parse("geo:0,0?q=emergency shelters");break;
            case R.id.hospital1:gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");break;
            case R.id.police1:gmmIntentUri = Uri.parse("geo:0,0?q=police stations");break;
            case R.id.fire1:gmmIntentUri = Uri.parse("geo:0,0?q=fire stations");break;

        }
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
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
}