package com.ntkhai.demonetworkandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HourlyForecastAdapter extends ArrayAdapter<HourlyForecast> {
    private Context context;
    private List<HourlyForecast> list;

    public HourlyForecastAdapter(Context context, List<HourlyForecast> list) {
        super(context, R.layout.item_hourly_forecast, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hourly_forecast, parent, false);
        }

        HourlyForecast item = list.get(position);

        TextView txtTime = convertView.findViewById(R.id.txtTime);
        ImageView imgIcon = convertView.findViewById(R.id.imgWeatherIcon);
        TextView txtTemp = convertView.findViewById(R.id.txtHourlyTemp);
        TextView txtWind = convertView.findViewById(R.id.txtHourlyWind);

        String time = item.getTime();
        if (time.contains(" ")) {
            time = time.replace(" ", "\n");
        }
        txtTime.setText(time);

        txtTemp.setText(item.getTemp());
        txtWind.setText(item.getWindOrVisibility());

        Glide.with(context)
                .load(item.getIconUrl())
                .into(imgIcon);

        return convertView;
    }
}
