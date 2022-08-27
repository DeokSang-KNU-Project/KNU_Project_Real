package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity3 extends AppCompatActivity {
    TextView textView;
    private Button major_btn;

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
                        Toast.makeText(getApplicationContext(),"준비 중입니다",
                                Toast.LENGTH_SHORT).show();
                        major_btn.setEnabled(false);
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),"준비 중입니다",
                                Toast.LENGTH_SHORT).show();
                        major_btn.setEnabled(false);
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),"준비 중입니다",
                                Toast.LENGTH_SHORT).show();
                        major_btn.setEnabled(false);
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(),"준비 중입니다",
                                Toast.LENGTH_SHORT).show();
                        major_btn.setEnabled(false);
                        break;
                    case 5:
                        major_btn.setEnabled(true);
                        major_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(SubActivity3.this,SubActivity4.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    case 6:
                        Toast.makeText(getApplicationContext(),"준비 중입니다",
                                Toast.LENGTH_SHORT).show();
                        major_btn.setEnabled(false);
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