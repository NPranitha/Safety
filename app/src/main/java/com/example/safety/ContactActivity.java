package com.example.safety;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.safety.App.CHANNEL_1_ID;
import static com.example.safety.App.CHANNEL_2_ID;

public class ContactActivity extends AppCompatActivity {
NotificationManagerCompat nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        nm = NotificationManagerCompat.from(this);
        Intent i= getIntent();
        String id= i.getStringExtra("id");
        Log.e("ID=", id);
        ContactsDatabase db=new ContactsDatabase(this);
        db.open();
        Contact contact= new Contact();
        contact= db.getContact(Integer.parseInt(id));

        TextView name=(TextView) findViewById(R.id.name);
        TextView lastname=(TextView) findViewById(R.id.lastname);
        TextView phone=(TextView) findViewById(R.id.phone);

        name.setText(contact.getName());
        lastname.setText(contact.getLastName());
        phone.setText(contact.getPhone());

    }

    public void call(View v){
        TextView phone=(TextView) findViewById(R.id.phone);
        String number= phone.getText().toString();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" +number));
        startActivity(intent);
        Notification n=new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_stat_call)
                .setContentTitle("SOS")
                .setContentText("Calling...")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .build();
        nm.notify(2,n);
    }

    public void modifier(View v){
        Intent i= getIntent();
        String id= i.getStringExtra("id");

        Intent intent=new Intent(this, EditContact.class);
        intent.putExtra("id", id);
        startActivity(intent);

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