package com.riprlutuk.bengkeldealer.helper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.riprlutuk.bengkeldealer.R;

public class activity_bantuan extends AppCompatActivity {
    Toolbar toolbar;
    TextView ww;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bantuan);
        ww = (TextView) findViewById(R.id.www_dev_text_view);
        ww.setMovementMethod(LinkMovementMethod.getInstance());
        String isi ="<a href=http://mapscrip.com>www.mapscrip.com</a>";
        ww.setText(Html.fromHtml(isi));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                //noinspection SimplifiableIfStatement
                if (id == R.id.menu_tentang) {
                    startActivity(new Intent(activity_bantuan.this, activity_tentang.class));
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
    }
}
