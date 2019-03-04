package com.riprlutuk.bengkeldealer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.riprlutuk.bengkeldealer.R;

public class SpinnerKecamatan extends ArrayAdapter<String> {
    private Context context;
    private LayoutInflater inflater;
    private final String[] itemSpinner;

    public SpinnerKecamatan(Context context, String[] itemSpinner) {
        super(context, R.layout.spinner_name, itemSpinner);

        this.context = context;
        this.itemSpinner = itemSpinner;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_name, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.spinner_text_view);

        textView.setText(itemSpinner[position]);

        return convertView;
    }
}
