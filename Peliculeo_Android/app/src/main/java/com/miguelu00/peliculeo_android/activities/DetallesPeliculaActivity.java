package com.miguelu00.peliculeo_android.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.miguelu00.peliculeo_android.CFG_APP;
import com.miguelu00.peliculeo_android.R;
import com.miguelu00.peliculeo_android.models.Ticket;
import com.miguelu00.peliculeo_android.servicioAPI.TicketAPIServicio;
import com.miguelu00.peliculeo_android.utiles.UtilesVista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallesPeliculaActivity extends AppCompatActivity implements CFG_APP {

    // Recoger el usuario para reservar los tickets
    private String userName;
    private int codPelicula;
    private List<Ticket> listaTickets;
    TicketAPIServicio servTickets;

    TextView contadorTickets;
    Button quitarTicket_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pelicula);

        String BASE_URL = "http://" + SERVER + ":" + PUERTO_HOST + "/api/";
        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servTickets = retrofit.create(TicketAPIServicio.class);

        ImageView moviePoster = findViewById(R.id.moviePoster);

        TextView movieTitle = findViewById(R.id.movieTitle);
        contadorTickets = findViewById(R.id.cuentaTickets);

        Button sacarTicket_btn = findViewById(R.id.sacarTicket);
        quitarTicket_btn = findViewById(R.id.eliminarUno);

        String tituloPeli = getIntent().getStringExtra("TITULO_PELI");
        String base64Image = getIntent().getStringExtra("POSTER_PELI");
        userName = getIntent().getStringExtra("USER_NAME");
        codPelicula = getIntent().getIntExtra("CODIGO_PELI", 0);
        listaTickets = new ArrayList<Ticket>();

        movieTitle.setText(tituloPeli);

        // Mostrar imágen Base64 usando Glide
        if (base64Image != null) {
            Glide.with(getApplicationContext())
                    .asBitmap()
                    .load(Base64.decode(base64Image, Base64.DEFAULT))
                    .into(moviePoster);
        }

        // Cargar todos los tickets que tiene el usuario (calcular número de tickets para contador)
        servTickets.getTicketsByUser(userName).enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                if (response.code() != 400 && response.code() != 500) {
                    listaTickets = response.body();
                    actualizarContadorTckts();
                    return;
                }
                UtilesVista.sacarToast(getApplicationContext(), "ERROR al listar tickets para \'" + userName + "\'!");
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {
                UtilesVista.sacarToast(getApplicationContext(), "ERROR al listar tickets para \'" + userName + "\'!");
                Log.e("ERR-listado", t.getMessage());
            }
        });

        //Agregar funcionalidad a botón de sacar ticket
        sacarTicket_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ticket ticket = new Ticket(0, userName, codPelicula, "");

                if (userName == null || codPelicula == 0) {
                    UtilesVista.sacarToast(getApplicationContext(), "ERROR: Falta ID de película, o usuario no logueado!");
                    return;
                }

                //Pedir confirmación al usuario a la hora de sacar un ticket
                new AlertDialog.Builder(DetallesPeliculaActivity.this)
                        .setTitle("Confirmación")
                        .setMessage("Seguro que quieres reservar un ticket para " + tituloPeli + "?")
                        .setPositiveButton("Sí", (dialog, which) -> sacarTicket(ticket))
                        .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                        .show();

                }
        });

        quitarTicket_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ticket ticket = new Ticket(0, userName, codPelicula, "");
                servTickets.deleteTicketUnaPeli(ticket).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            UtilesVista.sacarToast(DetallesPeliculaActivity.this,
                                    "Ticket eliminado!", Toast.LENGTH_LONG);
                            actualizarContadorTckts();
                        } else {
                            UtilesVista.sacarToast(DetallesPeliculaActivity.this,
                                    String.valueOf(response.code()) + " | Error al eliminar el ticket!", Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        UtilesVista.sacarToast(getApplicationContext(), "ERROR al eliminar el ticket!");
                        Log.e("ERR_eliminar", t.getMessage());
                        actualizarContadorTckts();
                    }
                });
            }
        });
    }

    public void actualizarContadorTckts() {
        //Filtrar por peli actual, y actualizar el número de tickets comprados por el usuario actual
        long numPelis = listaTickets.stream()
                .filter(ticket -> ticket.getCodPelicula() == codPelicula)
                .count();

        contadorTickets.setText(String.valueOf(numPelis));
        if (numPelis > 0) {
            quitarTicket_btn.setEnabled(true);
            return;
        }
        quitarTicket_btn.setEnabled(false);
        return;
    }

    public void sacarTicket(Ticket ticket) {
        servTickets.addTicket(ticket).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() == null) {
                    try {
                        Log.d("DEBUG-addT", response.errorBody().string());
                    } catch (IOException e) {
                        Log.d("DEBUG-addT", e.getMessage());
                    }
                    return;
                }
                UtilesVista.sacarToast(getApplicationContext(), "Ticket agregado correctamente!");
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                UtilesVista.sacarToast(getApplicationContext(), "ERROR al agregar ticket!");
                Log.e("ERR-addT", t.getMessage());
                actualizarContadorTckts();
            }
        });
    }
}