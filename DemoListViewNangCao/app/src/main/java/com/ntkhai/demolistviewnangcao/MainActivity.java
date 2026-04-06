package com.ntkhai.demolistviewnangcao;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvCity;
    ArrayList<City> cityArrayList = new ArrayList<>();
    CityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lvCity = findViewById(R.id.lvCity);
        cityArrayList.add(new City("Hà Nội", R.drawable.hanoi, "https://vi.wikipedia.org/wiki/Hà_N%E1%BB%99i"));
        cityArrayList.add(new City("Hà Nội", R.drawable.hanoi, "https://vi.wikipedia.org/wiki/Hà_N%E1%BB%99i"));

        adapter = new CityAdapter(this, R.layout.city_row, cityArrayList);
        lvCity.setAdapter(adapter);

        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){}

        });

    }
}