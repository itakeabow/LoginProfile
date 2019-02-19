package com.example.loginprofile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences mPreference;
    private SharedPreferences.Editor mEditor;

     EditText etEmail;
     EditText etpassword;
     Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.editText1);
        etpassword =  findViewById(R.id.editText2);
        bLogin = findViewById(R.id.button1);

        mPreference = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreference.edit();

        checkSharedPreferences();

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent (LoginActivity.this, ProfileActivity.class);
                LoginActivity.this.startActivity(loginIntent);


                String email = etEmail.getText().toString();
                mEditor.putString(getString(R.string.email),email);
                mEditor.commit();
            }
        });
    }



    private void checkSharedPreferences(){
    String email = mPreference.getString(getString(R.string.email),"");
        etEmail.setText(email);
    }
    }
