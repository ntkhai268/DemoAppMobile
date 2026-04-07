package com.ntkhai.demolesson5_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtUsername, edtPassword;
    CheckBox ckRememberPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.btnThoat).setOnClickListener(this);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        ckRememberPassword = findViewById(R.id.ckRememberPassword);

        readAutoLogin();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnLogin) {
            if (ckRememberPassword.isChecked()) {
                saveAutoLogin();
            } else {
                clearAutoLogin();
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.btnThoat) {
            finish();
        }
    }

    private void saveAutoLogin() {
        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", edtUsername.getText().toString());
        editor.putString("password", edtPassword.getText().toString());
        editor.commit();
    }

    private boolean readAutoLogin() {
        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        String username = preferences.getString("username", "");

        if (username != null && !username.equals("")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }

    private void clearAutoLogin() {
        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
