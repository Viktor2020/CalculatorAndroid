package com.example.ex2015_07_26.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ex2015_07_26.R;
import com.example.ex2015_07_26.database.DBHelper;

public class RegistrationActivity extends Activity implements View.OnClickListener {

    Button btnRegistration;
    EditText etLogin, etPassword;
    TextView textLinkLogin;
    DBHelper  dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnRegistration = (Button) findViewById(R.id.btnRegistration);
        textLinkLogin = (TextView) findViewById(R.id.textLinkLogin);

        textLinkLogin.setOnClickListener(this);
        btnRegistration.setOnClickListener(this);

        dbHelper = new DBHelper(getApplicationContext());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegistration:
                register();
                break;
            case R.id.textLinkLogin:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    private void register() {
        if (!etLogin.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
            dbHelper.getUserDao().insertUser( etLogin.getText().toString(), etPassword.getText().toString());
        }

    }
}