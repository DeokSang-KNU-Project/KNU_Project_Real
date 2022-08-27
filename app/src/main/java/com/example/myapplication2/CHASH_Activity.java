package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CHASH_Activity extends AppCompatActivity {
    private Button chash_lan_btn1;
    private Button chash_lan_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chash);

        chash_lan_btn1=findViewById(R.id.chash_lan_btn1);
        chash_lan_btn2=findViewById(R.id.chash_lan_btn2);

        chash_lan_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yes24.com/Product/Goods/90322506"));
                startActivity(intent);
            }
        });

        chash_lan_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.yes24.com/Product/Goods/96685582"));
                startActivity(intent);
            }
        });
    }
}