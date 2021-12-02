package com.example.safety;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactsDatabase {

    Context context;
    String [] columns={com.example.safety.DatabaseHelper.ID, com.example.safety.DatabaseHelper.NAME, com.example.safety.DatabaseHelper.LAST_NAME, com.example.safety.DatabaseHelper.PHONE};
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public ContactsDatabase(Context context){
        this.context= context;
        databaseHelper = new DatabaseHelper(context,"contacts",null,1);
    }

    public void open(){
        db= databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();
    }


    public long insertContact(Contact c){
        ContentValues values = new ContentValues();


        values.put("NAME", c.getName());
        values.put("LAST_NAME", c.getLastName());
        values.put("PHONE", c.getPhone());
        return db.insert("CONTACTS",null,values);
    }
    public boolean checkContact(String phone)
    {
        Cursor c= db.rawQuery("Select * from contacts where PHONE=?",new String[] {phone});
        if (c.getCount() == 0)
            return false;
        return true;
    }

    public long updateContact(Contact c){
        ContentValues values = new ContentValues();
        values.put("ID", c.getID());
        values.put("NAME", c.getName());
        values.put("LAST_NAME", c.getLastName());
        values.put("PHONE", c.getPhone());
        return db.update("CONTACTS",values,"ID ="+c.getID(), null);
    }

    public Contact getContact(int id){
        Cursor c= db.query("CONTACTS", columns,"ID =" + id,null,null,null,null);
        return cursorToContact(c);
    }
    public ArrayList<Contact> getAll(){
        ArrayList<Contact> l=new ArrayList<Contact>();
        Cursor c= db.rawQuery("Select id, name, LAST_NAME, phone from contacts",null);
        if (c.getCount() == 0)
            return null;
        c.moveToFirst();
        do {
            Contact contact=new Contact();
            contact.setID(c.getInt(0));
            contact.setName(c.getString(1));
            contact.setLastName(c.getString(2));
            contact.setPhone(c.getString(3));
            l.add(contact);
        } while (c.moveToNext());
        return l;
    }
    private Contact cursorToContact(Cursor c) {
        if (c.getCount() == 0)
            return null;
        c.moveToFirst();
        Contact contact=new Contact();
        contact.setID(c.getInt(0));
        contact.setName(c.getString(1));
        contact.setLastName(c.getString(2));
        contact.setPhone(c.getString(3));
        return contact;
    }
    public Integer deleteContact(String id)
    {
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        return db.delete("CONTACTS","ID=?",new String[] {id});

    }

}
