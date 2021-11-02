package com.hein.contacts;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {
    //Insert query
    @Insert(onConflict = REPLACE)
    public void insert(Contact contacts);

    //Update query
    @Update
    public void update(Contact contacts);

    //Delete query
    @Delete
    public void delete(Contact contacts);

    //Get all data query
    @Query("SELECT * FROM table_contact ORDER BY firstName ASC")
    public List<Contact> getAllContact();

}
