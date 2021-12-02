package com.example.safety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

public class Checklist extends AppCompatActivity {
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        c1 = (CheckBox)findViewById(R.id.checkBox);
        c2 = (CheckBox)findViewById(R.id.checkBox2);
        c3 = (CheckBox)findViewById(R.id.checkBox3);
        c4 = (CheckBox)findViewById(R.id.checkBox1);
        c5 = (CheckBox)findViewById(R.id.checkBox4);
        c6 = (CheckBox)findViewById(R.id.checkBox5);
        c7 = (CheckBox)findViewById(R.id.checkBox6);
        c8 = (CheckBox)findViewById(R.id.checkBox7);
        c9 = (CheckBox)findViewById(R.id.checkBox8);
        c10 = (CheckBox)findViewById(R.id.checkBox10);
        c11 = (CheckBox)findViewById(R.id.checkBox11);
        c12 = (CheckBox)findViewById(R.id.checkBox12);
        c13 = (CheckBox)findViewById(R.id.checkBox13);
        c14 = (CheckBox)findViewById(R.id.checkBox14);
        c15 = (CheckBox)findViewById(R.id.checkBox15);

        boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c1", false);
        c1.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c2", false);
        c2.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c3", false);
        c3.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c4", false);
        c4.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c5", false);
        c5.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c6", false);
        c6.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c7", false);
        c7.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c8", false);
        c8.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c9", false);
        c9.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c10", false);
        c10.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c11", false);
        c11.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c12", false);
        c12.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c13", false);
        c13.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c14", false);
        c14.setChecked(checked);checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("c15", false);
        c15.setChecked(checked);
    }
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkBox:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c1", checked).apply();
                break;
            case R.id.checkBox1:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c4", checked).apply();
                break;
            case R.id.checkBox2:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c2", checked).apply();
                break;
            case R.id.checkBox3:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c3", checked).apply();
                break;
            case R.id.checkBox4:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c5", checked).apply();
                break;
            case R.id.checkBox5:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c6", checked).apply();
                break;
            case R.id.checkBox6:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c7", checked).apply();
                break;
            case R.id.checkBox7:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c8", checked).apply();
                break;
            case R.id.checkBox8:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c9", checked).apply();
                break;
            case R.id.checkBox10:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c10", checked).apply();
                break;
            case R.id.checkBox11:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c11", checked).apply();
                break;
            case R.id.checkBox12:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c12", checked).apply();
                break;
            case R.id.checkBox13:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c13", checked).apply();
                break;
            case R.id.checkBox14:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c14", checked).apply();
                break;
            case R.id.checkBox15:
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean("c15", checked).apply();
                break;

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