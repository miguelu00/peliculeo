package com.miguelu00.peliculeoMobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miguelu00.peliculeoMobile.R;
import com.miguelu00.peliculeoMobile.models.Pelicula;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private List<Pelicula> peliculaList;

    public PeliculaAdapter(List<Pelicula> peliculaList) {
        this.peliculaList = peliculaList;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.peli_rv_item, parent, false);
        return new PeliculaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = peliculaList.get(position);
        holder.fechaEstreno.setText(pelicula.getFechaEstreno().toString());
        Glide.with(holder.itemView.getContext())
                .load(pelicula.getUrlPoster())
                .into(holder.posterPelicula);
    }

    @Override
    public int getItemCount() {
        return peliculaList.size();
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        public ImageView posterPelicula;
        public TextView fechaEstreno;

        public PeliculaViewHolder(View view) {
            super(view);
            posterPelicula = view.findViewById(R.id.moviePoster);
            fechaEstreno = view.findViewById(R.id.releaseDate);
        }
    }
}
