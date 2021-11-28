package com.hein.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.lusfold.spinnerloading.SpinnerLoading;

public class LoadingScreen extends Activity {

    SpinnerLoading spinnerLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_spinner);

        spinnerLoading = findViewById(R.id.spinner);
        spinnerLoading.setCircleRadius(20);
        startActivity(new Intent(this, MainActivity.class));
    }
}
