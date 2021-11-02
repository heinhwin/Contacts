package com.hein.contacts;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Add database entities
@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    //Define database name
    private static final String DATABASE_NAME = "contact.db";
    //Create database instance
    private static ContactDatabase contactDatabase;

    public synchronized static ContactDatabase getInstance(Context context){
        //Check condition
        if (contactDatabase == null) {
            //When database is null
            //Initialize database
            contactDatabase = Room.databaseBuilder(context, ContactDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        //Return database
        return contactDatabase;
    }

    //Create Dao
    public abstract ContactDAO getContactDAO();
}
