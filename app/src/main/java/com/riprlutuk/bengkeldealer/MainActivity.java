package com.riprlutuk.bengkeldealer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.riprlutuk.bengkeldealer.adapter.MainMenuAdapter;
import com.riprlutuk.bengkeldealer.module.activity_data_dealer;
import com.riprlutuk.bengkeldealer.helper.activity_bantuan;
import com.riprlutuk.bengkeldealer.helper.activity_tentang;
import com.riprlutuk.bengkeldealer.module.activity_data_bengkel;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    boolean doubleBackToExitPressedOnce = false;
    Toolbar toolbar;

    ListView menuListView;
    MainMenuAdapter adapter;
    private String[] menuTextView = {
            "Bengkel Motor",
            "Dealer Motor",
            "Bantuan",
    };

    private Integer[] iconMenu = {
            R.drawable.ic_motorcycle_white,
            R.drawable.ic_location_city_white,
            R.drawable.ic_help,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menuListView = (ListView) findViewById(R.id.menu_list_view);
        adapter = new MainMenuAdapter(this, menuTextView, iconMenu);
        menuListView.setAdapter(adapter);
        menuListView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i;
        switch (position) {
            case 0:
                i = new Intent(getApplicationContext(),activity_data_bengkel.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(getApplicationContext(),activity_data_dealer.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(getApplicationContext(),
                        activity_bantuan.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
                //noinspection SimplifiableIfStatement
                if (id == R.id.menu_tentang) {
                    startActivity(new Intent(MainActivity.this, activity_tentang.class));
                }else if (id == R.id.nav_share){
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, "\nHai..! Saya tahu tentang Lokasi " +
                            "Bengkel dan Dealer Motor di Kabupaten Pemalang melalui Aplikasi "
                            +getString(R.string.app_name)+",\nDownload aplikasinya di " +
                            "http://mapscrip.com/apk/bengkeldealer.apk"
                            +"\nBuat kalian yang penasaran sama Aplikasinya bisa dilihat " +
                            "di http://mapscrip.com/apk/screenshot.html "+"\nTerima Kasih.");
                    startActivity(Intent.createChooser(sharingIntent, "Bagikan Via : "));
                }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,"Tekan kembali sekali lagi untuk keluar",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        },2000);
    }
}
