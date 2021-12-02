package com.example.safety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HelpLlineActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton fire,hospital,police,helpline,sos_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_lline);
        helpline = findViewById(R.id.helpline);
        hospital = findViewById(R.id.hospital);
        police = findViewById(R.id.police);
        fire = findViewById(R.id.fire);

        helpline.setOnClickListener(this);
        hospital.setOnClickListener(this);
        police.setOnClickListener(this);
        fire.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.helpline:call("112");break;
            case R.id.hospital:call("102");break;
            case R.id.police:call("100");break;
            case R.id.fire:call("101");break;

        }

    }
    public void call(String number)
    {
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
        startActivity(callIntent);
//        callIntent.setData(Uri.parse("tel:"+number));
//
//        if (ActivityCompat.checkSelfPermission(HelpLineActivity.this,
//                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }

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