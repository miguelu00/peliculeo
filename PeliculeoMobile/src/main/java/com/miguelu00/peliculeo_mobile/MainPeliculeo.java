package com.miguelu00.peliculeo_mobile;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.miguelu00.peliculeo_mobile.adapters.PeliculaAdapter;
import com.miguelu00.peliculeo_mobile.models.Pelicula;
import com.miguelu00.peliculeo_mobile.servicioAPI.PeliculaAPIServicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPeliculeo extends AppCompatActivity {

    PeliculaAPIServicio servicioAPI;

    private RecyclerView recyclerView;
    private RelativeLayout backImage;
    private PeliculaAdapter movieAdapter;
    private List<Pelicula> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_peliculeo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    /**
     * Método que recuperará las películas en cartelera de la API
     */
    private void getAllPeliculas() {
        Retrofit retrofit = ClienteRetrofit.getClient(CFG_APP.URL_HOST + "/api/");
        PeliculaAPIServicio apiPeliculas = retrofit.create(PeliculaAPIServicio.class);

        //Preparar y lanzar la llamada para recuperar las películas en cartelera
        Call<List<Pelicula>> call = apiPeliculas.getPeliculas();
        call.enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movieList.addAll(response.body());
                    movieAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainPeliculeo.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                Toast.makeText(MainPeliculeo.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", t.getMessage());
            }
        });
    }

    private void getPeliculaByCod(int cod) {
        Call<Pelicula> call = servicioAPI.getPeliculaByCod(cod);
        call.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Pelicula pelicula = response.body();
                    // Hacer algo con la película obtenida
                    Toast.makeText(MainPeliculeo.this, "Película: " + pelicula.getFechaEstreno(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainPeliculeo.this, "No se pudo recuperar la película", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Toast.makeText(MainPeliculeo.this, "Error en la llamada a la API", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", t.getMessage());
            }
        });
    }
}