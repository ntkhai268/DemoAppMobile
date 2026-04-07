package com.ntkhai.demonetworkandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtSearch;
    private ImageView btnSearch, imgIcon;
    private TextView txtCityName, txtTemperature, txtCondition;
    private ListView lvForecast;
    private HourlyForecastAdapter adapter;
    private ArrayList<HourlyForecast> hourlyForecasts;

    private final String API_KEY = "fc8f4188ad3f48d8a10132707221212";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtSearch = findViewById(R.id.editTextTextPersonName);
        btnSearch = findViewById(R.id.imgSearch);
        txtCityName = findViewById(R.id.txtCityName);
        txtTemperature = findViewById(R.id.txtTemperature);
        txtCondition = findViewById(R.id.txtCityTemperature); // Reusing for condition
        imgIcon = findViewById(R.id.imgSeasonForecast);
        lvForecast = findViewById(R.id.lvWeatherForecast);

        hourlyForecasts = new ArrayList<>();
        adapter = new HourlyForecastAdapter(this, hourlyForecasts);
        lvForecast.setAdapter(adapter);

        btnSearch.setOnClickListener(v -> {
            String city = edtSearch.getText().toString().trim();
            if (!city.isEmpty()) {
                getWeatherData(city);
            } else {
                Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getWeatherData(String cityName) {
        String encodedCityName = cityName;
        try {
            encodedCityName = URLEncoder.encode(cityName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "https://api.weatherapi.com/v1/forecast.json?key=" + API_KEY + "&q=" + encodedCityName + "&days=1&aqi=no&alerts=no";

        Toast.makeText(MainActivity.this, "URL: " + url, Toast.LENGTH_LONG).show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject location = response.getJSONObject("location");
                        JSONObject current = response.getJSONObject("current");
                        JSONObject condition = current.getJSONObject("condition");

                        String name = location.getString("name");
                        double tempC = current.getDouble("temp_c");
                        String conditionText = condition.getString("text");
                        String iconUrl = "https:" + condition.getString("icon");

                        txtCityName.setText(name);
                        txtTemperature.setText(tempC + "°C");
                        txtCondition.setText(conditionText);

                        Glide.with(MainActivity.this)
                                .load(iconUrl)
                                .into(imgIcon);

                        JSONObject forecast = response.getJSONObject("forecast");
                        JSONArray forecastdayArray = forecast.getJSONArray("forecastday");
                        
                        hourlyForecasts.clear();
                        if (forecastdayArray.length() > 0) {
                            JSONObject todayForecast = forecastdayArray.getJSONObject(0);
                            JSONArray hourArray = todayForecast.getJSONArray("hour");
                            
                            for (int i = 0; i < hourArray.length(); i++) {
                                JSONObject hourObj = hourArray.getJSONObject(i);
                                String time = hourObj.getString("time");
                                String temp = hourObj.getDouble("temp_c") + "°C";
                                String wind = hourObj.getDouble("wind_kph") + " km";
                                
                                JSONObject hourCond = hourObj.getJSONObject("condition");
                                String hIconUrl = "https:" + hourCond.getString("icon");
                                
                                hourlyForecasts.add(new HourlyForecast(time, temp, wind, hIconUrl));
                            }
                        }
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                },
                error -> {
                    Log.e("WeatherApp", "Volley Error: " + error.toString());
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
}