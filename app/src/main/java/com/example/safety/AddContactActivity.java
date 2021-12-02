package com.example.safety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }
    public void add(View v){
        ContactsDatabase db=new ContactsDatabase(this);
        db.open();
        Contact contact = new Contact();
        contact = db.getContact(1);
        String name=((EditText) findViewById(R.id.name)).getText().toString();
        String lastname=((EditText) findViewById(R.id.lastname)).getText().toString();
        String phone=((EditText) findViewById(R.id.phone)).getText().toString();
        if(name.matches("")||lastname.matches("")||phone.matches(""))
            Toast.makeText(this, "Enter all the details", Toast.LENGTH_LONG).show();
        else if(phone.length()<8||phone.length()>10||phone.length()==9)
            Toast.makeText(this, "Enter a valid phone number", Toast.LENGTH_LONG).show();
        else if(db.checkContact(phone)&&contact!=null)
            Toast.makeText(this, "Contact exists", Toast.LENGTH_SHORT).show();
        else {
            // String note=((EditText) findViewById(R.id.note)).getText().toString();
            contact = new Contact();
            contact.setName(name);
            contact.setLastName(lastname);
            contact.setPhone(phone);

            db.insertContact(contact);
            Toast.makeText(this, " Contact Added", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ContactsActivity.class);
            startActivity(i);
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