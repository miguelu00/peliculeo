package com.miguelu00.peliculeo_mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miguelu00.peliculeo_mobile.R;
import com.miguelu00.peliculeo_mobile.models.Pelicula;
import com.miguelu00.peliculeo_mobile.models.PosterPelicula;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private final List<Pelicula> peliculaList;
    private final List<PosterPelicula> posters;

    public PeliculaAdapter(List<Pelicula> peliculaList, List<PosterPelicula> posters) {
        this.peliculaList = peliculaList;
        this.posters = posters;
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
        PosterPelicula poster = posters.get(position);
        holder.fechaEstreno.setText(pelicula.getFechaEstreno());
        Glide.with(holder.itemView.getContext())
                .load(poster.getUrlPoster())
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
