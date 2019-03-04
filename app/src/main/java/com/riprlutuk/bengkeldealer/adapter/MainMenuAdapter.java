package com.riprlutuk.bengkeldealer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.riprlutuk.bengkeldealer.R;

public class MainMenuAdapter extends ArrayAdapter<String> {
    private Context context;
    private LayoutInflater inflater;
    private final String[] menuTextView;
    private final Integer[] iconMenu;

    public MainMenuAdapter(Context context, String[] menuTextView, Integer[] iconMenu) {
        super(context, R.layout.list_main, menuTextView);

        this.context = context;
        this.menuTextView = menuTextView;
        this.iconMenu = iconMenu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_main,parent,false);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.menu_text_view);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.icon_menu);

        txtTitle.setText(menuTextView[position]);
        imageView.setImageResource(iconMenu[position]);

        return convertView;
    }
}
