package com.miguelu00.peliculeo_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.miguelu00.peliculeo_android.databinding.ActivityMainBinding;
import com.miguelu00.peliculeo_android.fragments.LoginFragment;
import com.miguelu00.peliculeo_android.fragments.PrivacyNoticeFragment;
import com.miguelu00.peliculeo_android.fragments.RegisterFragment;
import com.miguelu00.peliculeo_android.inputs.DialogoConfirmacion;
import com.miguelu00.peliculeo_android.utiles.UtilesVista;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //Conseguir el link del intent (para funcionalidad del fragment de política de privacidad)
        Intent intent = getIntent();
        Uri data = intent.getData();

        if (data != null) {
            String fragmento = data.getLastPathSegment();
            if (fragmento != null) {
                navigateToFragment(fragmento);
            }
        }


        binding.fab.setVisibility(View.INVISIBLE);

    }

    //Sólo activaremos el botón una vez estemos logueados correctamente.
    public void activarBoton() {
        binding.fab.setVisibility(View.VISIBLE);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
                DialogoConfirmacion nuevoTicket = new DialogoConfirmacion();
                nuevoTicket.show(getSupportFragmentManager(), "Seleccione un film...");
            }
        });
    }

    private void navigateToFragment(String fragmentName) {
        Fragment fragment;

        switch (fragmentName) {
            case "loginFragmento":
                fragment = new LoginFragment();
                break;
            case "registroFragmento":
                fragment = new RegisterFragment();
                break;
            case "PrivacyNoticeFragment":
                fragment = new PrivacyNoticeFragment();
                break;
            default:
                UtilesVista.sacarToast(getApplicationContext(), "Fragmento no válido!");
                return;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}