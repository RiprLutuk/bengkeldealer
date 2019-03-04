package com.riprlutuk.bengkeldealer.module;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.riprlutuk.bengkeldealer.R;
import com.riprlutuk.bengkeldealer.adapter.ListDataBengkelAdapter;
import com.riprlutuk.bengkeldealer.app.AppController;
import com.riprlutuk.bengkeldealer.data.DataBengkel;

import android.widget.AdapterView.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class activity_data_bengkel extends AppCompatActivity implements LocationListener,
        SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout swipe;
    ListView list;
    ListDataBengkelAdapter adapter;
    List<DataBengkel> itemList = new ArrayList<>();
    Double latitude, longitude;
    Criteria criteria;
    Location location;
    LocationManager locationManager;
    String provider;
    Toolbar toolbar;

    private static final String TAG = activity_data_bengkel.class.getSimpleName();
    private static final String url = "http://bengkeldealer.mapscrip.com/info/data_bengkel_all.php?latitude=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_data_bengkel);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // menyamakan variabel pada layout dan java
        list = (ListView) findViewById(R.id.list);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);

        // mengisi data dari adapter ke listview
        adapter = new ListDataBengkelAdapter(this, itemList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pa, View view, int position, long l) {
                //klik intent
                Intent i = new Intent(activity_data_bengkel.this, detail_bengkel.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Detail Bengkel", itemList.get(position));
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           lokasi();
                       }
                   }
        );

    }

    @Override
    public void onRefresh() {
        lokasi();
    }
    // fungsi ngecek lokasi GPS device pengguna
    private void lokasi() {
        location = locationManager.getLastKnownLocation(provider);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // permintaan update lokasi device dalam waktu per 1 menit
        locationManager.requestLocationUpdates(provider, 60000, 1, this);

        if (location != null) {
            onLocationChanged(location);
        } else {
           /* latitude longitude Alun-alun Pemalang sebagai default jika tidak ditemukan lokasi dari device pengguna */
            callListVolley(-6.890290, 109.380663);
        }
    }
    // untuk menampilkan lokasi bengkel terdekat dari device pengguna
    private void callListVolley(double lat, double lng) {
        itemList.clear();
        adapter.notifyDataSetChanged();

        swipe.setRefreshing(true);

        JsonArrayRequest jArr = new JsonArrayRequest(url + lat + "&longitude=" + lng,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                DataBengkel j = new DataBengkel();
                                j.setNmbengkel(obj.getString("nmbengkel"));
                                j.setJmlmontir(obj.getString("jmlmontir"));
                                j.setHaribuka(obj.getString("haribuka"));
                                j.setJambuka(obj.getString("jambuka"));
                                j.setAlamat(obj.getString("alamat"));
                                j.setKelurahan(obj.getString("kelurahan"));
                                j.setKecamatan(obj.getString("kecamatan"));
                                j.setKdpos(obj.getString("kdpos"));
                                j.setKabupaten(obj.getString("kabupaten"));
                                j.setTelepon(obj.getString("telepon"));
                                j.setLati(obj.getString("latitude"));
                                j.setLongi(obj.getString("longitude"));
                                j.setFoto(obj.getString("foto"));
                                double jarak = Double.parseDouble(obj.getString("jarak"));

                                j.setJarak("" + round(jarak, 2));

                                itemList.add(j);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // memberitahu adapter jika ada perubahan data
                        adapter.notifyDataSetChanged();
                        swipe.setRefreshing(false);
                        return;
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),"Server not responding", Toast.LENGTH_SHORT).show();
                swipe.setRefreshing(false);
                return;
            }
        });

        // menambah permintaan ke queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    // untuk menyederhanakan angka dibelakan koma jarak
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    // untuk menentukan lokasi gps dari device pengguna
    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        // untuk melihat latitude longitude posisi device pengguna pada logcat ditemukan atau tidak
        Log.e(TAG, "User location latitude:" + latitude + ", longitude:" + longitude);

        callListVolley(latitude, longitude);
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
