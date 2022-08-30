package com.example.myapplication2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookDetail extends AppCompatActivity {
    Bitmap bitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_information);

        Intent intent = getIntent();
        String Title = intent.getStringExtra("BookTitle");
        String Image = intent.getStringExtra("BookImage");
        String Pub = intent.getStringExtra("BookPub");
        String Link = intent.getStringExtra("BookLink");
        String Descri = intent.getStringExtra("BookDescription");

        ImageView imageView = findViewById(R.id.bookinfo_image);
        TextView Title_lay = findViewById(R.id.bookinfo_title);
        TextView pub = findViewById(R.id.bookinfo_pub);
        ImageButton URLlink = findViewById(R.id.bookinfo_link);
        TextView Bookdes = findViewById(R.id.bookinfo_info);

        Title_lay.setText(Title);
        pub.setText(Pub);
        Bookdes.setText(Descri);

        URLlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(Link));
                startActivity(intent1);
            }
        });

        Thread mThread = new Thread(){ //이미지 설정
            @Override
            public void run() {
                try{
                    URL url = new URL(Image);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                }
                catch (MalformedURLException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                super.run();
            }
        };
        mThread.start();
        try{
            mThread.join();
            imageView.setImageBitmap(bitmap);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }


    }
}
