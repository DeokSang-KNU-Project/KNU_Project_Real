package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
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
    private String jsonData, Major;
    private ArrayList<FileData> DataArray = new ArrayList<>();
    private int Count, curindex=0, ReTime, CurTime=0;
    private int Step =0;
    private final int MAXCOUNT = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub4);

        RadioGroup radioGroup = findViewById(R.id.r_group);
        ImageButton pivBtn = findViewById(R.id.piv_btn);
        ImageButton nxtBtn = findViewById(R.id.next_btn);
        ImageButton confilmBtn = findViewById(R.id.confirm_btn);

        Intent intent = getIntent();
        Major = intent.getStringExtra("Major");
        JSONPassing();

       String Fstep = "filed";
       FindJsonArray(Fstep);
       SetRadioBtn(radioGroup);

        nxtBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if((CurTime+1) < ReTime){//x < 2  0+1 T 1+1 F
                    CurTime +=1;
                    Log.i("press nx curtime : ", Integer.toString(CurTime));
                    SetRadioBtn(radioGroup);
                }
                else{
                    Toast.makeText(getApplicationContext(),"맨 뒷 페이지 입니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        pivBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("count : ", Integer.toString(CurTime));
                if((CurTime -1) >= 0){// 1-1 == 0 T
                    CurTime -=1;
                    Count += MAXCOUNT;
                    if(curindex % MAXCOUNT !=0){
                        curindex -= (curindex%MAXCOUNT)+MAXCOUNT;
                    }
                    else if(curindex % MAXCOUNT ==0){
                        curindex -= (MAXCOUNT*2);
                    }
                    if(curindex<0){
                        curindex =0;
                    }
                    Log.i("press piv curtime : ", Integer.toString(CurTime));
                    SetRadioBtn(radioGroup);
                }
                else{
                    Toast.makeText(getApplicationContext(),"맨 앞 페이지 입니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        confilmBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int ID = radioGroup.getCheckedRadioButtonId();
                if(Step < 1) {
                    curindex = 0;
                    CurTime = 0;
                    FindJsonArray(DataArray.get(ID).getName());
                    SetRadioBtn(radioGroup);
                    Step++;
                }
                else{
                    Intent intent1 = new Intent(SubActivity4.this, Search_Activity.class);
                    intent1.putExtra("SearchWord", DataArray.get(ID).getURLname());
                    startActivity(intent1);
                    finish();
                }
            }
        });


    }

    public void SetRadioBtn(RadioGroup radioGroup){
        radioGroup.removeAllViews();
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.weight =1;
        Log.i("corCount : ", Integer.toString(Count));
        if(Count >= MAXCOUNT){
            for(int i=0; i<MAXCOUNT; i++){

                RadioButton btn = new RadioButton(this);
                btn.setId(curindex);
                btn.setText(DataArray.get(curindex).getViewname());
                btn.setTextSize(20);
                radioGroup.addView(btn, layoutParams);
                curindex++;
            }
            Count -= MAXCOUNT;
        }
        else{
            for(int i=0; i<Count; i++){
                RadioButton btn = new RadioButton(this);
                btn.setId(curindex);
                btn.setText(DataArray.get(curindex).getViewname());
                btn.setTextSize(20);
                radioGroup.addView(btn, layoutParams);
                curindex++;
            }
        }


    }
    public void JSONPassing(){
        try{
            InputStream is = getAssets().open("Json/"+Major+".json");
            int filesize = is.available();

            byte[] buffer = new byte[filesize];
            is.read(buffer);
            is.close();

            jsonData = new String(buffer, "UTF-8");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private void FindJsonArray(String str){
        DataArray.clear();
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray(str);

            for(int i=0; i< jsonArray.length(); i++){
                JSONObject getVal = jsonArray.getJSONObject(i);

                FileData fileData = new FileData();
                fileData.setData(getVal.getString("Viewname"),getVal.getString("name"),getVal.getString("URLname"));

                DataArray.add(fileData);
            }
            Count = DataArray.size();
            ReTime = Count/MAXCOUNT;
            if(Count%MAXCOUNT!=0){
                ReTime++;
            }
            Log.i("Set Retime", Integer.toString(ReTime));
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}