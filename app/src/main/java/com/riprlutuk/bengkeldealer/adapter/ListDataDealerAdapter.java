package com.riprlutuk.bengkeldealer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.riprlutuk.bengkeldealer.R;
import com.riprlutuk.bengkeldealer.data.DataDealer;

import java.util.List;

public class ListDataDealerAdapter extends BaseAdapter {
    private Context contextdlr;
    private LayoutInflater inflaterdlr;
    private List<DataDealer> listDataDealers;

    public ListDataDealerAdapter(Context context, List<DataDealer> listDataDealers) {
        this.contextdlr = context;
        this.listDataDealers = listDataDealers;
    }

    public int getCount() {
        return listDataDealers.size();
    }

    public Object getItem(int location) {
        return listDataDealers.get(location);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflaterdlr == null) {
            inflaterdlr = (LayoutInflater) contextdlr.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflaterdlr.inflate(R.layout.list_dealer, parent, false);
        }

        TextView nama = (TextView) convertView.findViewById(R.id.nama_dealer_text_view);

        DataDealer data = listDataDealers.get(position);

        nama.setText(data.getNmdealer());

        return convertView;
    }

}
