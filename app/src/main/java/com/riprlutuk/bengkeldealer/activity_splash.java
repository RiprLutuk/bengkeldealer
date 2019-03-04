package com.riprlutuk.bengkeldealer;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;

public class activity_splash extends AppCompatActivity {
    //Set waktu lama splash screen
    public LocationManager mLocationManager;
    static int splash = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(activity_splash.this, MainActivity.class);
                startActivity(i); // menghubungkan activity splashscren ke xml
                //jeda selesai Splashscreen
                this.finish();
            }

            private void finish() {
                // cek adanya koneksi internet
                mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                boolean ena = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                ConnectivityManager koneksi = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = koneksi.getActiveNetworkInfo();

                if (info == null) {
                    Toast.makeText(activity_splash.this, "Tidak Ada Koneksi Jaringan", Toast.LENGTH_LONG).show();
                }else if (!ena){
                    Toast.makeText(activity_splash.this,"GPS Belum Aktif", Toast.LENGTH_LONG).show();
                }
            }
        }, splash);
    }
}
