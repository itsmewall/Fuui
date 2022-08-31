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

import com.example.projetofuui.MainActivity;
import com.example.projetofuui.R;
import com.example.projetofuui.view.formCadastro.FormCadastro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FormLogin extends AppCompatActivity {

    private TextView hi;
    private TextView text_cadastro2;
    private EditText loginEmail;
    private EditText password;
    private Button Button;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        mAuth = FirebaseAuth.getInstance();
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = loginEmail.getText().toString();
                String senha = password.getText().toString();

                if(!TextUtils.isEmpty(login) || !TextUtils.isEmpty(senha)) {
                    mAuth.signInWithEmailAndPassword(login,senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                abrirTelaPrincipal();
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(FormLogin.this, "", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(FormLogin.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void abrirCadastro(){
        Intent intent = new Intent(FormLogin.this, FormCadastro.class);
        startActivity(intent);
        finish();
    }
}