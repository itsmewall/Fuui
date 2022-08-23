package com.example.projetofuui.view.formLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projetofuui.R;
import com.example.projetofuui.view.formCadastro.FormCadastro;

import java.util.Objects;

public class FormLogin extends AppCompatActivity {

    private TextView text_tela_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        Objects.requireNonNull(getSupportActionBar()).hide();

        text_tela_cadastro.setOnClickListener(view -> {

            Intent intent = new Intent(FormLogin.this, FormCadastro.class);
            startActivity(intent);
        });
    }

    private void IniciarComponentes(){
        text_tela_cadastro = findViewById(R.id.text_cadastro2);
    }
}