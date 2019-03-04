package com.riprlutuk.bengkeldealer.viewmaps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.riprlutuk.bengkeldealer.R;
import com.riprlutuk.bengkeldealer.data.DataBengkel;

public class MapsActivityBengkel extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap mMapBengkel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_bengkel);
        // menyiapkan map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapbengkel);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMapBengkel = googleMap;
        final DataBengkel dataBengkel = (DataBengkel) getIntent().getExtras().getSerializable("Rute");
        double ltt = Double.parseDouble(dataBengkel.getLati());
        double lgt = Double.parseDouble(dataBengkel.getLongi());
        // menambahkan marker dan mengatur posisi kamera
        LatLng lttlgt = new LatLng(ltt, lgt);
        mMapBengkel.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker))
                .position(lttlgt)
                .title(dataBengkel.getNmbengkel())
                .snippet(dataBengkel.getAlamat()));
        Uri gmnIntentUri = Uri.parse("google.navigation:q=" +dataBengkel.getLati() + "," +dataBengkel.getLongi());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmnIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        try {
            startActivity(mapIntent);
        } catch (android.content.ActivityNotFoundException ex) {
        }
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(lttlgt)      // kamera set center
                .zoom(17)
                .bearing(60)                // orientasi kamera dari sisi timur
                .tilt(30)                   // memiringkan kamera 30 derajat
                .build();                   // membuat kamera
        mMapBengkel.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),2000,null);
    }
}