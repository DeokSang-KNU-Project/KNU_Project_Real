package com.example.myapplication2;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Naver_Api extends AsyncTask<String, Void , String> {

      private String clientId = "c4WFndgFv36xUjOW6c3k";
      private String clientPw = "i81XdmGVVW";
      private String str, Msg, text;
      private final int display = 100;

   @Override
   protected String doInBackground(String... strings) {
      text = "C";
      URL url;
      try{
         url = new URL(	"https://openapi.naver.com/v1/search/book.json"
                        +"?query="+text
                        +"&display"+display);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.setRequestProperty("X-Naver-Client-Id", clientId);
         conn.setRequestProperty("X-Naver-Client-Secret", clientPw);

         if(conn.getResponseCode() == conn.HTTP_OK){
            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuffer buffer = new StringBuffer();
            while ((str = reader.readLine())!=null){
               buffer.append(str);
            }
            Msg = buffer.toString();
            Log.i("Msg : ", Msg);

            reader.close();
         }
         else{
            Log.i("통신 결과 : ", conn.getResponseCode()+"에러");
         }

      }
      catch (MalformedURLException e){
         e.printStackTrace();
      }
      catch (IOException e){
         e.printStackTrace();
      }
      return Msg;
   }

}
