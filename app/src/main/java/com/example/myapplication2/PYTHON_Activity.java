package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PYTHON_Activity extends AppCompatActivity {
    private Button python_lan_btn1;
    private Button python_lan_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);

        python_lan_btn1=findViewById(R.id.python_lan_btn1);
        python_lan_btn2=findViewById(R.id.python_lan_btn2);

        python_lan_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yes24.com/Product/Goods/98795965"));
                startActivity(intent);
            }
        });

        python_lan_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.yes24.com/Product/Goods/30963475"));
                startActivity(intent);
            }
        });
    }
}