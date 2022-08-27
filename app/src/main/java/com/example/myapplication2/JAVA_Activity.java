package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JAVA_Activity extends AppCompatActivity {
    private Button java_lan_btn1;
    private Button java_lan_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        java_lan_btn1=findViewById(R.id.java_lan_btn1);
        java_lan_btn2=findViewById(R.id.java_lan_btn2);

        java_lan_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yes24.com/Product/Goods/35395185"));
                startActivity(intent);
            }
        });

        java_lan_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.yes24.com/Product/Goods/103389317"));
                startActivity(intent);
            }
        });
    }
}