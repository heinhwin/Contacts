package com.hein.contacts;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Define table name
@Entity(tableName = "table_contact")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long Id;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    //Initialize variable
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String age;

    //Set Getter & Setter
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
