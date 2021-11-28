package com.hein.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lusfold.spinnerloading.SpinnerLoading;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Initialize variable
    EditText firstName_Et, lastName_Et, address_Et, city_Et, age_Et;
    Button add_btn;
    RecyclerView recycler_View;

    List<Contact> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    ContactDatabase contactDatabase;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        firstName_Et = findViewById(R.id.firstName_Et);
        lastName_Et = findViewById(R.id.lastName_Et);
        address_Et = findViewById(R.id.address_Et);
        city_Et = findViewById(R.id.city_Et);
        age_Et = findViewById(R.id.age_Et);
        add_btn = findViewById(R.id.add_btn);
        recycler_View = findViewById(R.id.recycler_View);

        //Initialize database
        contactDatabase = ContactDatabase.getInstance(this);
        //Store database value in data list
        dataList = contactDatabase.getContactDAO().getAllContact();

        //Initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        //Set layout manager
        recycler_View.setLayoutManager(linearLayoutManager);
        //Initialize adapter
        adapter = new MainAdapter(this, dataList);
        //Set adapter
        recycler_View.setAdapter(adapter);

        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Get string from edit text
                String fText = firstName_Et.getText().toString().trim();
                String lText = lastName_Et.getText().toString().trim();
                String aText = address_Et.getText().toString().trim();
                String cText = city_Et.getText().toString().trim();
                String ageText = age_Et.getText().toString().trim();
                //Check condition
                if (!fText.equals("")){
                    //When text is not empty
                    //Initialize contact data
                    Contact data = new Contact();
                    //Set contact variable on contact data
                    data.setFirstName(fText);
                    data.setLastName(lText);
                    data.setAddress(aText);
                    data.setCity(cText);
                    data.setAge(ageText);
                    //Insert data in database
                    contactDatabase.getContactDAO().insert(data);
                    //Clear edit text
                    firstName_Et.setText("");
                    lastName_Et.setText("");
                    address_Et.setText("");
                    city_Et.setText("");
                    age_Et.setText("");
                    //Notify when data is inserted
                    dataList.clear();
                    dataList.addAll(contactDatabase.getContactDAO().getAllContact());
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
}