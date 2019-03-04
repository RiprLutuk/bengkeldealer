package com.riprlutuk.bengkeldealer.module;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.riprlutuk.bengkeldealer.data.DataDealer;
import com.riprlutuk.bengkeldealer.viewmaps.MapsActivityDealer;
import com.riprlutuk.bengkeldealer.R;
import com.riprlutuk.bengkeldealer.app.detail_gambar_activity;
import com.squareup.picasso.Picasso;

public class detail_dealer extends AppCompatActivity {
    Toolbar toolbardlr;
    ImageView foto;
    TextView nmdealer, merk, jmlkaryawan, haribukadlr, jambukadlr, alamatdlr, kelurahandlr, kecamatandlr, kdposdlr, kabupatendlr,
            telepondlr, pelayanandlr, fasilitasdlr, latitudedlr, longitudedlr;
    Button btnLihatPetaDlr, btnTelpDlr;
    int b=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_dealer);

        toolbardlr = (Toolbar)findViewById(R.id.toolbardlr);
        setSupportActionBar(toolbardlr);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foto = (ImageView) findViewById(R.id.gambar_dlr);
        nmdealer = (TextView) findViewById(R.id.nm_dealer_text_view);
        merk = (TextView) findViewById(R.id.merk_text_view);
        jmlkaryawan = (TextView) findViewById(R.id.jmlkaryawan_text_view);
        haribukadlr = (TextView) findViewById(R.id.haribuka_dlr_text_view);
        jambukadlr = (TextView) findViewById(R.id.jambuka_dlr_text_view);
        alamatdlr = (TextView) findViewById(R.id.alamat_dlr_text_view);
        kelurahandlr = (TextView) findViewById(R.id.kelurahan_dlr_text_view);
        kecamatandlr = (TextView) findViewById(R.id.kecamatan_dlr_text_view);
        kdposdlr = (TextView) findViewById(R.id.kdpos_dlr_text_view);
        kabupatendlr = (TextView) findViewById(R.id.kabupaten_dlr_text_view);
        telepondlr = (TextView) findViewById(R.id.telepon_dlr_text_view);
        pelayanandlr = (TextView) findViewById(R.id.pelayanan_text_view);
        fasilitasdlr = (TextView) findViewById(R.id.fasilitas_text_view);
        latitudedlr = (TextView) findViewById(R.id.latitude_text_view);
        longitudedlr = (TextView) findViewById(R.id.longitude_text_view);
        btnLihatPetaDlr = (Button) findViewById(R.id.btn_lihat_peta_dlr);
        btnTelpDlr =(Button) findViewById(R.id.btn_tlp_dealer);

        final DataDealer dataDealer = (DataDealer) getIntent().getExtras().getSerializable("Detail Dealer");

        Picasso.with(this).load(dataDealer.getFoto()).into(foto);
        nmdealer.setText(dataDealer.getNmdealer());
        haribukadlr.setText(dataDealer.getHaribukadlr());
        jambukadlr.setText(dataDealer.getJambukadlr());
        merk.setText(dataDealer.getMerk());
        jmlkaryawan.setText(dataDealer.getJmlkaryawan());
        alamatdlr.setText(dataDealer.getAlamatdlr());
        kelurahandlr.setText(dataDealer.getKelurahandlr());
        kecamatandlr.setText(dataDealer.getKecamatandlr());
        kdposdlr.setText(dataDealer.getKdposdlr());
        kabupatendlr.setText(dataDealer.getKabupatendlr());
        if(dataDealer.getTelepondlr().length() <=b){
            telepondlr.setTextColor(Color.parseColor("#FFB807"));
            telepondlr.setText("Belum Diketahui");
        }else {
            telepondlr.setText(dataDealer.getTelepondlr());
        }
        pelayanandlr.setText(dataDealer.getPelayanandlr());
        fasilitasdlr.setText(dataDealer.getFasilitasdlr());
        latitudedlr.setText(dataDealer.getLatitudedlr());
        longitudedlr.setText(dataDealer.getLongitudedlr());

        foto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detail_dealer.this,detail_gambar_activity.class);
                intent.putExtra("detail_gambar",dataDealer.getFoto());
                startActivity(intent);
            }
        });
        btnTelpDlr.setOnClickListener(new View.OnClickListener(){
            TextView notelp = (TextView) findViewById(R.id.telepon_dlr_text_view);
            @Override
            public void onClick(View view) {
                String toDial = "tel:" + notelp.getText().toString();
                if (notelp.getText() !="Belum Diketahui"){
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
                } else {
                    Toast.makeText(detail_dealer.this, "Tidak ada nomor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLihatPetaDlr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detail_dealer.this,MapsActivityDealer.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Lihat Peta",dataDealer);
                intent.putExtras(bundle);
                startActivity(intent);
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
