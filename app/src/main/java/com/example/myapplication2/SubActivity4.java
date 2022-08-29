package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SubActivity4 extends AppCompatActivity {

    private Button next_btn;
    RadioGroup radio_group;
    int change_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub4);

        next_btn=findViewById(R.id.next_btn);
        radio_group=findViewById((R.id.radio_group));

       radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i)
                {
                    case R.id.c_box:
                    {
                        change_num=1;
                        break;
                    }
                }
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (change_num)
                {
                    case 1:
                    {
                        Intent intent=new Intent(SubActivity4.this,Search_Activity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });

    }
}