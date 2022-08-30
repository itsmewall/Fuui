package com.example.projetofuui.view.formLogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetofuui.MapsActivity;
import com.example.projetofuui.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FormLogin extends AppCompatActivity {

    private TextView hi;
    private EditText email;
    private EditText password;
    private Button Button;
    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;

    public FormLogin() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = firebaseAuth -> {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(FormLogin.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }

        };

        Button.setOnClickListener(view -> {
            String registerEmail = email.getText().toString();
            String senha = password.getText().toString();

            mAuth.signInWithEmailAndPassword(registerEmail, senha).addOnCompleteListener(FormLogin.this, task -> {
                if (!task.isSuccessful()) {
                    Toast.makeText(FormLogin.this, "Erro no login", Toast.LENGTH_SHORT).show();
                }
        });
    });
}}