package com.ntkhai.broadcastreceiverdemo.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("Day la Second Activity");
        tv.setTextSize(24f);
        tv.setPadding(32, 32, 32, 32);
        setContentView(tv);
    }
}
