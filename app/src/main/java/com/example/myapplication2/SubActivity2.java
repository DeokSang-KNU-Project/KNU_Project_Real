package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import okio.Utf8;

public class SubActivity2 extends FragmentActivity implements OnMapReadyCallback {
    LatLng coord = new LatLng(37.27472779999972, 127.1305704999994);
    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.27472779999972, 127.1305704999994));
    InfoWindow infoWindow;
    String json = "";

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private static final String[] PERMISSONS = {Manifest.permission.ACCESS_FINE_LOCATION,
                                                Manifest.permission.ACCESS_COARSE_LOCATION};
    private FusedLocationSource locationSource;
    private NaverMap naverMap;

    private JSONObject jsonObject;
    private JSONObject BulidOB;
    private JSONArray BulidArray;
    private BulidInfo bulidInfo = new BulidInfo();;
    private ArrayList<String> arrayList;
    private String InfoVal;
    private String Bname = "";
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

        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            else{
                naverMap.setLocationTrackingMode(LocationTrackingMode.NoFollow);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) { //지도에 관한 설정은 다 여기서
        LatLng sw = new LatLng( 37.27342108607269, 127.12829799161149);
        LatLng ne = new LatLng( 37.2788543429793, 127.13636939859602);
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);

        ActivityCompat.requestPermissions(this, PERMISSONS, LOCATION_PERMISSION_REQUEST_CODE);//현재 위치 표시할 때 권한 확인

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
        ListView struct_list = findViewById(R.id.menu_list);

        ArrayList<LatLng> Latlist = new ArrayList<>();
        arrayList = new ArrayList<>();

        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray BulidArray = jsonObject.getJSONArray("Buliding_name");
            for(int i=0; i<BulidArray.length();i++) {
                JSONObject BulidOB = BulidArray.getJSONObject(i);
                BulidInfo bulidInfo = new BulidInfo();
                bulidInfo.setBulid(BulidOB.getString("name"), BulidOB.getString("info"));
                bulidInfo.setLat(BulidOB.getString("latitude"), BulidOB.getString("longitude"));
                arrayList.add(bulidInfo.getName());
                Latlist.add(bulidInfo.getLat());
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        LatLngBounds latb = LatLngBounds.from(Latlist); // 폴리곤 영역 설정
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        struct_list.setAdapter(adapter);
        naverMap.setExtent(latb); //범위 제한
        Marker marker = new Marker();

        struct_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                double lat = 0.0;
                double lon = 0.0;

                String Capon ="상세한 정보를 보려면 \n 말풍선 클릭";
                try{
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray BulidArray = jsonObject.getJSONArray("Buliding_name");
                    JSONObject BulidOB = BulidArray.getJSONObject(i);

                    BulidInfo bulidInfo = new BulidInfo();

                    bulidInfo.setBulid(BulidOB.getString("name"), BulidOB.getString("info"));
                    bulidInfo.setLat(BulidOB.getString("latitude"), BulidOB.getString("longitude"));
                    lat = bulidInfo.getLatit();
                    lon = bulidInfo.getLongit();
                    Bname = bulidInfo.getName();
                    InfoVal = bulidInfo.getInfo();
                    infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(getApplication()) {
                        @NonNull
                        @Override
                        public CharSequence getText(@NonNull InfoWindow infoWindow) {
                            return (CharSequence)Capon;
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
                marker.setCaptionText(Bname);
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

        infoWindow.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                Intent intent = new Intent(SubActivity2.this, ShowBulidInfo.class);
                intent.putExtra("BulidTitle", Bname);
                intent.putExtra("BulidIInfo", InfoVal);
                startActivity(intent);
                return false;
            }
        });
    }

}
