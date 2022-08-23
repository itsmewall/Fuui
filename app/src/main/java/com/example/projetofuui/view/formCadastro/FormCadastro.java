package com.example.projetofuui.view.formCadastro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetofuui.MainActivity;
import com.example.projetofuui.R;
import com.example.projetofuui.databinding.ActivityFormCadastroBinding;
import com.example.projetofuui.view.formLogin.FormLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class FormCadastro extends AppCompatActivity {

    private EditText email;
    private EditText create;
    private EditText confirm;
    private Button button_cadastro;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        create = findViewById(R.id.create);
        confirm = findViewById(R.id.confirm);
        button_cadastro = findViewById(R.id.button_cadastro);

        button_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registerEmail = email.getText().toString();
                String novaSenha = create.getText().toString();
                String confirmarSenha = confirm.getText().toString();

                if (!TextUtils.isEmpty(registerEmail) || !TextUtils.isEmpty(novaSenha) || !TextUtils.isEmpty(confirmarSenha)
                ) {
                    if (novaSenha.equals(confirmarSenha)) {
                        mAuth.createUserWithEmailAndPassword(registerEmail,novaSenha).addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                abrirTelaDeLogin();
                            } else {
                                String error = Objects.requireNonNull(task.getException()).getMessage();
                                Toast.makeText(FormCadastro.this, ""+error, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(FormCadastro.this, "A senha deve ser a mesma!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        button_cadastro.setOnClickListener(view -> {
            Intent intent = new Intent( FormCadastro.this, FormLogin.class);
            startActivity(intent);
            finish();
        });


    }


    private void abrirTelaDeLogin() {
        Intent intent = new Intent(FormCadastro.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}