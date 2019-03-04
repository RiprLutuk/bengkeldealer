package com.riprlutuk.bengkeldealer.module;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.riprlutuk.bengkeldealer.R;
import com.riprlutuk.bengkeldealer.adapter.ListDataDealerAdapter;
import com.riprlutuk.bengkeldealer.adapter.SpinnerKecamatan;
import com.riprlutuk.bengkeldealer.data.DataDealer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class activity_data_dealer extends AppCompatActivity implements OnItemClickListener {
    Toolbar toolbardlr;
    ListView listViewDlr;
    Spinner spinnerdlr;
    private ListDataDealerAdapter adapterListDlr;
    private List<DataDealer> listDatadDealers;
    private final String[] itemSpinner = {
            "Kec. Bantarbolang",
            "Kec. Belik",
            "Kec. Comal",
            "Kec. Moga",
            "Kec. Pemalang",
            "Kec. Petarukan",
            "Kec. Randudongkal",
            "Kec. Taman",
            "Kec. Ulujami",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_data_dealer);

        toolbardlr = (Toolbar) findViewById(R.id.toolbardlr);
        setSupportActionBar(toolbardlr);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listDatadDealers = new ArrayList<>();

        listViewDlr  = (ListView) findViewById(R.id.dealer_list_view);
        adapterListDlr = new ListDataDealerAdapter(this, listDatadDealers);
        listViewDlr.setAdapter(adapterListDlr);
        listViewDlr.setOnItemClickListener(this);

        spinnerdlr = (Spinner) findViewById(R.id.dealer_spinner);
        SpinnerKecamatan adapterSpinner = new SpinnerKecamatan(this, itemSpinner);
        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerdlr.setAdapter(adapterSpinner);
        spinnerdlr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int posisi, long idd) {
                switch (posisi){
                    case 0:
                        listDatadDealers.clear();
                        ambilDataDealer("Bantarbolang");
                        break;
                    case 1:
                        listDatadDealers.clear();
                        ambilDataDealer("Belik");
                        break;
                    case 2:
                        listDatadDealers.clear();
                        ambilDataDealer("Comal");
                        break;
                    case 3:
                        listDatadDealers.clear();
                        ambilDataDealer("Moga");
                        break;
                    case 4:
                        listDatadDealers.clear();
                        ambilDataDealer("Pemalang");
                        break;
                    case 5:
                        listDatadDealers.clear();
                        ambilDataDealer("Petarukan");
                        break;
                    case 6:
                        listDatadDealers.clear();
                        ambilDataDealer("Randudongkal");
                        break;
                    case 7:
                        listDatadDealers.clear();
                        ambilDataDealer("Taman");
                        break;
                    case 8:
                        listDatadDealers.clear();
                        ambilDataDealer("Ulujami");
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void ambilDataDealer(final String kecdealer){
        AsyncTask<String, Void, Void> taskk = new AsyncTask<String, Void, Void>() {
            protected Void doInBackground(String... params) {
                OkHttpClient clientd = new OkHttpClient();
                Request req = new Request.Builder()
                        .url("http://bengkeldealer.mapscrip.com/info/data_dealer.php?kecamatan="+kecdealer)
                        .build();
                try {
                    Response response = clientd.newCall(req).execute();

                    JSONArray arry = new JSONArray(response.body().string());

                    listDatadDealers.clear();

                    for (int i=0; i<arry.length(); i++) {
                        JSONObject object = arry.getJSONObject(i);

                        DataDealer data = new DataDealer(object.getString("nmdealer"), object.getString("merk"),
                                object.getString("jmlkaryawan"), object.getString("haribuka"),
                                object.getString("jambuka"), object.getString("alamat"),
                                object.getString("kelurahan"), object.getString("kecamatan"),
                                object.getString("kdpos"), object.getString("kabupaten"),
                                object.getString("telepon"), object.getString("pelayanan"),
                                object.getString("fasilitas"), object.getString("latitude"),
                                object.getString("longitude"), object.getString("foto"));

                        listDatadDealers.add(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread( new Runnable(){
                        public void run(){
                            Toast.makeText(getApplicationContext(), "Ada masalah saat pengambilan data, coba lagi..!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapterListDlr.notifyDataSetChanged();
            }
        };

        taskk.execute(kecdealer);
    }

    public void onItemClick(AdapterView<?> parent, View view, int posisi, long idd) {
        Intent idlr = new Intent(this, detail_dealer.class);
        Bundle bdlr = new Bundle();
        bdlr.putSerializable("Detail Dealer", listDatadDealers.get(posisi));
        idlr.putExtras(bdlr);
        startActivity(idlr);
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