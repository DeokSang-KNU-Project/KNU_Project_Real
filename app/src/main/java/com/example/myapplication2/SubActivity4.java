package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class SubActivity4 extends AppCompatActivity {

    private Button next_btn;
    RadioGroup radio_group;
    int change_num;
    int count = 0;
    String json = "";
    String bname = "ddddd";

    private ArrayList<String> arrayList;



    private String getJsonString()
    {
        try {
            InputStream is = getAssets().open("Json/ICTSOFT.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }

    public class booksys{
        private String name;
        private String URLname;

        public String getName() {
            return name;
        }

        public String getURLname() {
            return URLname;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setURLname(String URLname) {
            this.URLname = URLname;
        }

    }

    private void jsonParsing(String json)
    {
        try{
            arrayList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(json);

            JSONArray bookArray = jsonObject.getJSONArray("OS");

            for(int i=0; i<bookArray.length(); i++)
            {
                count++;
                JSONObject bookobject = bookArray.getJSONObject(i);

                booksys books = new booksys();

                books.setName(bookobject.getString("name"));
                books.setURLname(bookobject.getString("URLname"));

                arrayList.add(books.getName());
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub4);

        final LinearLayout lm = (LinearLayout) findViewById(R.id.ll);
        final RadioGroup radiogroup = (RadioGroup) findViewById(R.id.group1);


        // linearLayout params 정의

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(

                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);







        for (int j = 0; j <= 5; j++) {

            // LinearLayout 생성

            LinearLayout ll = new LinearLayout(this);

            ll.setOrientation(LinearLayout.HORIZONTAL);

            RadioGroup.LayoutParams rprms= new RadioGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


            // 버튼 생성

            final RadioButton btn = new RadioButton(this);

            // setId 버튼에 대한 키값

            btn.setId(j + 1);

            btn.setText(arrayList.get(1));
            btn.setTextSize(20);
            btn.setPadding(20,0,0,40);
            btn.setLayoutParams(params);

            //버튼 add

            radiogroup.addView(btn,rprms);
            //LinearLayout 정의된거 add

            lm.addView(ll);

        }

    }

    }