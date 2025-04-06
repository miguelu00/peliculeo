package com.miguelu00.peliculeo_android.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.miguelu00.peliculeo_android.databinding.FragmentAgregarPeliculaListDialogItemBinding;
import com.miguelu00.peliculeo_android.databinding.FragmentAgregarPeliculaListDialogBinding;

import java.time.Instant;
import java.util.Date;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     AgregarPeliculaFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class AgregarPeliculaFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";
    private FragmentAgregarPeliculaListDialogBinding binding;

    // TODO: Customize parameters
    public static AgregarPeliculaFragment newInstance(int itemCount) {
        final AgregarPeliculaFragment fragment = new AgregarPeliculaFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentAgregarPeliculaListDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(getArguments().getInt(ARG_ITEM_COUNT)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        EditText anioPelicula;
        NumberPicker anioPeli, diaEstr, mesEstr, anioEstr;
        Button guardarTodo;

        ViewHolder(FragmentAgregarPeliculaListDialogItemBinding binding) {
            super(binding.getRoot());
            anioPelicula = binding.etTitulo;
            anioPeli = binding.pickerAnio;

            anioPeli.setMaxValue(2100);
            anioPeli.setMinValue(1825);
            diaEstr.setMaxValue(31);
            diaEstr.setMinValue(1);
            mesEstr.setMaxValue(12);
            mesEstr.setMinValue(1);
            anioEstr.setMaxValue(Date.from(Instant.now()).getYear()); //todo CAMBIAR POR CÓDIG. NO DEPRECADO


        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final int mItemCount;

        ItemAdapter(int itemCount) {
            mItemCount = itemCount;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new ViewHolder(FragmentAgregarPeliculaListDialogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.anioPelicula.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

    }
}