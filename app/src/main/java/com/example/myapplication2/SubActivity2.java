package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okio.Utf8;

public class SubActivity2 extends FragmentActivity implements OnMapReadyCallback {
    LatLng coord = new LatLng(37.27472779999972, 127.1305704999994);
    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.27472779999972, 127.1305704999994));
    InfoWindow infoWindow;
    String json = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.UnivMap);
        if(mapFragment == null){
            mapFragment = mapFragment.newInstance();
            fm.beginTransaction().add(R.id.UnivMap, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

    }
    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) { //지도에 관한 설정은 다 여기서
        final String[] structure = {"샬롬관","인문사회관(인사관)","예술관","우원관","도서관",
                "목양관","승리관","교육관","천은관","본관","후생관","경천관","이공관","사범대학",
                "심전1관","심전2관","다솔관","강남학교"};
        ListView struct_list = findViewById(R.id.menu_list);
        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, structure);
        struct_list.setAdapter(adapter);

        Marker marker = new Marker();
        CameraPosition cameraPosition = naverMap.getCameraPosition();


        try{
            InputStream is = getAssets().open("Json/structure_info.json");
            int filesize = is.available();

            byte[] buffer = new byte[filesize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException e){
            e.printStackTrace();
        }

        infoWindow = new InfoWindow();


        struct_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                double lat = 0.0;
                double lon = 0.0;
                String strinfo = "";
                try{
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray BulidArray = jsonObject.getJSONArray("Buliding_name");

                    JSONObject BulidOB = BulidArray.getJSONObject(i);

                    BulidInfo bulidInfo = new BulidInfo();

                    bulidInfo.setBulid(BulidOB.getString("name"), BulidOB.getString("info"));
                    bulidInfo.setLat(BulidOB.getString("latitude"), BulidOB.getString("longitude"));
                    lat = bulidInfo.getLatit();
                    lon = bulidInfo.getLongit();
                    strinfo = bulidInfo.getInfo();
                    infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(getApplication()) {
                        @NonNull
                        @Override
                        public CharSequence getText(@NonNull InfoWindow infoWindow) {
                            return (CharSequence)bulidInfo.getInfo();
                        }
                    });
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                coord = new LatLng( lat,lon);
                cameraUpdate = CameraUpdate.scrollTo(coord);
                naverMap.moveCamera(cameraUpdate);
                marker.setPosition(coord);
                marker.setMap(naverMap);
                marker.setCaptionText(structure[i]);
                infoWindow.open(marker);
            }
        });

        marker.setOnClickListener(overlay -> {
            if(marker.getInfoWindow() == null){
                infoWindow.open(marker);
            }
            else{
                infoWindow.close();
            }
            return true;
        });
    }

}
