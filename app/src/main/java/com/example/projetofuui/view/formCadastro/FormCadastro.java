package com.example.projetofuui.view.formCadastro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetofuui.MainActivity;
import com.example.projetofuui.MapsActivity;
import com.example.projetofuui.R;
import com.example.projetofuui.view.formLogin.FormLogin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class FormCadastro extends AppCompatActivity {

    private EditText email;
    private EditText create;
    private EditText confirm;
    private Button button_cadastro;
    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = firebaseAuth -> {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null){
                Intent intent = new Intent(FormCadastro.this, FormLogin.class);
                startActivity(intent);
                finish();
            }
        };

        email = (EditText) findViewById(R.id.email);
        create = (EditText) findViewById(R.id.create);
        confirm = (EditText) findViewById(R.id.confirm);
        button_cadastro = (Button) findViewById(R.id.button_cadastro);

        button_cadastro.setOnClickListener(view -> {
            String registerEmail = email.getText().toString();
            String novaSenha = create.getText().toString();
            String confirmarSenha = confirm.getText().toString();

            if (!TextUtils.isEmpty(registerEmail) || !TextUtils.isEmpty(novaSenha) || !TextUtils.isEmpty(confirmarSenha)
            ) {
                if (novaSenha.equals(confirmarSenha)) {
                    mAuth.createUserWithEmailAndPassword(registerEmail,novaSenha).addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            String user_id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(user_id);
                            current_user_db.setValue(true);
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
        });


        button_cadastro.setOnClickListener(view -> {
            Intent intent = new Intent( FormCadastro.this, FormLogin.class);
            startActivity(intent);
            finish();
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }

    private void abrirTelaDeLogin() {
        Intent intent = new Intent(FormCadastro.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}