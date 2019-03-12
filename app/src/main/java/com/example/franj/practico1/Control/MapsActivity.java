package com.example.franj.practico1.Control;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.franj.practico1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private static final int REQUEST_CODE = 11;
    private static final int QUESTIONS = 1;
    private static final int EXCHANGE = 0;
    private LocationManager manager;
    private Marker personalMarker;
    private LatLng personalPosition;
    private Button btn_Exchange,btn_Questions;
    private Intent intent;
    private int level,score;
    private TextView txt_Score;


    //public interface listener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        btn_Exchange = findViewById(R.id.btn_Exchange);
        btn_Exchange.setOnClickListener(this);
        btn_Questions = findViewById(R.id.btn_Questions);
        btn_Questions.setOnClickListener(this);
        score=0;
        txt_Score = findViewById(R.id.txt_Score);
        txt_Score.setText(""+score);

        //solicitar permisos

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, REQUEST_CODE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 11);
        } else {
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
        }

    }
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            personalPosition = null;
            personalPosition = new LatLng(location.getLatitude(),location.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(personalPosition));
            personalMarker.setPosition(personalPosition);
            /*
            Biblioteca Icesi
            */
            if (((location.getLatitude()<3.341942)&&(location.getLatitude()>3.341671))&&((location.getLongitude()>-76.530062)&&(location.getLongitude()<-76.529789))){
                btn_Exchange.setEnabled(true);
                btn_Questions.setEnabled(false);
            }
            /*
            * Saman
            */
            else if (((location.getLatitude()<3.341905)&&(location.getLatitude()>3.341712))&&((location.getLongitude()>-76.530601)&&(location.getLongitude()<-76.530333))){
                btn_Exchange.setEnabled(false);
                btn_Questions.setEnabled(true);
                level=0;
            }
            /*
            * Cafeteria Central
            */
            else if (((location.getLatitude()<3.342280)&&(location.getLatitude()>3.341846))&&((location.getLongitude()>-76.529727)&&(location.getLongitude()<-76.529480))){
                level = 1;
                btn_Exchange.setEnabled(false);
                btn_Questions.setEnabled(true);
            }
            else{
                btn_Exchange.setEnabled(false);
                btn_Questions.setEnabled(false);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        personalPosition = new LatLng(3.341757 ,  -76.530808);
        personalMarker = mMap.addMarker(new MarkerOptions().position(personalPosition).title("YO"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(personalPosition));


    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 11) {
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Questions:
                intent = new Intent(MapsActivity.this, QuestionsActivity.class);
                intent.putExtra("level",level);
                startActivityForResult(intent,QUESTIONS);
                break;
            case R.id.btn_Exchange:
                intent = new Intent(MapsActivity.this, ExchangeActivity.class);
                intent.putExtra("score",score);
                startActivityForResult(intent,EXCHANGE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case QUESTIONS:
                switch (resultCode){
                    case RESULT_OK:

                        score += data.getExtras().getInt("newScore");
                        txt_Score.setText(""+score);
                        break;
                }

                break;
            case EXCHANGE:
                switch (resultCode){
                    case RESULT_OK:
                        score += data.getExtras().getInt("newScore");
                        txt_Score.setText(""+score);
                        break;
                }
                break;
        }

    }
}
