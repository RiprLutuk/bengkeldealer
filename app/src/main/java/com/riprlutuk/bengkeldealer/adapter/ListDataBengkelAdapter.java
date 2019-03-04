package com.riprlutuk.bengkeldealer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.riprlutuk.bengkeldealer.R;
import com.riprlutuk.bengkeldealer.app.AppController;
import com.riprlutuk.bengkeldealer.data.DataBengkel;

import java.util.List;


public class ListDataBengkelAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataBengkel> jarakItems;
    ImageLoader imageLoader;

    public ListDataBengkelAdapter(Activity activity, List<DataBengkel> jarakItems) {
        this.activity = activity;
        this.jarakItems = jarakItems;
    }

    @Override
    public Object getItem(int location) {
        return jarakItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.gambar);
        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView jarak = (TextView) convertView.findViewById(R.id.jarak);

        DataBengkel j = jarakItems.get(position);

        thumbNail.setImageUrl(j.getFoto(), imageLoader);
        nama.setText(j.getNmbengkel());
        jarak.setText(j.getJarak()+" Km");

        return convertView;
    }

    @Override
    public int getCount() {
        //return 5;
        return jarakItems.size();
    }

}