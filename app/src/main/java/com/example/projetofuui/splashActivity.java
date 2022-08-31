package com.example.projetofuui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetofuui.view.formLogin.FormLogin;

public class splashActivity extends AppCompatActivity {

    private Bundle savedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent abrir = new Intent(splashActivity.this, FormLogin.class);
                startActivity(abrir);
                finish();
            }
        },2*1000);
    }
}
