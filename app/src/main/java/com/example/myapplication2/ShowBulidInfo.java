package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowBulidInfo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulid_info);

        Intent intent = getIntent();
        TextView BulidTitleTx = findViewById(R.id.Bulid_title_id);
        TextView BulidInfoTx = findViewById(R.id.Bulid_info_id);

        String BulidTitle = intent.getStringExtra("BulidTitle");
        String BulidInfo = intent.getStringExtra("BulidIInfo");

        BulidTitleTx.setText(BulidTitle);
        BulidInfoTx.setText(BulidInfo);
    }
}
