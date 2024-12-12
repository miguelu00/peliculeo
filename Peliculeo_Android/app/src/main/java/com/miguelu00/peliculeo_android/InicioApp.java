package com.miguelu00.peliculeo_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class InicioApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new Handler().postDelayed(() -> {
            // After the delay, start the main activity
            Intent intent = new Intent(InicioApp.this, MainActivity.class);
            startActivity(intent);
            finish(); // Con este finish(), evitamos que se pueda volver con el botón Atrás, y repetir la animación de la "Activity"
        }, 1230);
    }
}