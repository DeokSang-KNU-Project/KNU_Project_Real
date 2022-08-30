package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;



public class Search_Activity extends AppCompatActivity {

    private ArrayList<String> arraylist = new ArrayList<>();
    private ArrayList<Bookinfo> bookarr = new ArrayList<>();
    public int Count=0; // 다음 페이지 보여주는것
    private String SearchWord;
    private CustomAdapter adapter;
    private String resulttext;
    private String str;
    private int TotalPage, currPage =1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        SearchWord = intent.getStringExtra("SearchWord");

        resulttext = "없음";
        ListView booklist = findViewById(R.id.list_Id);
        Button nxtBtn = findViewById(R.id.booklist_next);
        Button pivBtn = findViewById(R.id.booklist_piv);
        TextView sText = findViewById(R.id.search_book_count);
        TextView lText = findViewById(R.id.ListPage_Id);

        try{
            Naver_Api naver_api = new Naver_Api();
            naver_api.setStart(Count);
            naver_api.setText(SearchWord);
            resulttext = naver_api.execute().get();
            //총 권 수 가져오기
            JSONObject jsonObject = new JSONObject(resulttext);
            str = jsonObject.getString("total");
            sText.setText(str+ "권");
            BooklistjsonParser(resulttext);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        catch (ExecutionException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        TotalPage = Integer.parseInt(str)/100;
        if((TotalPage%100)!=0){
            TotalPage++;
        }

        lText.setText(currPage+"/"+TotalPage);

        adapter = new CustomAdapter(this, 0, bookarr);
        booklist.setAdapter(adapter);


        booklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Search_Activity.this, BookDetail.class);
                intent.putExtra("BookImage", bookarr.get(i).getImage());
                intent.putExtra("BookPub", bookarr.get(i).getPublisher());
                intent.putExtra("BookTitle", bookarr.get(i).getTitle());
                intent.putExtra("BookLink", bookarr.get(i).getLink());
                intent.putExtra("BookDescription", bookarr.get(i).getDescription());

                startActivity(intent);
            }
        });

        pivBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Count >0){
                    currPage--;
                    Count--;
                    try{
                        bookarr.clear();
                        Naver_Api naver_api = new Naver_Api();
                        naver_api.setStart(Count);
                        naver_api.setText(SearchWord);
                        resulttext = naver_api.execute().get();
                        BooklistjsonParser(resulttext);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    catch (ExecutionException e){
                        e.printStackTrace();
                    }
                    adapter = new CustomAdapter(Search_Activity.this, 0, bookarr);
                    booklist.setAdapter(adapter);
                    lText.setText(currPage+"/"+TotalPage);
                }
            }
        });

        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Count++;
                currPage++;
                try{
                    bookarr.clear();
                    Naver_Api naver_api = new Naver_Api();
                    naver_api.setStart(Count);
                    naver_api.setText(SearchWord);
                    resulttext = naver_api.execute().get();
                    BooklistjsonParser(resulttext);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                catch (ExecutionException e){
                    e.printStackTrace();
                }

                adapter = new CustomAdapter(Search_Activity.this, 0, bookarr);
                booklist.setAdapter(adapter);
                lText.setText(currPage+"/"+TotalPage);
            }
        });
    }


    public void BooklistjsonParser(String jsonString){

        try{
            JSONArray jsonArray = new JSONObject(jsonString).getJSONArray("items");
            for(int i=0; i<jsonArray.length(); i++){
                //HashMap map = new HashMap<>();
                JSONObject job = jsonArray.getJSONObject(i);
                Bookinfo BD = new Bookinfo();
                BD.setVal(job.optString("title"), job.optString("link"),job.optString("image"),job.optString("autor")
                        ,job.optString("publisher"), job.optString("description"), job.optString("discount"));
                bookarr.add(BD);
                arraylist.add(BD.getTitle());
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private class CustomAdapter extends ArrayAdapter<Bookinfo>{
        private ArrayList<Bookinfo> bk;
        Bitmap bitmap;

        public CustomAdapter(Context context, int textresource, ArrayList<Bookinfo> bobj){
            super(context, textresource, bobj);
            this.bk = bobj;
        }

        public View getView(int pos, View convert, ViewGroup parent){
            View v = convert;
            if(v == null){
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_ltem, null);
            }
            ImageView imageView = (ImageView) v.findViewById(R.id.imageview);
            TextView textView = v.findViewById(R.id.itemtext);

            textView.setText(bk.get(pos).getTitle());
            Thread mThread = new Thread(){
                @Override
                public void run() {
                    try{
                        URL url = new URL(bk.get(pos).getImage());
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

            return v;
        }
    }
}

