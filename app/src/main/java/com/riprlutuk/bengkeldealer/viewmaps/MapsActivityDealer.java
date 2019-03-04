package com.riprlutuk.bengkeldealer.viewmaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.riprlutuk.bengkeldealer.R;
import com.riprlutuk.bengkeldealer.data.DataDealer;

public class MapsActivityDealer extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_dealer);
        // menyiapkan map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final DataDealer dataDealer = (DataDealer) getIntent().getExtras().getSerializable("Lihat Peta");
        double lat = Double.parseDouble(dataDealer.getLatitudedlr());
        double lon = Double.parseDouble(dataDealer.getLongitudedlr());

        // menambahkan marker dan mengatur posisi kamera
        LatLng latlon = new LatLng(lat,lon);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker))
                .position(latlon)
                .title(dataDealer.getNmdealer())
                .snippet(dataDealer.getAlamatdlr()));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latlon)      // kamera set center
                .zoom(17)
                .bearing(60)                // orientasi kamera dari sisi timur
                .tilt(30)                   // memiringkan kamera 30 derajat
                .build();                   // membuat kamera
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),2000,null);
    }
}