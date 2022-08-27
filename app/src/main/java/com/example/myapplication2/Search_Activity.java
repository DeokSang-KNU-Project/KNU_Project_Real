package com.example.myapplication2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Search_Activity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private String client_id = "c4WFndgFv36xUjOW6c3k";
    private String client_pw = "i81XdmGVVW";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getResultSearch();
    }

    void getResultSearch()
    {
        ApiInterface apiInterface = Naver_Api.getInstance().create(ApiInterface.class);
        Call<String> call = apiInterface.getSearchResult(client_id, client_pw, "book.json", "C");
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    String result = response.body();
                    Log.e(TAG, "성공 : " + result);
                }
                else
                {
                    Log.e(TAG, "실패 : " + response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                Log.e(TAG, "에러 : " + t.getMessage());
            }
        });
    }
}

