package com.example.projetofuui.view.formCadastro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetofuui.MainActivity;;;
import com.example.projetofuui.R;
import com.example.projetofuui.view.Model.UserModel;
import com.example.projetofuui.view.formLogin.FormLogin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class FormCadastro extends AppCompatActivity {

    private EditText fullname;
    private EditText gender;
    private EditText Age;
    private EditText email;
    private EditText create;
    private EditText confirm;


    private Button button_cadastro;
    private Button text_cadastro2;
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

        fullname = (EditText) findViewById(R.id.fullname);
        gender = (EditText) findViewById(R.id.gender);
        Age = (EditText) findViewById(R.id.Age);
        email = (EditText) findViewById(R.id.email);
        create = (EditText) findViewById(R.id.create);
        confirm = (EditText) findViewById(R.id.confirm);
        button_cadastro = (Button) findViewById(R.id.button_cadastro);
        text_cadastro2 = (Button) findViewById(R.id.text_cadastro2);

        button_cadastro.setOnClickListener(view -> {

            UserModel userModel = new UserModel();

            userModel.setEmail(email.getText().toString());
            userModel.setFullname(fullname.getText().toString());
            userModel.setCreate(create.getText().toString());
            userModel.setConfirm(confirm.getText().toString());
            userModel.setAge(Age.getText().toString());
            userModel.setGender(gender.getText().toString());

            String novaSenha = create.getText().toString();
            String confirmarSenha = confirm.getText().toString();

            if (!TextUtils.isEmpty((userModel.getFullname())) || !TextUtils.isEmpty(userModel.getAge())|| !TextUtils.isEmpty(userModel.getEmail()) || !TextUtils.isEmpty(novaSenha) || !TextUtils.isEmpty(confirmarSenha)){
                if (novaSenha.equals(confirmarSenha)) {
                    mAuth.createUserWithEmailAndPassword(userModel.getEmail(), novaSenha).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            userModel.setId(mAuth.getUid());
                            userModel.salvar();
                            abrirTelaDeLogin();
                        } else {
                            String error = Objects.requireNonNull(task.getException()).getMessage();
                            Toast.makeText(FormCadastro.this, "" + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(FormCadastro.this, "A senha deve ser a mesma!", Toast.LENGTH_SHORT).show();
                }

            }    });


        button_cadastro.setOnClickListener(view -> {
            Intent intent = new Intent( FormCadastro.this, FormLogin.class);
            startActivity(intent);
            finish();
        });

        text_cadastro2.setOnClickListener(view -> {
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