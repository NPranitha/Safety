package com.example.safety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    String urls[]={
            "https://eonet.sci.gsfc.nasa.gov/api/v2.1/events?limit=15&days=40", //all events
            //"https://eonet.sci.gsfc.nasa.gov/api/v2.1/categories/9?status=open&limit=10",//floods
            "https://eonet.sci.gsfc.nasa.gov/api/v2.1/categories/8?status=open&limit=10", //earthquakes
            "https://eonet.sci.gsfc.nasa.gov/api/v2.1/categories/12?status=open&limit=10",
            "https://eonet.sci.gsfc.nasa.gov/api/v2.1/categories/16?status=open"

             //Landslides
             //Severe Storms
    };
    private String api_key = "5cFyFyRjubQA2aJphTxabEwVba34giEd74Fdx8HO";
    private static final String TAG = "Json output";
    public TextView mTextView;
    private List<DisasterNews> listDisaster = new ArrayList<>();
    public double[] latitude;
    public double[] longitude;
    public int index;
    private RecyclerView Myrv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Myrv = (RecyclerView) findViewById(R.id.rv);

        index = 0;
        latitude = new double[100];
        longitude = new double[100];
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Extracting Data...");
        showDialog();

        for (int i = 1; i < urls.length; i++) {
            String url1 = urls[i];
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    url1, null, response -> {
                Log.d(TAG, response.toString());
                hideDialog();

                try {
                    // Parsing json object response
                    // response will be a json object
                    JSONArray events = response.getJSONArray("events");
                    for (int i1 = 0; i1 < events.length(); i1++) {

                        JSONObject list = (JSONObject) events.get(i1);
                        DisasterNews disaster = new DisasterNews();

                        String title = list.getString("title");
                        Log.d(TAG, title);

                          String description = list.getString("description");
                          Log.d(TAG,description);

                        JSONArray categories = list.getJSONArray("categories");
                        JSONObject categories_data = (JSONObject) categories.get(0);
                        String type = categories_data.getString("title");
                        JSONArray sources = list.getJSONArray("sources");
                        JSONObject sources_data = (JSONObject) sources.get(0);
                        String link = sources_data.getString("url");
                       // Toast.makeText(getApplicationContext(), link, Toast.LENGTH_LONG).show();
                       // if(list.getJSONArray("geometry")!=null) {
                            JSONArray geometries = list.getJSONArray("geometries");
                            JSONObject geometries_data = (JSONObject) geometries.get(0);
                            String date = geometries_data.getString("date");
                            Log.d(TAG, date);
                            disaster.setDate(date);
                       // }
                        //Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();


                        JSONArray coordinates = geometries_data.getJSONArray("coordinates");
                        double lat, longi;
                        lat = (double) coordinates.get(0);
                        longi = (double) coordinates.get(1);
                        Log.d(TAG, String.valueOf(lat));
                        Log.d(TAG, String.valueOf(longi));
                        Log.d(TAG, String.valueOf(index));
                        latitude[index] = lat;
                        longitude[index++] = longi;


                        disaster.setName(title + "\n" + type);
                        disaster.setDescription(description);
                        disaster.setLink(link);

                        disaster.setLatitude(lat);
                        disaster.setLongitude(longi);

                       // if(!description.matches("")) {
                            listDisaster.add(disaster);
                            Log.d(TAG, listDisaster.toString());
                          //  Toast.makeText(NewsActivity.this, "Size of List" + String.valueOf(listDisaster.size()), Toast.LENGTH_SHORT).show();
                        //}
                        }


                } catch (JSONException ignore) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),
//                            "Error: " + e.getMessage(),
//                            Toast.LENGTH_LONG).show();
                }

                setRvadapter(listDisaster);

            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
//                    Toast.makeText(getApplicationContext(),
//                            error.getMessage(), Toast.LENGTH_SHORT).show();
                    // hide the progress dialog

                }
            });
            AppSingleton.getInstance(this).addToRequestQueue(jsonObjReq);

        }
    }

    public void setRvadapter(List<DisasterNews> lst) {
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, lst);
        Myrv.setLayoutManager(new LinearLayoutManager(this));
        Myrv.setAdapter(myAdapter);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
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