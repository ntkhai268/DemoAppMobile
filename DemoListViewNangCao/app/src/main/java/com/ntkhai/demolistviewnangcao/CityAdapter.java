package com.ntkhai.demolistviewnangcao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CityAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<City> cityList;

    public CityAdapter(Context context, int layout, ArrayList<City> cityList) {
        this.context = context;
        this.layout = layout;
        this.cityList = cityList;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView txtTen = convertView.findViewById(R.id.txtTen);
        TextView txtLink = convertView.findViewById(R.id.txtlink);
        ImageView imgHinh = convertView.findViewById(R.id.imgHinh);


        City city = cityList.get(position);
        txtTen.setText(city.getName());
        txtLink.setText(city.getLinkWiki());
        imgHinh.setImageResource(city.getImage());

        return convertView;
    }

}
