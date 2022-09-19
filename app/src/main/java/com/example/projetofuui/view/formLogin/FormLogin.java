package com.example.projetofuui.view.formLogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetofuui.FormInicial;
import com.example.projetofuui.R;
import com.example.projetofuui.fragments.PedidoFragment;
import com.example.projetofuui.view.formCadastro.FormCadastro;
import com.example.projetofuui.view.ofertas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class FormLogin extends AppCompatActivity {

    private TextView hi;
    private TextView text_cadastro2;
    private EditText loginEmail;
    private EditText password;
    private Button loginButton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        loginEmail = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.password);


        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String login = loginEmail.getText().toString();
                final String senha = password.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                if(!TextUtils.isEmpty(login) || !TextUtils.isEmpty(senha)) {
                    mAuth.signInWithEmailAndPassword(login,senha)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        String user_id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(user_id);
                                        current_user_db.setValue(true);
                                        abrirTelaPrincipal();

                                    } else {
                                        Toast.makeText(FormLogin.this, "Erro no login", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        Button text_cadastro2 = (Button) findViewById(R.id.text_cadastro2);
        text_cadastro2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  abrirCadastro();
              }
          });
}


    private void abrirTelaPrincipal(){
        Intent intent = new Intent(FormLogin.this, ofertas.class);
        startActivity(intent);
        finish();
    }

    private void abrirCadastro(){
        Intent intent = new Intent(FormLogin.this, FormCadastro.class);
        startActivity(intent);
        finish();
    }
}