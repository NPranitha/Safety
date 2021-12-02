package com.example.safety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
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
    public void edit(View v){
        TextView name=(TextView) findViewById(R.id.name);
        TextView lastname=(TextView) findViewById(R.id.lastname);
        TextView phone=(TextView) findViewById(R.id.phone);
        ContactsDatabase db=new ContactsDatabase(this);
        db.open();
        Contact contact= new Contact();
        Intent i= getIntent();
        String id= i.getStringExtra("id");
        contact.setID(Integer.parseInt(id));
        contact.setPhone(phone.getText().toString());
        contact.setLastName(lastname.getText().toString());
        contact.setName(name.getText().toString());

        db.updateContact(contact);

        Toast.makeText(this, "Contact editted successfully",Toast.LENGTH_LONG).show();
        Intent in = new Intent(this, ContactsActivity.class);
        startActivity(in);



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