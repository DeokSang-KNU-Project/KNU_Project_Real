package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity3 extends AppCompatActivity {
    TextView textView;
    private ImageButton major_btn;

    String[] select_major={"전공 선택","복지융합인재학부","사회복지학부","실버산업학과","글로벌경영학부","ICT융합공학부","부동산건설학부"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);
        textView = (TextView) findViewById(R.id.textView);
        major_btn=findViewById(R.id.major_btn);
        Spinner spinner=(Spinner)findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, select_major
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(select_major[position]);
                switch (position)
                {
                    case 1:
                        major_btn.setEnabled(true);
                        major_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(SubActivity3.this,SubActivity4.class);
                                intent.putExtra("Major", "Faculty_Welfare");
                                startActivity(intent);
                            }
                        });
                        break;
                    case 2:
                        major_btn.setEnabled(true);
                        major_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(SubActivity3.this,SubActivity4.class);
                                intent.putExtra("Major", "Social_Welfare");
                                startActivity(intent);
                            }
                        });
                        break;
                    case 3:
                        major_btn.setEnabled(true);
                        major_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(SubActivity3.this,SubActivity4.class);
                                intent.putExtra("Major", "Silver_industry");
                                startActivity(intent);
                            }
                        });
                        break;
                    case 4:
                        major_btn.setEnabled(true);
                        major_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(SubActivity3.this,SubActivity4.class);
                                intent.putExtra("Major", "Global_management");
                                startActivity(intent);
                            }
                        });
                        break;
                    case 5:
                        major_btn.setEnabled(true);
                        major_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(SubActivity3.this,SubActivity4.class);
                                intent.putExtra("Major", "ICTSOFT");
                                startActivity(intent);
                            }
                        });
                        break;
                    case 6:
                        major_btn.setEnabled(true);
                        major_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(SubActivity3.this,SubActivity4.class);
                                intent.putExtra("Major", "Estate_construction");
                                startActivity(intent);
                            }
                        });
                        break;
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText("choose : ");
            }
        });
    }
}