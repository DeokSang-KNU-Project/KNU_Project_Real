package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

public class SubActivity2 extends FragmentActivity implements OnMapReadyCallback {
    LatLng coord = new LatLng(37.27472779999972, 127.1305704999994);
    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.27472779999972, 127.1305704999994));
    InfoWindow infoWindow;

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

        infoWindow = new InfoWindow();
        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return null;
            }
        });

        struct_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch(i){
                    case 0:
                        coord = new LatLng( 37.27496452179004,127.12999189152013);
                        break;
                    case 1:
                        coord = new LatLng( 37.27534657189453, 127.13080154844329);
                        break;
                    case 2:
                        coord = new LatLng(37.27606956456262,127.13088454580281);
                        break;
                    case 3:
                        coord = new LatLng(37.275834190366126,127.13187918651508);
                        break;
                    case 4:
                        coord = new LatLng(37.276462085367605,127.13239895154122);
                        break;
                    case 5:
                        coord = new LatLng(37.27413099265969,127.13208762320942);
                        break;
                    case 6:
                        coord = new LatLng(37.274495465847295,127.13248852725621);
                        break;
                    case 7:
                        coord = new LatLng(37.275336971145606,127.13334974052292);
                        break;
                    case 8:
                        coord = new LatLng(37.27571662098761, 127.13426934845258);
                        break;
                    case 9:
                        coord = new LatLng(37.2760961622743, 127.13329188397137);
                        break;
                    case 10:
                        coord = new LatLng(37.27691576668962, 127.13359494859745);
                        break;
                    case 11:
                        coord = new LatLng(37.27650533891552, 127.13399732003852);
                        break;
                    case 12:
                        coord = new LatLng(37.27709303933176, 127.13419850227572);
                        break;
                    case 13:
                        coord = new LatLng(37.27783465641703, 127.1337459746537);
                        break;
                    case 14:
                        coord = new LatLng(37.27810389699764, 127.13469078856325);
                        break;
                    case 15:
                        coord = new LatLng(37.27851038679704, 127.13378945423662);
                        break;
                    case 16:
                        coord = new LatLng(37.27718937911577, 127.13465815138105);
                        break;
                    case 17:
                        coord = new LatLng(37.2773282652328, 127.13533775105167);
                        break;
                    default:
                        break;
                }
                cameraUpdate = CameraUpdate.scrollTo(coord);
                naverMap.moveCamera(cameraUpdate);
                marker.setPosition(coord);
                marker.setMap(naverMap);
                marker.setCaptionText(structure[i]);


            }
        });
    }
}
