package com.example.projetofuui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.projetofuui.databinding.ActivityFormInicialBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FormInicial extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    private ActivityFormInicialBinding binding;
    private NavHostFragment navHostFragment;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormInicialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavigation();

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(FormInicial.this,"FAB is clicked!", Toast.LENGTH_LONG). show();

            }
        });
                
    }
    
    private void initNavigation(){
        navHostFragment 
                = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }
    
}