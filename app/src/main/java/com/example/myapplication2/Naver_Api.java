package com.example.myapplication2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Naver_Api {
   private static final String BASE_URL = "https://openapi.naver.com/v1/";
   private static Retrofit retrofit;

   public static Retrofit getInstance(){
      Gson gson = new GsonBuilder().setLenient().create();

      if(retrofit == null)
      {
         retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                 .addConverterFactory(ScalarsConverterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create(gson))
                 .build();
      }
      return retrofit;
   }
}
