package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Loading_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
    }
}