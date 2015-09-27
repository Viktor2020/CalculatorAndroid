package com.example.ex2015_07_26.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ex2015_07_26.MyActivity;
import com.example.ex2015_07_26.R;
import com.example.ex2015_07_26.adapter.UserAdapter;
import com.example.ex2015_07_26.database.DBHelper;
import com.example.ex2015_07_26.model.User;

public class LoginActivity extends Activity implements View.OnClickListener {

    Button btnLogin;
    EditText etLogin, etPassword ;
    TextView textLinkRegistration;
    DBHelper  dbHelper;
    ListView userListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        textLinkRegistration = (TextView) findViewById(R.id.textLinkRegistration);

        textLinkRegistration.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        dbHelper = new DBHelper(getApplicationContext());

        userListView = (ListView) findViewById(R.id.user_list);

        UserAdapter userAdapter = new UserAdapter(getApplicationContext(),R.layout.item
                , dbHelper.getUserDao().getCursor()
                , new String[]{ BaseColumns._ID,DBHelper.USER_NAME_COLUMN, DBHelper.USER_PASSWORD_COLUMN}
                , new int[]{R.id.item_id, R.id.item_name, R.id.item_password}
                , 1);

        userListView.setAdapter(userAdapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (isAuthorization()){
                    startActivity(new Intent(this, MyActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, getString(R.string.error_text_toast), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.textLinkRegistration:
                startActivity(new Intent(this, RegistrationActivity.class));
                finish();
                break;
        }
    }

    private boolean isAuthorization() {
        User user = dbHelper.getUserDao().getUser(etLogin.getText().toString(), etPassword.getText().toString());

        return user != null;
    }
}