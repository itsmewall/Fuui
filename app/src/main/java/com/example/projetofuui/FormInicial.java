package com.example.projetofuui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.projetofuui.databinding.ActivityFormInicialBinding;

public class FormInicial extends AppCompatActivity {

    private ActivityFormInicialBinding binding;
    
    private NavHostFragment navHostFragment;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormInicialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavigation();
                
    }
    
    private void initNavigation(){
        navHostFragment 
                = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }
    
}