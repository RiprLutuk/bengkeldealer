package com.riprlutuk.bengkeldealer.module;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.riprlutuk.bengkeldealer.data.DataBengkel;
import com.riprlutuk.bengkeldealer.viewmaps.MapsActivityBengkel;
import com.riprlutuk.bengkeldealer.R;
import com.riprlutuk.bengkeldealer.app.detail_gambar_activity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class detail_bengkel extends AppCompatActivity {
    Toolbar toolbar;
    ImageView foto;
    TextView nmbengkel, jmlmontir, haribuka, jambuka, alamat, kelurahan, kecamatan, kdpos, kabupaten,
            telepon,lati,longi;
    Button btnPetaBengkel, btnTelp;
    int a=1;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_bengkel);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foto = (ImageView) findViewById(R.id.foto_bengkel);
        nmbengkel = (TextView) findViewById(R.id.nm_bengkel_text_view);
        jmlmontir = (TextView) findViewById(R.id.jmlmontir_text_view);
        haribuka = (TextView) findViewById(R.id.haribuka_text_view);
        jambuka = (TextView) findViewById(R.id.jambuka_text_view);
        alamat = (TextView) findViewById(R.id.alamat_text_view);
        kelurahan = (TextView) findViewById(R.id.kel_text_view);
        kecamatan = (TextView) findViewById(R.id.kec_text_view);
        kdpos = (TextView) findViewById(R.id.kdpos_text_view);
        kabupaten = (TextView) findViewById(R.id.kab_text_view);
        telepon = (TextView) findViewById(R.id.telp_text_view);
        lati = (TextView) findViewById(R.id.lati_text_view);
        longi = (TextView) findViewById(R.id.longi_text_view);
        btnPetaBengkel = (Button) findViewById(R.id.btn_lihat_peta);
        btnTelp =(Button) findViewById(R.id.btn_tlp_bengkel);

        final DataBengkel dataBengkel = (DataBengkel) getIntent().getExtras().getSerializable("Detail Bengkel");

        Picasso.with(this).load(dataBengkel.getFoto()).into(foto);

        nmbengkel.setText(dataBengkel.getNmbengkel());
        haribuka.setText(dataBengkel.getHariBuka());
        jambuka.setText(dataBengkel.getJambuka());
        jmlmontir.setText(dataBengkel.getJmlMontir());
        alamat.setText(dataBengkel.getAlamat());
        kelurahan.setText(dataBengkel.getKelurahan());
        kecamatan.setText(dataBengkel.getKecamatan());
        kdpos.setText(dataBengkel.getKdpos());
        kabupaten.setText(dataBengkel.getKabupaten());
        if(dataBengkel.getTelepon().length() <=a){
            telepon.setTextColor(Color.parseColor("#FFB807"));
            telepon.setText("Belum Diketahui");
        }else {
            telepon.setText(dataBengkel.getTelepon());
        }
        lati.setText((dataBengkel.getLati()));
        longi.setText(dataBengkel.getLongi());
        foto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(detail_bengkel.this,detail_gambar_activity.class);
                inten.putExtra("detail_gambar",dataBengkel.getFoto());
                startActivity(inten);
            }
        });
        btnTelp.setOnClickListener(new View.OnClickListener(){
            TextView notelp = (TextView) findViewById(R.id.telp_text_view);
            @Override
            public void onClick(View view) {
                String toDial = "tel:" + notelp.getText().toString();
                if (notelp.getText() !="Belum Diketahui"){
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
                } else {
                    Toast.makeText(detail_bengkel.this, "Tidak ada nomor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPetaBengkel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(detail_bengkel.this,MapsActivityBengkel.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Rute", (Serializable) dataBengkel);
                inten.putExtras(bundle);
                startActivity(inten);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
