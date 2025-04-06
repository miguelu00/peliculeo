package com.miguelu00.peliculeo_android.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelu00.peliculeo_android.R;
import com.miguelu00.peliculeo_android.activities.DetallesPeliculaActivity;
import com.miguelu00.peliculeo_android.models.Pelicula;
import com.miguelu00.peliculeo_android.models.PosterPelicula;

import java.io.File;
import java.net.URL;
import java.util.List;

public class PosterPelisAdapter extends RecyclerView.Adapter<PosterPelisAdapter.PeliculaViewHolder>{
    private Context ctx;
    private List<PosterPelicula> peliculas;
    private List<Pelicula> listaPelisFull;
    private String userName;

    public PosterPelisAdapter(Context ctx, List<PosterPelicula> peliculas, List<Pelicula> listaPelis, String userName) {
        this.ctx = ctx;
        this.peliculas = peliculas;
        this.listaPelisFull = listaPelis;
        this.userName = userName;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_pelicula, parent, false);
        return new PeliculaViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        PosterPelicula pelicula = peliculas.get(position);
        holder.txtTitulo.setText(pelicula.getTitulo());
        // Si no se encuentra el poster de la película, ponemos un placeholder
        if (pelicula.getBlobPoster() == null) {
            holder.imgPoster.setImageDrawable(AppCompatResources.getDrawable(ctx, R.drawable.estrenos_icon));
        } else {
            holder.imgPoster.setScaleType(ImageView.ScaleType.CENTER);
            holder.imgPoster.setAdjustViewBounds(true);
            if (pelicula.getBlobPoster().length() == 15) {
                holder.imgPoster.setImageDrawable(AppCompatResources.getDrawable(ctx, R.drawable.estrenos_icon));
            } else {
                holder.imgPoster.setImageBitmap(pelicula.getBlobPosterAsBitmap());
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pasar al fragment de sacar ticket, el titulo de peli y su imagen
                Intent intent = new Intent(ctx, DetallesPeliculaActivity.class);
                intent.putExtra("TITULO_PELI", peliculas.get(position).getTitulo());
                intent.putExtra("POSTER_PELI", peliculas.get(position).getBlobPoster());
                intent.putExtra("CODIGO_PELI", peliculas.get(position).getCodPelicula());
                intent.putExtra("USER_NAME", userName);
                ctx.startActivity(intent);
            }
        });

        Log.d("Pelicula" + pelicula.getTitulo() + " - ", pelicula.toString());
    }
    
    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo;
        ImageView imgPoster;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            imgPoster = itemView.findViewById(R.id.imgPoster);
        }
    }
}
