package com.miguelu00.peliculeo_android.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelu00.peliculeo_android.R;
import com.miguelu00.peliculeo_android.adapters.PosterPelisAdapter;
import com.miguelu00.peliculeo_android.models.Pelicula;
import com.miguelu00.peliculeo_android.models.PosterPelicula;
import com.miguelu00.peliculeo_android.servicioAPI.PeliculaAPIServicio;
import com.miguelu00.peliculeo_android.servicioAPI.RetrofitClient;
import com.miguelu00.peliculeo_android.utiles.DecoRecyclerView;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculeoHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PosterPelisAdapter adapter;
    private List<Pelicula> listaPelis;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculeo_home);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerPeliculas);
        // Hacer que el layout del recyclerview sean 2 columnas
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DecoRecyclerView(16));

        //Recibir datos del login/registro (Registro WIP!)
        getIntentInfo();

        // Obtener datos de la API. Películas
        PeliculaAPIServicio servicioAPI = RetrofitClient.getClient().create(PeliculaAPIServicio.class);
        servicioAPI.getPeliculas().enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaPelis = response.body();
                }
                Log.i("CONX-ok", "Conexión OK: Conectado a la API de peliculas/auth correctamente!");
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                Toast.makeText(PeliculeoHome.this, "Estoy harto, ERROR: Lista de pelis vacía?", Toast.LENGTH_SHORT).show();
                Log.e("CONX-err", t.getMessage());
            }
        });

        // Los posters (objetos del recyclerView) de las películas
        servicioAPI.getPosters().enqueue(new Callback<List<PosterPelicula>>() {
            @Override
            public void onResponse(Call<List<PosterPelicula>> call, Response<List<PosterPelicula>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PosterPelicula> peliculas = response.body();
                    adapter = new PosterPelisAdapter(PeliculeoHome.this, peliculas, listaPelis, userName);
                    recyclerView.setAdapter(adapter);
                    try {
                        Log.e("err", "PosterPelicula:\n" + peliculas.stream().findFirst().get().toString());
                    } catch (NoSuchElementException noElemXcpt) {
                        System.out.println(noElemXcpt.getMessage());
                    }
                } else {
                    Toast.makeText(PeliculeoHome.this, "Error al obtener las películas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PosterPelicula>> call, Throwable t) {
                Toast.makeText(PeliculeoHome.this, "ERROR al conectar a api!: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("E_CargaPelis", "onFailure: " + t.getMessage());
            }
        });
    }

    //Función auxiliar para recoger info desde el Login/Registro (Registro WIP)
    public void getIntentInfo() {
        userName = getIntent().getStringExtra("USER_NAME");
    }
}