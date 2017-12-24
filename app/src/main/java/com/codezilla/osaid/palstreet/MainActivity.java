package com.codezilla.osaid.palstreet;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;

    List<News> lastNews;
    //get marker from server

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoib3NhaWQtbWFraGFsZmVoIiwiYSI6ImNqOGd6NTJ0dzBtYnkyeG93cncwcDFlM3AifQ.uRL2x-O5OKkjJigG5aOlvQ");
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        WebService webService = retrofit.create(WebService.class);
        Call<List<News>> call = webService.getNews();

        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                lastNews = response.body();
                mapView.getMapAsync(new OnMapReadyCallback() {


                    @Override
                    public void onMapReady(MapboxMap mapboxMap) {

                        // Create an Icon object for the marker to use
                        IconFactory iconFactory = IconFactory.getInstance(MainActivity.this);
                        Icon icon = iconFactory.fromResource(R.drawable.koas);


                        try{
                            for (int i=0;i<lastNews.size();i++){

                                double xp= lastNews.get(i).getXpoint();
                                double yp= lastNews.get(i).getYpoint();
                                String title = lastNews.get(i).getTitle();
                                int type = lastNews.get(i).getType();

                                if (type==1){

                                    icon = iconFactory.fromResource(R.drawable.gun);
                                    mapboxMap.addMarker(new MarkerOptions()
                                            .position(new LatLng(xp, yp))
                                            .title(title)
                                            .icon(icon)
                                    );

                                }else if(type==2){
                                    icon = iconFactory.fromResource(R.drawable.koas);
                                    mapboxMap.addMarker(new MarkerOptions()
                                            .position(new LatLng(xp, yp))
                                            .title(title)
                                            .icon(icon)
                                    );
                                }else if (type==5){
                                    icon = iconFactory.fromResource(R.drawable.car);
                                    mapboxMap.addMarker(new MarkerOptions()
                                            .position(new LatLng(xp, yp))
                                            .title(title)
                                            .icon(icon)
                                    );
                                }else {
                                    mapboxMap.addMarker(new MarkerOptions()
                                            .position(new LatLng(xp, yp))
                                            .title(title)
                                    );

                                }
                            }
                        }catch (Exception e){
                            Log.e("MarkersProblem",e.getLocalizedMessage());
                        }
                    }
                });





            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage()+"retrofit(call Support) problem", Toast.LENGTH_SHORT).show();
            }
        });




    }




    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }



}
