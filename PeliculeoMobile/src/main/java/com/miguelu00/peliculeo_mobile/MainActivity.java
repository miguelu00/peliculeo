package com.miguelu00.peliculeo_mobile;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelu00.peliculeo_mobile.databinding.ActivityMainBinding;
import com.miguelu00.peliculeo_mobile.fragments.LoginPeliculeo;
import com.miguelu00.peliculeo_mobile.fragments.RegistroActivity;

public class MainActivity extends AppCompatActivity {

    //La variable para hacer binding tiene el mismo nombre en todos los ficheros como buena práctica.
    ActivityMainBinding binding;
    private BottomNavigationView navbarAbajo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        navbarAbajo = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelay, LoginPeliculeo.newInstance());
        transaction.commit();

        /**
         * Configuración del BOTTOM NAVIGATION VIEW para actualizar el cambio entre menús.
         */
        navbarAbajo.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment currentFragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.menu_login) {
                    currentFragment = LoginPeliculeo.newInstance();
                } else if (itemId == R.id.menu_registro) {
                    currentFragment = RegistroActivity.newInstance();
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.framelay, currentFragment);
                transaction.commit();
                return true;
            }
        });
    }
}