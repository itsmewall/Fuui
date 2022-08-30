package com.example.projetofuui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetofuui.view.formCadastro.FormCadastro;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {

    private Button mDriver, mCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDriver = (Button) findViewById(R.id.driver);
        mCustomer = (Button) findViewById(R.id.customer);

        mDriver.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormCadastro.class);
            startActivity(intent);
            finish();

        });

        Object mapFragment = getSupportFragmentManager().findFragmentById(R.id.map_fragment);

    }
}